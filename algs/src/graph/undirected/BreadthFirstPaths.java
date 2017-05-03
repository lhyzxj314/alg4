package graph.undirected;

import java.util.LinkedList;

import edu.princeton.cs.algs4.Stack;

/**
 * 使用广度优先搜索算法实现 单点最短路径搜寻
 * @author xshrimp
 * 2017年4月30日
 */
public class BreadthFirstPaths extends Paths {
  private boolean[] marked;
  private int[] edgeTo;
  
  public BreadthFirstPaths(Graph G, int s) {
    super(G, s);
    marked = new boolean[G.V()];
    edgeTo = new int[G.V()];
    
    bfs(G);
  }
  
  private void bfs(Graph G) {
    // 使用队列记录已被遍历过的节点，出队顺序为先进先出
    LinkedList<Integer> queue = new LinkedList<Integer>();
    queue.add(s);   // 入队
    marked[s] = true;
    
    while (!queue.isEmpty()) {
      int v = queue.removeFirst();  // 出队
      for (int w : G.adj(v)) {
        if (!marked[w]) {
          marked[w] = true;
          queue.add(w);
          edgeTo[w] = v;
        }
      }
    }
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
