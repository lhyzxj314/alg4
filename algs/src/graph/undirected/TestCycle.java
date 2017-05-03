package graph.undirected;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class TestCycle {
  public static void main(String[] args) {
    Graph G = new Graph(new In(args[0]));
    System.out.println(G);
    DepthFirstCycle search = new DepthFirstCycle(G);
    if (search.hasCycle())
      StdOut.print("有环");
    else
      StdOut.print("无环");
  }
}
