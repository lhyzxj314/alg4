package graph.directed;


import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;

/**
 * 基于深度优先搜索的顶点遍历顺序
 * 
 * @author xshrimp
 * 2017年5月4日
 */
public class DepthFirstOrder {
  private boolean[] marked;
  private Queue<Integer> pre;         // 前序排列,dfs的调用顺序
  private Queue<Integer> post;        // 后序排列,dfs的完成顺序
  private Stack<Integer> reversePost; // 逆后序排列，后序排列的逆序
  
  public DepthFirstOrder(Digraph G) {
    marked = new boolean[G.V()];
    pre    = new Queue<Integer>();
    post   = new Queue<Integer>();
    reversePost = new Stack<Integer>();
    for (int v = 0; v < G.V(); v++)
      if (!marked[v])
        dfs(G, v);
  }
  
  private void dfs(Digraph G, int v) {
    marked[v] = true;
    pre.enqueue(v);
    
    for (int w : G.adj(v)) {
      if (!marked[w])
        dfs(G, w);
    }
    
    post.enqueue(v);
    reversePost.push(v);
  }
  
  public Iterable<Integer> pre() { return pre; }
  
  public Iterable<Integer> post() {return post;}
  
  public Iterable<Integer> reversePost() { return reversePost; }
}
