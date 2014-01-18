/**
 * 
 */
package com.marswork.fulltext.componentbase.config;

import com.marswork.core.configwizard.ConfigItemManifest;
import com.marswork.core.configwizard.MarsWorkConfig;
import com.marswork.core.exceptions.config.PropertiesNotFoundException;

/**
 * <p>
 * 全文检索组件配置向导
 * <p>
 * 使用向导可以快速获取需要的配置信息
 * 
 * @author MarsDJ
 * @since 2012-2-8
 * @version 1.0
 */
public class FullTextConfig extends MarsWorkConfig {

	public static String getDefaultExtdDict() {
		try {
			return FullTextConfig.getConfig(ConfigItemManifest.COM_MARSWORK_FULLTEXT_EXTDICT);
		} catch (PropertiesNotFoundException e) {
			return "";
		}
	}

	public static String getDefaultExtStopWord() {
		try {
			return FullTextConfig.getConfig(ConfigItemManifest.COM_MARSWORK_FULLTEXT_EXTSTOPWORD);
		} catch (PropertiesNotFoundException e) {
			return "";
		}
	}

	public static String getDefaultLuceneIndex() {
		try {
			return FullTextConfig.getConfig(ConfigItemManifest.COM_MARSWORK_FULLTEXT_LUCENE_INDEX);
		} catch (PropertiesNotFoundException e) {
			return "";
		}
	}
}
