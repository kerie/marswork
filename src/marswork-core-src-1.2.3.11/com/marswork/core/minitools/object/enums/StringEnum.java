/**
 * 
 */
package com.marswork.core.minitools.object.enums;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.marswork.core.exceptions.object.string.InvalidFieldException;
import com.marswork.core.minitools.object.BaseString;

/**
 * <p>
 * <p>
 * 
 * @author MarsDJ
 * @since 2012-3-29
 * @version 1.0
 */
public class StringEnum implements I_DataEnum {

	public static <T extends StringEnum> String reflect(Class<T> class_, String field)
			throws InvalidFieldException {
		try {
			return String.valueOf(class_.getDeclaredField(field).get(null));
		} catch (Exception e) {
			throw new InvalidFieldException(class_, field);
		}
	}

	public static <T extends StringEnum> List<Map<String, String>> getMapdata(Class<T> class_) {
		List<Map<String, String>> resultList = new ArrayList<Map<String, String>>();
		try {
			Field[] enums = class_.getDeclaredFields();
			for (int i = 0; i < enums.length; i++) {
				HashMap<String, String> radioMap = new HashMap<String, String>();
				radioMap.put("name", enums[i].getName());
				radioMap.put("value", BaseString.cleanUp(enums[i].get(null)));
				resultList.add(radioMap);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultList;
	}
}
