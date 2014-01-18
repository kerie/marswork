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
 * 直接插入排序
 * <p>
 * 时间复杂度：(O(n2))<br>
 * 稳定性：稳定<br>
 * <p>
 * 基本思想：<br>
 * 每次将一个待排序的记录<br>
 * 按其关键字大小插入到<br>
 * 前面已经排好序的子文件中的适当位置<br>
 * 直到全部记录插入完成为止。
 * <p>
 * 最佳使用场景：<br>
 * 若n较小(如n≤50)，可采用<br>
 * 直接插入或直接选择{@link SelectSort}排序。<br>
 * 当文件为正序时，直接插入和冒泡{@link BubbleSort}最佳
 * <p>
 * 算法分析：<br>
 * 当文件的初始状态不同时<br>
 * 直接插入排序所耗费的时间是有很大差异的<br>
 * 最好情况是文件初态 为正序<br>
 * 此时算法的时间复杂度为O(n)<br>
 * 最坏情况是文件初态为反序<br>
 * 相应的时间复杂度为O(n2)<br>
 * 算法的平均时间复杂度是O(n2)<br>
 * 算法的辅助空间 复杂度是O(1)，是一个就地排序。
 * <p>
 * 最优和最坏：<br>
 * 比较次数：最少n-1次；最多(n-1)(n+2)/2<br>
 * 移动次数：最少0； 最多(n-1)(n+4)/2<br>
 * 使用一个辅助存储空间<br>
 * 
 * @author MarsDJ
 * @since 2012-1-5
 * @version 1.0
 */
public class InsertSort extends AbstractSort {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.marswork.algorithm.sort.AbstractSort#sort(int[])
	 */
	@Override
	public void sort(int[] source) {
		for (int i = 1; i < source.length; i++) { // n-1趟扫描
			int temp = source[i], j; // 每趟将table[i]插入到前面已排序的序列中
			for (j = i - 1; j > -1 && temp < source[j]; j--) { // 将前面较大元素向后移动
				source[j + 1] = source[j];
			}
			source[j + 1] = temp; // temp值到达插入位置
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.marswork.algorithm.sort.AbstractSort#sort(float[])
	 */
	@Override
	public void sort(float[] source) {
		for (int i = 1; i < source.length; i++) { // n-1趟扫描
			float temp = source[i];
			int j; // 每趟将table[i]插入到前面已排序的序列中
			for (j = i - 1; j > -1 && temp < source[j]; j--) { // 将前面较大元素向后移动
				source[j + 1] = source[j];
			}
			source[j + 1] = temp; // temp值到达插入位置
		}
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
			for (int i = 1; i < source.length; i++) { // n-1趟扫描
				Object tempObj = source[i];
				float temp = Float.parseFloat(BeanUtils.getProperty(source[i],
						sortFieldName));
				int j; // 每趟将table[i]插入到前面已排序的序列中
				for (j = i - 1; j > -1
						&& temp < Float.parseFloat(BeanUtils.getProperty(
								source[j], sortFieldName)); j--) { // 将前面较大元素向后移动
					source[j + 1] = source[j];
				}
				source[j + 1] = tempObj; // temp值到达插入位置
			}
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
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

		for (int i = 1; i < source.size(); i++) { // n-1趟扫描
			T tempObj = source.get(i);
			float temp = tempObj.sortValue();
			int j = i - 1; // 每趟将table[i]插入到前面已排序的序列中
			while (j > -1) {
				if (temp >= source.get(j).sortValue()) {
					break;
				}
				j--;
			}
			source.remove(i);
			source.add(j + 1, tempObj);
		}
		order(source, order);
	}

}
