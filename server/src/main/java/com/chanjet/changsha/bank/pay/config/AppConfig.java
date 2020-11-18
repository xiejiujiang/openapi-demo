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
    /**
     * 长沙银行支付结果通知地址
     */
    private String backUrl;
    /**
     * OAuth重定向地址
     */
    private String redirectUri;
    /**
     * 前端地址
     */
    private String frontUrl;
    /**
     * 撤销订单url
     */
    private String orderCancelUrl;
    /**
     * 支付url
     */
    private String orderPayUrl;
    /**
     * 查询支付订单url
     */
    private String queryOrderUrl;
    /**
     * 查询退款订单url
     */
    private String queryRefundOrderUrl;
    /**
     * 退款url
     */
    private String requestRefundUrl;
}
