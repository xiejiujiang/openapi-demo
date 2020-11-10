package com.chanjet.changsha.bank.pay.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: zsc
 * @create: 2020/11/9 10:02 上午
 **/
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ChanjetQueryOrderResponse {
    /**
     * 支付时间
     */
    private String payTime;
    /**
     * 支付通道订单号，如支付宝或微信订单号
     */
    private String transactionId;
    /**
     * 支付类型
     */
    private String payType = "OPEN";
    /**
     * 支付状态
     */
    private String payStatus;
    /**
     * 付款人标识，如微信的openId
     */
    private String openId;
    /**
     * 第三方订单号
     */
    private String thirdOrderId;
}
