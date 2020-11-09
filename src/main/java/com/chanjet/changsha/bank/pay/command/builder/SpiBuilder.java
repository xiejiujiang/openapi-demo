package com.chanjet.changsha.bank.pay.command.builder;

/**
 * @author: zsc
 * @create: 2020/11/5 1:42 下午
 **/

import com.chanjet.changsha.bank.pay.exception.PropertiesMustBeNotNullException;
import okhttp3.OkHttpClient;
import retrofit2.Converter.Factory;
import retrofit2.Retrofit;
import retrofit2.Retrofit.Builder;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.HashMap;
import java.util.Map;

public class SpiBuilder {
    private final Map<Class<?>, Object> retrofitBeans = new HashMap<>();
    private OkHttpClient client;
    private String baseUrl;

    public SpiBuilder() {
    }

    public void setClient(OkHttpClient client) {
        this.client = client;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public <T> T create(Class<T> clazz) {
        if (this.retrofitBeans.containsKey(clazz)) {
            return (T) this.retrofitBeans.get(clazz);
        } else {
            this.validProperties();
            Factory convertFactory = GsonConverterFactory.create();
            Retrofit retrofit = (new Builder()).client(this.client).baseUrl(this.baseUrl).addConverterFactory(convertFactory).build();
            T retrofitBean = retrofit.create(clazz);
            this.retrofitBeans.put(clazz, retrofitBean);
            return retrofitBean;
        }
    }

    private void validProperties() {
        this.validClient();
        this.validBaseUrl();
    }

    private void validClient() {
        if (this.client == null) {
            throw new PropertiesMustBeNotNullException("client must not null");
        }
    }

    private void validBaseUrl() {
        if (this.baseUrl == null) {
            throw new PropertiesMustBeNotNullException("baseUrl must not null");
        }
    }
}
