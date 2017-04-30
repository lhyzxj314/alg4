package graph;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class TestTwoColor {
  public static void main(String[] args) {
    Graph G = new Graph(new In(args[0]));
    DepthFirstTwoColor search = new DepthFirstTwoColor(G);
    
    if (search.isBipartite())
      StdOut.print("是二分图");
    else
      StdOut.print("不是二分图");
  }
}
