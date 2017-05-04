package graph.directed;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class TestDirectedCycle {
  public static void main(String[] args) {
    Digraph G = new Digraph(new In(args[0]));
    DirectedCycle cycle = new DirectedCycle(G);
    
    if (cycle.hasCycle()) {
      StdOut.print("有环:");
      for (int v : cycle.cycle())
        StdOut.print(v + " ");
      StdOut.println();
    }
    else
      StdOut.println("无环");
  }
}
