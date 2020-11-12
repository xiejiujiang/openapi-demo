package com.chanjet.changsha.bank.pay.event.handler;

import com.chanjet.changsha.bank.pay.command.builder.CsBankCommandBuilder;
import com.chanjet.changsha.bank.pay.common.BizResponseBean;
import com.chanjet.changsha.bank.pay.common.RefundStatus;
import com.chanjet.changsha.bank.pay.event.ChanjetMsg;
import com.chanjet.changsha.bank.pay.event.EventHandler;
import com.chanjet.changsha.bank.pay.event.content.QueryRefundOrderContent;
import com.chanjet.changsha.bank.pay.pojo.ChanjetQueryRefundOrderResponse;
import com.chanjet.changsha.bank.pay.pojo.ChanjetStatus;
import com.chanjet.changsha.bank.pay.pojo.QueryRefundOrderResponse;
import com.chanjet.changsha.bank.pay.service.MerchantService;
import com.chanjet.changsha.bank.pay.spi.csbank.QueryRefundOrder;
import com.chanjet.changsha.bank.pay.utils.DateUtil;
import com.chanjet.changsha.bank.pay.utils.StatusUtils;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author: zsc
 * @create: 2020/11/11 11:07 上午
 **/
@Log4j2
@Component
public class QueryRefundOrderHandler implements EventHandler<QueryRefundOrderContent> {

    @Autowired
    private MerchantService merchantService;
    @Autowired
    private CsBankCommandBuilder csBankCommandBuilder;

    @Override
    public Object execute(ChanjetMsg<QueryRefundOrderContent> chanjetMsg) {
        try {
            QueryRefundOrderContent queryRefundOrderContent = chanjetMsg.getBizContent();
            String merchanId = queryRefundOrderContent.getMerchanId();
            String privateKeyString = merchantService.getPrivateKey(merchanId,queryRefundOrderContent.getBookId());
            QueryRefundOrder queryRefundOrder = csBankCommandBuilder.create(QueryRefundOrder.class);
            queryRefundOrder.setECustId(merchanId);
            queryRefundOrder.setPrivateKeyString(privateKeyString);
            queryRefundOrder.setOrderId(queryRefundOrderContent.getThirdOrderId());
            queryRefundOrder.setRePaymentSn(queryRefundOrderContent.getRefundOrderId());
            QueryRefundOrderResponse queryRefundOrderResponse = queryRefundOrder.excute();
            ChanjetQueryRefundOrderResponse chanjetQueryRefundOrderResponse;
            BizResponseBean bizResponseBean;
            if ("0000".equals(queryRefundOrderResponse.getStatus())) {
                ChanjetStatus refundStatus = StatusUtils.getRefundStatus(queryRefundOrderResponse.getOrderStat(), queryRefundOrderResponse.getMsg());
                chanjetQueryRefundOrderResponse = ChanjetQueryRefundOrderResponse.builder()
                        .refundStatus(refundStatus.getResultCode())
                        .refundId(queryRefundOrderResponse.getOrderId())
                        .refundTime(DateUtil.getDate())
                        .thirdRefundId(queryRefundOrderResponse.getOrderId())
                        .build();
                bizResponseBean = BizResponseBean.builder()
                        .result_code(refundStatus.getResultCode())
                        .data(chanjetQueryRefundOrderResponse)
                        .build();
            } else {
                ChanjetStatus refundStatus = StatusUtils.getRefundStatus(queryRefundOrderResponse.getOrderStat(), queryRefundOrderResponse.getMsg());
                chanjetQueryRefundOrderResponse = ChanjetQueryRefundOrderResponse.builder()
                        .refundStatus(refundStatus.getResultCode())
                        .build();
                bizResponseBean = BizResponseBean.builder()
                        .result_code(refundStatus.getResultCode())
                        .error_message(refundStatus.getErrorMessage())
                        .data(chanjetQueryRefundOrderResponse)
                        .build();
            }
            return bizResponseBean;
        } catch (Exception e) {
            log.error("查询长沙银行退款订单接口调用失败", e);
            return BizResponseBean.builder()
                    .result_code(RefundStatus.REFUNDED_ERROR)
                    .error_message("查询长沙银行退款订单接口调用失败")
                    .build();
        }
    }
}
