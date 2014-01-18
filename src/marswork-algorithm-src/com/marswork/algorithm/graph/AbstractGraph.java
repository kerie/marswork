package com.marswork.algorithm.graph;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * 抽象图类。非连通图的深度优先搜索遍历和广度优先搜索遍历
 * 
 * @author MarsDJ 2010-11-21
 */
public abstract class AbstractGraph<E> implements I_Graph<E> {
	/**
	 * 返回顶点数，方法由子类实现
	 */
	public abstract int vertexCount();

	/**
	 * 返回顶点vi的数据域
	 */
	public abstract E get(int i);

	/**
	 * 返回顶点vi的第一个邻接顶点的序号
	 */
	public abstract int getFirstNeighbor(int i);

	/**
	 * 返回vi在vj后的下一个邻接顶点的序号
	 */
	public abstract int getNextNeighbor(int i, int j);

	/**
	 * 从顶点v出发对非连通图的一次深度优先搜索遍历
	 * 
	 * @param v
	 */
	public void DFSTraverse(int v) {
		boolean[] visited = new boolean[vertexCount()]; // 访问标记数组，元素初值为false，表示未被访问
		int i = v;
		do {
			if (!visited[i]) // 若顶点vi未被访问
			{
				System.out.print("{ ");
				depthfs(i, visited); // 从顶点vi出发的一次深度优先搜索遍历
				System.out.print("} ");
			}
			i = (i + 1) % vertexCount(); // 在其他连通分量中寻找未被访问顶点
		} while (i != v);
		System.out.println();
	}

	/**
	 * 从顶点v开始发的一次深度优先搜索遍历
	 * 
	 * @param v
	 *            顶点v
	 * @param visited
	 *            访问标记数组
	 */
	private void depthfs(int v, boolean[] visited) { // 遍历一个连通分量
		System.out.print(this.get(v) + " "); // 访问该顶点
		visited[v] = true; // 置已访问标记
		int w = getFirstNeighbor(v); // 获得第一个邻接顶点
		while (w != -1) // 若存在邻接顶点
		{
			if (!visited[w]) // 若邻接顶点w未被访问
				depthfs(w, visited); // 从w出发的深度优先搜索遍历，递归调用
			w = getNextNeighbor(v, w); // 返回v在w后的下一个邻接顶点的序号
		}
	}

	/**
	 * 从顶点v出发对非连通图进行一次广度优先搜索遍历
	 * 
	 * @param v
	 *            顶点v
	 */
	public void BFSTraverse(int v) {
		boolean[] visited = new boolean[vertexCount()]; // 访问标记数组
		int i = v;
		do {
			if (!visited[i]) // 若顶点vi未被访问
			{
				System.out.print("{ ");
				breadthfs(i, visited); // 从顶点vi出发的广度优先搜索遍历
				System.out.print("} ");
			}
			i = (i + 1) % vertexCount(); // 在其他连通分量中寻找未被访问顶点
		} while (i != v);
		System.out.println();
	}

