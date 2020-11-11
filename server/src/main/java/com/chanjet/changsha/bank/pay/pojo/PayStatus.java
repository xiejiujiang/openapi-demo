package com.chanjet.changsha.bank.pay.pojo;

/**
 * @author: zsc
 * @create: 2020/11/9 5:00 下午
 **/
public class PayStatus {
    //待支付
    public static final String PAY_PAYMENT = "PAY_PAYMENT";
    //支付失败
    public static final String PAY_FAIL = "PAY_FAIL";
    //支付错误
    public static final String PAY_ERROR = "PAY_ERROR";
    //转入退款
    public static final String PAY_REFUND = "PAY_REFUND";
    //支付已完成
    public static final String PAY_COMPLETE = "PAY_COMPLETE";
    //支付已关闭
    public static final String PAY_CLOSE = "PAY_CLOSE";
}
