package graph.directed;

import java.util.LinkedList;
import java.util.List;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class TestDirectedDFS {
  public static void main(String[] args) {
    Digraph G = new Digraph(new In(args[0]));
    
    List<Integer> sources = new LinkedList<Integer>();
    for (int i = 1; i < args.length; i++)
      sources.add(Integer.parseInt(args[i]));
    
    DirectedDFS reachable = new DirectedDFS(G, sources);
    for (int v = 0; v < G.V(); v++)
      if (reachable.mark(v))
        StdOut.print(v + " ");
  }
}
