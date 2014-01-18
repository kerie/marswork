package com.marswork.core.exceptions.object.list;

import com.marswork.core.exceptions.messaging.MarsException;

/**
 * 
 * <p>
 * 没有这个对象例外
 * <p>
 * 通常用于判断List中元素数量<br>
 * 要求一定存在元素而不存在元素的对表可以抛此例外
 * 
 * @author MarsDJ
 * @since 2011-8-7
 * @version 1.0
 */
public class NoSuchObjectException extends MarsException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4399673199079631533L;

	/**
	 * 构造函数<br>
	 * 没有找到指定的对象
	 * 
	 */
	public NoSuchObjectException() {
		super(1001);
	}

	/**
	 * 构造函数<br>
	 * 没有找到指定ID为 id 的对象
	 * 
	 * @param id
	 *            指定的ID
	 */
	public NoSuchObjectException(String id) {
		super(1002, id);
	}

	/**
	 * 构造函数<br>
	 * 没有找到指定ID为 id 的 type 对象
	 * 
	 * @param id
	 *            指定的id
	 * @param type
	 *            对象的类型
	 */
	public NoSuchObjectException(String id, String type) {
		super(1003, id, type);
	}

	/**
	 * 构造函数<br>
	 * 没有找到指定 condition 为 value 的 type 对象
	 * 
	 * @param condition
	 *            指定的条件
	 * @param value
	 *            条件的值
	 * @param type
	 *            对象的类型
	 */
	public NoSuchObjectException(String condition, String value, String type) {
		super(1004, condition, value, type);
	}

}
