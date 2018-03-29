package com.htschk.tai.dao;

import com.htschk.tai.common.TaiException;
import com.htschk.tai.common.TaiExceptionCode;
import com.htschk.tai.util.LogManager;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.ibatis.executor.ErrorContext;
import org.apache.ibatis.executor.ExecutorException;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.ParameterMode;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.property.PropertyTokenizer;
import org.apache.ibatis.scripting.xmltags.ForEachSqlNode;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.type.TypeHandler;
import org.apache.ibatis.type.TypeHandlerRegistry;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by qikai.yu on 2016/8/9.
 * 用于自动获取相关 SQL 的记录总数量
 */
abstract public class AbstractDaoImpl {

    @Value("${config.useSqlInterceptor:true}")
    private boolean useSqlInterceptor = true;

    @Qualifier("dataSource")
    @Autowired
    private DataSource dataSource;

    protected long getTotalCount(SqlSessionTemplate sqlSessionTemplate, String statementName, Object values) {
        long count = -1L;

        //有条件开放: 前台 供应商总数 + 后台项目总数,
        //Map parameterMap = toParameterMap(values);
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection conn = null;
        try {
//            if(!statementName.contains("searchMySupplierList")
//                    &&!statementName.contains("fetchPiListByCriteriaForPurchase")){
//                return count;
//            }
            long start = System.currentTimeMillis();
            MappedStatement mst = sqlSessionTemplate.getConfiguration().getMappedStatement(statementName);
            BoundSql boundSql = mst.getBoundSql(values);

            String lowerCaseSql = boundSql.getSql().toLowerCase();

            //int startPos = lowerCaseSql.indexOf("from");
            int endPos = 0;

//            if (lowerCaseSql.contains("order by")) {
//                endPos = lowerCaseSql.lastIndexOf("order by");
//            } else

            if (lowerCaseSql.contains("limit")) {
                endPos = lowerCaseSql.lastIndexOf("limit");
            }
//            else if (lowerCaseSql.contains("group by")) {
//                endPos = lowerCaseSql.lastIndexOf("group by");
//            }
            else {
                endPos = lowerCaseSql.length();
            }
            String sql = " select count(1) total_count from (" + boundSql.getSql().substring(0, endPos)+") a";
//            LogManager.detailLog(sql);
            conn = dataSource.getConnection();
            pstmt = conn.prepareStatement(sql);
            setParameters(pstmt, mst, boundSql, values);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                count = rs.getLong("total_count");
            }

            if (useSqlInterceptor) {
                long end = System.currentTimeMillis();
                long time = (end - start);
                StringBuilder str = new StringBuilder(100);
                //        str.append(sqlId);
                //        str.append(":");
                str.append(sql);
                str.append("\ncost(ms):");
                str.append(time);
                System.err.println(sql);
            }


        } catch (Exception e) {
            LogManager.errorLog(new TaiException(TaiExceptionCode.UNEXPECTED,
                    "获取查询总数发生异常, message:" + e.getMessage(), e));
            return -1;
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return count;
    }

    /**
     * 对SQL参数(?)设值,参考org.apache.ibatis.executor.parameter.DefaultParameterHandler
     *
     * @param ps
     * @param mappedStatement
     * @param boundSql
     * @param parameterObject
     * @throws SQLException
     */
    private void setParameters(PreparedStatement ps, MappedStatement mappedStatement, BoundSql boundSql, Object parameterObject) throws SQLException {
        ErrorContext.instance().activity("setting parameters").object(mappedStatement.getParameterMap().getId());
        List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
        if (parameterMappings != null) {
            Configuration configuration = mappedStatement.getConfiguration();
            TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
            MetaObject metaObject = parameterObject == null ? null : configuration.newMetaObject(parameterObject);

            int paramSize = parameterMappings.size();

//            if (boundSql.getSql().toLowerCase().contains("order by") || boundSql.getSql().toLowerCase().contains("limit")) {
//                paramSize -= 2;
//            }
            if (boundSql.getSql().toLowerCase().contains("limit")) {
                paramSize -= 2;
            }


            for (int i = 0; i < paramSize; i++) {
                ParameterMapping parameterMapping = parameterMappings.get(i);
                if (parameterMapping.getMode() != ParameterMode.OUT) {
                    Object value;
                    String propertyName = parameterMapping.getProperty();
                    PropertyTokenizer prop = new PropertyTokenizer(propertyName);
                    if (parameterObject == null) {
                        value = null;
                    } else if (typeHandlerRegistry.hasTypeHandler(parameterObject.getClass())) {
                        value = parameterObject;
                    } else if (boundSql.hasAdditionalParameter(propertyName)) {
                        value = boundSql.getAdditionalParameter(propertyName);
                    } else if (propertyName.startsWith(ForEachSqlNode.ITEM_PREFIX) && boundSql.hasAdditionalParameter(prop.getName())) {
                        value = boundSql.getAdditionalParameter(prop.getName());
                        if (value != null) {
                            value = configuration.newMetaObject(value).getValue(propertyName.substring(prop.getName().length()));
                        }
                    } else {
                        value = metaObject == null ? null : metaObject.getValue(propertyName);
                    }
                    TypeHandler typeHandler = parameterMapping.getTypeHandler();
                    if (typeHandler == null) {
                        throw new ExecutorException("There was no TypeHandler found for parameter " + propertyName + " of statement " + mappedStatement.getId());
                    }
                    typeHandler.setParameter(ps, i + 1, value, parameterMapping.getJdbcType());
                }
            }
        }
    }

    private Map toParameterMap(Object parameter) {
        if (parameter == null) {
            return new HashMap();
        }

        if (parameter instanceof Map) {
            return (Map<?, ?>) parameter;
        } else {
            try {
                return PropertyUtils.describe(parameter);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
    }


}
