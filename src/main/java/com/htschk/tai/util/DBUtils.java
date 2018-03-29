package com.htschk.tai.util;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.commons.lang.math.NumberUtils;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

public class DBUtils {
    public static DruidDataSource generatorDataSource(String url, RelaxedPropertyResolver dbResolver) {
        System.out.println("is connecting db : " + url);
        DruidDataSource datasource = new DruidDataSource();
        datasource.setUrl(url);
        datasource.setDriverClassName(dbResolver.getProperty("driverClassName"));
        datasource.setUsername(dbResolver.getProperty("username"));
        datasource.setPassword(dbResolver.getProperty("password"));
        datasource.setMaxActive(NumberUtils.toInt(dbResolver.getProperty("max-active")));
        datasource.setMinIdle(NumberUtils.toInt(dbResolver.getProperty("min-idle")));
        datasource.setInitialSize(NumberUtils.toInt(dbResolver.getProperty("initial-size")));
        datasource.setValidationQuery(dbResolver.getProperty("validation-query"));
        return datasource;
    }

    public static SqlSessionTemplate generatorSqlSessionTemplate(DataSource dataSource, String path, boolean useSqlInterceptor) throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setFailFast(true);
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        System.out.println("mapper file location:" + path);
        sessionFactory.setMapperLocations(resolver.getResources(path));

        SqlSessionTemplate template = new SqlSessionTemplate(sessionFactory.getObject());
        if (useSqlInterceptor) {
            template.getConfiguration().addInterceptor(new MybatisSqlInterceptor());
        }
        return template;
    }
}
