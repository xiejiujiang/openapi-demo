package com.chanjet.changsha.bank.pay.event.content;

import lombok.Data;

/**
 * @author: zsc
 * @create: 2020/11/11 11:07 上午
 **/
@Data
public class QueryRefundOrderContent {
    /**
     * 支付订单号
     */
    private String payOrderId;
    /**
     * 退款订单号
     */
    private String refundOrderId;
    /**
     * 商户号
     */
    private String merchanId;
    /**
     * 第三方订单号
     */
    private String thirdOrderId;
    /**
     * 账套ID
     */
    private String bookId;
}
