package com.marswork.core.common.data;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

/**
 * <p>
 * bean转换类
 * <p>
 * 处理bean和map之间的转换<br>
 * 也可以从map中去一列值
 * 
 * @author MarsDJ
 * @since 2010-9-11
 * @version 1.0
 */
public class PojoConverter {

	/**
	 * 将pojo对象转换为map对象<br>
	 * 默认不转换内部对象
	 * 
	 * @param pojo
	 *            简单java对象
	 * 
	 * @return 该对象的可访问属性（有getter方法的属性）
	 */
	public static Map<String, String> parsePOJO(Object pojo) {
		return parsePOJO(pojo, false);
	}

	/**
	 * 将pojo对象转换为map对象
	 * 
	 * @param pojo
	 *            简单java对象
	 * @param parseInnerBean
	 *            如果对象是MarsBean，且有内部对象，指定是否转换内部对像
	 * @return 该对象的可访问属性（有getter方法的属性）
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, String> parsePOJO(Object pojo, boolean parseInnerBean) {
		if (pojo instanceof Map)
			return (Map<String, String>) pojo;
		Map<String, String> hashMap = new HashMap<String, String>();
		try {
			Class<?> c = pojo.getClass();

			Method m[] = c.getDeclaredMethods();
			for (int i = 0; i < m.length; i++) {
				if (m[i].getName().indexOf("get") == 0) {
					if (parseInnerBean == false && pojo instanceof MarsBean
							&& (m[i].getName().contains("For") || m[i].getName().contains("By"))) {
						continue;
					}
					try {
						String key = m[i].getName().substring(4);
						key = m[i].getName().substring(3, 4).toLowerCase() + key;
						hashMap.put(key, (m[i].invoke(pojo, new Object[0])).toString());
					} catch (Throwable e) {
						continue;
					}
				}
			}
		} catch (Throwable e) {
			return null;
		}
		return hashMap;
	}

	/**
	 * 将一个pojo列表制作成map列表<br>
	 * 默认不转换内部对象
	 * 
	 * @param pojoList
	 *            简单java对象组成的list
	 * 
	 * @return 该对象的可访问属性（有getter方法的属性）集合
	 */
	public static List<Map<String, String>> parsePOJOList(List<?> pojoList) {
		return parsePOJOList(pojoList, false);
	}

	/**
	 * 将一个pojo列表制作成map列表
	 * 
	 * @param pojoList
	 *            简单java对象组成的list
	 * @param parseInnerBean
	 *            如果对象是MarsBean，且有内部对象，指定是否转换内部对像
	 * @return 该对象的可访问属性（有getter方法的属性）集合
	 */
	public static List<Map<String, String>> parsePOJOList(List<?> pojoList, boolean parseInnerBean) {
		try {
			List<Map<String, String>> lm = new ArrayList<Map<String, String>>();
			for (int i = 0; i < pojoList.size(); i++) {
				lm.add(parsePOJO(pojoList.get(i), parseInnerBean));
			}
			return lm;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 获取一个pojo列表的某个属性集合
	 * 
	 * @param pojo
	 *            简单java对象
	 * @param key
	 *            该对象的某个属性名
	 * @return 该对象的某个属性集合
	 */
	public static <T> List<String> getField(List<T> pojoList, String key) {
		List<String> fieldValues = new ArrayList<String>();

		try {
			for (T pojo : pojoList) {
				fieldValues.add(BeanUtils.getProperty(pojo, key));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return fieldValues;
	}

	/**
	 * 获取一个map列表的某个属性集合
	 * 
	 * @param mapList
	 *            属性集合列表
	 * @param key
	 *            某个属性名
	 * @return 这个属性名对应的某个属性集合
	 */
	public static List<String> getMapField(List<Map<?, ?>> mapList, String key) {
		List<String> filed = new ArrayList<String>();
		for (int i = 0; i < mapList.size(); i++) {
			try {
				filed.add(mapList.get(i).get(key).toString());
			} catch (NullPointerException e) {
				continue;
			}
		}
		return filed;
	}
}
