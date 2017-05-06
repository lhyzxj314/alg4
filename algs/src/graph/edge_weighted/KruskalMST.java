package graph.edge_weighted;

import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.UF;

/**
 * 最小生成树的kruskal算法
 * 
 * @author xshrimp
 * 2017年5月6日
 */
public class KruskalMST {
  private Queue<Edge> mst;  // 最小生成树
  
  public KruskalMST(EdgeWeightedGraph G) {
    mst = new Queue<Edge>();
    MinPQ<Edge> pq = new MinPQ<Edge>();
    UF uf = new UF(G.V());
    
    for (Edge e : G.edges())
      pq.insert(e);
    
    while (!pq.isEmpty() && mst.size() < G.V() - 1) {
      Edge e = pq.delMin();
      
      int v = e.either();
      int w = e.other(v);
      if (uf.connected(v, w)) continue;
      uf.union(v, w);
      mst.enqueue(e);
    }
  }
  
  public Iterable<Edge> edges() {
    return mst;
  }
  
  public double weight() {
    double w = 0.0;
    for (Edge e : mst)
      w += e.weight();
    return w;
  }
}
