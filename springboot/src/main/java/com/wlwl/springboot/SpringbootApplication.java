package com.wlwl.springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("com/wlwl/springboot/mapper")
@ComponentScan({"com.wlwl.springboot.config","com.wlwl.springboot.service","com.wlwl.springboot.controller","com.wlwl" +
        ".springboot.entity"})
public class SpringbootApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootApplication.class, args);
    }

}
