package com.marswork.core.minitools.object;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import com.marswork.core.datastructure.uniquelist.UniqueList;

/**
 * <p>
 * 有关数组的基础操作
 * 
 * @author MarsDJ
 * @since 2010-12-31
 * @version 1.0
 */
public class BaseCollection {

	private BaseCollection() {
	}

	/**
	 * 数组中是否包含某个孩子
	 * 
	 * @param array
	 *            源数组
	 * @param child
	 *            要判断的对象
	 * @return 是否包含
	 */
	public static boolean hasChild(Object[] array, Object child) {
		for (int i = 0; i < array.length; i++) {
			if (array[i].equals(child)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 数组中是否包含某个与判断对象相似的孩子
	 * 
	 * @param array
	 *            源数组
	 * @param child
	 *            要判断的对象
	 * @return 是否包含
	 */
	public static boolean hasChildIgnoreCase(String[] array, String child) {
		for (int i = 0; i < array.length; i++) {
			if (array[i].equalsIgnoreCase(child)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 数组中是否包含某个与判断对象相似的孩子
	 * 
	 * @param array
	 *            源数组
	 * @param child
	 *            要判断的对象
	 * @return 是否包含
	 */
	public static boolean hasChildLike(String[] array, String child) {
		for (int i = 0; i < array.length; i++) {
			if (array[i].contains(child)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * list中是否包含某个与判断对象相似的孩子
	 * 
	 * @param array
	 *            源数组
	 * @param child
	 *            要判断的对象
	 * @return 是否包含
	 */
	public static boolean hasChildLike(List<String> array, String child) {
		for (int i = 0; i < array.size(); i++) {
			if (array.get(i).contains(child)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 移除所有child
	 * 
	 * @param list
	 *            目标list
	 */
	public static void removeAll(List<?> list) {
		int index = list.size();
		for (int i = 0; i < index; i++) {
			list.remove(0);
		}
	}

	/**
	 * 将列表转换为数组
	 * 
	 * @param list
	 *            需要转换的列表
	 * @return 转换后的数组
	 */
	public static Object[] list2Array(List<?> list) {
		if (list != null) {
			return list.toArray();
		} else {
			return new Object[0];
		}
	}

	/**
	 * 将数组转换为列表
	 * 
	 * @param objArray
	 *            需要转换的数组
	 * @return 转换后的列表
	 */
	public static <T> List<T> array2List(T... objArray) {
		return Arrays.asList(objArray);
	}

	/**
	 * 将字符串数组转换为字符串<br>
	 * 字符串之间没有间隔符
	 * 
	 * @param source
	 *            字符串数组
	 * @return 结果字符串
	 */
	public static String listString(List<String> source) {
		return listString(source, "");
	}

	/**
	 * 将字符串数组转换为字符串<br>
	 * 字符串之间有指定的间隔符
	 * 
	 * @param source
	 *            字符串数组
	 * @param splitChar
	 *            指定的间隔符
	 * @return 结果字符串
	 */
	public static String listString(List<String> source, String splitChar) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < source.size(); i++) {
			if (i != 0) {
				sb.append(splitChar);
			}
			sb.append(source.get(i));
		}
		return sb.toString();
	}

	/**
	 * 将字符串数组转换为字符串
	 * 
	 * @param charSet
	 *            字符驻足
	 * @return
	 */
	public static String charsetString(char[] charSet) {
		String result = "";
		for (int i = 0; i < charSet.length; i++) {
			result += charSet[i];
		}
		return result;
	}

	/**
	 * 将对象数组转换为字符串数组
	 * 
	 * @param source
	 *            需要转换的对象数组
	 * @return 字符串数组
	 */
	public static String[] objectArrToStringArr(Object[] source) {
		String[] temp = new String[source.length];
		for (int i = 0; i < source.length; i++) {
			temp[i] = String.valueOf(source[i]);
		}
		return temp;
	}

	/**
	 * 合并两个数据
	 * 
	 * @param src
	 *            源数组
	 * @param des
	 *            目标数组
	 * @return 合并后的新数组
	 */
	public static Object[] arrayCombine(Object[] src, Object[] des) {
		Object[] newArray = new Object[src.length + des.length];
		System.arraycopy(src, 0, newArray, 0, src.length);
		System.arraycopy(des, 0, newArray, src.length, des.length);
		return newArray;
	}

	/**
	 * 将对象集合元素单一化
	 * 
	 * @param srouce
	 *            目标对象集合
	 * @return 元素单一化后的集合
	 */
	public static <T> List<T> distinct(List<T> srouce) {
		UniqueList<T> temp = new UniqueList<T>();
		for (T unit : srouce) {
			temp.add(unit);
		}
		return temp;
	}

	/**
	 * 根据指定的域将对象集合进行单一话
	 * <p>
	 * 如果对象集合中的某个对象在指定域为空值<br>
	 * 则该对象会被排除在结果集外
	 * 
	 * @param srouce
	 *            目标对象集合
	 * @param distinctField
	 *            指定的域
	 * @return 元素单一化后的集合
	 */
	public static <T> List<T> distinct(List<T> srouce, String distinctField) {
		ArrayList<T> temp = new ArrayList<T>();
		for (T srouceUnit : srouce) {
			boolean isExsist = false;
			for (T tempUnit : temp) {
				try {
					if (BeanUtils.getProperty(tempUnit, distinctField).equals(
							BeanUtils.getProperty(srouceUnit, distinctField))) {
						isExsist = true;
						break;
					}
				} catch (Exception e) {
					isExsist = true;
					break;
				}
			}
			if (!isExsist) {
				temp.add(srouceUnit);
			}
		}
		return temp;
	}
}
