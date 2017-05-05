package graph.edge_weighted;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.IndexMinPQ;

/**
 * 加权无向图最小生成树算法的即时实现
 * @author xshrimp
 * 2017年5月5日
 */
public class PrimMST {
  private boolean[] marked;  // 在树中的顶点
  private Edge[] edgeTo;     // 距离树最近的边
  private double[] distTo;   // distTo[w] = edgeTo[w].weight
  private IndexMinPQ<Double> pq; // 有效的横切边
  
  public PrimMST(EdgeWeightedGraph G) {
    marked = new boolean[G.V()];
    edgeTo = new Edge[G.V()];
    distTo = new double[G.V()];
    pq     = new IndexMinPQ<Double>(G.V());
    for (int v = 0; v < G.V(); v++)
      distTo[v] = Double.POSITIVE_INFINITY;
    
    distTo[0] = 0.0;
    pq.insert(0, 0.0);
    while (!pq.isEmpty())
      visit(G, pq.delMin());
  }
  
  @SuppressWarnings("deprecation")
  private void visit(EdgeWeightedGraph G, int v) {
    marked[v] = true;
    for (Edge e : G.adj(v)) {
      int w = e.other(v);
      if (marked[w]) continue;  // v-w失效
      if (e.weight() < distTo[w]) {
        edgeTo[w] = e;
        distTo[w] = e.weight();
        if (pq.contains(w)) pq.change(w, distTo[w]);
        else pq.insert(w, distTo[w]);
      }
    }
  }
  
  public Iterable<Edge> edges() {
    Bag<Edge> b = new Bag<Edge>();
    for (int v = 1; v < edgeTo.length; v++)
      b.add(edgeTo[v]);
    return b;
  }
  
  public double weight() {
    double weight = 0.0;
    for (int v = 1; v <edgeTo.length; v++)
      weight += edgeTo[v].weight();
    return weight;
  }
}
