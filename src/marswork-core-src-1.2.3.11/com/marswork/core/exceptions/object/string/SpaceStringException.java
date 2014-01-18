package com.marswork.core.exceptions.object.string;

import com.marswork.core.exceptions.messaging.MarsException;

/**
 * 
 * <p>
 * 无有效字符字符串例外
 * <p>
 * 源字符串被判定为空字符串或全部是空格所抛出的例外
 * 
 * @author MarsDJ
 * @since 2011-9-4
 * @version 1.0
 */
public class SpaceStringException extends MarsException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2725960487840576317L;

	/**
	 * 返回错误信息
	 * 
	 * @return 错误信息
	 */
	public SpaceStringException() {
		super(1013);
	}

}
