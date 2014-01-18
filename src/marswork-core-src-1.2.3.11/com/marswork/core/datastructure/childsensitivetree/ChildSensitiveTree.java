package com.marswork.core.datastructure.childsensitivetree;

import java.util.ArrayList;

/**
 * <p>
 * 子节点敏感树
 * <p>
 * 用于常用树的构建<br>
 * 该树可以轻松对子节点进行操作
 * 
 * @author MarsDJ
 * @since 2011-3-16
 * @version 1.0
 */
public class ChildSensitiveTree<E> {

	/**
	 * 节点对象
	 */
	private E obj;

	/**
	 * 子节点列表
	 */
	private ArrayList<ChildSensitiveTree<E>> children;

	/**
	 * 构造函数
	 */
	public ChildSensitiveTree() {
		children = new ArrayList<ChildSensitiveTree<E>>();
	}

	/**
	 * 构造函数
	 * 
	 * @param obj
	 *            根节点对象
	 */
	public ChildSensitiveTree(E obj) {
		this.obj = obj;
		children = new ArrayList<ChildSensitiveTree<E>>();
	}

	/**
	 * 追加子节点
	 * 
	 * @param marsTree
	 *            要追加的子节点
	 */
	public void appendChild(ChildSensitiveTree<E> childSensitiveTree) {
		children.add(childSensitiveTree);
	}

	/**
	 * 新增子节点
	 * 
	 * @param index
	 *            追加到第index个
	 * @param marsTree
	 *            要追加的子节点
	 */
	public void addChild(int index, ChildSensitiveTree<E> childSensitiveTree) {
		children.add(index, childSensitiveTree);
	}

	/**
	 * 判断是否叶子节点
	 * 
	 * @return 是否叶子节点
	 */
	public boolean isLeaf() {
		return getChildren().size() == 0 ? true : false;
	}

	/**
	 * 获取子节点数量
	 * 
	 * @return 子节点数量
	 */
	public int getCountOfChildren() {
		return getChildren().size();
	}

	/**
	 * 获取指定序列的子节点
	 * 
	 * @param index
	 *            指定序列
	 * @return 指定序列的子节点
	 */
	public ChildSensitiveTree<E> getChildAt(int index) {
		return getChildren().get(index);
	}

	/**
	 * @return the children
	 */
	public ArrayList<ChildSensitiveTree<E>> getChildren() {
		return children;
	}

	/**
	 * @return the obj
	 */
	public E getObj() {
		return obj;
	}

	/**
	 * @param children
	 *            the children to set
	 */
	public void setChildren(ArrayList<ChildSensitiveTree<E>> children) {
		this.children = children;
	}

	/**
	 * @param obj
	 *            the obj to set
	 */
	public void setObj(E obj) {
		this.obj = obj;
	}
}
