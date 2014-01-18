package com.marswork.core.exceptions.object.string;

import com.marswork.core.exceptions.messaging.MarsException;

/**
 * 
 * <p>
 * 非法名称例外
 * <p>
 * 一般用于文件名等的例外
 * 
 * @author MarsDJ
 * @since 2011-9-4
 * @version 1.0
 */
public class IllegalNameException extends MarsException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 492803522915856721L;

	/**
	 * 构造函数
	 * 
	 * @param name
	 *            名称
	 */
	public IllegalNameException(String name) {
		super(1011, name);
	}

}
