package graph.edge_weighted;


import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Queue;

/**
 * 最小生成树的prim算法的延迟实现
 * @author xshrimp
 * 2017年5月5日
 */
public class LazyPrimMST {
  private boolean[] marked; // 最小生成树的顶点
  private Queue<Edge> mst;  // 最小生成树的边
  private MinPQ<Edge> pq;   // 横切边(可能包含失效边)
  
  public LazyPrimMST(EdgeWeightedGraph G) {
    marked = new boolean[G.V()];
    mst = new Queue<Edge>();
    pq = new MinPQ<Edge>();
    
    visit(G, 0);
    while (!pq.isEmpty()) {
      Edge e = pq.delMin();
      int v = e.either(); 
      int w = e.other(v);
      if (marked[v] && marked[w]) continue; // 无效边
      mst.enqueue(e);                       // 将边加入树中
      if (marked[v]) visit(G, w);          // 将边(v或w)加入树中
      if (marked[w]) visit(G, v);
    }
  }
  
  private void visit(EdgeWeightedGraph G, int v) {
    marked[v] = true;
    for (Edge e : G.adj(v)) {
      int w = e.other(v);
      if (!marked[w]) pq.insert(e);
    }
  }
  
  public Iterable<Edge> edges() {
    return mst;
  }
  
  public double weight() {
    double w = 0;
    for (Edge e : mst)
      w += e.weight();
    return w;
  }
}
