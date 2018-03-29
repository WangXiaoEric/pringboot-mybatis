package com.htschk.tai;

import com.htschk.tai.util.EnvUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * Created by yuqikai on 2017/6/13.
 */

@SpringBootApplication
public class ServletInitializerForTest implements EmbeddedServletContainerCustomizer {
    /*
        根据设置的系统环境,加载相应的配置文件
     */
    static {
        EnvUtil.preparePropertiesByEnv();
    }

    private static ConfigurableApplicationContext applicationContext;

    //用于本地main方法直接启动
    @Override
    public void customize(ConfigurableEmbeddedServletContainer configurableEmbeddedServletContainer) {
//        configurableEmbeddedServletContainer.setContextPath("/");
//        configurableEmbeddedServletContainer.setPort(8080);
    }

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(ServletInitializerForTest.class);
        applicationContext = springApplication.run(args);
        System.out.println("ServletInitializer.main started ...");
    }

    public static void stop(){
        applicationContext.close();
        System.out.println("ServletInitializer stopped ...");
    }

    public static ConfigurableApplicationContext getApplicationContext() {
        return applicationContext;
    }
}
