package com.chanjet.openapi.demo.config;

import com.chanjet.openapi.demo.annotation.ApiRestController;
import com.chanjet.openapi.demo.web.interceptor.LogInterceptor;
import com.chanjet.openapi.demo.web.interceptor.StaticInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @program: developer
 * @description:
 * @author: nxw
 * @create: 2020-02-12 09:17
 **/
@Configuration
public class SpringInterceptorConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LogInterceptor());
        registry.addInterceptor(new StaticInterceptor());
    }
}
