/**
 * 
 */
package com.marswork.core.exceptions.object.method;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.marswork.core.exceptions.messaging.MarsException;

/**
 * <p>
 * <p>
 * 
 * @author MarsDJ
 * @since 2012-3-16
 * @version 1.0
 */
public class MethodInvokeException extends MarsException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8767616783771331918L;

	/**
	 * @param exceptionCode
	 * @param params
	 */
	public MethodInvokeException(Object value, IllegalArgumentException e) {
		super(1014, value);
	}

	/**
	 * @param exceptionCode
	 * @param params
	 */
	public MethodInvokeException(Method value, IllegalAccessException e) {
		super(1015, value);
	}

	/**
	 * @param exceptionCode
	 * @param params
	 */
	public MethodInvokeException(Method value, InvocationTargetException e) {
		super(1016, value);
	}

}
