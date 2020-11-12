package com.chanjet.changsha.bank.pay.event.content;

import com.chanjet.changsha.bank.pay.event.ChanjetMsg;
import com.chanjet.changsha.bank.pay.utils.AESUtils;
import com.google.gson.Gson;
import lombok.Data;

/**
 * 支付码付款
 *
 * @author: zsc
 * @create: 2020/11/9 2:57 下午
 **/
@Data
public class PayContent {
    /**
     * 支付订单号
     */
    private String payOrderId;
    /**
     * 支付金额  单位分
     */
    private String totalAmount;
    /**
     * 付款码内容
     */
    private String authCode;
    /**
     * 交易简介
     */
    private String subject;
    /**
     * 门店收银员
     */
    private String operator;
    /**
     * 结果推送地址
     */
    private String notifyUrl;
    /**
     * 入网之后的商户ID
     */
    private String merchanId;
    /**
     * 支付方式
     */
    private String payMethod;

    public static void main(String[] args) throws Exception {
        ChanjetMsg<PayContent> chanjetMsg = new ChanjetMsg<>();
        PayContent payContent = new PayContent();
        payContent.setAuthCode("134723697045206799");
        payContent.setMerchanId("E2020052200035");
        payContent.setNotifyUrl("http://17f1bba78b51.ngrok.io/auth/test");
        payContent.setOperator("nihao");
        payContent.setPayOrderId("3518953981212598152422277");
        payContent.setSubject("畅捷通测试");
        payContent.setTotalAmount("0.01");
        chanjetMsg.setBizContent(payContent);
        chanjetMsg.setMsgType("MICROPAY");
        chanjetMsg.setAppKey("ptPrU0YV");
        System.out.println("明文" + chanjetMsg);

        System.out.println("密文" + AESUtils.aesEncrypt(new Gson().toJson(chanjetMsg), "9876543210987654"));
    }
}
