package com.chanjet.openapi.demo.utils;


import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.security.Key;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.util.Enumeration;

/**
 * 文件流获取公私钥，拷贝自长沙银行提供demo
 *
 * @author: zsc
 * @create: 2020/11/4 5:28 下午
 **/
public class CertUtil {
    public CertUtil() {
    }

    public static PrivateKey readPrivateKeyFromPKCS12StoredFile(InputStream certFile, String password) throws GeneralSecurityException, IOException {
        KeyStore keystore = KeyStore.getInstance("PKCS12");
        keystore.load(certFile, password.toCharArray());
        Enumeration<String> enumeration = keystore.aliases();
        String alias = null;
        PrivateKey privateKey = null;

        while (enumeration.hasMoreElements()) {
            alias = ((String) enumeration.nextElement()).toString();
            Key key = keystore.getKey(alias, password.toCharArray());
            if (key != null && key instanceof PrivateKey) {
                privateKey = (PrivateKey) key;
                break;
            }
        }

        return privateKey;
    }

    public static PublicKey readPublicKeyFromX509StoredFile(InputStream certFile) throws GeneralSecurityException {
        CertificateFactory cf = CertificateFactory.getInstance("X.509");
        Certificate certificate = cf.generateCertificate(certFile);
        return certificate != null ? certificate.getPublicKey() : null;
    }
}
