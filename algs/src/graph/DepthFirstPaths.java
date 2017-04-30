package graph;

import edu.princeton.cs.algs4.Stack;

public class DepthFirstPaths extends Paths {
  private boolean[] marked;
  private int[] edgeTo;
  
  public DepthFirstPaths(Graph G, int s) {
    super(G, s);
    marked = new boolean[G.V()];
    edgeTo = new int[G.V()];
    dfs(G, s);
  }
  
  private void dfs(Graph G, int v) {
    marked[v] = true;
    for (int w : G.adj(v)) {
      if (!marked[w]) {
        dfs(G, w);
        edgeTo[w] = v; // 保存父节点信息
      }
    }
    return;
  }

  @Override
  public boolean hasPathTo(int v) {
    return marked[v];
  }

  @Override
  public Iterable<Integer> pathTo(int v) {
    if (!hasPathTo(v)) return null;
    Stack<Integer> path = new Stack<Integer>();
    for (int x = v; x != s; x = edgeTo[x])
      path.push(x);
    path.push(s);
    return path;
  }

}
