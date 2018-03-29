package com.htschk.tai.configuration;

import com.htschk.tai.util.DBUtils;
import com.htschk.tai.util.LogManager;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * Created by xuejian.sun on 2016/12/27.
 */
@ComponentScan
@Configuration
@EnableTransactionManagement
public class DataBaseConfiguration implements EnvironmentAware {
    private RelaxedPropertyResolver dbResolver;

    @Override
    public void setEnvironment(Environment env) {
        this.dbResolver = new RelaxedPropertyResolver(env, "db.");
    }

    @Bean(name = "dataSource", destroyMethod = "close", initMethod = "init")
//    @Bean(name = "dataSource")
    public DataSource dataSource() {
        String url = dbResolver.getProperty("url");
        LogManager.infoSystemLog("正在连接数据库:" + url);
        if (StringUtils.isNotEmpty(url)) {
            DataSource ds = DBUtils.generatorDataSource(url, dbResolver);
            return ds;
        } else {
//            return new FakeDataSource();
            return null;
        }
    }
}
