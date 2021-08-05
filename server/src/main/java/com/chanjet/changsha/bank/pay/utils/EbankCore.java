package com.chanjet.changsha.bank.pay.utils;


import lombok.extern.log4j.Log4j2;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.*;


/**
 * 类名：EbankCore
 * 功能：E钱庄支付接入公用函数类
 * 版本：1.0
 * 日期：2016-07-28
 */
@Log4j2
public class EbankCore {
    /**
     * 除去数组中的空值和签名参数
     *
     * @param sArray 签名参数组
     * @return 去掉空值与签名参数后的新签名参数组
     */
    public static Map<String, String> paraFilter(Map<String, String> sArray) {
        Map<String, String> result = new HashMap<>();
        if (sArray == null || sArray.size() <= 0) {
            return result;
        }
        for (String key : sArray.keySet()) {
            String value = sArray.get(key);
            if (value == null || value.equals("") || key.equalsIgnoreCase("Sign")
                    || key.equalsIgnoreCase("SignData") || key.equalsIgnoreCase("code")) {
                continue;
            }
            result.put(key, value);
        }

        return result;
    }

    /**
     * 把数组所有元素排序，并按照“参数=参数值”的模式用“&”字符拼接成字符串
     *
     * @param params 需要排序并参与字符拼接的参数组
     * @return 拼接后字符串
     */
    public static String createLinkString(Map<String, String> params) {
        List<String> keys = new ArrayList<>(params.keySet());
        Collections.sort(keys);
        String prestr = "";
        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value = params.get(key);

            if (i == keys.size() - 1) {//拼接时，不包括最后一个&字符
                prestr = prestr + key + "=" + value;
            } else {
                prestr = prestr + key + "=" + value + "&";
            }
        }

        return prestr;
    }

    /**
     * md5加密
     *
     * @param str
     * @return
     * @throws Exception
     */
    public static String EncoderByMd5(String str) throws Exception {
        //返回实现指定摘要算法的 MessageDigest 对象。
        MessageDigest md5 = MessageDigest.getInstance("md5");
        //先将字符串转换成byte数组，再用byte 数组更新摘要
        md5.update(str.getBytes(StandardCharsets.UTF_8));
        //哈希计算，即加密
        byte[] nStr = md5.digest();
        //加密的结果是byte数组，将byte数组转换成字符串
        return bytes2Hex(nStr);
    }

    private static String bytes2Hex(byte[] bts) {
        StringBuilder des = new StringBuilder();
        String tmp = null;
        for (byte bt : bts) {
            tmp = (Integer.toHexString(bt & 0xFF));
            if (tmp.length() == 1) {
                des.append("0");
            }
            des.append(tmp);
        }
        return des.toString();
    }


}
