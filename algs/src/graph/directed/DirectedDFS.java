package graph.directed;

public class DirectedDFS {
  private boolean[] marked;
  
  public DirectedDFS(Digraph G, int s) {
    marked = new boolean[G.V()];
    dfs(G, s);
  }
  
  public DirectedDFS(Digraph G, Iterable<Integer> srcs) {
    marked = new boolean[G.V()];
    for (int s : srcs)
      if (!marked[s])
        dfs(G, s);
  }
  
  private void dfs(Digraph G, int v) {
    marked[v] = true;
    for (int w : G.adj(v))
      if (!marked[w]) dfs(G, w);
  }
  
  public boolean mark(int v) {
    return marked[v];
  }
}
