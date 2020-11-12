package com.chanjet.changsha.bank.pay.service;

import com.chanjet.changsha.bank.pay.pojo.PayResult;

/**
 * @author: zsc
 * @create: 2020/11/11 3:13 下午
 **/
public interface MessageService {
    /**
     * 接收长沙银行支付结果的接口
     *
     * @param payResult
     * @return
     */
    void changshaReceive(PayResult payResult);
}
