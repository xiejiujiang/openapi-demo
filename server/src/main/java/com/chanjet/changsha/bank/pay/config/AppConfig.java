package com.chanjet.changsha.bank.pay.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author: zsc
 * @create: 2020/11/5 11:07 上午
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Configuration
@ConfigurationProperties("app.config")
public class AppConfig {
    /**
     * 长沙银行网关地址
     */
    private String csBankUrl;
    /**
     * 畅捷通开放平台地址
     */
    private String chanjetOpenUrl;
    /**
     * 畅捷通开放平台appKey
     */
    private String appKey;
    /**
     * 畅捷通开放平台appSecret
     */
    private String appSecret;
    /**
     * 加解密key
     */
    private String encryptKey;
}
