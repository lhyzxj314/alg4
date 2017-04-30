package graph;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class TestPaths {
  public static void main(String[] args) {
    Graph G = new Graph(new In(args[0]));
    int   s = Integer.parseInt(args[1]);
    
    Paths search = new BreadthFirstPaths(G, s);
    for (int v = 0; v < G.V(); v++) {
      StdOut.print(s + " to " + v + ": ");
      if (search.hasPathTo(v))
        for (int w : search.pathTo(v))
          if (w == s) StdOut.print(w);
          else        StdOut.print("-" + w);
      StdOut.println();
    }
  }
}
