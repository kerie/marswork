package com.marswork.core.exceptions.messaging;

import java.util.HashMap;
import java.util.Map;

import com.marswork.core.configwizard.ConfigItemManifest;
import com.marswork.core.configwizard.MarsWorkConfig;
import com.marswork.core.exceptions.config.PropertiesNotFoundException;
import com.marswork.core.minitools.file.PropertiesOperator;
import com.marswork.core.minitools.object.BaseString;
import com.marswork.core.minitools.object.BasicUtils;
import com.marswork.core.minitools.object.map.BaseMap;

/**
 * 
 * <p>
 * 携带消息的例外
 * <p>
 * 一般用于将系统消息发送给用户
 * 
 * @author MarsDJ
 * @since 2011-9-4
 * @version 1.0
 */
public class MarsException extends Exception {

	private static final long serialVersionUID = 2725960007740576317L;

	private static Map<Object, Object> codes;

	static {
		if (codes == null) {
			codes = new HashMap<Object, Object>();
			try {
				codes = BaseMap.parseMap(PropertiesOperator.loadJarProperties("/exceptions"));
				String extCodes = MarsWorkConfig
						.getConfig(ConfigItemManifest.COM_MARSWORK_EXCEPTIONS_EXTEXCEPTIONS);
				if (!BasicUtils.isTrimBlank(extCodes)) {
					codes.putAll(BaseMap.parseMap(PropertiesOperator
							.loadClassRootProperties(extCodes)));
				}
			} catch (PropertiesNotFoundException e) {
			}
		}
	}

	private String exceptionCode;

	private Object[] params;

	private String message = "";

	/**
	 * 构造函数
	 * 
	 * @param exceptionCode
	 *            异常编号
	 * @param params
	 *            异常信息所需要的参数
	 */
	public MarsException(int exceptionCode, Object... params) {
		this.exceptionCode = String.valueOf(exceptionCode);
		this.params = params;
	}

	public MarsException(String message) {
		super(message);
		this.message = message;
	}

	@Override
	public String getMessage() {
		if (!BasicUtils.isTrimBlank(exceptionCode)) {
			return getEerroMessage(exceptionCode, params);
		} else {
			return message;
		}
	}

	@Override
	public String toString() {
		if (!BasicUtils.isTrimBlank(exceptionCode)) {
			return String.format(
					this.getClass()
							+ ": "
							+ BaseString.cleanUp(codes.get(exceptionCode)).replace("{", "%")
									.replace("}", "$s"), params).substring(6);
		} else {
			return (this.getClass() + ": " + message).substring(6);
		}
	}

	private static String getEerroMessage(String exceptionCode, Object... params) {
		return String.format(BaseString.cleanUp(codes.get(exceptionCode)).replace("{", "%")
				.replace("}", "$s"), params);
	}

	public static String getEerroMessage(int exceptionCode, Object... params) {
		return getEerroMessage(String.valueOf(exceptionCode), params);
	}
}
