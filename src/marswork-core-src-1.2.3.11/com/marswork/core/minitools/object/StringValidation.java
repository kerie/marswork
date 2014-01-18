/**
 * 
 */
package com.marswork.core.minitools.object;

/**
 * <p>
 * 特殊字符串校验
 * <p>
 * 用于指定类型的字符串校验<br>
 * 如电子邮箱，邮编等
 * 
 * @author MarsDJ
 * @since 2011-12-7
 * @version 1.0
 */
public class StringValidation {

	/**
	 * 校验电子邮箱地址 (空串不校验)
	 * 
	 * @param (String)str 电子邮件地址
	 * @return (boolean) true --正确 false --不正确
	 * 
	 */
	public static final boolean isEmail(String str) {
		if (str.length() < 1) {
			return true;
		}
		int Ihoutou = str.indexOf('@');
		int Ipoint = str.lastIndexOf('.');
		int Elength = str.trim().length();

		if ((Ihoutou == -1) || (Ihoutou == 0) || (Ipoint == -1)
				|| (Ipoint == Elength)) {
			return false;
		}
		return true;
	}

	/**
	 * 校验邮政编码(空串不校验)
	 * 
	 * @param str
	 *            邮政编码
	 * @return boolean true --正确 false --不正确
	 * 
	 */
	public static final boolean isPostCode(String str) {
		if (str.length() < 1) {
			return true;
		}
		if (str.length() != 6) {
			return false;
		}
		try {
			Double.parseDouble(str);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	/**
	 * 校验邮政编码
	 * 
	 * @param str
	 *            邮政编码
	 * @param (int) ifempty 是否允许为空 0--允许 1--不允许
	 * @return boolean true --正确 false --不正确
	 * 
	 */
	public static final boolean isPostCode(String str, int ifempty) {
		if (str.length() == 0)
			return (ifempty == 0) ? true : false;
		return isPostCode(str);
	}

}
