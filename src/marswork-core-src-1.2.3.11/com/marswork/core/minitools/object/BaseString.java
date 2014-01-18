package com.marswork.core.minitools.object;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>
 * 常用字符串操作
 * 
 * @author MarsDJ
 * @since 2011-3-14
 * @version 1.0
 */
public class BaseString {

	private BaseString() {
	}

	/**
	 * 转换字符串编码：ISO-8859-1 ---> GBK
	 * 
	 * @param (String) str --需转换的字符串
	 * @return （String） --转换后的字符串
	 */
	public final static String convertISOToGBK(String str) throws IOException,
			UnsupportedEncodingException {

		return (str == null) ? "" : new String(str.getBytes("ISO-8859-1"), "GBK");
	}

	/**
	 * 转换字符串编码：GBK ---> UTF-8
	 * 
	 * @param (String) str --需转换的字符串
	 * @return （String） --转换后的字符串
	 */
	public final static String convertGBKToUTF(String str) throws IOException,
			UnsupportedEncodingException {

		return (str == null) ? "" : new String(str.getBytes("GBK"), "utf-8");
	}

	/**
	 * 转换字符串编码：ISO-8859-1 ---> utf-8
	 * 
	 * @param (String) str --需转换的字符串
	 * @return （String） --转换后的字符串
	 */
	public final static String convertISOToUTF(String str) throws IOException,
			UnsupportedEncodingException {

		return (str == null) ? "" : new String(str.getBytes("ISO-8859-1"), "utf-8");
	}

	/**
	 * 解析URL字符串
	 * 
	 * @param str
	 *            源字符串
	 * @param enc
	 *            解析编码
	 * @return 解析后的字符串
	 * @throws UnsupportedEncodingException
	 *             不可加密例外
	 */
	public final static String decodeUrl(String str, String enc)
			throws UnsupportedEncodingException {
		return URLDecoder.decode(str, enc);
	}

	/**
	 * 解析URL字符串，默认utf-8
	 * 
	 * @param str
	 *            源字符串
	 * @return 解析后的字符串
	 * @throws UnsupportedEncodingException
	 *             不可加密例外
	 */
	public final static String decodeUrl(String str) throws UnsupportedEncodingException {
		return decodeUrl(str, "utf-8");
	}

	/**
	 * 将加密为url字符串
	 * 
	 * @param str
	 *            源字符串
	 * @param enc
	 *            加密编码
	 * @return 解析后的字符串
	 * @throws UnsupportedEncodingException
	 *             不可加密例外
	 */
	public final static String encodeUrl(String str, String enc)
			throws UnsupportedEncodingException {
		return URLEncoder.encode(str, enc);
	}

	/**
	 * 将加密为url字符串，默认使用utf-8
	 * 
	 * @param str
	 *            源字符串
	 * @return 解析后的字符串
	 * @throws UnsupportedEncodingException
	 *             不可加密例外
	 */
	public final static String encodeUrl(String str) throws UnsupportedEncodingException {
		return URLEncoder.encode(str, "utf-8");
	}

	/**
	 * 分析两层分隔符的字符串对象，生成Map
	 * 
	 * @param sourceString
	 *            源字符串
	 * @param lowerDivisionChar
	 *            低维度分隔符
	 * @param higherDivisionChar
	 *            高维度分隔符
	 * @return 生成的结果集对象
	 */
	public static Map<String, String> analyseSquareString(String sourceString, String lowerDivisionChar,
			String higherDivisionChar) {
		HashMap<String, String> hm = new HashMap<String, String>();
		String[] temp = sourceString.split(higherDivisionChar);
		for (int i = 0; i < temp.length; i++) {
			if (!BasicUtils.isTrimBlank(temp[i])) {
				String[] obj = temp[i].split(lowerDivisionChar);
				hm.put(obj[0], obj[1]);
			}
		}
		return hm;
	}

	/**
	 * 大写首字母，其余小写
	 * 
	 * @param str
	 *            需要转换的字符串
	 * @return 转换后的字符串
	 */
	public static String upperFirstChar(String str) {
		return upperFirstChar(str, true);
	}

	/**
	 * 大写首字母
	 * 
	 * @param str
	 *            需要转换的字符串
	 * @return 转换后的字符串
	 */
	public static String upperFirstChar(String str, boolean changeOther) {
		if (str == null || str.equals("")) {
			return str;
		} else {
			StringBuilder result = new StringBuilder(str.substring(0, 1).toUpperCase());
			if (changeOther) {
				result.append(str.substring(1).toLowerCase());
			} else {
				result.append(str.substring(1));
			}
			return result.toString();
		}
	}

	/**
	 * 小写首字母,其余也小写
	 * 
	 * @param str
	 *            需要转换的字符串
	 * @return 转换后的字符串
	 */
	public static String lowerFirstChar(String str) {
		return lowerFirstChar(str, true);
	}

