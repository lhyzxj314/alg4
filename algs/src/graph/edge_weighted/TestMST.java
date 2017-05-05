package graph.edge_weighted;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class TestMST {
  public static void main(String[] args) {
    EdgeWeightedGraph G = new EdgeWeightedGraph(new In(args[0]));
    LazyPrimMST prim = new LazyPrimMST(G);
    
    for (Edge e : prim.edges())
      StdOut.println(e);
    StdOut.println(prim.weight());
  }
}
