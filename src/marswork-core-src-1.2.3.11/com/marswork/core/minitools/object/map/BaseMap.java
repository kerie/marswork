/**
 * 
 */
package com.marswork.core.minitools.object.map;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import com.marswork.core.exceptions.messaging.MarsException;
import com.marswork.core.minitools.object.BaseString;

/**
 * <p>
 * Map操作类
 * <p>
 * 针对Map的一些操作
 * 
 * @author MarsDJ
 * @since 2011-9-4
 * @version 1.0
 */
public class BaseMap {

	private BaseMap() {
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
	public static <T, K> List<String> getMapField(List<Map<T, K>> mapList, String key) {
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

	/**
	 * 判断源列表中某个域是否有某个值
	 * 
	 * @param mapList
	 *            源列表
	 * @param key
	 *            指定判断某个域
	 * @param value
	 *            判断某个域是否存在该值
	 * @return 是否存在
	 */
	public static <T, K> boolean hasField(List<Map<T, K>> mapList, String key, Object value) {
		for (int i = 0; i < mapList.size(); i++) {
			if (mapList.get(i).get(key).equals(value)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 连接两个map
	 * 
	 * @param targetMap
	 *            连接目标数据
	 * @param sourceMap
	 *            连接源数据
	 * @param bridge
	 *            连接桥
	 * @return 返回连接后的数据
	 * @throws MarsException
	 *             消息例外
	 */
	public static <T, K> List<Map<T, K>> combineMaps(List<Map<T, K>> targetMap,
			List<Map<T, K>> sourceMap, String bridge) throws Exception {
		try {
			if (targetMap != null) {
				for (int i = 0; i < targetMap.size(); i++) {
					for (int j = 0; j < sourceMap.size(); j++) {
						if (targetMap.get(i).get(bridge).equals(sourceMap.get(j).get(bridge))) {
							targetMap.get(i).putAll(sourceMap.get(j));
							break;
						}
					}
				}
				return targetMap;
			} else {
				return sourceMap;
			}
		} catch (NullPointerException e) {
			throw new Exception("连接Map的桥无效");
		}
	}

	/**
	 * 连接两个map
	 * 
	 * @param targetMap
	 *            连接目标数据
	 * @param sourceMap
	 *            连接源数据
	 * @param targetBridge
	 *            连接目标数据的桥头
	 * @param sourceBridge
	 *            连接源数据的桥头
	 * @return 返回连接后的数据
	 * @throws MarsException
	 *             消息例外
	 */
	public static <T, K> List<Map<T, K>> combineMaps(List<Map<T, K>> targetMap,
			List<Map<T, K>> sourceMap, String targetBridge, String sourceBridge) throws Exception {
		try {
			if (targetMap != null) {
				for (int i = 0; i < targetMap.size(); i++) {
					for (int j = 0; j < sourceMap.size(); j++) {
						if (targetMap.get(i).get(targetBridge)
								.equals(sourceMap.get(j).get(sourceBridge))) {
							targetMap.get(i).putAll(sourceMap.get(j));
							break;
						}
					}
				}
				return targetMap;
			} else {
				return sourceMap;
			}
		} catch (NullPointerException e) {
			throw new Exception("连接Map的桥无效");
		}
	}

	/**
	 * 遍历Map对象，获取这个对象的键值对列表
	 * 
	 * @param source
	 *            需要遍历的Map对象
	 * @return 源Map对象的键值列表
	 */
	public static <T, K> List<MapKeyValue<T, K>> walkMap(Map<T, K> source) {
		List<MapKeyValue<T, K>> reslut = new ArrayList<MapKeyValue<T, K>>();
		Iterator<Entry<T, K>> it = source.entrySet().iterator();
		while (it.hasNext()) {
			Entry<T, K> entry = (Entry<T, K>) it.next();
			reslut.add(new MapKeyValue<T, K>(entry.getKey(), entry.getValue()));
		}
		return reslut;
	}

	/**
	 * 将Properties对象转换为Map对象
	 * 
	 * @param properties
	 *            需要转换的Properties对象
	 * @return 生成的Map对象
	 */
	public static Map<Object, Object> parseMap(Properties properties) {
		Map<Object, Object> result = new HashMap<Object, Object>();
		Iterator<Entry<Object, Object>> it = properties.entrySet().iterator();
		while (it.hasNext()) {
			Entry<Object, Object> entry = (Entry<Object, Object>) it.next();

			result.put(entry.getKey(),
					BaseString.unicodeToUtf8(BaseString.cleanUp(entry.getValue())));
		}
		return result;
	}

	/**
	 * 判断原Map列表对象是否被选中<br>
	 * 通常用于checkbox的选中之类的应用<br>
	 * <p>
	 * 例如原列表中是所有学生的信息<br>
	 * 目标列表中是已提交作业的学生的信息<br>
	 * 则产生一个Map列表对象<br>
	 * 这个对象中包含所有学生的ID和交作业的情况
	 * 
	 * @param source
	 *            原列表
	 * @param target
	 *            目标列表
	 * @param sourceIdFieldName
	 *            源列表中用于唯一标识的字段名
	 * @param targetIdFieldName
	 *            目标列表中用于唯一标识的字段名
	 * @return 含有唯一标志字段和是否选中字段的Map列表对象
	 */
	public static List<Map<String, String>> selectedId(List<Map<String, String>> source,
			List<String> target, String sourceIdFieldName, String targetIdFieldName) {
		for (Map<String, String> temp : source) {
			temp.put("selected", "false");
			for (String temp2 : target) {
				if (temp2.equals(temp.get(sourceIdFieldName))) {
					temp.put("selected", "true");
				}
			}
		}
		return source;
	}
}
