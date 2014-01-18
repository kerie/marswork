/**
 * 
 */
package com.marswork.algorithm.sort.sortclass;

import java.util.List;

import com.marswork.algorithm.sort.AbstractSort;
import com.marswork.algorithm.sort.SortOrder;
import com.marswork.algorithm.sort.SortableSupport;

/**
 * <p>
 * 快速排序
 * <p>
 * 时间复杂度：(O(n*logn))<br>
 * 稳定性：不稳定<br>
 * <p>
 * 基本思想：<br>
 * 选择一个中间值middle（程序中可使用数组中间值）<br>
 * 把比中间值小的放在其左边<br>
 * 比中间值大的放在其右边<br>
 * 递归之后得到有序数组
 * <p>
 * 最佳使用场景：<br>
 * 适用于对时间要求最高<br>
 * 且对空间和稳定性没有要求的场景<br>
 * 
 * <p>
 * 算法分析：<br>
 * 快速排序法的性能与中间值的选定关系密切<br>
 * 如果每一次选择的中间值都是最大值（或最小值）<br>
 * 该算法的速度就会大大下降
 * <p>
 * 最优和最坏：<br>
 * 比较和移动次数最少时间复杂度表示为O(n*log2n)<br>
 * 比较和移动次数最多的时间复杂度表示为O(n2)<br>
 * 使用的辅助存储空间最少为log2n，最多为n的平方
 * 
 * @author MarsDJ
 * @since 2012-1-6
 * @version 1.0
 */
public class QuickSort extends AbstractSort {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.marswork.algorithm.sort.AbstractSort#sort(int[])
	 */
	@Override
	public void sort(int[] source) {
		quickSort(source, 0, source.length - 1);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.marswork.algorithm.sort.AbstractSort#sort(float[])
	 */
	@Override
	public void sort(float[] source) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.marswork.algorithm.sort.AbstractSort#sort(java.util.List,
	 * com.marswork.algorithm.sort.Order)
	 */
	@Override
	public <T extends SortableSupport> void sort(List<T> source, SortOrder order) {
		// TODO 目前用的是归并排序啊，需要的时候再改咯
		AbstractSort.getSortInstance(MergeSort.class).sort(source, order);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.marswork.algorithm.sort.AbstractSort#sort(java.lang.Object[],
	 * java.lang.String)
	 */
	@Override
	public void sort(Object[] source, String sortFieldName) {
		// TODO Auto-generated method stub

	}

	/**
	 * 一趟快速排序，递归算法
	 * 
	 * @param table
	 *            需要排序的数组
	 * @param low
	 *            指定序列的下界
	 * @param high
	 *            指定序列的上界
	 * @return 排序后的数组
	 */
	private void quickSort(int[] table, int low, int high) {
		if (low < high) // 序列有效
		{
			int i = low, j = high;
			int vot = table[i]; // 第一个值作为基准值
			while (i != j) // 一趟排序
			{
				while (i < j && vot <= table[j])
					// 从后向前寻找较小值
					j--;
				if (i < j) {
					table[i] = table[j]; // 较小元素向前移动
					i++;
				}
				while (i < j && table[i] < vot)
					// 从前向后寻找较大值
					i++;
				if (i < j) {
					table[j] = table[i]; // 较大元素向后移动
					j--;
				}
			}
			table[i] = vot; // 基准值的最终位置
			quickSort(table, low, j - 1); // 前端子序列再排序
			quickSort(table, i + 1, high); // 后端子序列再排序
		}
	}

}
