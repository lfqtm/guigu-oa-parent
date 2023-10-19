package com.atguigu.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan("com.atguigu") // 当前包com.atguigu.auth,所以需要指定扫描的包路径为com.atguigu
//@MapperScan("com.atguigu.*.mapper") 添加当mp的配置类上
public class ServiceAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceAuthApplication.class);
    }
}
