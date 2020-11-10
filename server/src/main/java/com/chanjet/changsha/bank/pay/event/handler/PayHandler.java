package com.chanjet.changsha.bank.pay.event.handler;

import com.chanjet.changsha.bank.pay.command.builder.CsBankCommandBuilder;
import com.chanjet.changsha.bank.pay.common.BizResponseBean;
import com.chanjet.changsha.bank.pay.config.AppConfig;
import com.chanjet.changsha.bank.pay.dao.MerchantDao;
import com.chanjet.changsha.bank.pay.dao.PrivateKeyDao;
import com.chanjet.changsha.bank.pay.entity.Merchant;
import com.chanjet.changsha.bank.pay.entity.PrivateKey;
import com.chanjet.changsha.bank.pay.event.ChanjetMsg;
import com.chanjet.changsha.bank.pay.event.EventHandler;
import com.chanjet.changsha.bank.pay.event.content.PayContent;
import com.chanjet.changsha.bank.pay.pojo.ChanjetPayResponse;
import com.chanjet.changsha.bank.pay.pojo.ChanjetStatus;
import com.chanjet.changsha.bank.pay.pojo.OrderPayResponse;
import com.chanjet.changsha.bank.pay.pojo.PayStatus;
import com.chanjet.changsha.bank.pay.service.MerchantService;
import com.chanjet.changsha.bank.pay.spi.csbank.OrderPay;
import com.chanjet.changsha.bank.pay.utils.StatusUtils;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 付款码付款事件处理器
 *
 * @author: zsc
 * @create: 2020/11/9 2:40 下午
 **/
@Log4j2
@Component("MICROPAY")
public class PayHandler implements EventHandler<PayContent> {
    @Autowired
    private MerchantDao merchantDao;
    @Autowired
    private PrivateKeyDao privateKeyDao;
    @Autowired
    private CsBankCommandBuilder csBankCommandBuilder;
    @Autowired
    private AppConfig appConfig;
    @Autowired
    private MerchantService merchantService;

    @Override
    public Object execute(ChanjetMsg<PayContent> chanjetMsg) {
        try {
            PayContent payContent = chanjetMsg.getBizContent();
            String privateString = merchantService.getPrivateKey(payContent.getMerchanId());
            OrderPay orderPay = csBankCommandBuilder.create(OrderPay.class);
            orderPay.setBackUrl(appConfig.getBackUrl());
            orderPay.setECustId(payContent.getMerchanId());
            orderPay.setPayMethod("7");
            orderPay.setCardNo(payContent.getAuthCode());
            orderPay.setMerchOrder(payContent.getPayOrderId());
            orderPay.setOrderAmount(payContent.getTotalAmount());
            orderPay.setRemark(payContent.getSubject());
            orderPay.setPrivateKeyString(privateString);
            OrderPayResponse orderPayResponse = orderPay.excute();
            ChanjetPayResponse chanjetPayResponse;
            BizResponseBean bizResponseBean;
            //构建成功响应
            if ("0000".equals(orderPayResponse.getStatus())) {
                chanjetPayResponse = ChanjetPayResponse.builder()
                        .payTime(orderPayResponse.getOrderTime())
                        .transactionId(orderPayResponse.getOrderId())
                        .payType("OPEN")
                        .payStatus(PayStatus.PAY_COMPLETE)
                        .openId(orderPayResponse.getThirdUserId())
                        .thirdOrderId(orderPayResponse.getOrderId())
                        .build();
                bizResponseBean = BizResponseBean.builder()
                        .result_code(chanjetPayResponse.getPayStatus())
                        .data(chanjetPayResponse)
                        .build();
                //构建待支付响应
            } else if ("0008".equals(orderPayResponse.getStatus())) {
                chanjetPayResponse = ChanjetPayResponse.builder()
                        .payTime(orderPayResponse.getOrderTime())
                        .transactionId(orderPayResponse.getOrderId())
                        .payType("OPEN")
                        .payStatus(PayStatus.PAY_PAYMENT)
                        .thirdOrderId(orderPayResponse.getOrderId())
                        .openId(orderPayResponse.getThirdUserId())
                        .build();
                bizResponseBean = BizResponseBean.builder()
                        .result_code(chanjetPayResponse.getPayStatus())
                        .error_message(orderPayResponse.getMsg())
                        .data(chanjetPayResponse)
                        .build();
                //构建失败响应
            } else {
                ChanjetStatus chanjetStatus = StatusUtils.getChanjetStatus(orderPayResponse.getOrderStat(), orderPayResponse.getMsg());
                chanjetPayResponse = ChanjetPayResponse.builder()
                        .payType("OPEN")
                        .payStatus(chanjetStatus.getResultCode())
                        .payTime(orderPayResponse.getOrderTime())
                        .transactionId(orderPayResponse.getOrderId())
                        .thirdOrderId(orderPayResponse.getOrderId())
                        .openId(orderPayResponse.getThirdUserId())
                        .build();
                bizResponseBean = BizResponseBean.builder()
                        .result_code(chanjetStatus.getResultCode())
                        .error_code(chanjetStatus.getErrorCode())
                        .error_message(chanjetStatus.getErrorMessage())
                        .data(chanjetPayResponse)
                        .build();
            }
            return bizResponseBean;
        } catch (Exception e) {
            log.error("支付失败", e);
            return BizResponseBean.builder()
                    .result_code(PayStatus.PAY_FAIL)
                    .error_code("PAY_ERROR")
                    .error_message("接口调用失败")
                    .build();
        }
    }

}
