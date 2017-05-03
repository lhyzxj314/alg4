package graph.undirected;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;

/**
 * 使用邻接表表示的无向图
 * 
 * @author xiaojun
 * @version 1.0.0
 * @date 2017年4月29日
 */
public class Graph {
	private final int V;  // 顶点数
	private int E;		  // 边的数目
	private Bag<Integer>[] adj; // 邻接表
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Graph(int v) {
		V = v;
		adj = new Bag[V];
		for (int i = 0; i < V; i++)
			adj[i] = new Bag();
	}
	
	public Graph(In in) {
		this(in.readInt());
		int E = in.readInt();
		for (int i = 0; i < E; i++) {
			int v = in.readInt();
			int w = in.readInt();
			addEdge(v, w);
		}
	}
	
	public void addEdge(int v, int w) {
		adj[v].add(w);
		adj[w].add(v);
		E++;
	}
	
	public int V() {
		return V;
	}
	
	public int E() {
		return E;
	}
	
	public Iterable<Integer> adj(int v) {
		return adj[v];
	}
	
	public static int degree(Graph G, int v) {
		int degree = 0;
		for (@SuppressWarnings("unused") Integer w : G.adj[v])
			degree++;
		return degree;
	}
	
	public static int maxDegree(Graph G) {
		int max = 0;
		for (int v = 0; v < G.V(); v++) {
			int d = degree(G, v);
			if (max < d) max = d;
		}
		return max;
	}
	
	public static double avgDegree(Graph G) {
		return 2 * G.E() / G.V();
	}
	
	public static int numberOfSelfLoops(Graph G) {
		int count = 0;
		for (int v = 0; v < G.V(); v++) {
			for (Integer w : G.adj[v])
				if (w == v) count++;
		}
		return count / 2;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(V + " 个顶点，" + E + " 条边\n");
		for (int v = 0; v < V; v++) {
			sb.append(v + ": ");
			for (Integer w : adj[v])
				sb.append(w + " ");
			sb.append("\n");
		}
		return sb.toString();
	}
}
