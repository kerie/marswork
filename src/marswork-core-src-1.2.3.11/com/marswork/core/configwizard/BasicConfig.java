/**
 * 
 */
package com.marswork.core.configwizard;

import com.marswork.core.exceptions.config.PropertiesNotFoundException;

/**
 * <p>
 * <p>
 * 
 * @author MarsDJ
 * @since 2012-5-17
 * @version 1.0
 */
public class BasicConfig extends MarsWorkConfig {

	public static String getDefaultServerIp() {
		try {
			return BasicConfig.getConfig(ConfigItemManifest.COM_MARSWORK_SERVER_SERVERACCESS);
		} catch (PropertiesNotFoundException e) {
			return "";
		}
	}
}
