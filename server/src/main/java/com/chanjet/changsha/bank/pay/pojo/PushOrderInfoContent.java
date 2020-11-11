package com.chanjet.changsha.bank.pay.pojo;

import com.chanjet.openapi.sdk.java.AbstractChanjetContent;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author: zsc
 * @create: 2020/11/11 1:43 下午
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class PushOrderInfoContent extends AbstractChanjetContent {
    /**
     * 支付系统订单号 ，用于向第三方支付时候请求的订单号
     */
    private String payOrderId;
    /**
     * 总金额  单位 分
     */
    private String totalAmount;
    /**
     * 交易简介
     */
    private String subject;
    /**
     * 支付时间
     */
    private String payTime;
    /**
     * 支付通道订单号
     */
    private String transactionId;
    /**
     * 支付状态
     */
    private String payStatus;
    /**
     * 第三方订单号
     */
    private String thirdOrderId;
    /**
     * 支付用户标识
     */
    private String openId;
}
