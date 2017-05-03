package graph.undirected;

public abstract class Paths {
  protected Graph G;
  protected final int s;
  
  public Paths(Graph G, int s) {
    this.G = G;
    this.s = s;
  }
  
  public abstract boolean hasPathTo(int v);
  public abstract Iterable<Integer> pathTo(int v);
}
