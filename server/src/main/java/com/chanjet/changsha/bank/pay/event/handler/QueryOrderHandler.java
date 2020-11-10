package com.chanjet.changsha.bank.pay.event.handler;

import com.chanjet.changsha.bank.pay.command.builder.CsBankCommandBuilder;
import com.chanjet.changsha.bank.pay.common.BizResponseBean;
import com.chanjet.changsha.bank.pay.event.ChanjetMsg;
import com.chanjet.changsha.bank.pay.event.EventHandler;
import com.chanjet.changsha.bank.pay.event.content.QueryOrderContent;
import com.chanjet.changsha.bank.pay.pojo.ChanjetQueryOrderResponse;
import com.chanjet.changsha.bank.pay.pojo.ChanjetStatus;
import com.chanjet.changsha.bank.pay.pojo.PayStatus;
import com.chanjet.changsha.bank.pay.pojo.QueryOrderResponse;
import com.chanjet.changsha.bank.pay.service.MerchantService;
import com.chanjet.changsha.bank.pay.spi.csbank.QueryOrder;
import com.chanjet.changsha.bank.pay.utils.StatusUtils;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 查询支付结果事件处理器
 *
 * @author: zsc
 * @create: 2020/11/9 2:40 下午
 **/
@Log4j2
@Component("QUERYORDER")
public class QueryOrderHandler implements EventHandler<QueryOrderContent> {

    @Autowired
    private CsBankCommandBuilder csBankCommandBuilder;
    @Autowired
    private MerchantService merchantService;

    @Override
    public Object execute(ChanjetMsg<QueryOrderContent> chanjetMsg) {
        try {
            QueryOrderContent queryOrderContent = chanjetMsg.getBizContent();
            String merchanId = queryOrderContent.getMerchanId();
            String privateKeyString = merchantService.getPrivateKey(merchanId);
            QueryOrder queryOrder = csBankCommandBuilder.create(QueryOrder.class);
            queryOrder.setECustId(merchanId);
            queryOrder.setPrivateKeyString(privateKeyString);
            queryOrder.setMerchOrder(queryOrderContent.getPayOrderId());
            QueryOrderResponse queryOrderResponse = queryOrder.excute();
            ChanjetQueryOrderResponse chanjetQueryOrderResponse;
            BizResponseBean bizResponseBean;
            if ("0000".equals(queryOrderResponse.getStatus())) {
                chanjetQueryOrderResponse = ChanjetQueryOrderResponse.builder()
                        .payTime(queryOrderResponse.getOrderTime())
                        .transactionId(queryOrderResponse.getOrderId())
                        .payType("OPEN")
                        .payStatus(PayStatus.PAY_COMPLETE)
                        .openId(queryOrderResponse.getThirdUserId())
                        .thirdOrderId(queryOrderResponse.getOrderId())
                        .build();
                bizResponseBean = BizResponseBean.builder()
                        .result_code(chanjetQueryOrderResponse.getPayStatus())
                        .data(chanjetQueryOrderResponse)
                        .build();
                //构建待支付响应
            } else if ("0008".equals(queryOrderResponse.getStatus())) {
                chanjetQueryOrderResponse = ChanjetQueryOrderResponse.builder()
                        .payTime(queryOrderResponse.getOrderTime())
                        .transactionId(queryOrderResponse.getOrderId())
                        .payType("OPEN")
                        .payStatus(PayStatus.PAY_PAYMENT)
                        .thirdOrderId(queryOrderResponse.getOrderId())
                        .openId(queryOrderResponse.getThirdUserId())
                        .build();
                bizResponseBean = BizResponseBean.builder()
                        .result_code(chanjetQueryOrderResponse.getPayStatus())
                        .error_message(queryOrderResponse.getMsg())
                        .data(chanjetQueryOrderResponse)
                        .build();
                //构建失败响应
            } else {
                ChanjetStatus chanjetStatus = StatusUtils.getChanjetStatus(queryOrderResponse.getOrderStat(), queryOrderResponse.getMsg());
                chanjetQueryOrderResponse = ChanjetQueryOrderResponse.builder()
                        .payType("OPEN")
                        .payStatus(chanjetStatus.getResultCode())
                        .payTime(queryOrderResponse.getOrderTime())
                        .transactionId(queryOrderResponse.getOrderId())
                        .thirdOrderId(queryOrderResponse.getOrderId())
                        .openId(queryOrderResponse.getThirdUserId())
                        .build();
                bizResponseBean = BizResponseBean.builder()
                        .result_code(chanjetStatus.getResultCode())
                        .error_code(chanjetStatus.getErrorCode())
                        .error_message(chanjetStatus.getErrorMessage())
                        .data(chanjetQueryOrderResponse)
                        .build();
            }
            return bizResponseBean;
        } catch (Exception e) {
            log.error("查询支付订单异常", e);
            //TODO 内部异常处理
            return null;
        }
    }
}
