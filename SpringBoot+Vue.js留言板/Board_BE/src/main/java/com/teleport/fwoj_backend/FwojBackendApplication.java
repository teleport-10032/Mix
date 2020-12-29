package com.teleport.fwoj_backend;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.teleport.fwoj_backend.mapper")
public class FwojBackendApplication {
    public static void main(String[] args) {
        SpringApplication.run(FwojBackendApplication.class, args);
    }

}
