/**
 * 
 */
package com.marswork.core.configwizard;

import com.marswork.core.exceptions.config.PropertiesNotFoundException;
import com.marswork.core.minitools.object.BaseString;
import com.marswork.core.minitools.utils.EvaluateUtils;

/**
 * <p>
 * 配置向导
 * <p>
 * MarsWork框架中的所有基础配置的集合<br>
 * 程序在使用这些配置的时候<br>
 * 使用向导可以快速获取需要的配置信息
 * 
 * @author MarsDJ
 * @since 2011-9-17
 * @version 1.0
 */
public class MarsWorkConfig {

	public static String getConfig(ConfigItemManifest item)
			throws PropertiesNotFoundException {
		return item.getValue();
	}

	public static String getConfig(ConfigItemManifest item, Object fallBack)
			throws PropertiesNotFoundException {
		return EvaluateUtils.evaluateString(getConfig(item),
				BaseString.cleanUp(fallBack));
	}
}
