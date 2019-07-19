package com.example.port;

import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import tk.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.example.port")
@MapperScan("com.example.port.dao")
@EnableEurekaClient
public class PortApplication {

    public static void main(String[] args) {
        SpringApplication.run(PortApplication.class, args);
    }

}

