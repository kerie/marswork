/**
 * 
 */
package com.marswork.core.minitools.object.enums;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.marswork.core.enums.I_Category;
import com.marswork.core.minitools.object.BaseString;

/**
 * <p>
 * <p>
 * 
 * @author MarsDJ
 * @since 2012-5-7
 * @version 1.0
 */
public class EnumUtils {

	public static List<Map<String, String>> getMapdata(Class<? extends I_Category<?>> class_) {
		List<Map<String, String>> resultList = new ArrayList<Map<String, String>>();
		try {
			Method method = class_.getMethod("values");
			Method nameMethod ; 
			I_Category<?>[] enums = (I_Category[]) method.invoke(null);
			for (int i = 0; i < enums.length; i++) {
				HashMap<String, String> radioMap = new HashMap<String, String>();
				nameMethod = enums[i].getClass().getMethod("name");
				radioMap.put("name", BaseString.cleanUp(nameMethod.invoke( enums[i])));
				radioMap.put("value", enums[i].getCategoryName());
				resultList.add(radioMap);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultList;
	}
}
