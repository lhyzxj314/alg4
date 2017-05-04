package graph.directed;

import edu.princeton.cs.algs4.StdOut;

public class TestTopological {
  public static void main(String[] args) {
    String filename = args[0];
    String seperator = args[1];
    SymbolGraph sg = new SymbolGraph(filename, seperator);
    
    Topological top = new Topological(sg.graph());
    for (int v : top.order())
      StdOut.println(sg.name(v));
  }
}
