package com.jili.test;


import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableScheduling;


@EnableDubbo
@SpringBootApplication
@EnableScheduling
@MapperScan("com.jili.mapper")
@ComponentScan(basePackages = {"com.jili.config",
        "com.jili.controller",
        "com.jili.service","com.jili.drools","com.jili.mapper"})
//@ImportResource(locations = {"classpath:applicationContext-mq.xml"})
public class TestApplication {
    public static void main(String[] args) {
        SpringApplication.run(TestApplication.class, args);
    }

}
