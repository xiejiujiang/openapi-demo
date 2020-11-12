package com.chanjet.changsha.bank.pay.utils;

import java.io.File;
import java.io.IOException;


public class FileUtil {
	private static String tmpDir = "" ;
	static {
		File tmp;
		try {
			tmp = File.createTempFile("java.file.tmp", "tmp");
			tmpDir  = tmp.getParent();
		} catch (IOException e) {
			tmpDir  = System.getProperty("java.io.tmpdir");
		}
		
	}
	
	public static String getTmpDir() {
		return tmpDir;
	}

	private FileUtil(){}
	
}
