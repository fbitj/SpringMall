package com.springmall;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.springmall.mapper")
<<<<<<< HEAD:src/main/java/com/springmall/MallApplication.java
public class MallApplication {
=======
public class LiaohaoApplication {
>>>>>>> 2cf51b272762e8935427014aa95bf36ab05295d2:src/main/java/com/springmall/LiaohaoApplication.java

    public static void main(String[] args) {
        SpringApplication.run(MallApplication.class, args);
    }

}
