/**
 * 
 */
package com.marswork.core.exceptions.object.string;

import com.marswork.core.exceptions.messaging.MarsException;

/**
 * <p>
 * <p>
 * 
 * @author MarsDJ
 * @since 2012-3-29
 * @version 1.0
 */
public class InvalidFieldException extends MarsException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -781578701457207589L;

	/**
	 * @param exceptionCode
	 * @param params
	 */
	public InvalidFieldException(Class<?> class_, String name) {
		super(1017, class_, name);
	}

}
