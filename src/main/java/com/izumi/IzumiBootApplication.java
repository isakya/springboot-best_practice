package com.izumi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan({"com.izumi.mapper"})
public class IzumiBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(IzumiBootApplication.class, args);
    }
}
