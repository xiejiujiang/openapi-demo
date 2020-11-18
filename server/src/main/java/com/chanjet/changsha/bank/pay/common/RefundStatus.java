package com.chanjet.changsha.bank.pay.common;

/**
 * 退款状态
 *
 * @author: zsc
 * @create: 2020/11/11 11:04 上午
 **/
public class RefundStatus {
    /**
     * 退款完成
     */
    public static final String REFUNDED_COMPLETE = "REFUNDED_COMPLETE";
    /**
     * 退款失败
     */
    public static final String REFUNDED_FAIL = "REFUNDED_FAIL";
    /**
     * 退款错误
     */
    public static final String REFUNDED_ERROR = "REFUNDED_ERROR";
    /**
     * 退款进行中
     */
    public static final String REFUNDED_INPROGRESS = "REFUNDED_INPROGRESS";
}
