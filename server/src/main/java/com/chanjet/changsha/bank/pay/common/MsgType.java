package com.chanjet.changsha.bank.pay.common;

/**
 * @author: zsc
 * @create: 2020/11/10 11:33 上午
 **/
public class MsgType {
    /**
     * 测试
     */
    public static final String APP_TEST = "APP_TEST";
    /**
     * 应用ticket
     */
    public static final String APP_TICKET = "APP_TICKET";
    /**
     * 企业临时授权码
     */
    public static final String TEMP_AUTH_CODE = "TEMP_AUTH_CODE";
    /**
     * 付款码支付
     */
    public static final String MICROPAY = "MICROPAY";
    /**
     * 查询支付结果
     */
    public static final String QUERYORDER = "QUERYORDER";
    /**
     * 退款
     */
    public static final String REFUND = "REFUND";
    /**
     * 查询退款结果
     */
    public static final String REFUNDQUERY = "REFUNDQUERY";
    /**
     * 拉取流水
     */
    public static final String GETBILLINFO = "GETBILLINFO";
    /**
     * 撤销订单
     */
    public static final String REVERSEORDER = "REVERSEORDER";
}
