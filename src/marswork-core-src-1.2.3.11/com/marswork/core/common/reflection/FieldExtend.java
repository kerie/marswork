/**
 * 
 */
package com.marswork.core.common.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import com.marswork.core.minitools.object.BaseString;

/**
 * <p>
 * 反射中的Field扩展
 * <p>
 * JDK中提供的获取一个类的Field有getField<br>
 * 和getDeclaredField方法<br>
 * 但这两个方法都有缺陷，getField只能获取<br>
 * 该类的所有Field，而不能获取超类的Field<br>
 * getDeclaredField可以获得该类和超类的<br>
 * public的Field，而不能获取其他访问属性的Field<br>
 * FieldExtend中的方法可以支持<br>
 * 指定类和超类的的Field操作
 * 
 * @author MarsDJ
 * @since 2011-10-21
 * @version 1.0
 */
public class FieldExtend {

	/**
	 * 根据Field获取与Field对应的getter方法
	 * 
	 * @param source
	 *            要分析的对象
	 * @param field
	 *            要获取getter的Field
	 * @return 与Field对应的getter方法
	 * @throws SecurityException
	 *             无权访问例外
	 * @throws NoSuchMethodException
	 *             没有相应的方法
	 */
	public static Method getReaderMethod(Object source, Field field) throws SecurityException,
			NoSuchMethodException {
		Method method = source.getClass().getMethod("get" + dealAttrName(field.getName()));
		return method;
	}

	/**
	 * 根据Field获取与Field对应的setter方法
	 * 
	 * @param source
	 *            要分析的对象
	 * @param field
	 *            要获取setter的Field
	 * @return 与Field对应的setter方法
	 * @throws SecurityException
	 *             无权访问例外
	 * @throws NoSuchMethodException
	 *             没有相应的方法
	 */
	public static Method getWriterMethod(Object source, Field field) throws SecurityException,
			NoSuchMethodException {
		Method method = source.getClass().getMethod("set" + dealAttrName(field.getName()),
				field.getType());
		return method;
	}

	public static Object runSuperClassPrivateMethod(Object object, String methodName,
			Class<?>[] parameter, Object[] parameterValue) throws NoSuchMethodException {
		return runSuperClassPrivateMethod(object, object.getClass(), methodName, parameter,
				parameterValue);
	}

	/**
	 * 运行指定类和超类中的方法，包括私有方法
	 * 
	 * @param object
	 *            指定对象
	 * @param currClass
	 *            指定对象的当前类<br>
	 *            该方法会追溯指定对象的所有超类<br>
	 *            currClass表示当前类，并将向上追溯
	 * @param methodName
	 *            要运行的方法名
	 * @param parameter
	 *            要运行的方法参数类
	 * @param parameterValue
	 *            要运行的方法参数值
	 * @return 运行结果
	 * @throws NoSuchMethodException
	 *             没有找到指定方法例外
	 */
	public static Object runSuperClassPrivateMethod(Object object, Class<?> currClass,
			String methodName, Class<?>[] parameter, Object[] parameterValue)
			throws NoSuchMethodException {
		try {
			Method method = currClass.getDeclaredMethod(methodName, parameter);
			method.setAccessible(true);
			return method.invoke(object, parameterValue);
		} catch (Exception e) {
			if (currClass.getSuperclass() == null) {
				if (e instanceof NoSuchMethodException) {
					System.out.println("NoSuchFieldException");
					throw (NoSuchMethodException) e;
				} else {
					System.out.println("we getField failed,info is :" + e.toString());
					return null;
				}
			} else {
				return runSuperClassPrivateMethod(object, currClass.getSuperclass(), methodName,
						parameter, parameterValue);
			}
		}
	}

	/**
	 * 为指定类和超类中的Field赋值
	 * 
	 * @param object
	 *            指定对象
	 * @param currClass
	 *            指定对象的当前类<br>
	 *            该方法会追溯指定对象的所有超类<br>
	 *            currClass表示当前类，并将向上追溯
	 * @param fieldName
	 *            要赋值的Field
	 * @param value
	 *            要赋值的值
	 * @throws NoSuchFieldException
	 *             没有找到指定Field例外
	 */
	public static void setSuperClassPrivateFieldValue(Object object, Class<?> currClass,
			String fieldName, Object value) throws NoSuchFieldException {
		try {
			Field field = currClass.getDeclaredField(fieldName);
			field.setAccessible(true);
			field.set(object, value);
		} catch (Exception e) {

			if (currClass.getSuperclass() == null) {
				if (e instanceof NoSuchFieldException) {
					throw (NoSuchFieldException) e;
				}
			} else {
				setSuperClassPrivateFieldValue(object, currClass.getSuperclass(), fieldName, value);
			}
		}
	}

	/**
	 * 获取指定类及其父类中的指定Field的值
	 * 
	 * @param tClass
	 *            要从中去Field的类<br>
	 *            方法可以取该类及其父类中Field
	 * @param fieldName
	 *            要获取的Field的名称
	 * @return 指定的Field的值
	 * @throws Exception
	 *             任何例外
	 */
	public static Object getSuperClassFieldValue(Object object, Class<?> tClass, String fieldName)
			throws Exception {
		try {
			Field field = tClass.getDeclaredField(fieldName);
			field.setAccessible(true);
			return field.get(object);
		} catch (Exception e) {
			if (tClass.getSuperclass() == null) {
				if (e instanceof NoSuchFieldException) {
					throw (NoSuchFieldException) e;
				} else {
					throw e;
				}
			} else {
				return getSuperClassFieldValue(object, tClass.getSuperclass(), fieldName);
			}
		}
	}

	/**
	 * 获取指定类及其父类中的指定Field
	 * 
	 * @param tClass
	 *            要从中去Field的类<br>
	 *            方法可以取该类及其父类中Field
	 * @param fieldName
	 *            要获取的Field的名称
	 * @return 指定的Field
	 * @throws NoSuchFieldException
	 *             没有找到指定的Field例外
	 */
	public static Field getSuperClassField(Class<?> tClass, String fieldName)
			throws NoSuchFieldException {
		try {
			Field field = tClass.getDeclaredField(fieldName);
			return field;
		} catch (NoSuchFieldException e) {
			if (tClass.getSuperclass() == null) {
				throw new NoSuchFieldException(fieldName);
			} else {
				return getSuperClassField(tClass.getSuperclass(), fieldName);
			}
		}
	}

	/**
	 * 获取getter,setter方法对应的属性名
	 * 
	 * @param methodName
	 * @return
	 */
	public static String getMethodAttribute(String methodName) {
		if (methodName == null || !(methodName.startsWith("get") || methodName.startsWith("set"))) {
			return null;
		} else {
			methodName = methodName.substring(3, methodName.length());
			StringBuilder result = new StringBuilder(methodName.substring(0, 1).toLowerCase());
			result.append(methodName.substring(1));
			return result.toString();
		}
	}

	private static String dealAttrName(String attrName) {
		if (attrName.endsWith("_")) {
			attrName = attrName.substring(0, attrName.length() - 1);
		}
		return BaseString.upperFirstChar(attrName, false);
	}
}
