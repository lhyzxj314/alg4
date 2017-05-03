package graph;

/**
 * 计算无向图的连通分量
 * 
 * @author xshrimp
 * 2017年5月3日
 */
public class CC {
  private boolean[] marked;
  private int[] id;
  private int count;
  
  public CC(Graph G) {
    marked = new boolean[G.V()];
    id     = new int[G.V()];
    for (int v = 0; v < G.V(); v++)
      if (!marked[v]) {
        dfs(G, v);
        count++;
      }
  }
  
  private void dfs(Graph G, int v) {
    marked[v] = true;
    id[v] = count;
    for (int w : G.adj(v))
      if (!marked[w]) dfs(G, w);
    return;
  }
  
  public boolean connected(int v, int w) {
    return id[v] == id[w];
  }
  
  public int count() {
    return count;
  }
  
  public int id(int v) {
    return id[v];
  }
}
