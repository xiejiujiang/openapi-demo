package com.chanjet.changsha.bank.pay.utils;

import lombok.extern.log4j.Log4j2;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

/**
 * 签名工具类
 *
 * @author: zsc
 * @create: 2020/11/5 11:23 上午
 **/
@Log4j2
public class SignUtil {
    /**
     * 加签
     *
     * @param privateKeyString 商户私钥字符串
     * @param paramMap         需要加签的参数map
     * @return
     */
    public static String sign(String privateKeyString, Map<String, String> paramMap) {
        try {
            // 1.剔除无值参数和sign
            Map<String, String> text = EbankCore.paraFilter(paramMap);
            // 2.参数和值用“=”符号连接，组成键值对;并用&拼接
            String signStr = EbankCore.createLinkString(text);
            log.info("MD5加密前明文为" + signStr);

            // 3.对处理后的字符串md5加密
            String plainText = EbankCore.EncoderByMd5(signStr);
            log.info("加密前MD5明文为" + plainText);

            // 4.证书加密
            String sign = signData(plainText, privateKeyString);
            log.info("得到最终的签名字段为" + sign + "\n");
            return sign;
        } catch (Exception e) {
            throw new RuntimeException("签名失败", e);
        }
    }

    /**
     * 签名
     *
     * @param data             签名
     * @param privateKeyString 私钥字符串
     * @return
     * @throws GeneralSecurityException
     */
    public static String signData(String data, String privateKeyString) throws GeneralSecurityException {
        //获取私钥
        PrivateKey privateKey = getPrivateKey(privateKeyString);
        // 使用SHA-1签名算法对证书进行签名
        byte[] signed = doSign(privateKey, data.getBytes(StandardCharsets.UTF_8), "SHA1WithRSA");
        // 把字符数组形式的签名转换成16进制
        return bytesToHexString(signed);
    }

    /**
     * 私钥字符串生成私钥key
     *
     * @param privateKeyString 私钥字符串
     * @return
     */
    public static PrivateKey getPrivateKey(String privateKeyString) {
        try {
            byte[] byteKey = Base64.getDecoder().decode(privateKeyString);
            PKCS8EncodedKeySpec x509EncodedKeySpec = new PKCS8EncodedKeySpec(byteKey);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            return keyFactory.generatePrivate(x509EncodedKeySpec);
        } catch (Exception e) {
            throw new RuntimeException("私钥字符串生成私钥key失败", e);
        }
    }


