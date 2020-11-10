package com.chanjet.changsha.bank.pay.utils;

import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author: zsc
 * @create: 2020/11/4 4:55 下午
 **/
@Log4j2
public class SignUtils {
    /**
     * 签名实现
     *
     * @param paramMap 请求报文参数集合
     * @return
     */
    public static String sign(Map<String, String> paramMap) {
        String sign = "";
        // 1.剔除无值参数和sign
        Map<String, String> text = EbankCore.paraFilter(paramMap);
        // 2.参数和值用“=”符号连接，组成键值对;并用&拼接
        String signStr = EbankCore.createLinkString(text);
        log.info("MD5加密前明文为" + signStr);
        try {
            // 3.对处理后的字符串md5加密
            String plainText = EbankCore.EncoderByMd5(signStr);
            log.info("加密前MD5明文为" + plainText);
            // 4.证书加密
            sign = signData(plainText);
            log.info("得到最终的签名字段为" + sign + "\n");
        } catch (Exception e) {
            throw new RuntimeException("签名失败", e);
        }
        return sign;
    }


    /**
     * 解码PublicKey
     * @param key
     * @return
     */
    public static PublicKey getPublicKey(String key) {
        try {
            byte[] byteKey = Base64.getDecoder().decode(key);
            X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(byteKey);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            return keyFactory.generatePublic(x509EncodedKeySpec);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 解码PrivateKey
     * @param key
     * @return
     */
    public static PrivateKey  getPrivateKey(String key) {
        try {
            byte[] byteKey = Base64.getDecoder().decode(key);
            PKCS8EncodedKeySpec x509EncodedKeySpec = new PKCS8EncodedKeySpec(byteKey);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            return keyFactory.generatePrivate(x509EncodedKeySpec);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 验签实现
     *
     * @param paramMap 服务方报文参数集合
     * @return
     */
    public static String verify(Map<String, String> paramMap, String sign) {
        // 1.剔除无值参数和sign
        Map<String, String> text = EbankCore.paraFilter(paramMap);
        // 2.参数和值用“=”符号连接，组成键值对;并用&拼接
        String signStr = EbankCore.createLinkString(text);
        log.info("MD5加密前明文为" + signStr);
        try {
            // 3.对处理后的字符串md5加密
            String plainText = EbankCore.EncoderByMd5(signStr);
            log.info("加密前MD5后明文为1:" + plainText);
            // 4.证书解签
            boolean flag = verify(sign, plainText);
            log.info("验签结果:" + (flag ? "成功" : "失败"));
        } catch (Exception e) {
            throw new RuntimeException("验签失败", e);
        }
        return sign;
    }

    /**
     * 加签
     *
     * @param data 传入需要签名的函数
     * @return 返回 密文
     * @throws Exception
     */
    public static String signData(String data) throws GeneralSecurityException, IOException {
        InputStream in = new java.io.FileInputStream("/Users/zsc/IdeaProjects/changsha-bank-pay-service/src/main/resources/BCS_private_key.pfx");
        PrivateKey privateKey = CertUtil.readPrivateKeyFromPKCS12StoredFile(in, "123456");
        in.close();
        // 使用SHA-1签名算法对证书进行签名
        byte[] signed = doSign(privateKey, data.getBytes(StandardCharsets.UTF_8), "SHA1WithRSA");
        // 把字符数组形式的签名转换成16进制
        return bytesToHexString(signed);
    }

    /**
     * 验签
     *
     * @param signData 密文
     * @param outBody  明文
     * @throws Exception
     */
    public static boolean verify(String signData, String outBody) throws IOException, GeneralSecurityException {
        InputStream in = new java.io.FileInputStream("/Users/zsc/IdeaProjects/changsha-bank-pay-service/src/main/resources/BCS_public_key.cer");
        PublicKey publicKey = CertUtil.readPublicKeyFromX509StoredFile(in);// 读取公钥
        in.close();
        byte[] signDataBytes = hexStringToByte(signData);// 签名数据字节

        byte[] content = outBody.getBytes(StandardCharsets.UTF_8);// 明文字节
        boolean pass;
        try {
            pass = doVerify(publicKey, content, signDataBytes, "SHA1WithRSA");// 将明文字节与密文字节传入
            // 返回true 签名成功 false则为签名失败
            log.info("验签结果:{}", pass);
        } catch (Exception e) {
            throw new RuntimeException("验签失败", e);
        }
        return pass;
    }

    public static String bytesToHexString(byte[] bArray) {
        StringBuilder sb = new StringBuilder(bArray.length);
        for (byte b : bArray) {
            String sTemp = Integer.toHexString(255 & b);
            if (sTemp.length() < 2) {
                sb.append(0);
            }
            sb.append(sTemp.toUpperCase());
        }

        return sb.toString();
    }

    public static byte[] hexStringToByte(String hex) {
        int len = hex.length() / 2;
        byte[] result = new byte[len];
        char[] achar = hex.toCharArray();
        for (int i = 0; i < len; i++) {
            int pos = i * 2;
            result[i] = (byte) (toByte(achar[pos]) << 4 | toByte(achar[pos + 1]));
        }
        return result;
    }

    private static int toByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }

    public static byte[] doSign(PrivateKey priKey, byte[] content, String algorithms) throws GeneralSecurityException {
        Signature signature = Signature.getInstance(algorithms);
        signature.initSign(priKey);
        signature.update(content);
        return signature.sign();
    }

    public static boolean doVerify(PublicKey pubKey, byte[] content, byte[] signature, String algorithms) throws GeneralSecurityException {
        Signature signCheck = Signature.getInstance(algorithms);
        signCheck.initVerify(pubKey);
        signCheck.update(content);
        return signCheck.verify(signature);
    }

    public static void main(String[] args) throws IOException, GeneralSecurityException {
        TreeMap<String, String> map = new TreeMap<>();
        map.put("BackUrl", "https://inte-openapi.chanjet.com");
        map.put("CardNo", "134610224910212580");
        map.put("ECustId", "E2020052200035");
        map.put("MerchOrder", "2020110515112777");
        map.put("OrderAmount", "0.01");
        map.put("PayMethod", "7");
        map.put("Remark", "pos_alipay");
        map.put("Service_version", "1.0");
        String signature = sign(map);
        System.out.println("付款码支付签名：" + signature);


        TreeMap<String, String> map1 = new TreeMap<>();
        map1.put("ECustId", "E2020052200035");
        map1.put("MerchOrder", "2020110515112734");
        map1.put("Service_version", "1.0");
        String signature1 = sign(map1);
        System.out.println("查询订单签名：" + signature1);

        TreeMap<String, String> map2 = new TreeMap<>();
        map2.put("ECustId", "E2020052200035");
        map2.put("Service_version", "1.0");
        map2.put("OrderId", "202011053001561153");
        String signature2 = sign(map2);
        System.out.println("查询退款订单签名：" + signature2);

        TreeMap<String, String> map3 = new TreeMap<>();
        map3.put("ECustId", "E2020052200035");
        map3.put("Service_version", "1.0");
        map3.put("BalanceDate", "20201105");
        map3.put("FileType", "3");
        String signature3 = sign(map3);
        System.out.println("查询对账单签名：" + signature3);

        TreeMap<String, String> map4 = new TreeMap<>();
        map4.put("ECustId", "E2020052200035");
        map4.put("Service_version", "1.0");
        map4.put("OrderId", "202011053001561153");
        map4.put("CancelReason", "退款");
        map4.put("RefundAmount", "0.01");
        String signature4 = sign(map4);
        System.out.println("申请退款签名：" + signature4);
    }
}
