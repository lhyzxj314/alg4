package graph;

/**
 * 用深度优先搜索算法判断节点连通性
 * @author xiaojun
 * @version 1.0.0
 * @date 2017年4月29日
 */
public class DepthFirstSearch extends Search {
	private boolean[] marked;
	private int count;
	
	public DepthFirstSearch(Graph G, int s) {
		super(G, s);
		marked = new boolean[G.V()];
		dfs(G, s);
	}
	
	// 深度优先搜索算法
	private void dfs(Graph G, int s) {
		marked[s] = true;
		count++;
		for (Integer w : G.adj(s))
			if (!marked[w]) dfs(G, w);
		return;
	}
	
	@Override
	public boolean marked(int v) {
		return marked[v];
	}

	@Override
	public int count() {
		return count;
	}
}
