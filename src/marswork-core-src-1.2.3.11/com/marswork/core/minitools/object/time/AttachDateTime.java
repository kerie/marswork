package com.marswork.core.minitools.object.time;

import com.marswork.core.minitools.object.BasicUtils;

/**
 * 
 * <p>
 * 数据库时间处理
 * <p>
 * 对从数据库中取出的时间进行处理<br>
 * 是指能转换为java中的时间
 * 
 * @author MarsDJ
 * @since 2011-10-15
 * @version 1.0
 */
public class AttachDateTime {

	/**
	 * 对从数据库中取出的时间进行处理<br>
	 * 是指能转换为java中的时间
	 * 
	 * @param value
	 *            需要处理的时间
	 * @return 处理过的时间
	 */
	public static String attachDateTime(String value) {
		if (!BasicUtils.isTrimBlank(value)) {
			final String defaultValue = "1970-01-01 00:00:00";

			if (value.length() < 19) {
				value += defaultValue.substring(value.length());
			}
			value = value.substring(0, 19);
		}
		return value;
	}

}
