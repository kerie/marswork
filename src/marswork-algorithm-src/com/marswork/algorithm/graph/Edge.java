package com.marswork.algorithm.graph;

/**
 * 带权图的边类
 * 
 * @author MarsDJ 2010-11-21
 */
public class Edge implements Comparable<Edge> {

	/**
	 * 边的起点序号
	 */
	public int start;

	/**
	 * 边的终点序号
	 */
	public int dest;

	/**
	 * 边的权值
	 */
	public int weight;

	/**
	 * 边的构造函数
	 * 
	 * @param start
	 *            开始节点
	 * @param dest
	 *            目标节点
	 * @param weight
	 *            权重
	 */
	public Edge(int start, int dest, int weight) {
		this.start = start;
		this.dest = dest;
		this.weight = weight;
	}

	public String toString() {
		return "(" + start + "," + dest + "," + weight + ")";
	}

	/**
	 * 约定两条边比较大小的规则
	 * 
	 * @param e
	 *            比较大小的对像
	 * @return 比较大小的结果
	 */
	public int compareTo(Edge e) {
		if (this.start != e.start)
			return this.start - e.start;
		else
			return this.dest - e.dest;
	}

}
