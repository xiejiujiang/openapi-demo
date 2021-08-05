package com.chanjet.changsha.bank.pay;

import java.util.HashMap;
import java.util.TreeMap;

/**
 * @author: zsc
 * @create: 2020/11/10 10:45 上午
 **/
public class Test {

    @org.junit.Test
    public void test() {
        HashMap<String, String> map = new HashMap<>();
        map.put("BackUrl", "https://inte-openapi.chanjet.com");
        map.put("CardNo", "134525978162423622");
        map.put("ECustId", "E2020052200035");
        map.put("MerchOrder", "202011091111");
        map.put("OrderAmount", "0.02");
        map.put("PayMethod", "7");
        map.put("Remark", "weixin");
        map.put("Service_version", "1.0");
        String signature = SignUtils.sign(map);
        System.out.println("付款码支付签名：" + signature);

        TreeMap<String, String> map1 = new TreeMap<>();
        map1.put("ECustId", "E2020052200035");
        map1.put("MerchOrder", "202011098888");
        map1.put("Service_version", "1.0");
        String signature1 = SignUtils.sign(map1);
        System.out.println("查询订单签名：" + signature1);

        TreeMap<String, String> map2 = new TreeMap<>();
        map2.put("ECustId", "E2020052200035");
        map2.put("Service_version", "1.0");
        map2.put("OrderId", "202011103001680953");
        map2.put("RePaymentSn", "1234567892");
        String signature2 = SignUtils.sign(map2);
        System.out.println("查询退款订单签名：" + signature2);

        TreeMap<String, String> map3 = new TreeMap<>();
        map3.put("ECustId", "E2020052200035");
        map3.put("Service_version", "1.0");
        map3.put("BalanceDate", "20201105");
        map3.put("FileType", "3");
        String signature3 = SignUtils.sign(map3);
        System.out.println("查询对账单签名：" + signature3);

        TreeMap<String, String> map4 = new TreeMap<>();
        map4.put("ECustId", "E2020052200035");
        map4.put("Service_version", "1.0");
        map4.put("OrderId", "202011103001680953");
        map4.put("CancelReason", "退款");
        map4.put("RefundAmount", "0.02");
        map4.put("ERefundSn","1234567892");
        String signature4 = SignUtils.sign(map4);
        System.out.println("申请退款签名：" + signature4);
    }
}
