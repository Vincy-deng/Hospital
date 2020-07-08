package com.neu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by Administrator on 2019/10/21.
 */
@SpringBootApplication
@EnableSwagger2
@MapperScan(basePackages = "com.neu.dao")//配置接口dao层
public class App {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(App.class,args);
        System.out.println("该服务的名称为：{}"+context.getEnvironment().getProperty("spring.application.name"));
        System.out.println("该服务器启动的端口：{}"+context.getEnvironment().getProperty("server.port"));
    }
}
