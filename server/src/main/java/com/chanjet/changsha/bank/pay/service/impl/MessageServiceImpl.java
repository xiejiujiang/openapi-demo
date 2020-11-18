package com.chanjet.changsha.bank.pay.service.impl;

import com.chanjet.changsha.bank.pay.common.PayStatus;
import com.chanjet.changsha.bank.pay.pojo.PayResult;
import com.chanjet.changsha.bank.pay.pojo.PushOrderInfoContent;
import com.chanjet.changsha.bank.pay.service.MessageService;
import com.chanjet.changsha.bank.pay.spi.chanjet.ChanjetSpi;
import com.chanjet.changsha.bank.pay.utils.DateUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * 消息接收服务
 *
 * @author: zsc
 * @create: 2020/11/11 3:13 下午
 **/
@Log4j2
@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private ChanjetSpi chanjetSpi;

    @Async
    @Override
    public void changshaReceive(PayResult payResult) {
        try {
            String respCode = payResult.getRespCode();
            String payStatus = PayStatus.PAY_ERROR;
            if ("00000000".equals(respCode)) {
                payStatus = PayStatus.PAY_COMPLETE;
            }
            PushOrderInfoContent pushOrderInfoContent = new PushOrderInfoContent();
            pushOrderInfoContent.setOpenId(payResult.getStaffId());
            pushOrderInfoContent.setPayOrderId(payResult.getMerchOrder());
            pushOrderInfoContent.setPayStatus(payStatus);
            pushOrderInfoContent.setPayTime(DateUtil.getDate());
            pushOrderInfoContent.setSubject(payResult.getRemark());
            pushOrderInfoContent.setThirdOrderId(payResult.getOrderId());
            pushOrderInfoContent.setTotalAmount(payResult.getOrderAmount());
            pushOrderInfoContent.setTransactionId(payResult.getOrderId());
            chanjetSpi.pushOrderInfo(pushOrderInfoContent);
        } catch (Exception e) {
            log.error("推送支付结果消息失败", e);
        }
    }
}
