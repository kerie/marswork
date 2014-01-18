package com.marswork.core.minitools.utils;

import java.security.MessageDigest;

/**
 * <p>MD5加密
 * 
 * @author anonymous 
 * @since 2010-12-11
 * @version 1.0
 */
public class Encrpytion {

	/**
	 * 字符串加密
	 * 
	 * @param 需加密字符串
	 * @return 加密后的字符串
	 * 
	 */

	public static String encrypt(String text) throws Exception {

		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(text.getBytes());
		byte b[] = md.digest();

		int i;

		StringBuffer ntext = new StringBuffer("");
		for (int offset = 0; offset < b.length; offset++) {
			i = b[offset];
			if (i < 0)
				i += 256;
			if (i < 16)
				ntext.append("0");
			ntext.append(Integer.toHexString(i));
		}
		return ntext.toString();
	}

}
