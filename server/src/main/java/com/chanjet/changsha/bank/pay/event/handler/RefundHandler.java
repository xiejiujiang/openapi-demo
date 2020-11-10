package com.chanjet.changsha.bank.pay.event.handler;

import com.chanjet.changsha.bank.pay.command.builder.CsBankCommandBuilder;
import com.chanjet.changsha.bank.pay.common.BizResponseBean;
import com.chanjet.changsha.bank.pay.event.ChanjetMsg;
import com.chanjet.changsha.bank.pay.event.EventHandler;
import com.chanjet.changsha.bank.pay.event.content.RefundContent;
import com.chanjet.changsha.bank.pay.pojo.RequestRefundResponse;
import com.chanjet.changsha.bank.pay.service.MerchantService;
import com.chanjet.changsha.bank.pay.spi.csbank.RequestRefund;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author: zsc
 * @create: 2020/11/10 3:28 下午
 **/
@Log4j2
@Component
public class RefundHandler implements EventHandler<RefundContent> {

    @Autowired
    private MerchantService merchantService;
    @Autowired
    private CsBankCommandBuilder csBankCommandBuilder;

    @Override
    public Object execute(ChanjetMsg<RefundContent> chanjetMsg) {
        try {
            RefundContent refundContent = chanjetMsg.getBizContent();
            String merchanId = refundContent.getMerchanId();
            String privateKeyString = merchantService.getPrivateKey(merchanId);
            RequestRefund requestRefund = csBankCommandBuilder.create(RequestRefund.class);
//            requestRefund.setECustId();
//            requestRefund.setCancelReason();
//            requestRefund.setERefundSn();
//            requestRefund.setOrderId();
//            requestRefund.setRefundAmount();
            requestRefund.setPrivateKeyString(privateKeyString);
            RequestRefundResponse requestRefundResponse = requestRefund.excute();
            return null;
        } catch (Exception e) {
            log.error("退款错误", e);
            return BizResponseBean.builder()
                    .result_code("REFUNDED_ERROR")
                    .error_message("长沙银行退款调用失败")
                    .build();
        }
    }
}
