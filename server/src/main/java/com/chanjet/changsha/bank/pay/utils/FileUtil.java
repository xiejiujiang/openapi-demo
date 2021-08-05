package com.chanjet.changsha.bank.pay.utils;

import lombok.extern.log4j.Log4j2;
import sun.misc.BASE64Decoder;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.zip.GZIPInputStream;

@Log4j2
public class FileUtil {
    private static String tmpDir = "";

    static {
        File tmp;
        try {
            tmp = File.createTempFile("java.file.tmp", "tmp");
            tmpDir = tmp.getParent();
        } catch (IOException e) {
            tmpDir = System.getProperty("java.io.tmpdir");
        }

    }

    public static String getTmpDir() {
        return tmpDir;
    }

    private FileUtil() {
    }

    /**
     * base64加密的文件流转换为按行读取的list数据
     *
     * @param base64String
     * @return
     * @throws IOException
     */
    public static List<String> decodeBase64(String base64String) throws IOException {
        List<String> list = new ArrayList<>();
        File file = new File(tmpDir, UUID.randomUUID().toString() + ".gzip");
        try (FileOutputStream out = new FileOutputStream(file)) {
            byte[] buffer = new BASE64Decoder().decodeBuffer(base64String);
            out.write(buffer);
            try (InputStreamReader inputStreamReader = new InputStreamReader(new GZIPInputStream(new FileInputStream(file)), "GBK")) {
                try (BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {
                    String str;
                    // 按行读取字符串
                    while ((str = bufferedReader.readLine()) != null) {
                        list.add(str);
                    }
                }
            }
        } catch (Exception e) {
            log.error("base64解码失败!", e);
            throw e;
        }
        return list;
    }
}
