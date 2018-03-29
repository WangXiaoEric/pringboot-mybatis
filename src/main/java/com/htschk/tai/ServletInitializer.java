package com.htschk.tai;

import com.htschk.tai.util.EnvUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

//@SpringBootApplication
public class ServletInitializer extends SpringBootServletInitializer{
    /*
        根据设置的系统环境,加载相应的配置文件
     */
    static {
        EnvUtil.preparePropertiesByEnv();
    }

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        System.out.println("----onStartup");
        super.onStartup(servletContext);
    }

    @Override
    protected WebApplicationContext run(SpringApplication application) {
        System.out.println("----run");
        return super.run(application);
    }

    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(ServletInitializer.class);
    }

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(ServletInitializer.class);
        springApplication.run(args);
        System.out.println("ServletInitializer.main started ...");
    }


}