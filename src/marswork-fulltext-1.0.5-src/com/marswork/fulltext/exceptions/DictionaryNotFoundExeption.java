/**
 * 
 */
package com.marswork.fulltext.exceptions;

import com.marswork.core.exceptions.messaging.MarsException;

/**
 * <p>
 * 无效的字典文件
 * <p>
 * 指定的字典文件没有找到，将抛出此例外
 * 
 * @author MarsDJ
 * @since 2012-1-4
 * @version 1.0
 */
public class DictionaryNotFoundExeption extends MarsException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1441733667819449908L;

	public DictionaryNotFoundExeption(String fileName) {
		super(1100, fileName);
	}
}