	/**
	 * 小写首字母
	 * 
	 * @param str
	 *            需要转换的字符串
	 * @param changeOther
	 *            其余字符是否要转换为小写
	 * @return 转换后的字符串
	 */
	public static String lowerFirstChar(String str, boolean changeOther) {
		if (str == null || str.equals("")) {
			return str;
		} else {
			StringBuilder result = new StringBuilder(str.substring(0, 1).toLowerCase());
			if (changeOther) {
				result.append(str.substring(1).toLowerCase());
			} else {
				result.append(str.substring(1));
			}
			return result.toString();
		}
	}

	/**
	 * 转换对象为字符串，空对象转换为空字符串
	 * 
	 * @param source
	 *            要转换的对象
	 * @return 清理后的字符串
	 */
	public static String cleanUp(Object source) {
		if (source != null)
			return String.valueOf(source);
		else
			return "";
	}

	/**
	 * 将字符串中的多个空格保留一个
	 * 
	 * @param source
	 *            源字符串
	 * @return 转换后的字符串
	 */
	public static String cleanSpace(String source) {
		return source.replaceAll("\\s+", " ");
	}

	/**
	 * unicode 转换成 utf-8
	 * 
	 * @param theString
	 * @return
	 */
	public static String unicodeToUtf8(String theString) {
		char aChar;
		int len = theString.length();
		StringBuffer outBuffer = new StringBuffer(len);
		for (int x = 0; x < len;) {
			aChar = theString.charAt(x++);
			if (aChar == '\\') {
				aChar = theString.charAt(x++);
				if (aChar == 'u') {
					// Read the xxxx
					int value = 0;
					for (int i = 0; i < 4; i++) {
						aChar = theString.charAt(x++);
						switch (aChar) {
						case '0':
						case '1':
						case '2':
						case '3':
						case '4':
						case '5':
						case '6':
						case '7':
						case '8':
						case '9':
							value = (value << 4) + aChar - '0';
							break;
						case 'a':
						case 'b':
						case 'c':
						case 'd':
						case 'e':
						case 'f':
							value = (value << 4) + 10 + aChar - 'a';
							break;
						case 'A':
						case 'B':
						case 'C':
						case 'D':
						case 'E':
						case 'F':
							value = (value << 4) + 10 + aChar - 'A';
							break;
						default:
							throw new IllegalArgumentException("Malformed   \\uxxxx   encoding.");
						}
					}
					outBuffer.append((char) value);
				} else {
					if (aChar == 't')
						aChar = '\t';
					else if (aChar == 'r')
						aChar = '\r';
					else if (aChar == 'n')
						aChar = '\n';
					else if (aChar == 'f')
						aChar = '\f';
					outBuffer.append(aChar);
				}
			} else
				outBuffer.append(aChar);
		}
		return outBuffer.toString();
	}

	/**
	 * 将字符串转成unicode
	 * 
	 * @param str
	 *            待转字符串
	 * @return unicode字符串
	 */
	public static String convertToUnicode(String str) {
		str = (str == null ? "" : str);
		String tmp;
		StringBuffer sb = new StringBuffer(1000);
		char c;
		int i, j;
		sb.setLength(0);
		for (i = 0; i < str.length(); i++) {
			c = str.charAt(i);
			sb.append("\\u");
			j = (c >>> 8); // 取出高8位
			tmp = Integer.toHexString(j);
			if (tmp.length() == 1)
				sb.append("0");
			sb.append(tmp);
			j = (c & 0xFF); // 取出低8位
			tmp = Integer.toHexString(j);
			if (tmp.length() == 1)
				sb.append("0");
			sb.append(tmp);

		}
		return (new String(sb));
	}

	/**
	 * 去掉成对符号的标签<br>
	 * 通常用于去掉html代码<br>
	 * 去掉中括号中间的代码等
	 * 
	 * @param source
	 *            源字符串
	 * @param startChar
	 *            开始字符
	 * @param endChar
	 *            结束字符
	 * @return 去掉Html标签后的字符串
	 */
	public static String removeBlock(String source, String startChar, String endChar) {
		String resultString = source;
		String headString;
		while (resultString.indexOf(startChar) >= 0) {
			headString = resultString.substring(0, resultString.indexOf(startChar));
			resultString = resultString.substring(resultString.indexOf(endChar) + 1);
			resultString = headString + resultString;
		}
		return resultString;
	}

	/**
	 * 判断字符串是否含有中文
	 * 
	 * @param source
	 *            要判断的字符串
	 * @return 源字符串是否含有中文
	 */
	public static boolean containsChinese(String source) {
		Pattern pattern = Pattern.compile(".*[\\u4e00-\\u9fa5].*");
		Matcher matcher = pattern.matcher(source);
		return matcher.matches();
	}

}