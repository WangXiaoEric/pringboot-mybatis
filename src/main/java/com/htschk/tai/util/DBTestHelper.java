package com.htschk.tai.util;

import com.htschk.tai.common.TaiException;
import com.htschk.tai.common.TaiExceptionCode;
import com.htschk.tai.model.CommonConstant;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class DBTestHelper {

    @Qualifier("dataSource")
    @Autowired
    private DataSource dataSource;
    //需要验证 connection 连接到特定的数据库

    public void executeSql(String sql) {
        Connection conn = null;
        try {
            //1. 校验数据库 connection
            conn = canExecuteAndGetConnection();
            //2. 执行
            if (conn != null) {
                doDBExecution(conn, sql);
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    public int selectCount(String sql) {
        Connection conn = null;
        try {
            //1. 校验数据库 connection
            conn = canExecuteAndGetConnection();
            //2. 执行
            if (conn != null) {
                return doDBSelectCount(conn, sql);
            }
            conn.commit();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }


    public String selectTarget(String sql) {
        Connection conn = null;
        try {
            //1. 校验数据库 connection
            conn = canExecuteAndGetConnection();
            //2. 执行
            if (conn != null) {
                return doDBSelectTarget(conn, sql);
            }
            conn.commit();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return CommonConstant.BLANK;
    }


    public void executeSql(List<String> sqls) {
        Connection conn = null;
        try {
            //1. 校验数据库 connection
            conn = canExecuteAndGetConnection();
            //2. 执行
            if (conn == null) {
                throw new RuntimeException("Get database connection exception ...");
            }
            conn.setAutoCommit(false);
            for (String sql : sqls) {
                doDBExecution(conn, sql);
            }
            conn.commit();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    private Connection canExecuteAndGetConnection() throws Exception {
        Connection conn = dataSource.getConnection();

        String url = conn.getMetaData().getURL();
//        System.out.println(conn.getMetaData().getURL());
        if (url.contains("_test")) {
            return conn;
        } else {
            return null;
        }
    }

    private void doDBExecution(Connection conn, String sql) {
        if (StringUtils.isEmpty(sql)) {
            return;
        }
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.execute(sql);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private int doDBSelectCount(Connection conn, String sql) {
        int count = 0;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery(sql);
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
            count = 0;
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return count;
    }

    private String doDBSelectTarget(Connection conn, String sql) {
        String target = CommonConstant.BLANK;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery(sql);
            if (rs.next()) {
                target = rs.getString(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
            target = CommonConstant.BLANK;
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return target;
    }

    public List fetchDataFromDBAsList(String sql) {
        List<Map> l = new ArrayList<>();
        Connection conn = null;

        PreparedStatement ps = null;
        try {
            conn = dataSource.getConnection();
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery(sql);

            ResultSetMetaData m = null;//获取 列信息
            m = rs.getMetaData();

            int columns = m.getColumnCount();
//            //显示列,表格的表头
//            for (int i = 1; i <= columns; i++) {
//                System.out.print(m.getColumnName(i));
//                System.out.print("\t\t");
//            }
            //显示表格内容
            while (rs.next()) {
                Map<String, String> map = new HashMap<>();
                for (int i = 1; i <= columns; i++) {
//                    System.out.print(rs.getString(i));
//                    System.out.print("\t\t");
                    map.put(m.getColumnName(i), rs.getString(i));
                }
                l.add(map);
//                System.out.println();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return l;
    }

    public List<Map> execSpWithoutParam(String spName, Object values) {
        List<Map> resultList = new ArrayList<>();

        //有条件开放: 前台 供应商总数 + 后台项目总数,
        //Map parameterMap = toParameterMap(values);
        CallableStatement cstmt = null;
        ResultSet rs = null;
        Connection conn = null;
        try {
            String sql = "call " + spName;
            conn = dataSource.getConnection();
            cstmt = conn.prepareCall(sql);

            rs = cstmt.executeQuery();
            ResultSetMetaData m = null;//获取 列信息
            m = rs.getMetaData();

            int columns = m.getColumnCount();
            //显示表格内容
            while (rs.next()) {
                System.out.println(rs);
                Map<String, String> map = new HashMap<>();
                for (int i = 1; i <= columns; i++) {
                    map.put(m.getColumnLabel(i), rs.getString(i));
                }
                resultList.add(map);
            }


        } catch (Exception e) {
            LogManager.errorLog(new TaiException(TaiExceptionCode.UNEXPECTED,
                    "执行SP发生异常, message:" + e.getMessage(), e));
            return null;
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if (cstmt != null) {
                try {
                    cstmt.close();
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

        return resultList;
    }

    public List<Map> execSpWithParam(String spName, Long piId) {
        List<Map> resultList = new ArrayList<>();

        //有条件开放: 前台 供应商总数 + 后台项目总数,
        //Map parameterMap = toParameterMap(values);
        CallableStatement cstmt = null;
        ResultSet rs = null;
        Connection conn = null;
        try {
            String sql = "call " + spName + " (?)";
            conn = dataSource.getConnection();
            cstmt = conn.prepareCall(sql);
            cstmt.setLong(1, piId);

            rs = cstmt.executeQuery();
            ResultSetMetaData m = null;//获取 列信息
            m = rs.getMetaData();

            int columns = m.getColumnCount();
            //显示表格内容
            while (rs.next()) {
                System.out.println(rs);
                Map<String, String> map = new HashMap<>();
                for (int i = 1; i <= columns; i++) {
                    map.put(m.getColumnLabel(i), rs.getString(i));
                }
                resultList.add(map);
            }


        } catch (Exception e) {
            LogManager.errorLog(new TaiException(TaiExceptionCode.UNEXPECTED,
                    "执行SP发生异常, message:" + e.getMessage(), e));
            return null;
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if (cstmt != null) {
                try {
                    cstmt.close();
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

        return resultList;
    }
}
