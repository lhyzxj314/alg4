package graph.undirected;

import java.util.LinkedList;
import java.util.List;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class TestCC {
  
  public static void main(String[] args) {
    Graph G = new Graph(new In(args[0]));
    StdOut.println(G);
    
    CC cc = new CC(G);
    
    int m = cc.count();
    StdOut.println("有连通分量" + m + "个");
    
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
