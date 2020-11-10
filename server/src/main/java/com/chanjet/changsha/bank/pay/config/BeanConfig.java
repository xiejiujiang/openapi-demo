package com.chanjet.changsha.bank.pay.config;

import com.chanjet.changsha.bank.pay.command.builder.CsBankCommandBuilder;
import com.chanjet.changsha.bank.pay.command.builder.SpiBuilder;
import com.chanjet.openapi.sdk.java.ChanjetClient;
import com.chanjet.openapi.sdk.java.DefaultChanjetClient;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.binder.okhttp3.OkHttpMetricsEventListener;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

/**
 * Bean初始化
 *
 * @author: zsc
 * @create: 2020/11/5 11:07 上午
 **/
@Configuration
public class BeanConfig {

    @Bean
    public OkHttpClient okHttpClient(MeterRegistry registry) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY));
        builder.connectTimeout(15, TimeUnit.SECONDS);
        builder.readTimeout(45, TimeUnit.SECONDS);
        builder.eventListener(OkHttpMetricsEventListener.builder(registry, "okhttp.requests")
                .uriMapper(req -> req.url().encodedPath())
                .build());
        return builder.build();
    }

    @Bean
    @ConditionalOnMissingBean
    public CsBankCommandBuilder ciaCommandBuilder(OkHttpClient client, AppConfig appConfig) {
        SpiBuilder spiBuilder = new SpiBuilder();
        spiBuilder.setClient(client);
        spiBuilder.setBaseUrl(appConfig.getCsBankUrl());
        return new CsBankCommandBuilder(spiBuilder);
    }

    @Bean
    public ChanjetClient chanjetClient(AppConfig appConfig) {
        return new DefaultChanjetClient(appConfig.getChanjetOpenUrl());
    }

}
