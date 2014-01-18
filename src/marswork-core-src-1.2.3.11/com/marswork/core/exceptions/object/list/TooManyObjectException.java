package com.marswork.core.exceptions.object.list;

import com.marswork.core.exceptions.messaging.MarsException;

/**
 * 
 * <p>
 * 对象过多例外
 * <p>
 * 通常用于判断List中元素数量<br>
 * 要求一定存在的元素不多于某值<br>
 * 而元素数量大于等于这个值可以抛此例外
 * 
 * @author MarsDJ
 * @since 2011-8-7
 * @version 1.0
 */
public class TooManyObjectException extends MarsException {

	private static final long serialVersionUID = 2735960487740578317L;

	/**
	 * 构造函数<br>
	 * 满足条件的对象过多
	 */
	public TooManyObjectException() {
		super(1005);
	}

	/**
	 * 构造函数<br>
	 * 指定ID为 id 的对象过多
	 * 
	 * @param id
	 *            指定的ID
	 */
	public TooManyObjectException(String id) {
		super(1006, id);
	}

	/**
	 * 构造函数<br>
	 * 指定ID为 id 的 type 对象过多
	 * 
	 * @param id
	 *            指定的id
	 * @param type
	 *            对象的类型
	 */
	public TooManyObjectException(String id, String type) {
		super(1007, id, type);
	}

	/**
	 * 构造函数<br>
	 * 指定 condition 为 value 的 type 对象过多
	 * 
	 * @param condition
	 *            指定的条件
	 * @param value
	 *            条件的值
	 * @param type
	 *            对象的类型
	 */
	public TooManyObjectException(String condition, String value, String type) {
		super(1008, condition, value, type);
	}

	/**
	 * 构造函数<br>
	 * 指定 condition 为 value 的 type 对象过多
	 * 
	 * @param condition
	 *            指定的条件
	 * @param value
	 *            条件的值
	 * @param type
	 *            对象的类型
	 * @param limit
	 *            对象的限制数
	 */
	public TooManyObjectException(String condition, String value, String type,
			int limit) {
		super(1009, condition, value, type, limit);
	}
}
