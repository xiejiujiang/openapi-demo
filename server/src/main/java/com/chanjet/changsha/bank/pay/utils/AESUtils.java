package com.chanjet.changsha.bank.pay.utils;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;


public class AESUtils {

    /**
     * aes加密
     *
     * @param str
     * @param key
     * @return
     * @throws Exception
     */
    public static String aesEncrypt(String str, String key) throws Exception {
        if (str == null || key == null) {
            return null;
        }
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(key.getBytes("utf-8"), "AES"));
        byte[] bytes = cipher.doFinal(str.getBytes(StandardCharsets.UTF_8));
        return Base64.encodeBase64String(bytes);
    }

    /**
     * aes解密
     *
     * @param str
     * @param key
     * @return
     * @throws Exception
     */
    public static String aesDecrypt(String str, String key) throws Exception {
        if (str == null || key == null) {
            return null;
        }
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "AES"));
        byte[] bytes = Base64.decodeBase64(str);
        bytes = cipher.doFinal(bytes);
        return new String(bytes, StandardCharsets.UTF_8);
    }


    public static void main(String[] args) {
        String content = "{\n" +
                " \"id\": \"dbe8970a-53a7-165c-7339-02c55bbddea5\",\n" +
                " \"appKey\": \"FQa4kEGD\",\n" +
                " \"appId\": \"34526534673\",\n" +
                " \"msgType\": \"notice\",\n" +
                " \"time\": \"1603698652093\",\n" +
                " \"bizContent\": {\n" +
                "  \"name:\": \"测试\",\n" +
                "  \"value\": \"测试\"\n" +
                " }\n" +
                "}";
        System.out.println("加密前：" + content);
        String key = "1234567890123456";
        System.out.println("加密密钥和解密密钥：" + key);
        try {
            String encrypt = aesEncrypt(content, key);
            System.out.println("加密后：" + encrypt);
//            String str = " Ww/5c2K+8uJS8F9Z2Gsi3fDvHEhsUNktFRyJZdjSqjeGV8SzbnCiv2oSwRoTft2T6NsHviUS3zHqYwvKVsgOfNR6QVOzerfB+9LekYqf4iGp+D7kKA+v2MxPl84gWB7yJAa10JjEkzZkcb3SL9kLE5ngmJ6furfOXMbls4jg07CN2TaokUDrQudvpX6yAKoEvAHmV2KJBrk/1g/AjxVtSt8rp0Dn/rQn/c1gUVA6cgs9PKhOEXS/M50xxIatrc17Uv+OCdLpmpmJNeL87DtkBftwDnQhIq2EWc7pIWvi1SUxBwMpAIiIieWiHCHxFMvU";
//            String decrypt = aesDecrypt(str, "testchaotestchao");
            String decrypt = aesDecrypt(encrypt, key);
            System.out.println("解密后：" + decrypt);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}