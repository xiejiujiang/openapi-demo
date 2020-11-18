package com.chanjet.changsha.bank.pay.pojo;

import lombok.Builder;
import lombok.Data;

/**
 * @author: zsc
 * @create: 2020/11/10 4:02 下午
 **/
@Builder
@Data
public class ChanjetRefundResponse {
    /**
     * 退款状态
     */
    private String refundStatus;
    /**
     * 退款时间
     */
    private String refundTime;
}
