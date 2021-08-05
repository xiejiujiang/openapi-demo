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
}
