package graph.directed;

/**
 * 使用Kosaraju算法计算有向图的强连通分量
 * 
 * @author xshrimp 2017年5月4日
 */
public class KosarajuCC {
  private boolean[] marked;
  private int[] id;
  private int count;

  public KosarajuCC(Digraph G) {
    marked = new boolean[G.V()];
    id = new int[G.V()];
    // Kosaraju算法：使用G的反向图的逆后序遍历顺序进行dfs，每次新的递归调用是一个强连通分量
    DepthFirstOrder order = new DepthFirstOrder(G.reverse());
    
    for (int v : order.reversePost())
      if (!marked[v]) {
        dfs(G, v);
        count++;
      }
  }

  private void dfs(Digraph G, int v) {
    marked[v] = true;
    id[v] = count;
    for (int w : G.adj(v))
      if (!marked[w])
        dfs(G, w);
  }

  public boolean stronglyConnected(int v, int w) {
    return id[w] == id[v];
  }

  public int id(int v) {
    return id[v];
  }

  public int count() {
    return count;
  }
}
