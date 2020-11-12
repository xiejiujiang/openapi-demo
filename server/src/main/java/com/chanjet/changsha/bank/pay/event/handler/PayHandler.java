package com.chanjet.changsha.bank.pay.event.handler;

import com.chanjet.changsha.bank.pay.command.builder.CsBankCommandBuilder;
import com.chanjet.changsha.bank.pay.common.BizResponseBean;
import com.chanjet.changsha.bank.pay.common.PayStatus;
import com.chanjet.changsha.bank.pay.config.AppConfig;
import com.chanjet.changsha.bank.pay.event.ChanjetMsg;
import com.chanjet.changsha.bank.pay.event.EventHandler;
import com.chanjet.changsha.bank.pay.event.content.PayContent;
import com.chanjet.changsha.bank.pay.pojo.ChanjetPayResponse;
import com.chanjet.changsha.bank.pay.pojo.ChanjetStatus;
import com.chanjet.changsha.bank.pay.pojo.OrderPayResponse;
import com.chanjet.changsha.bank.pay.service.MerchantService;
import com.chanjet.changsha.bank.pay.spi.csbank.OrderPay;
import com.chanjet.changsha.bank.pay.utils.DateUtil;
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
    private CsBankCommandBuilder csBankCommandBuilder;
    @Autowired
    private AppConfig appConfig;
    @Autowired
    private MerchantService merchantService;

    @Override
    public Object execute(ChanjetMsg<PayContent> chanjetMsg) {
        try {
            PayContent payContent = chanjetMsg.getBizContent();
            Double amount = Double.parseDouble(payContent.getTotalAmount())/100;
            String payMethod = payContent.getPayMethod();
            if ("2".equals(payMethod)) {
                payMethod = "5";
            } else if ("4".equals(payMethod)) {
                payMethod = "7";
            } else {
                return BizResponseBean.builder()
                        .result_code(PayStatus.PAY_ERROR)
                        .error_code("PAY_ERROR")
                        .error_message("不支持的支付方式")
                        .build();
            }
            String privateString = merchantService.getPrivateKey(payContent.getMerchanId(),payContent.getBookId());
            OrderPay orderPay = csBankCommandBuilder.create(OrderPay.class);
            orderPay.setBackUrl(appConfig.getBackUrl());
            orderPay.setECustId(payContent.getMerchanId());
            orderPay.setPayMethod(payMethod);
            orderPay.setCardNo(payContent.getAuthCode());
            orderPay.setMerchOrder(payContent.getPayOrderId());
            orderPay.setOrderAmount(String.valueOf(amount));
            orderPay.setRemark(payContent.getSubject());
            orderPay.setPrivateKeyString(privateString);
            OrderPayResponse orderPayResponse = orderPay.excute();
            ChanjetPayResponse chanjetPayResponse;
            BizResponseBean bizResponseBean;
            //构建成功响应
            if ("0000".equals(orderPayResponse.getStatus())) {
                chanjetPayResponse = ChanjetPayResponse.builder()
                        .payTime(DateUtil.getDate())
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
                        .payTime(DateUtil.getDate())
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
                ChanjetStatus chanjetStatus = StatusUtils.getPayStatus(orderPayResponse.getOrderStat(), orderPayResponse.getMsg());
                chanjetPayResponse = ChanjetPayResponse.builder()
                        .payType("OPEN")
                        .payStatus(chanjetStatus.getResultCode())
                        .payTime(DateUtil.getDate())
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
                    .result_code(PayStatus.PAY_ERROR)
                    .error_code("PAY_ERROR")
                    .error_message("长沙银行付款码支付调用失败")
                    .build();
        }
    }

}
