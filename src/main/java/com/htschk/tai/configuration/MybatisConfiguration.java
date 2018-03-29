package com.htschk.tai.configuration;

import com.htschk.tai.util.DBUtils;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

/**
 * Created by xuejian.sun on 2016/12/27.
 */

@Configuration
@AutoConfigureAfter({DataBaseConfiguration.class})
@ComponentScan
public class MybatisConfiguration {
    @Autowired
    private DataSource dataSource;

    @Value("${config.useSqlInterceptor:true}")
    private boolean useSqlInterceptor = true;

    @Value("${config.sqlMapper.path:}")
    private String path;

    @Bean
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "sqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate() throws Exception {
        return DBUtils.generatorSqlSessionTemplate(dataSource, path, useSqlInterceptor);
    }
}
