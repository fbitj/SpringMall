package com.springmall;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.springmall.mapper")
public class LiaohaoApplication {

    public static void main(String[] args) {
        SpringApplication.run(LiaohaoApplication.class, args);
    }

}
