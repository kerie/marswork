package com.marswork.core.exceptions.object.datetime;

import com.marswork.core.exceptions.messaging.MarsException;

/**
 * <p>
 * 指定字符串不能转换为时间
 * <p>
 * 通常在时间字符串转换时使用
 * 
 * @author MarsDJ
 * @since 2011-11-21
 * @version 1.0
 */
public class NotDateTimeException extends MarsException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7661543460002705297L;

	/**
	 * 构造函数
	 * 
	 * @param exceptionString
	 *            例外消息
	 */
	public NotDateTimeException(String exceptionString) {
		super(1010, exceptionString);
	}
}
