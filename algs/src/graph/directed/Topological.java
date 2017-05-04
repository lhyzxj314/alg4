package graph.directed;

/**
 * 拓扑排序
 * @author xshrimp
 * 2017年5月4日
 */
public class Topological {
  private Iterable<Integer> order;
  
  public Topological(Digraph G) {
    DirectedCycle cycle = new DirectedCycle(G);
    if (!cycle.hasCycle()) {
      DepthFirstOrder dfs = new DepthFirstOrder(G);
      order = dfs.reversePost();
    }
  }
  
  public Iterable<Integer> order() {
    return order;
  }
  
  public boolean isDAG() {
    return order != null;
  }
}
