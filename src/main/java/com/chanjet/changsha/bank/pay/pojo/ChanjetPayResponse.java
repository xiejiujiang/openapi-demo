package com.chanjet.changsha.bank.pay.pojo;

import lombok.Builder;
import lombok.Data;

/**
 * @author: zsc
 * @create: 2020/11/9 4:57 下午
 **/
@Builder
@Data
public class ChanjetPayResponse {
    /**
     * 支付时间
     */
    private String payTime;
    /**
     * 支付通道订单号
     */
    private String transactionId;
    /**
     * 支付类型
     * "PAY_PAYMENT", "待支付"
     * "PAY_FAIL", "支付失败"
     * "PAY_ERROR", "支付错误"
     * "PAY_REFUND", "转入退款"
     * "PAY_COMPLETE", "支付已完成"
     * "PAY_CLOSE", "支付已关闭"
     */
    private String payType;
    /**
     * 支付状态
     */
    private String payStatus;
    /**
     * 付款人标识，如微信的openId等
     */
    private String openId;
    /**
     * 第三方订单号
     */
    private String thirdOrderId;
}
