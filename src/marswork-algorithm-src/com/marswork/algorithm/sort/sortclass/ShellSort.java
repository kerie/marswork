/**
 * 
 */
package com.marswork.algorithm.sort.sortclass;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import com.marswork.algorithm.sort.AbstractSort;
import com.marswork.algorithm.sort.SortOrder;
import com.marswork.algorithm.sort.SortableSupport;

/**
 * <p>
 * 希尔排序
 * <p>
 * 
 * 时间复杂度：O(n*logn)*O(n*s) s为所选分组 <br>
 * 稳定性：不稳定<br>
 * <p>
 * 基本思想：<br>
 * 先取一个小于n的整数d1作为第一个增量<br>
 * 把文件的全部记录分成d1个组<br>
 * 所有距离为d1的倍数的记录放在同一个组中<br>
 * 先在各组内进行直接插入排序<br>
 * 然 后，取第二个增量d2<d1重复上述的分组和排序<br>
 * 直至所取的增量dt=1(dt < dt-l <…< d2 < d1) <br>
 * 即所有记 录放在同一组中进行直接插入排序为止。
 * <p>
 * 算法分析：<br>
 * 当文件初态基本有序时直接插入排序所需的比较和移动次数均较少<br>
 * 当n值较小时，n和n2的差别也较小<br>
 * 即直接插入排序的最好时间复杂度O(n)<br>
 * 和最坏时间复杂度0(n2)差别不大<br>
 * 在希尔排序开始时增量较大，分组较多<br>
 * 每组的记录数目少，故各组内直接插入较快<br>
 * 后来增量di逐渐缩小，分组数逐渐减少<br>
 * 而各组的记录数目逐渐增多<br>
 * 但由于已经按di-1作为距离排过序<br>
 * 使文件较接近于有序状态<br>
 * 所以新的一趟排序过程也较快
 * 
 * @author MarsDJ
 * @since 2012-1-6
 * @version 1.0
 */
public class ShellSort extends AbstractSort {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.marswork.algorithm.sort.AbstractSort#sort(int[])
	 */
	@Override
	public void sort(int[] source) {
		for (int delta = source.length / 2; delta > 0; delta /= 2) // 控制增量，增量减半，若干趟扫描
		{
			for (int i = delta; i < source.length; i++) // 一趟中若干组，每个元素在自己所属组内进行直接插入排序
			{
				int temp = source[i]; // 当前待插入元素
				int j = i - delta; // 相距delta远
				while (j >= 0 && temp < source[j]) // 一组中前面较大的元素向后移动
				{
					source[j + delta] = source[j];
					j -= delta; // 继续与前面的元素比较
				}
				source[j + delta] = temp; // 插入元素位置
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
		for (int delta = source.length / 2; delta > 0; delta /= 2) // 控制增量，增量减半，若干趟扫描
		{
			for (int i = delta; i < source.length; i++) // 一趟中若干组，每个元素在自己所属组内进行直接插入排序
			{
				float temp = source[i]; // 当前待插入元素
				int j = i - delta; // 相距delta远
				while (j >= 0 && temp < source[j]) // 一组中前面较大的元素向后移动
				{
					source[j + delta] = source[j];
					j -= delta; // 继续与前面的元素比较
				}
				source[j + delta] = temp; // 插入元素位置
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
		int group = 3;
		for (int delta = source.size() / group; delta > 0; delta /= group) { // 控制增量，增量减半，若干趟扫描
			AbstractSort.getSortInstance(InsertSort.class).sort(
					source.subList(delta - group + 1, source.size()));
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
			for (int delta = source.length / 2; delta > 0; delta /= 2) // 控制增量，增量减半，若干趟扫描
			{
				for (int i = delta; i < source.length; i++) // 一趟中若干组，每个元素在自己所属组内进行直接插入排序
				{
					float temp = Float.parseFloat(BeanUtils.getProperty(
							source[i], sortFieldName));
					int j = i - delta; // 相距delta远
					while (j >= 0
							&& temp < Float.parseFloat(BeanUtils.getProperty(
									source[j], sortFieldName))) // 一组中前面较大的元素向后移动
					{
						source[j + delta] = source[j];
						j -= delta; // 继续与前面的元素比较
					}
					source[j + delta] = temp; // 插入元素位置
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
