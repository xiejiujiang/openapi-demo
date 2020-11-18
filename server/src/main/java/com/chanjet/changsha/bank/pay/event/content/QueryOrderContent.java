package com.chanjet.changsha.bank.pay.event.content;

import lombok.Data;

/**
 * 查询订单支付结果
 *
 * @author: zsc
 * @create: 2020/11/9 2:57 下午
 **/
@Data
public class QueryOrderContent {
    /**
     * 商户支付订单号
     */
    private String payOrderId;
    /**
     * 商户号
     */
    private String merchanId;
    /**
     * 账套ID
     */
    private String bookId;
}