    public static String bytesToHexString(byte[] bArray) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bArray) {
            String sTemp = Integer.toHexString(255 & b);
            if (sTemp.length() < 2) {
                sb.append(0);
            }
            sb.append(sTemp.toUpperCase());
        }

        return sb.toString();
    }

    public static byte[] doSign(PrivateKey priKey, byte[] content, String algorithms) throws GeneralSecurityException {
        Signature signature = Signature.getInstance(algorithms);
        signature.initSign(priKey);
        signature.update(content);
        return signature.sign();
    }

    /**
     * 获取私钥字符串
     *
     * @param file
     * @param password
     * @return
     */
    public static String getPrivateKeyString(File file, String password) throws GeneralSecurityException, IOException {
        try (InputStream in = new FileInputStream(file)) {
            PrivateKey privateKey = CertUtil.readPrivateKeyFromPKCS12StoredFile(in, password);
            return Base64.getEncoder().encodeToString(privateKey.getEncoded());
        } catch (IOException | GeneralSecurityException e) {
            log.error("获取私钥字符串失败", e);
            throw e;
        }
    }

    /**
     * 获取公钥字符串
     *
     * @param file
     * @return
     */
    public static String getPublicKeyString(File file) throws IOException, GeneralSecurityException {
        try (InputStream in = new FileInputStream(file)) {
            PublicKey publicKey = CertUtil.readPublicKeyFromX509StoredFile(in);
            return Base64.getEncoder().encodeToString(publicKey.getEncoded());
        } catch (IOException | GeneralSecurityException e) {
            log.error("获取公钥字符串失败", e);
            throw e;
        }

    }

    public static void main(String[] args) {
        String privateKeyString = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQDGeaQ630sAT9LuyJ9/enfKcpthDUFqJI9V4YMD/gUFz7tS/s42uzsXYqfafpkV3wQxyC30ZvSOtOYvAQuAPfLwJkfIX2Ud83CFMIaCsnfkqBFVT/xe/vNUjz6p29LqRrt3w7gn1+o5pTsFuBvd9qLttjC+QUMLfRJOejk4Nm2VJr//gWdCqemqxTcyFkNSCRIA5JhP9OmeasUODTF/tGShFB/QgvdD/Iv1+Wl4Nc0dF18PBhaPB7MzNkbAPxFCeU5POcNQqQLrbnXrhqA6dBcX+QozSE5H0VIoqgqQXc0SAqXlKGd421Za7T8h1oI4E/ENS7Mlda681A97Q2OscIABAgMBAAECggEAcBLaQQA76tYoZvvUzPhL0n667Ke+i4BPjBBGmieDkI+H2MrXiSJasM7dt8yLB0WiqbW+9WZ5rGfMHzi41NamayNCn4v2nl4L6LnJCt5aQz0AZKtA+CeBIfutR99Aj1gXnf5f6+fJdHmwBX+8BK+zuwWJKrXRdRhQEQ6FZw+c+34icfF7LIJ5wa1UUfBjI3U/eiKkCjiLmhnIRTOIZcB0yPo+iLy5Pavo6uZ6IlCSRaQrPOtlDYPJhBw3BBdvlknLXYH8fKDu03rPYHhYn68zF+UaqkB/nnANRKmGvy3c2jNHq27E/QTV/0z++08wP7TjvWCBblxEQCm2QDrBJgp9bQKBgQDo64bGRGEKKKZfT5Dy+8NtSz0aZnmFTpI7BjV+vbWPKhZX3nL5xzEuDYWR12rKKBahdC8Be7w4TZzJZNmQdwvsLM5+rjq4LkFRYjfPMgkEVQJA4u1xbRyBQt9hRZMWy47prXrezPyGsvSrn9Ci0p357kTNHlIJT3HCZ95QhxfemwKBgQDaJFp/zXu8hl4HXY80mTRETZlIJ//S5HC85VwRy//Thl51QKiXwuO2di9IGJZZw8DdQJaxgsXzAEohRktuz43wpF0n/cgpkrYue2CyqhO9cGowYkGu+ifypMnM+ahr09ONTB3KOV8MuU576q9pmNnME/bIJtLw7hYdVLCq9g9XkwKBgBfNIZuXFzd7iezJa614zrKXcqoE+LwHmGfRrZvC9Q+pJFlcUPF3Sm5DUXfixHXd/NoIzljfdQ9n1vK5jIX3tE8xEPTfBv8VMvWHDN7uZP99VFqfFdZ2aOhIlh0RiANXT8ZPRPQJjHCTTu9OyHoq/ZuxnnQ7efSbmqtcA9rEw0qrAoGAVXQSQ6hyRXr5kLcU50zwzaT8W+jXbXzxsP3EYHBiqjEkBZirsq+01oBbq0l+dHGbttCbd7Q76SJ+56QK3sDN4KvyljzzUV3YokLQsYywqPBFmOiUxH/YcFQffX17faoAYISl0tdApdJdrWtVBe/LugdEEEotLhaGcT0A7CfyAKMCgYApga/KXFgrMu9jYz5uzAHFG5MFOYPrUPa+/2aHAMFU2RrpcdCCle3t67/ZKUoghtmIbRz1as3LhsMfalkYKNiIf2yiEgw5qQ37kiLSDCyUP15Kq8DehzjOfYHK9d+WeDL3OZRNGRx6SA/PXesjsrKatYbzkS8bIty+EX/JATgGPg==";
        Map<String, String> map = new HashMap<>();
        map.put("BackUrl", "https://inte-openapi.chanjet.com");
        map.put("BusiType", "113");
        map.put("CardNo", "135316677471509504");
        map.put("ECustId", "E2020052200035");
        map.put("MerchOrder", "2020110515112734");
        map.put("OrderAmount", "0.01");
        map.put("PayMethod", "7");
        map.put("Remark", "pos_alipay");
        map.put("Service_version", "1.0");
        System.out.println("付款码支付签名：" + sign(privateKeyString, map));
    }
}
