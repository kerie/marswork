/**
 * 
 */
package com.marswork.algorithm.sort;

import java.util.Comparator;

/**
 * <p>
 * 可排序支持超类
 * <p>
 * 继承该类的子类可以获得<br>
 * {@link AbstractSort#sort(java.util.List)}和<br>
 * {@link AbstractSort#sort(java.util.List, Order)}的支持<br>
 * 使得该子类在List集合中可以轻松排序
 * 
 * @author MarsDJ
 * @since 2012-1-6
 * @version 1.0
 */
public abstract class SortableSupport implements I_Sortable,
		Comparator<SortableSupport> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	@Override
	public int compare(SortableSupport source, SortableSupport target) {
		if (source.sortValue() == target.sortValue()) {
			return 0;
		} else if (source.sortValue() > target.sortValue()) {
			return 1;
		}
		return -1;
	}
}
