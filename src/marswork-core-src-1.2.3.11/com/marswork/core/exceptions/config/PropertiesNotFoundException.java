/**
 * 
 */
package com.marswork.core.exceptions.config;

import com.marswork.core.exceptions.messaging.MarsException;

/**
 * <p>
 * <p>
 * 
 * @author MarsDJ
 * @since 2012-3-16
 * @version 1.0
 */
public class PropertiesNotFoundException extends MarsException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1179604612755993024L;

	public static final String PROPERTIES_MAIN = "marswork.properties";

	/**
	 * @param exceptionCode
	 * @param params
	 */
	public PropertiesNotFoundException(String properties) {
		super(100, properties);
	}

}
