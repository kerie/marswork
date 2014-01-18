package com.marswork.core.minitools.utils;

import java.util.List;

import com.marswork.core.exceptions.object.list.NoSuchObjectException;
import com.marswork.core.exceptions.object.list.TooManyObjectException;
import com.marswork.core.exceptions.object.string.NullStringException;
import com.marswork.core.exceptions.object.string.SpaceStringException;

/**
 * <p>
 * 断言类
 * <p>
 * 不符合断言条件的，抛出相应例外
 * 
 * @author MarsDJ
 * @since 2011-8-7
 * @version 1.0
 */
@SuppressWarnings("rawtypes")
public class AssertUtils {

	private AssertUtils() {
	}

	/**
	 * 断言List中只有一个对象<br>
	 * 如果数据源中指定的对象数 没有或者多于一个时，抛出相应例外
	 * 
	 * @param list
	 *            要处理的数据源。
	 * @return 返回满足要求的对象
	 * @throws NoSuchObjectException
	 *             没有这个对象例外
	 * @throws TooManyObjectException
	 *             满足要求的对象过多例外
	 */
	public static Object assertUniqueChild(List list)
			throws NoSuchObjectException, TooManyObjectException {
		if (list != null) {
			if (list.size() == 0)
				throw new NoSuchObjectException();
			if (list.size() > 1)
				throw new TooManyObjectException();
			return list.get(0);
		} else
			throw new NullPointerException();
	}

	/**
	 * 断言List中的对象数量不超过受限数量<br>
	 * 如果数据源中指定的对象数 没有或者多于指定个数时，抛出相应例外
	 * 
	 * @param list
	 *            要处理的数据源。
	 * @param objectName
	 *            名为objectName的
	 * @param typeName
	 *            typeName的对象
	 * @return
	 * @throws NoSuchObjectException
	 *             没有这个对象例外
	 * @throws TooManyObjectException
	 *             满足要求的对象过多例外
	 */
	public static void assertNoMoreThanLimitedChild(List list,
			String objectName, String typeName, int limit)
			throws NoSuchObjectException, TooManyObjectException {
		if (list != null) {
			if (list.size() > limit)
				throw new TooManyObjectException();
		} else
			throw new NullPointerException();
	}

	/**
	 * 断言List中的对象数量不超过受限数量<br>
	 * 如果数据源中指定的对象数 没有或者多于指定个数时，抛出相应例外
	 * 
	 * @param list
	 *            要处理的数据源。
	 * @return
	 * @throws NoSuchObjectException
	 *             没有这个对象例外
	 * @throws TooManyObjectException
	 *             满足要求的对象过多例外
	 */
	public static void assertNoMoreThanLimitedChild(List list, int limit)
			throws NoSuchObjectException, TooManyObjectException {
		if (list != null) {
			if (list.size() > limit)
				throw new TooManyObjectException();
		} else
			throw new NullPointerException();
	}

	/**
	 * 断言List中有且不超过受限数量的对象
	 * 
	 * @param list
	 *            要处理的数据源。
	 * @return
	 * @throws NoSuchObjectException
	 *             没有这个对象例外
	 * @throws TooManyObjectException
	 *             满足要求的对象过多例外
	 */
	public static void assertLimitedChild(List list, int limit)
			throws NoSuchObjectException, TooManyObjectException {
		if (list != null) {
			if (list.size() == 0)
				throw new NoSuchObjectException();
			if (list.size() > limit)
				throw new TooManyObjectException();
		} else
			throw new NullPointerException();
	}

	/**
	 * 断言对象不为空
	 * 
	 * @param obj
	 *            要判断的对象
	 * @return 不为空返回true
	 * @throws NullPointerException
	 *             空指针例外
	 */
	public static boolean assertObjectNotNull(Object obj) {
		if (obj == null)
			throw new NullPointerException();
		return true;
	}

	/**
	 * 断言字符串不为空，且不为空字串
	 * 
	 * @param str
	 *            源字符串
	 * @return 不为空返回true
	 * @throws NullStringException
	 *             空字符串例外
	 */
	public static boolean assertStringNotNull(String str)
			throws NullStringException {
		assertObjectNotNull(str);
		if (str == "") {
			throw new NullStringException();
		}
		return true;
	}

	/**
	 * 断言字符串不为空，不为空字串，不为只有空格字串
	 * 
	 * @param str
	 *            源字符串
	 * @return 不为空且不等于空格返回true
	 * @throws NullStringException
	 *             空字符串例外
	 * @throws SpaceStringException
	 *             空格字符串例外
	 */
	public static boolean assertStringNotSpace(String str)
			throws NullStringException, SpaceStringException {
		assertStringNotNull(str);
		if (str.trim() == "") {
			throw new SpaceStringException();
		}
		return true;
	}
}
