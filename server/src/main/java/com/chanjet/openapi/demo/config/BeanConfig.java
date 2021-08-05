package com.chanjet.openapi.demo.config;

import com.chanjet.openapi.sdk.java.ChanjetClient;
import com.chanjet.openapi.sdk.java.DefaultChanjetClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Bean初始化
 *
 * @author: zsc
 * @create: 2020/11/5 11:07 上午
 **/
@Configuration
public class BeanConfig {

    @Bean
    public ChanjetClient chanjetClient(AppConfig appConfig) {
        return new DefaultChanjetClient(appConfig.getChanjetOpenUrl());
    }

}