	/**
	 * 从顶点v出发的广度优先搜索遍历
	 * 
	 * @param v
	 *            顶点v
	 * @param visited
	 *            访问标记数组
	 */
	private void breadthfs(int v, boolean[] visited) { // 遍历一个连通分量
		System.out.print(this.get(v) + " ");
		visited[v] = true;
		ArrayBlockingQueue<Integer> que = new ArrayBlockingQueue<Integer>(vertexCount()); // 创建顺序队列
		que.add(new Integer(v)); // 访问过的顶点v的序号入队
		while (!que.isEmpty()) // 当队列不空时循环
		{
			v = que.remove().intValue(); // 出队
			int w = getFirstNeighbor(v); // 获得顶点v的第一个邻接顶点序号
			while (w != -1) // 当邻接顶点存在时循环
			{
				if (!visited[w]) // 若该顶点未访问过
				{
					System.out.print(this.get(w) + " "); // 访问顶点
					visited[w] = true;
					que.add(new Integer(w)); // 访问过的顶点w的序号入队
				}
				w = getNextNeighbor(v, w); // 返回v在w后的下一个邻接顶点的序号
			}
		}
	}

}
/*
 * public Edge[] prim() // { Edge[] mst = Edge; int n = g.getNumOfVertices();
 * int minCost; int[] lowCost = new int[n]; int k = 0;
 * 
 * for(int i = 1; i < n; i ++) lowCost[i] = g.getWeight(0, i); MinSpanTree temp =
 * new MinSpanTree(); temp.vertex = g.getValue(0); closeVertex[0] = temp;
 * lowCost[0] = - 1;
 * 
 * for(int i = 1; i < n; i ++){ minCost = maxWeight; for(int j = 1; j < n; j
 * ++){ if(lowCost[j] < minCost && lowCost[j] > 0){ minCost = lowCost[j]; k = j; } }
 * 
 * MinSpanTree curr = new MinSpanTree(); curr.vertex = g.getValue(k);
 * curr.weight = minCost; closeVertex[i] = curr; lowCost[k] = -1;
 * 
 * for(int j = 1; j < n; j ++){ if(g.getWeight(k, j) < lowCost[j]) lowCost[j] =
 * g.getWeight(k, j); } } } }
 * 
 * public class Graph3 extends Graph1 //图的遍历 { protected int tree[][];
 * //图的生成树的邻接矩阵 Graph3(int m1[][]) //以邻接矩阵表示图 { super(m1); tree = new int
 * [n][n]; } Graph3() { } public void prim() { int
 * i=0,j=0,min=10000,min_i=0,min_j=0; int n=0; while (n<this.mat.length-1)
 * //找出权值最小的边 { min=10000; min_i=0; min_j=0; for (i=0;i<visited.length;i++) {
 * if (visited[i]!=0) { for (j=0;j<this.mat[i].length;j++) if
 * ((this.mat[i][j]!=0) && (this.mat[i][j]<min)) { min = this.mat[i][j]; min_i =
 * i; min_j = j; } } } System.out.println("min="+min+" min_i="+min_i+"
 * min_j="+min_j); tree[min_i][min_j] = this.mat[min_i][min_j];
 * this.mat[min_i][min_j] = 0; visited[min_i] = 1; n++; output(tree); } } public
 * void kruskal() { } public static void main(String args[]) { int n=5,k; int
 * mat3[][] = {{0,2,4,5,0}, //无向带权图的邻接矩阵 {2,0,8,0,9}, {4,8,0,6,3}, {5,0,6,0,7},
 * {0,9,3,7,0}}; Graph3 g3 = new Graph3(mat3); System.out.print("生成树的");
 * g3.prim(); } }
 *  /* for (i=0;i<this.mat.length;i++) for (j=0;j<this.mat[i].length;j++) if
 * ((this.mat[i][j]!=0) && (this.mat[i][j]<min)) { min = this.mat[i][j]; min_i =
 * i; min_j = j; } System.out.println("min="+min+" min_i="+min_i+"
 * min_j="+min_j); tree[min_i][min_j] = this.mat[min_i][min_j];
 * this.mat[min_i][min_j] = 0; visited[min_i] = 1; n=1; output(tree);
 * 
 * System.out.println("深度优先遍历Depth first search:"); for (k=0;k<n;k++) {
 * g3.depthfs(k); System.out.println(); g3.unvisited(); }
 * System.out.println("广度优先遍历Breath first search:"); for (k=0;k<n;k++) {
 * g3.breadthfs(k); System.out.println(); g3.unvisited(); }
 * 
 * public void output() //输出邻接表 { super.output(); //输出邻接矩阵 int i=0; OnelinkNode
 * p; System.out.println("邻接表table:"); for (i=0;i<table.length;i++) {
 * System.out.print("table["+i+"]= "); if (table[i] != null) {
 * System.out.print(table[i].vertex); p = table[i].next; while (p!=null) {
 * System.out.print(" -> "+p.number+","+p.weight); p = p.next; } }
 * System.out.println(" null"); } }
 */
// }
