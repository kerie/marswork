/**
 * 
 */
package com.marswork.algorithm.sort.sortclass;

import java.util.Collections;
import java.util.List;

import com.marswork.algorithm.sort.AbstractSort;
import com.marswork.algorithm.sort.SortOrder;
import com.marswork.algorithm.sort.SortableSupport;

/**
 * <p>
 * 归并排序
 * <p>
 * 时间复杂度：(O(n*logn))<br>
 * 稳定性：稳定<br>
 * <p>
 * 基本思想：<br>
 * 归并排序先分解要排序的序列<br>
 * 从1分成2，2分成4，依次分解<br>
 * 当分解到只有1个一组的时候<br>
 * 就可以排序这些分组，然后依次合并回原来的序列中<br>
 * 这样就可以排序所有数据。
 * <p>
 * 最佳使用场景：<br>
 * 唯一一个时间复杂度为(O(n*logn))的稳定排序<br>
 * 适宜于记录数较大的序列<br>
 * 空间要求不高，要求稳定的场景<br>
 * <p>
 * 算法分析：<br>
 * 合并排序比堆排序稍微快一点<br>
 * 但是需要比堆排序多一倍的内存空间<br>
 * 因为它需要一个额外的数组
 * <p>
 * 最优和最坏：<br>
 * 比较和移动次数没有好坏之分，都是O(n*log2n)<br>
 * 需要n个辅助存储空间，是稳定的排序；
 * 
 * @author MarsDJ
 * @since 2012-1-6
 * @version 1.0
 */
public class MergeSort extends AbstractSort {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.marswork.algorithm.sort.AbstractSort#sort(int[])
	 */
	@Override
	public void sort(int[] source) {
		int n = 1; // 已排序的子序列长度，初值为1
		int[] index = new int[source.length]; // Y数组长度同X数组
		do {
			mergepass(source, index, n); // 一趟归并，将X数组中各子序列归并到Y中
			n *= 2; // 子序列长度加倍

			if (n < source.length) {
				mergepass(index, source, n); // 将Y数组中各子序列再归并到X中
				n *= 2;
			}
		} while (n < source.length);
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
		if (source.size() > 0) {
			Collections.sort(source, source.get(0));
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
		// TODO Auto-generated method stub

	}

	/**
	 * 一趟归并
	 * 
	 * @param X
	 *            源数组
	 * @param Y
	 *            参与归并的数组
	 * @param n
	 *            已排序的子序列长度
	 */
	private void mergepass(int[] X, int[] Y, int n) {
		System.out.print("子序列长度n=" + n + "  ");
		int i = 0;
		while (i < X.length - 2 * n + 1) {
			merge(X, Y, i, i + n, n);
			i += 2 * n;
		}
		if (i + n < X.length)
			merge(X, Y, i, i + n, n); // 再一次归并
		else
			for (int j = i; j < X.length; j++)
				// 将X剩余元素复制到Y中
				Y[j] = X[j];
	}

	/**
	 * 一次归并
	 * 
	 * @param X
	 *            源数组
	 * @param Y
	 *            参与归并的数组
	 * @param m
	 *            X当前下标
	 * @param r
	 *            Y当前下标
	 * @param n
	 *            已排序的子序列长度
	 */
	private void merge(int[] X, int[] Y, int m, int r, int n) {
		int i = m, j = r, k = m;
		while (i < r && j < r + n && j < X.length)
			// 将X中两个相邻子序列归并到Y中
			if (X[i] < X[j]) // 较小值复制到Y中
				Y[k++] = X[i++];
			else
				Y[k++] = X[j++];

		while (i < r)
			// 将前一个子序列剩余元素复制到Y中
			Y[k++] = X[i++];
		while (j < r + n && j < X.length)
			// 将后一个子序列剩余元素复制到Y中
			Y[k++] = X[j++];
	}
}
