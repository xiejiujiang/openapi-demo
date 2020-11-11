package com.chanjet.changsha.bank.pay.common;

/**
 * @author: zsc
 * @create: 2020/11/10 1:02 下午
 **/
public class OrderStat {
    //已受理
    public static final String ACCEPTED = "0";
    //已支付
    public static final String PAID = "1";
    //已取消
    public static final String CANCELLED = "2";
    //已失效
    public static final String EXPIRED = "3";
    //执行中
    public static final String EXECUTING = "4";
    //支付失败
    public static final String PAYMENT_FAILED = "5";
    //支付可疑
    public static final String SUSPICIOUS_PAYMENT = "8";
    //冲正失败
    public static final String FAILED_CHARGE = "9";
    //已冲正
    public static final String FLUSHED = "10";
    //退款成功
    public static final String REFUND_SUCCESSFULLY = "11";
    //执行中（退款状态未知）
    public static final String REFUND_EXECUTION = "12";
    //退款失败
    public static final String REFUND_FAILED = "13";
    //退款可疑
    public static final String SUSPICIOUS_REFUND = "14";
    //撤销执行中
    public static final String CANCELLATION_EXECUTION = "15";
    //已关闭
    public static final String CLOSED = "16";

}
