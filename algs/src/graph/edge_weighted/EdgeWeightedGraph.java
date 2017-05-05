package graph.edge_weighted;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;

/**
 * 加权无向图
 * @author xshrimp
 * 2017年5月5日
 */
public class EdgeWeightedGraph {
  private final int V;
  private int E;
  private Bag<Edge>[] adj;
  
  @SuppressWarnings({ "unchecked", "rawtypes" })
  public EdgeWeightedGraph(int v) {
    V = v;
    adj = new Bag[v];
    for (int x = 0; x < V; x++) {
      adj[x] = new Bag();
    }
  }
  
  public EdgeWeightedGraph(In in) {
    this(in.readInt());
    int e = in.readInt();
    for (int x = 0; x < e; x++) {
      int v = in.readInt();
      int w = in.readInt();
      double weight = in.readDouble();
      addEdge(new Edge(v, w, weight));
    }
  }
  
  public void addEdge(Edge e) {
    int v = e.either();
    int w = e.other(v);
    adj[v].add(e);
    adj[w].add(e);
    E++;
  }
  
  public int V() { return V;}
  
  public int E() { return E;}
  
  public Iterable<Edge> adj(int v) {
    return adj[v];
  }
  
  public Iterable<Edge> edges() {
    Bag<Edge> edges = new Bag<Edge>();
    for (int v = 0; v < V; v++)
      for (Edge e : adj[v])
        if (e.other(v) > v)
          edges.add(e);
    return edges;
  }
}
