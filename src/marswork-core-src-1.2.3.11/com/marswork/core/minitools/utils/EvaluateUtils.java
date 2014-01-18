/**
 * 
 */
package com.marswork.core.minitools.utils;

import com.marswork.core.minitools.object.BaseString;
import com.marswork.core.minitools.object.BasicUtils;

/**
 * <p>
 * 赋值器
 * <p>
 * 赋值器相当于在给变量赋值之前进行一些常见的判断<br>
 * 例如是否为空，若判断失败，返回默认值等等
 * 
 * @author MarsDJ
 * @since 2012-1-2
 * @version 1.0
 */
public class EvaluateUtils {

	/**
	 * 为字符串类型对象赋值
	 * 
	 * @param source
	 *            需要赋值的对象
	 * @param fallback
	 *            若需要赋值的对象为空，返回默认值
	 * @return 赋值结果<br>
	 *         为需要赋值的对象或默认值
	 */
	public static String evaluateString(Object source, Object fallback) {
		if (!BasicUtils.isTrimBlank(source)) {
			return BaseString.cleanUp(source);
		}
		return BaseString.cleanUp(fallback);
	}
}
