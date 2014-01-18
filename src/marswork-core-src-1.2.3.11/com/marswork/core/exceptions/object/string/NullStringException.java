package com.marswork.core.exceptions.object.string;

import com.marswork.core.exceptions.messaging.MarsException;

/**
 * 
 * <p>
 * 空字符串例外
 * <p>
 * 源字符串被判定为空字符串所抛出的例外
 * 
 * @author MarsDJ
 * @since 2011-9-4
 * @version 1.0
 */
public class NullStringException extends MarsException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2725960487840576317L;

	public NullStringException() {
		super(1012);
	}

}
