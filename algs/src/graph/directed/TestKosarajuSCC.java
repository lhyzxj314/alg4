package graph.directed;

import java.util.LinkedList;
import java.util.List;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class TestKosarajuSCC {
  
  public static void main(String[] args) {
    Digraph G = new Digraph(new In(args[0]));
    StdOut.println(G);
    
    KosarajuSCC cc = new KosarajuSCC(G);
    
    int m = cc.count();
    StdOut.println("有强连通分量" + m + "个");
    
    @SuppressWarnings("unchecked")
    List<Integer>[] components = new LinkedList[m];
    
    for (int i = 0; i < m; i++)
      components[i] = new LinkedList<Integer>();
    
    for (int i = 0; i < G.V(); i++)
      components[cc.id(i)].add(i);
    
    for (int i = 0; i < m; i++) {
      for (int v : components[i]) {
        StdOut.print(v + " ");
      }
      StdOut.println();
    }
  }
}
