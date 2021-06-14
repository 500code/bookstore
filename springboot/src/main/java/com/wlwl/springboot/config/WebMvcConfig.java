package com.wlwl.springboot.config;

import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.wlwl.springboot.interceptor.JwtInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
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
                .allowedMethods("*")
                .maxAge(3600)
                .allowedHeaders("*");
    }
//    token 拦截
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new JwtInterceptor())
//                .addPathPatterns("/**")//需要token验证
//                .excludePathPatterns("/api/admin/login")
//                .excludePathPatterns("/api/user/login");//用户下的放行
    }

}
