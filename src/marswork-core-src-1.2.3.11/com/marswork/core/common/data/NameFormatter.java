package com.marswork.core.common.data;

import com.marswork.core.exceptions.object.string.IllegalNameException;
import com.marswork.core.minitools.object.BaseCollection;
import com.marswork.core.minitools.object.BaseString;
import com.marswork.core.minitools.object.BasicUtils;

/**
 * 
 * <p>
 * 数据库类名转换器
 * <p>
 * 数据库中的表名，列名<br>
 * 转换为标准匈牙利命名的类名和属性名<br>
 * 同时支持逆向操作<br>
 * 即将类名和属性名转换为数据库中的表名，列名
 * 
 * @author MarsDJ
 * @since 2011-4-16
 * @version 1.0
 */
public class NameFormatter {

	private static String[] keywords = { "package", "import", "public",
			"class", "private", "static", "final", "int", "throws", "throw",
			"do", "if", "try", "catch", "finally", "while", "for", "float",
			"double", "protected", "this", "super" };

	private NameFormatter() {
	}

	/**
	 * 转换成类名
	 * 
	 * @param source
	 *            源字符串
	 * @return 类名字符串
	 * @throws IllegalNameException
	 *             非法名称
	 */
	public static String formatClassName(String source)
			throws IllegalNameException {
		return format(source);
	}

	/**
	 * 转换成方法名或参数名
	 * 
	 * @param source
	 *            源字符串
	 * @return 方法名或参数名字符串
	 * @throws IllegalNameException
	 *             非法名称
	 */
	public static String formatMethodName(String source)
			throws IllegalNameException {
		return BaseString.lowerFirstChar(formatClassName(source), false);
	}

	private static String format(String source) throws IllegalNameException {
		try {
			StringBuffer result = new StringBuffer();
			if (!BasicUtils.isTrimBlank(source)
					&& source.matches("[a-zA-Z0-9_]+")) {
				String[] temp = source.split("_");

				for (int i = 0; i < temp.length; i++) {
					result.append(BaseString.upperFirstChar(temp[i]));
				}

				if (BaseCollection.hasChildIgnoreCase(keywords,
						result.toString())) {
					result.append("_");
				}

				return result.toString();
			}
		} catch (Exception e) {
		}
		throw new IllegalNameException(source);
	}

	/**
	 * 将类名转换成表名，属性名方法名转换为列名
	 * 
	 * @param source
	 *            需要转换的字符
	 * @return 转换后的字符
	 */
	public static String reverse(String source) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < source.length(); i++) {
			if (65 <= source.charAt(i) && source.charAt(i) <= 90) {
				if (i != 0)
					sb.append("_");
				sb.append(source.charAt(i));
			} else if (97 <= source.charAt(i) && source.charAt(i) <= 122) {
				sb.append((char) (source.charAt(i) - 32));
			} else {
				sb.append(source.charAt(i));
			}
		}
		return sb.toString();
	}
}