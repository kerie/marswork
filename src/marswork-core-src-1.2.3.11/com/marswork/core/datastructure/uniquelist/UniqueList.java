package com.marswork.core.datastructure.uniquelist;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * <p>
 * 单一元素列表
 * <p>
 * 列表中的所有元素具有唯一性<br>
 * 如果有与新增的元素相等的元素在列表中已存在<br>
 * 将不会在列表中新增该元素
 * 
 * @author MarsDJ
 * @since 2011-8-4
 * @version 1.0
 */
public class UniqueList<E> extends ArrayList<E> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2201866668615524897L;

	/**
	 * 新增一个元素，并指定插入的位置
	 * 
	 * @param e
	 *            要加入列表的元素
	 * @return 元素是否成功加入列表
	 */
	@Override
	public boolean add(E e) {
		if (!isExsist(e)) {
			return super.add(e);
		}
		return false;
	}

	/**
	 * 新增一个元素，并指定插入的位置
	 * 
	 * @param index
	 *            元素插入的位置<br>
	 *            不可小于0或大于列表当前元素个数
	 * @param e
	 *            要加入列表的元素
	 */
	@Override
	public void add(int index, E e) {
		if (!isExsist(e)) {
			super.add(index, e);
		}
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
		try {
			Iterator<? extends E> iterator = c.iterator();
			while (iterator.hasNext()) {
				add(iterator.next());
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 判断要新增的元素在列表中是否存在相等的值<br>
	 * 若新增的元素为null，则不进行操作
	 * 
	 * @param e
	 *            要新增的元素
	 * @return 要新增的元素在列表中是否存在相等的值<br>
	 *         true为存在，false不存在
	 */
	private boolean isExsist(E e) {
		if (e == null) {
			return true;
		}
		for (int i = 0; i < this.size(); i++) {
			if (e.equals(this.get(i))) {
				return true;
			}
		}
		return false;
	}
}
