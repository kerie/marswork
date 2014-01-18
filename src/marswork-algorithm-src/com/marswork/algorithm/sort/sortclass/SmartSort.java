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
 * 智能排序
 * <p>
 * 智能排序不是一种具体的排序方式<br>
 * 而是根据数据源和提供的排序要求<br>
 * 指定排序策略<br>
 * 然后交给最适合的排序类型进行排序<br>
 * <p>
 * 数据源大小和数据源状态<br>
 * 对时间，空间，稳定性的要求<br>
 * 均会影响对排序算法的选择
 * <p>
 * 如果不对智能排序题任何要求<br>
 * 智能排序会根据数据源大小和系统默认分值选择排序方式<br>
 * 数据源大小在1000以内默认直接插入排序<br>
 * 大于1000默认归并排序
 * <p>
 * 系统默认分值为<br>
 * 归并>直接插入>快速>冒泡>直接选择>堆>希尔
 * 
 * @author MarsDJ
 * @since 2012-2-2
 * @version 0.9
 */
public class SmartSort extends AbstractSort {

	// 分别代表shell，heap，select，bubble，quick，insert，merge算法的分值
	private int[] scores = { 1, 2, 3, 4, 5, 6, 7 };

	private static final int CRITICAL_SIZE = 1000;

	/**
	 * 设定时间优先
	 */
	public void timePriority() {
		scores[4] += 15;
		scores[6] += 10;
		scores[1] += 10;
		scores[5] += 2;
		scores[0] += 2;
	}

	/**
	 * 设定空间优先
	 */
	public void spacePriority() {
		scores[1] += 10;
		scores[5] += 10;
		scores[2] += 10;
		scores[3] += 10;
		scores[6] += 5;
		scores[0] += 5;
	}

	/**
	 * 稳定性优先
	 */
	public void stablePriority() {
		scores[6] += 10;
		scores[5] += 10;
		scores[0] += 10;
		scores[3] += 10;
	}

	/**
	 * 源数据已接近正序或反序
	 * 
	 * @param isMeeted
	 *            true为已接近正序，false为已接近反序
	 */
	public void nearlySorted(boolean isMeeted) {
		if (isMeeted) {
			scores[3] += 10;
			scores[5] += 10;
		} else {
			scores[3] -= 10;
			scores[5] -= 10;
		}
	}

	/**
	 * 排序策略选择
	 * 
	 * @param size
	 *            数据源大小
	 * @return 被选择的排序策略
	 */
	private AbstractSort desideStretegy(int size) {
		try {
			if (size < CRITICAL_SIZE) {
				scores[3] += 5;
				scores[2] += 5;
				scores[5] += 5;
				scores[0] += 5;
			} else {
				scores[1] += 5;
				scores[4] += 5;
				scores[6] += 5;
			}

			int j = 0;
			for (int i = 0; i < 7; i++) {
				if (scores[j] <= scores[i]) {
					j = i;
				}
			}
			switch (j) {
			case 6:
				return MergeSort.class.newInstance();
			case 5:
				return InsertSort.class.newInstance();
			case 4:
				return QuickSort.class.newInstance();
			case 3:
				return BubbleSort.class.newInstance();
			case 2:
				return SelectSort.class.newInstance();
			case 1:
				return HeapSort.class.newInstance();
			case 0:
				return ShellSort.class.newInstance();
			default:
				return MergeSort.class.newInstance();
			}
		} catch (InstantiationException e) {
		} catch (IllegalAccessException e) {
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.marswork.algorithm.sort.AbstractSort#sort(int[])
	 */
	@Override
	public void sort(int[] source) {
		desideStretegy(source.length).sort(source);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.marswork.algorithm.sort.AbstractSort#sort(float[])
	 */
	@Override
	public void sort(float[] source) {
		desideStretegy(source.length).sort(source);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.marswork.algorithm.sort.AbstractSort#sort(java.util.List,
	 * com.marswork.algorithm.sort.Order)
	 */
	@Override
	public <T extends SortableSupport> void sort(List<T> source, SortOrder order) {
		desideStretegy(source.size()).sort(source, order);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.marswork.algorithm.sort.AbstractSort#sort(java.lang.Object[],
	 * java.lang.String)
	 */
	@Override
	public void sort(Object[] source, String sortFieldName) {
		desideStretegy(source.length).sort(source, sortFieldName);
	}

}
