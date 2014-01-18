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
 * 堆排序
 * <p>
 * 时间复杂度：(O(n*logn))<br>
 * 稳定性：不稳定<br>
 * <p>
 * 基本思想：<br>
 * 将R[1..n]构造为初始堆<br>
 * 每一趟排序的基本操作：将当前无序区的堆顶记录R[1]<br>
 * 和该区间的最后一个记录交换<br>
 * 然后将新的无序区调整为堆(亦称重建堆)。
 * <p>
 * 最佳使用场景：<br>
 * 适宜于数据量非常大的序列<br>
 * 所需的辅助空间少于快速排序<br>
 * 并且不会出现快速排序可能出现的最坏情况<br>
 * 速度慢于快速排序和归并排序<br>
 * 快速排序，归并排序都使用递归来设计算法<br>
 * 在数据量非常大的时候，可能会发生堆栈溢出错误
 * <p>
 * 算法分析：<br>
 * 堆排序的时间，主要由建立初始堆和<br>
 * 反复重建堆这两部分的时间开销构成<br>
 * 堆排序的最坏时间复杂度为O(nlgn)<br>
 * 堆排序的平均性能较接近于最坏性能<br>
 * 由于建初始堆所需的比较次数较多<br>
 * 所以堆排序不适宜于记录数较少的文件<br>
 * 堆排序是就地排序，辅助空间为O(1)<br>
 * 它是不稳定的排序方法
 * <p>
 * 最优和最坏：<br>
 * 比较和移动次数没有好坏之分，都是O(n*log2n)<br>
 * 使用一个辅存空间
 * 
 * @author MarsDJ
 * @since 2012-1-6
 * @version 1.0
 */
public class HeapSort extends AbstractSort {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.marswork.algorithm.sort.AbstractSort#sort(int[])
	 */
	@Override
	public void sort(int[] source) {
		int n = source.length;
		for (int j = n / 2 - 1; j >= 0; j--)
			// 创建最小堆
			sift(source, j, n - 1);
		// System.out.println("最小堆？ "+isMinHeap(table));

		for (int j = n - 1; j > 0; j--) // 每趟将最小值交换到后面，再调整成堆
		{
			int temp = source[0];
			source[0] = source[j];
			source[j] = temp;
			sift(source, 0, j - 1);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.marswork.algorithm.sort.AbstractSort#sort(float[])
	 */
	@Override
	public void sort(float[] source) {
		int n = source.length;
		for (int j = n / 2 - 1; j >= 0; j--)
			// 创建最小堆
			sift(source, j, n - 1);
		// System.out.println("最小堆？ "+isMinHeap(table));

		for (int j = n - 1; j > 0; j--) // 每趟将最小值交换到后面，再调整成堆
		{
			float temp = source[0];
			source[0] = source[j];
			source[j] = temp;
			sift(source, 0, j - 1);
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
		int n = source.length;
		for (int j = n / 2 - 1; j >= 0; j--)
			// 创建最小堆
			sift(source, sortFieldName, j, n - 1);
		// System.out.println("最小堆？ "+isMinHeap(table));

		for (int j = n - 1; j > 0; j--) // 每趟将最小值交换到后面，再调整成堆
		{
			Object temp = source[0];
			source[0] = source[j];
			source[j] = temp;
			sift(source, sortFieldName, 0, j - 1);
		}
	}

	/**
	 * 将以low为根的子树调整成最小堆
	 * 
	 * @param table
	 *            需要排序的数组
	 * @param low
	 *            序列下界
	 * @param high
	 *            序列上界
	 */
	private int[] sift(int[] table, int low, int high) {
		int i = low; // 子树的根
		int j = 2 * i + 1; // j为i结点的左孩子
		int temp = table[i]; // 获得第i个元素的值
		while (j <= high) // 沿较小值孩子结点向下筛选
		{
			if (j < high && table[j] > table[j + 1]) // 数组元素比较（改成<为最大堆）
				j++; // j为左右孩子的较小者
			if (temp > table[j]) // 若父母结点值较大（改成<为最大堆）
			{
				table[i] = table[j]; // 孩子结点中的较小值上移
				i = j; // i、j向下一层
				j = 2 * i + 1;
			} else
				j = high + 1; // 退出循环
		}
		table[i] = temp; // 当前子树的原根值调整后的位置
		return table;
	}

	/**
	 * 将以low为根的子树调整成最小堆
	 * 
	 * @param table
	 *            需要排序的数组
	 * @param low
	 *            序列下界
	 * @param high
	 *            序列上界
	 */
	private float[] sift(float[] table, int low, int high) {
		int i = low; // 子树的根
		int j = 2 * i + 1; // j为i结点的左孩子
		float temp = table[i]; // 获得第i个元素的值
		while (j <= high) // 沿较小值孩子结点向下筛选
		{
			if (j < high && table[j] > table[j + 1]) // 数组元素比较（改成<为最大堆）
				j++; // j为左右孩子的较小者
			if (temp > table[j]) // 若父母结点值较大（改成<为最大堆）
			{
				table[i] = table[j]; // 孩子结点中的较小值上移
				i = j; // i、j向下一层
				j = 2 * i + 1;
			} else
				j = high + 1; // 退出循环
		}
		table[i] = temp; // 当前子树的原根值调整后的位置
		return table;
	}

	/**
	 * 将以low为根的子树调整成最小堆
	 * 
	 * @param table
	 *            需要排序的数组
	 * @param low
	 *            序列下界
	 * @param high
	 *            序列上界
	 */
	private Object[] sift(Object[] table, String sortFieldName, int low,
			int high) {
		try {
			int i = low; // 子树的根
			int j = 2 * i + 1; // j为i结点的左孩子
			float temp = Float.parseFloat(BeanUtils.getProperty(table[i],
					sortFieldName)); // 获得第i个元素的值
			while (j <= high) // 沿较小值孩子结点向下筛选
			{
				if (j < high
						&& Float.parseFloat(BeanUtils.getProperty(table[j],
								sortFieldName)) > Float.parseFloat(BeanUtils
								.getProperty(table[j + 1], sortFieldName))) // 数组元素比较（改成<为最大堆）
					j++; // j为左右孩子的较小者
				if (temp > Float.parseFloat(BeanUtils.getProperty(table[j],
						sortFieldName))) // 若父母结点值较大（改成<为最大堆）
				{
					table[i] = table[j]; // 孩子结点中的较小值上移
					i = j; // i、j向下一层
					j = 2 * i + 1;
				} else
					j = high + 1; // 退出循环
			}
			table[i] = temp; // 当前子树的原根值调整后的位置
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		return table;
	}

}
