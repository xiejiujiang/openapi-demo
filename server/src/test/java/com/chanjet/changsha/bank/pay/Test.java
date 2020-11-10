package com.chanjet.changsha.bank.pay;

import com.chanjet.changsha.bank.pay.utils.SignUtils;

import java.util.HashMap;

/**
 * @author: zsc
 * @create: 2020/11/10 10:45 上午
 **/
public class Test {

    @org.junit.Test
    public void test() {
        HashMap<String, String> map = new HashMap<>();
        map.put("BackUrl", "https://inte-openapi.chanjet.com");
        map.put("CardNo", "134571691627065598");
        map.put("ECustId", "E2020052200035");
        map.put("MerchOrder", "202011096666");
        map.put("OrderAmount", "0.02");
        map.put("PayMethod", "7");
        map.put("Remark", "weixin");
        map.put("Service_version", "1.0");
        String signature = SignUtils.sign(map);
        System.out.println("付款码支付签名：" + signature);


//            TreeMap<String, String> map1 = new TreeMap<>();
//            map1.put("ECustId", "E2020052200035");
//            map1.put("MerchOrder", "2020110515112734");
//            map1.put("Service_version", "1.0");
//            String signature1 = sign(map1);
//            System.out.println("查询订单签名：" + signature1);
//
//            TreeMap<String, String> map2 = new TreeMap<>();
//            map2.put("ECustId", "E2020052200035");
//            map2.put("Service_version", "1.0");
//            map2.put("OrderId", "202011053001561153");
//            String signature2 = sign(map2);
//            System.out.println("查询退款订单签名：" + signature2);
//
//            TreeMap<String, String> map3 = new TreeMap<>();
//            map3.put("ECustId", "E2020052200035");
//            map3.put("Service_version", "1.0");
//            map3.put("BalanceDate", "20201105");
//            map3.put("FileType", "3");
//            String signature3 = sign(map3);
//            System.out.println("查询对账单签名：" + signature3);
//
//            TreeMap<String, String> map4 = new TreeMap<>();
//            map4.put("ECustId", "E2020052200035");
//            map4.put("Service_version", "1.0");
//            map4.put("OrderId", "202011053001561153");
//            map4.put("CancelReason", "退款");
//            map4.put("RefundAmount", "0.01");
//            String signature4 = sign(map4);
//            System.out.println("申请退款签名：" + signature4);
    }
}
