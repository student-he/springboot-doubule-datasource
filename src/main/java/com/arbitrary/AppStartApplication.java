package com.arbitrary;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan({"com.arbitrary.dao.master","com.arbitrary.dao.slave"})
public class AppStartApplication {
    public static void main(String[] args) {
        SpringApplication.run(AppStartApplication.class,args);
    }
}
