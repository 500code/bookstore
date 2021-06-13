package com.wlwl.springboot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/*
 * @Description 可以使用自定义类扩展MVC的功能
 **/
@Configuration(proxyBeanMethods = false)  //告诉springboot这是一个配置类
public class WebMvcConfig implements WebMvcConfigurer{


//    放行api下的所有
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")
                .allowedOrigins("*")
                .allowedMethods("GET","HEAD","POST","PUT","DELETE","OPTIONS")
                .maxAge(3600)
                .allowedHeaders("*");
    }
}
