package graph.edge_weighted;

/**
 * 带权重的边
 * @author xshrimp
 * 2017年5月5日
 */
public class Edge implements Comparable<Edge> {
  private final int v;
  private final int w;
  private final double weight;
  
  public Edge(int v, int w, double weight) {
    this.v = v;
    this.w = w;
    this.weight = weight;
  }
  
  public double weight() {
    return weight;
  }
  
  public int either() {
    return v;
  }
  
  public int other(int vertext) {
    if (v == vertext) return w;
    else if (w == vertext) return v;
    else throw new RuntimeException("不一致的边");
  }

  @Override
  public int compareTo(Edge that) {
    if (this.weight < that.weight)
      return -1;
    else if (this.weight > that.weight)
      return 1;
    else
    return 0;
  }
  
  @Override
  public String toString() {
    return String.format("%d-%d %.2f", w, v, weight);
   }
}
