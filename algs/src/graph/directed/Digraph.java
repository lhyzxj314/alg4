package graph.directed;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;

/**
 * 使用邻接表表示的有向图
 * 
 * @author xiaojun
 * @version 1.0.0
 * @date 2017年4月29日
 */
public class Digraph {
  private final int V;  // 顶点数
  private int E;      // 边的数目
  private Bag<Integer>[] adj; // 邻接表
  
  @SuppressWarnings({ "unchecked", "rawtypes" })
  public Digraph(int v) {
    V = v;
    adj = new Bag[V];
    for (int i = 0; i < V; i++)
      adj[i] = new Bag();
  }
  
  public Digraph(In in) {
    this(in.readInt());
    int E = in.readInt();
    for (int i = 0; i < E; i++) {
      int v = in.readInt();
      int w = in.readInt();
      addEdge(v, w);
    }
  }
  
  public void addEdge(int v, int w) {
    adj[v].add(w); // 只调用一次add()
    // adj[w].add(v);
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
  
  public Digraph reverse() {
    Digraph R = new Digraph(V);
    for (int v = 0; v < V; v++)
      for (int w : R.adj[v])
        R.addEdge(w, v);
    return R;
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
