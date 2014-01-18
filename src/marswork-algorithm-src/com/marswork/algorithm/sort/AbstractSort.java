/**
 * 
 */
package com.marswork.algorithm.sort;

import java.util.Collections;
import java.util.List;

import com.marswork.algorithm.sort.sortclass.SmartSort;

/**
 * <p>
 * 排序类型超类
 * <p>
 * 定义具体排序需要实现的方法<br>
 * 并且可以根据用户选择<br>
 * 生成一个排序实现类对象<br>
 * 默认生成{@link SmartSort}的对象
 * 
 * @author MarsDJ
 * @since 2012-1-5
 * @version 1.0
 */
public abstract class AbstractSort {

	/**
	 * 对整型数组进行排序
	 * 
	 * @param source
	 *            需要排序的整型数组
	 */
	public abstract void sort(int[] source);

	/**
	 * 对浮点型数组进行排序
	 * 
	 * @param source
	 *            需要排序的浮点型数组
	 */
	public abstract void sort(float[] source);

	/**
	 * 对对象列表中指定的数据进行排序
	 * 
	 * @param <T>
	 *            需要排序的对象列表中的对象类型
	 * @param source
	 *            需要排序的对象列表
	 * @param order
	 *            指定正序或反序
	 */
	public abstract <T extends SortableSupport> void sort(List<T> source,
			SortOrder order);

	/**
	 * 对对象列表中指定的数据进行排序<br>
	 * 默认正序排列
	 * 
	 * @param <T>
	 *            需要排序的对象列表中的对象类型
	 * @param source
	 *            需要排序的对象列表
	 */
	public <T extends SortableSupport> void sort(List<T> source) {
		sort(source, SortOrder.ASC);
	}

	/**
	 * 对对象数组中指定的数据进行排序
	 * 
	 * @param source
	 *            需要排序的对象数组
	 * @param sortFieldName
	 *            指定需要排序的数据域
	 */
	public abstract void sort(Object[] source, String sortFieldName);

	/**
	 * 获取排序类型对象<br>
	 * 默认{@link SmartSort}排序对象
	 * 
	 * @return 排序类型对象
	 */
	public static AbstractSort getSortInstance() {
		return new SmartSort();
	}

	/**
	 * 获取排序类型对象
	 * 
	 * @param sortClass
	 *            指定要使用的排序类型
	 * @return 排序类型对象
	 */
	public static AbstractSort getSortInstance(
			Class<? extends AbstractSort> sortClass) {
		try {
			return sortClass.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return getSortInstance();
	}

	/**
	 * 确定正反序列<br>
	 * 如果要求使用反序，将进行反序操作
	 * 
	 * @param <T>
	 *            对象列表中的对象类型
	 * @param source
	 *            对象列表
	 * @param order
	 *            指定正序或反序
	 */
	protected <T> void order(List<T> source, SortOrder order) {
		if (order.equals(SortOrder.DESC)) {
			Collections.reverse(source);
		}
	}

}
