package com.chanjet.changsha.bank.pay.event.handler;

import com.chanjet.changsha.bank.pay.command.builder.CsBankCommandBuilder;
import com.chanjet.changsha.bank.pay.config.AppConfig;
import com.chanjet.changsha.bank.pay.event.ChanjetMsg;
import com.chanjet.changsha.bank.pay.event.EventHandler;
import com.chanjet.changsha.bank.pay.event.content.ReverseOrderContent;
import com.chanjet.changsha.bank.pay.pojo.ChanjetReverseOrderResponse;
import com.chanjet.changsha.bank.pay.pojo.OrderCancelResponse;
import com.chanjet.changsha.bank.pay.service.MerchantService;
import com.chanjet.changsha.bank.pay.spi.csbank.OrderCancel;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 撤销订单事件处理器
 *
 * @author: zsc
 * @create: 2020/11/9 2:41 下午
 **/
@Log4j2
@Component("REVERSEORDER")
public class ReverseOrderHandler implements EventHandler<ReverseOrderContent> {
    @Autowired
    private CsBankCommandBuilder csBankCommandBuilder;
    @Autowired
    private MerchantService merchantService;
    @Autowired
    private AppConfig appConfig;

    @Override
    public Object execute(ChanjetMsg<ReverseOrderContent> chanjetMsg) {
        try {
            ReverseOrderContent reverseOrderContent = chanjetMsg.getBizContent();
            String merchanId = reverseOrderContent.getMerchanId();
            String privateKeyString = merchantService.getPrivateKey(merchanId,reverseOrderContent.getBookId());
            OrderCancel orderCancel = csBankCommandBuilder.create(OrderCancel.class);
            orderCancel.setUrl(appConfig.getOrderPayUrl());
            orderCancel.setECustId(merchanId);
            orderCancel.setPrivateKeyString(privateKeyString);
            orderCancel.setOrderId(reverseOrderContent.getThirdOrderId());
            OrderCancelResponse orderCancelResponse = orderCancel.excute();
            ChanjetReverseOrderResponse chanjetReverseOrderResponse;
            if ("0000".equals(orderCancelResponse.getStatus())) {
                chanjetReverseOrderResponse = new ChanjetReverseOrderResponse(false);
            } else {
                chanjetReverseOrderResponse = new ChanjetReverseOrderResponse(true);
            }
            return chanjetReverseOrderResponse;
        } catch (Exception e) {
            log.error("撤销订单异常", e);
            return new ChanjetReverseOrderResponse(true);
        }
    }
}
