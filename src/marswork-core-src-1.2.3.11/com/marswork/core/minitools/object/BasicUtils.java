package com.marswork.core.minitools.object;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 一些基础功能 打印
 * <p>
 * 简单的日志功能，在调试中使用
 * 
 * @author MarsDJ
 * @since 2009-4-25
 * @version 1.0
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
public class BasicUtils {

	/**
	 * 是否打印debug信息
	 */
	private static boolean debugOpened = true;

	private BasicUtils() {
	}

	/**
	 * 判断map中的某个值是否已选
	 * 
	 * @param targetMap
	 *            数据全集
	 * @param targetField
	 *            在哪个值域上进行判断
	 * @param sourceMap
	 *            List<String> 已选数据集
	 * @return 加工后的数据全集，被选中的数据中，“selected”值被标注为true
	 * @throws Exception
	 */
	public static List<Map> appendSelectedField(List<Map> targetMap, String targetField,
			List<String> sourceMap) throws Exception {
		try {
			if (targetMap != null) {
				for (int i = 0; i < targetMap.size(); i++) {
					for (int j = 0; j < sourceMap.size(); j++) {
						if (targetMap.get(i).get(targetField).equals(sourceMap.get(j))) {
							targetMap.get(i).put("selected", "true");
							break;
						}
					}
				}
			}
			return targetMap;
		} catch (NullPointerException e) {
			e.printStackTrace();
			return targetMap;
		}
	}

	/**
	 * 判断map中的某个值是否已选
	 * 
	 * @param targetMap
	 *            数据全集
	 * @param targetField
	 *            在哪个值域上进行判断
	 * @param sourceMap
	 *            List<Map> 已选数据集
	 * @return 加工后的数据全集，被选中的数据中，“selected”值被标注为true
	 * @throws Exception
	 */
	public static List<Map> appendSelectedFieldMap(List<Map> targetMap, String targetField,
			List<Map> sourceMap) throws Exception {
		try {
			if (targetMap != null) {
				for (int i = 0; i < targetMap.size(); i++) {
					for (int j = 0; j < sourceMap.size(); j++) {
						if (targetMap.get(i).get(targetField)
								.equals(sourceMap.get(j).get(targetField))) {
							targetMap.get(i).put("selected", "true");
							break;
						}
					}
				}
			}
			return targetMap;
		} catch (NullPointerException e) {
			e.printStackTrace();
			return targetMap;
		}
	}

	/**
	 * 清理从数据库中取出的时间信息
	 * 
	 * @param source
	 *            源对象
	 * @return 清理后的字符串
	 */
	public static String cleanDate(Object source) {
		if (source == null) {
			return "";
		} else if (source.toString().length() < 10) {
			return "";
		} else {
			return source.toString().substring(0, 10);
		}
	}

	/**
	 * 判断List中是否只有一个对象
	 * 
	 * @param list
	 *            源数据
	 * @return 是否只有一个对象
	 */
	public static boolean hasUniqueChild(List list) {
		return list.size() == 1;
	}

	/**
	 * 判断一个sting是否为空
	 * 
	 * @param str
	 *            源字符串
	 * @return 是否为空
	 */
	public static boolean isBlank(String str) {
		if ((str == null) || "".equals(str)) {
			return true;
		}
		return false;
	}

	/**
	 * 判断一个sting是否为空或只有空格
	 * 
	 * @param str
	 *            源字符串
	 * @return 是否为空或只有空格
	 */
	public static boolean isTrimBlank(String str) {
		if ((str == null) || "".equals(str.trim())) {
			return true;
		}
		return false;
	}

	/**
	 * 判断一个sting是否为空或只有空格
	 * 
	 * @param str
	 *            源字符串
	 * @return 是否为空或只有空格
	 */
	public static boolean isTrimBlank(Object str) {
		String temp = BaseString.cleanUp(str);
		if ((temp == null) || "".equals(temp)) {
			return true;
		}
		return false;
	}

	/**
	 * 判断一个sting是否为空或只有空格
	 * 
	 * @param str
	 *            源字符串
	 * @return 是否为空或只有空格
	 */
	public static boolean isEmptyCollection(Collection source) {
		if ((source == null)) {
			return true;
		}
		return source.isEmpty();
	}

	/**
	 * 打印一条分隔线
	 */
	public static void out() {
		System.out.println("----------------------");
	}

	/**
	 * 打印一个object
	 * 
	 * @param obj
	 *            要打印的对象
	 */
	public static Object out(Object obj) {
		out(obj, getCallerInfo(1));
		return obj;
	}

	/**
	 * 打印一个object
	 * 
	 * @param obj
	 *            要打印的对象
	 */
	public static Object out(Object obj, int level) {
		out(obj, getCallerInfo(level));
		return obj;
	}

	/**
	 * 打印一个object
	 * 
	 * @param obj
	 *            要打印的对象
	 * @param description
	 *            对该对象的描述
	 */
	public static Object out(Object obj, String description) {
		if (debugOpened) {
			System.out.println("-----------" + description + "-----------");
			System.out.println(obj);
		}
		return obj;
	}

	/**
	 * 打印一个list
	 * 
	 * @param list
	 *            要打印的list对象
	 */
	public static void outList(List list) {
		outList(list, getCallerInfo(1));
	}

	/**
	 * 打印一个list
	 * 
	 * @param list
	 *            要打印的list对象
	 * @param description
	 *            对该对象的描述
	 */
	public static List outList(List list, String description) {
		if (debugOpened) {
			System.out.println("-----------" + description + "-----------");
			for (int i = 0; i < list.size(); i++) {
				System.out.println(list.get(i));
			}
		}
		return list;
	}

	/**
	 * 打印一个list
	 * 
	 * @param list
	 *            要打印的list对象
	 */
	public static void outList(Object[] list) {
		outList(list, getCallerInfo(1));
	}

	/**
	 * 打印一个list
	 * 
	 * @param list
	 *            要打印的list对象
	 * @param description
	 *            对该对象的描述
	 */
	public static Object[] outList(Object[] list, String description) {
		if (debugOpened) {
			System.out.println("-----------" + description + "-----------");
			for (int i = 0; i < list.length; i++) {
				System.out.println(list[i]);
			}
		}
		return list;
	}

	/**
	 * Get the class name for a depth in call stack. <br />
	 * Utility function
	 * 
	 * @param depth
	 *            depth in the call stack (0 means current class, 1 means call
	 *            class, ...)
	 * @return class name
	 */
	public static String getCallerClassName(final int depth) {
		final StackTraceElement[] ste = new Throwable().getStackTrace();

		return ste[depth].getClassName();
	}

	/**
	 * Get the line number for a depth in call stack. <br />
	 * Utility function
	 * 
	 * @param depth
	 *            depth in the call stack (0 means current line number, 1 means
	 *            call line number, ...)
	 * @return line number
	 */
	public static String getCallerLineNumber(final int depth) {
		final StackTraceElement[] ste = new Throwable().getStackTrace();
		return "" + ste[depth].getLineNumber();
	}

	/**
	 * Get the method name for a depth in call stack. <br />
	 * Utility function
	 * 
	 * @param depth
	 *            depth in the call stack (0 means current method, 1 means call
	 *            method, ...)
	 * @return method name
	 */
	public static String getCallerMethodName(final int depth) {
		final StackTraceElement[] ste = new Throwable().getStackTrace();

		return ste[depth].getMethodName();
	}

	public static final String getCallerInfo(int level) {
		final StackTraceElement[] ste = new Throwable().getStackTrace();
		return " " + ste[level + 1].toString() + " ";
	}
}
