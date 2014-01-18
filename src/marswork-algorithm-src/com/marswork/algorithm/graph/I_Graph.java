package com.marswork.algorithm.graph;

/**
 * 图接口
 * 
 * @author MarsDJ 2010-11-21
 */
public interface I_Graph<E> {

	/**
	 * 返回顶点数
	 * 
	 * @return 顶点数
	 */
	public int vertexCount();

	/**
	 * 返回顶点vi的数据元素
	 * 
	 * @param i
	 *            顶点下标
	 * @return 顶点vi的数据元素
	 */
	public E get(int i);

	/**
	 * 插入一个顶点
	 * 
	 * @param vertex
	 *            顶点的数据元素
	 * @return 是否插入成功
	 */
	public boolean insertVertex(E vertex);

	/**
	 * 插入一条权值为weight的边〈vi,vj〉
	 * 
	 * @param i
	 *            顶点vi下标
	 * @param j
	 *            顶点vj下标
	 * @param weight
	 *            权值
	 * @return 是否插入成功
	 */
	public boolean insertEdge(int i, int j, int weight);

	/**
	 * 删除序号为v的顶点及其关联的边
	 * 
	 * @param v
	 *            顶点v下标
	 * @return 是否删除成功
	 */
	public boolean removeVertex(int v);

	/**
	 * 删除边〈vi,vj〉
	 * 
	 * @param i
	 *            顶点vi下标
	 * @param j
	 *            顶点vj下标
	 * @return 是否删除成功
	 */
	public boolean removeEdge(int i, int j);

	/**
	 * 返回顶点v的第一个邻接顶点的序号
	 * 
	 * @param v
	 *            顶点v下标
	 * @return 顶点v的第一个邻接顶点的序号
	 */
	public int getFirstNeighbor(int v);

	/**
	 * 返回v在w后的下一个邻接顶点的序号
	 * 
	 * @param v
	 *            顶点v下标
	 * @param w
	 *            跳跃几次后
	 * @return v在w后的下一个邻接顶点的序号
	 */
	public int getNextNeighbor(int v, int w); // 返回v在w后的下一个邻接顶点的序号
}
