package com.chanjet.changsha.bank.pay.event.content;

import lombok.Data;

/**
 * @author: zsc
 * @create: 2020/11/9 2:57 下午
 **/
@Data
public class ReverseOrderContent {
    /**
     * 订单号
     */
    private String payOrderId;
    /**
     * 商户号
     */
    private String merchanId;
}
