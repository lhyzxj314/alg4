package graph;

public abstract class Search {
	protected Graph G;
	protected int s;
	
	public Search(Graph G, int s) {
		this.G = G;
		this.s = s;
	}
	public abstract boolean marked(int v);
	public abstract int count();
}
