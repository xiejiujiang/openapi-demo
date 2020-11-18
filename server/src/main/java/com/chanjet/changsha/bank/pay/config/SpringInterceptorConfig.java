package com.chanjet.changsha.bank.pay.config;

import com.chanjet.changsha.bank.pay.annotation.ApiRestController;
import com.chanjet.changsha.bank.pay.web.interceptor.LogInterceptor;
import com.chanjet.changsha.bank.pay.web.interceptor.StaticInterceptor;
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

    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        configurer
                .addPathPrefix("api", c -> c.isAnnotationPresent(ApiRestController.class));
    }
}
