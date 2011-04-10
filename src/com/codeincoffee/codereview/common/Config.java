package com.codeincoffee.codereview.common;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import com.codeincoffee.codereview.utils.FileUtil;

/**
 * @author Andy Chen 2011-3-9
 */
public class Config {

	public static String root = "src";
	public static boolean SUCCESS_LOG_OPEN = false;
	public static boolean FAIL_LOG_OPEN = true;
	
	private static final String CONFIG = "config.xml";

	private Config() {}
	
	public static InputStream getReviewers() {
		try {
			return new FileInputStream(FileUtil.getConfigFile(CONFIG));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

}
