package com.chanjet.changsha.bank.pay.pojo;

import lombok.Builder;
import lombok.Data;

/**
 * @author: zsc
 * @create: 2020/11/11 1:16 下午
 **/
@Builder
@Data
public class ChanjetQueryRefundOrderResponse {
    /**
     * 退款时间
     */
    private String refundTime;
    /**
     * 支付通道退款单号
     */
    private String refundId;
    /**
     * 退款状态
     */
    private String refundStatus;
    /**
     * 第三方退款单号
     */
    private String thirdRefundId;
}
