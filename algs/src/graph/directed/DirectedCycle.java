package graph.directed;

import edu.princeton.cs.algs4.Stack;

/**
 * 在有向图中寻找环
 * @author xshrimp
 * 2017年5月4日
 */
public class DirectedCycle {
  private boolean[] marked;
  private boolean[] onStack;    // 记录递归调用栈上的所有顶点
  private int[] edgeTo;      
  private Stack<Integer> cycle; // 有向环上的所有顶点
  
  public DirectedCycle(Digraph G) {
    marked  = new boolean[G.V()];
    onStack = new boolean[G.V()];
    edgeTo  = new int[G.V()];
    for (int v = 0; v < G.V(); v++)
      if (!marked[v]) dfs(G, v);
  }
  
  private void dfs(Digraph G, int v) {
    if (this.hasCycle()) return;
    
    marked[v] = true;
    onStack[v] = true;
    for (int w : G.adj(v)) {
      if (!marked[w]) {
        edgeTo[w] = v;
        dfs(G, w);
      } 
      else if (onStack[w]) {
        cycle = new Stack<Integer>();
        for (int x = v; x != w; x = edgeTo[x])
          cycle.push(x);
        
        cycle.push(w);
        cycle.push(v);
      }
    }
    onStack[v] = false;
  }
  
  public boolean hasCycle() {
    return cycle != null;
  }
  
  public Iterable<Integer> cycle() {
    return cycle;
  }
  
}
