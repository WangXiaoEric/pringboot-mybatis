package com.htschk.tai.configuration;


import com.htschk.tai.util.IdGenerationUtil;
import com.htschk.tai.util.IdGenerationUtilMock;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * Created by yuqikai on 2017/6/12.
 */
@ComponentScan
@Configuration
public class SampleTestConfiguration {

    @Bean
    @Primary
    public IdGenerationUtil getIdGenerationUtilMock(){
        return new IdGenerationUtilMock();
    }

}
