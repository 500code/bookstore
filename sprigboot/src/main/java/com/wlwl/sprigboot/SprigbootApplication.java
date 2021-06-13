package com.wlwl.springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackages = "com.wlwl.sprigboot.service")
@MapperScan("com/wlwl/springboot/mapper")
@ComponentScan({"com.wlwl.sprigboot.config","com.wlwl.sprigboot.controller","com.wlwl.sprigboot.entity"})
public class SprigbootApplication {

    public static void main(String[] args) {
        SpringApplication.run(SprigbootApplication.class, args);
    }

}
