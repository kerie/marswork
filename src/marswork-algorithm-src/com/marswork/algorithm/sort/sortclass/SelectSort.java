/**
 * 
 */
package com.marswork.algorithm.sort.sortclass;

import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import com.marswork.algorithm.sort.AbstractSort;
import com.marswork.algorithm.sort.SortOrder;
import com.marswork.algorithm.sort.SortableSupport;

/**
 * <p>
 * 直接选择排序
 * <p>
 * 时间复杂度：(O(n2))<br>
 * 稳定性：不稳定<br>
 * <p>
 * 基本思想：<br>
 * 每一趟从待排序的数据元素中选出最小（或最大）的一个元素<br>
 * 顺序放在已排好序的数列的最后<br>
 * 直到全部待排序的数据元素排完。
 * <p>
 * 最佳使用场景：<br>
 * 若n较小(如n≤50)，可采用<br>
 * 直接插入{@link InsertSort}或直接选择排序。<br>
 * 如果规模特别小，应采用直接选择排序
 * <p>
 * 算法分析：<br>
 * 选择排序法的第一层循环从起始元素开始选到倒数第二个元素<br>
 * 主要是在每次进入的第二层循环之 前<br>
 * 将外层循环的下标赋值给临时变量<br>
 * 接下来的第二层循环中<br>
 * 如果发现有比这个最小位置处的元素更小的元素<br>
 * 则将那个更小的元素的下标赋给临时变量<br>
 * 最 后，在二层循环退出后，如果临时变量改变<br>
 * 则说明，有比当前外层循环位置更小的元素<br>
 * 需要将这两个元素交换
 * <p>
 * 最优和最坏：<br>
 * 比较次数：没有多少之分，均是n(n-1)/2<br>
 * 移动次数：最少为0，最多为3(n-1)<br>
 * 使用一个辅存空间；
 * 
 * @author MarsDJ
 * @since 2012-1-6
 * @version 1.0
 */
public class SelectSort extends AbstractSort {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.marswork.algorithm.sort.AbstractSort#sort(int[])
	 */
	@Override
	public void sort(int[] source) {
		for (int i = 0; i < source.length - 1; i++) // n-1趟排序
		{ // 每趟在从table[i]开始的子序列中寻找最小元素
			int min = i; // 设第i个数据元素最小
			for (int j = i + 1; j < source.length; j++)
				// 在子序列中查找最小值
				if (source[j] < source[min])
					min = j; // 记住最小元素下标

			if (min != i) // 将本趟最小元素交换到前边
			{
				int temp = source[i];
				source[i] = source[min];
				source[min] = temp;
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.marswork.algorithm.sort.AbstractSort#sort(float[])
	 */
	@Override
	public void sort(float[] source) {
		for (int i = 0; i < source.length - 1; i++) // n-1趟排序
		{ // 每趟在从table[i]开始的子序列中寻找最小元素
			int min = i; // 设第i个数据元素最小
			for (int j = i + 1; j < source.length; j++)
				// 在子序列中查找最小值
				if (source[j] < source[min])
					min = j; // 记住最小元素下标

			if (min != i) // 将本趟最小元素交换到前边
			{
				float temp = source[i];
				source[i] = source[min];
				source[min] = temp;
			}
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.marswork.algorithm.sort.AbstractSort#sort(java.util.List,
	 * com.marswork.algorithm.sort.Order)
	 */
	@Override
	public <T extends SortableSupport> void sort(List<T> source, SortOrder order) {
		for (int i = 0; i < source.size() - 1; i++) // n-1趟排序
		{ // 每趟在从table[i]开始的子序列中寻找最小元素
			int min = i; // 设第i个数据元素最小
			for (int j = i + 1; j < source.size(); j++)
				// 在子序列中查找最小值
				if (source.get(j).sortValue() < source.get(min).sortValue())
					min = j; // 记住最小元素下标

			if (min != i) // 将本趟最小元素交换到前边
			{
				Collections.swap(source, i, min);
			}
		}
		order(source, order);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.marswork.algorithm.sort.AbstractSort#sort(java.lang.Object[],
	 * java.lang.String)
	 */
	@Override
	public void sort(Object[] source, String sortFieldName) {
		try {
			for (int i = 0; i < source.length - 1; i++) // n-1趟排序
			{ // 每趟在从table[i]开始的子序列中寻找最小元素
				int min = i; // 设第i个数据元素最小
				for (int j = i + 1; j < source.length; j++)
					// 在子序列中查找最小值
					if (Float.parseFloat(BeanUtils.getProperty(source[j],
							sortFieldName)) < Float.parseFloat(BeanUtils
							.getProperty(source[min], sortFieldName)))
						min = j; // 记住最小元素下标

				if (min != i) // 将本趟最小元素交换到前边
				{
					Object temp = source[i];
					source[i] = source[min];
					source[min] = temp;
				}
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}

	}

}
