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
 * 冒泡排序
 * <p>
 * 时间复杂度:(O(n2))<br>
 * 稳定性：稳定<br>
 * <p>
 * 基本思想：<br>
 * 将被排序的记录数组R[1..n]垂直排列<br>
 * 每个记录R[i]看作是重量为 R[i].key的气泡<br>
 * 根据轻气泡不能在重气泡之下的原则<br>
 * 从下往上扫描数组R：凡扫描到违反本原则的轻气泡<br>
 * 就使其向上"飘浮"<br>
 * 如此反复进行<br>
 * 直到 最后任何两个气泡都是轻者在上<br>
 * 重者在下为止
 * <p>
 * 最佳使用场景：<br>
 * 当文件为正序时，直接插入{@link InsertSort}和冒泡均最佳
 * <p>
 * 算法分析：<br>
 * 起泡排序的结束条件为最后一趟没有进行“交换”<br>
 * 从起泡排序的过程可见<br>
 * 起泡排序是一个增加有序序列长度的过程<br>
 * 也是一个缩小无序序列长度的过程<br>
 * <p>
 * 最优和最坏：<br>
 * 比较次数：最少为：n-1次，最多时间复杂度表示为o(n2)<br>
 * 移动次数：最少为0，最多时间复杂度表示为O(n2)<br>
 * 使用一个辅存空间
 * 
 * @author MarsDJ
 * @since 2012-1-6
 * @version 1.0
 */
public class BubbleSort extends AbstractSort {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.marswork.algorithm.sort.AbstractSort#sort(int[])
	 */
	@Override
	public void sort(int[] source) {
		boolean exchange = true; // 是否交换的标记
		for (int i = 1; i < source.length && exchange; i++) // 有交换时再进行下一趟，最多n-1趟
		{
			exchange = false; // 假定元素未交换
			for (int j = 0; j < source.length - i; j++)
				// 一次比较、交换
				if (source[j] > source[j + 1]) // 反序时，交换
				{
					int temp = source[j];
					source[j] = source[j + 1];
					source[j + 1] = temp;
					exchange = true; // 有交换
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
		boolean exchange = true; // 是否交换的标记
		for (int i = 1; i < source.length && exchange; i++) // 有交换时再进行下一趟，最多n-1趟
		{
			exchange = false; // 假定元素未交换
			for (int j = 0; j < source.length - i; j++)
				// 一次比较、交换
				if (source[j] > source[j + 1]) // 反序时，交换
				{
					float temp = source[j];
					source[j] = source[j + 1];
					source[j + 1] = temp;
					exchange = true; // 有交换
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
		boolean exchange = true; // 是否交换的标记
		for (int i = 1; i < source.size() && exchange; i++) // 有交换时再进行下一趟，最多n-1趟
		{
			exchange = false; // 假定元素未交换
			for (int j = 0; j < source.size() - i; j++)
				// 一次比较、交换
				if (source.get(j).sortValue() > source.get(j + 1).sortValue()) // 反序时，交换
				{
					Collections.swap(source, j, j + 1);
					exchange = true; // 有交换
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
			boolean exchange = true; // 是否交换的标记
			for (int i = 1; i < source.length && exchange; i++) // 有交换时再进行下一趟，最多n-1趟
			{
				exchange = false; // 假定元素未交换
				for (int j = 0; j < source.length - i; j++)
					// 一次比较、交换
					if (Float.parseFloat(BeanUtils.getProperty(source[j],
							sortFieldName)) > Float.parseFloat(BeanUtils
							.getProperty(source[j + 1], sortFieldName))) // 反序时，交换
					{
						Object tempObj = source[i];
						source[j] = source[j + 1];
						source[j + 1] = tempObj;
						exchange = true; // 有交换
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
