package com.chanjet.changsha.bank.pay.utils;

import com.chanjet.changsha.bank.pay.common.OrderStat;
import com.chanjet.changsha.bank.pay.pojo.ChanjetStatus;
import com.chanjet.changsha.bank.pay.pojo.PayStatus;

/**
 * 状态码工具类，用于做长沙银行和畅捷通的错误码转换
 *
 * @author: zsc
 * @create: 2020/11/10 12:56 下午
 **/
public class StatusUtils {

    public static ChanjetStatus getChanjetStatus(String orderStat, String msg) {
        switch (orderStat) {
            case OrderStat.ACCEPTED:
                return new ChanjetStatus(PayStatus.PAY_PAYMENT, "", msg);
            case OrderStat.PAID:
                return new ChanjetStatus(PayStatus.PAY_COMPLETE, "", msg);
            case OrderStat.CANCELLED:
                return new ChanjetStatus(PayStatus.PAY_CLOSE, "", msg);
            case OrderStat.EXPIRED:
                return new ChanjetStatus(PayStatus.PAY_FAIL, "PAY_ERROR", msg);
            case OrderStat.EXECUTING:
                return new ChanjetStatus(PayStatus.PAY_PAYMENT, "", msg);
            case OrderStat.PAYMENT_FAILED:
                return new ChanjetStatus(PayStatus.PAY_FAIL, "PAY_ERROR", msg);
            case OrderStat.SUSPICIOUS_PAYMENT:
                return new ChanjetStatus(PayStatus.PAY_ERROR, "PAY_ERROR", msg);
            case OrderStat.FAILED_CHARGE:
                return new ChanjetStatus(PayStatus.PAY_REFUND, "", msg);
            case OrderStat.FLUSHED:
                return new ChanjetStatus(PayStatus.PAY_REFUND, "", msg);
            case OrderStat.REFUND_SUCCESSFULLY:
                return new ChanjetStatus(PayStatus.PAY_REFUND, "", msg);
            case OrderStat.REFUND_EXECUTION:
                return new ChanjetStatus(PayStatus.PAY_REFUND, "", msg);
            case OrderStat.REFUND_FAILED:
                return new ChanjetStatus(PayStatus.PAY_REFUND, "", msg);
            case OrderStat.SUSPICIOUS_REFUND:
                return new ChanjetStatus(PayStatus.PAY_REFUND, "", msg);
            case OrderStat.CANCELLATION_EXECUTION:
                return new ChanjetStatus(PayStatus.PAY_PAYMENT, "", msg);
            case OrderStat.CLOSED:
                return new ChanjetStatus(PayStatus.PAY_CLOSE, "", msg);
            default:
                return new ChanjetStatus(PayStatus.PAY_FAIL, "PAY_ERROR", msg);
        }
    }

}
