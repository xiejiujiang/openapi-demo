package com.chanjet.changsha.bank.pay.event.content;

import lombok.Data;

/**
 * @author: zsc
 * @create: 2020/11/10 3:29 下午
 **/
@Data
public class RefundContent {
    /**
     * 支付订单号
     */
    private String payOrderId;
    /**
     * 退款订单号
     */
    private String refundOrderId;
    /**
     * 退款金额
     */
    private String refundAmount;
    /**
     * 支付订单金额
     */
    private String totalAmount;
    /**
     * 商户号
     */
    private String merchanId;
    /**
     * 操作员
     */
    private String operator;
    /**
     * 第三方订单号
     */
    private String thirdOrderId;
    /**
     * 账套ID
     */
    private String bookId;
}
