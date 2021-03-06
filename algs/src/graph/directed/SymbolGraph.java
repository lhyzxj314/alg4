package graph.directed;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.ST;

/**
 * 符号图数据类型
 * 
 * @author xshrimp
 * 2017年5月4日
 */
public class SymbolGraph {
  private ST<String, Integer> st;  // 符号 -> 索引
  private String[] keys;           // 索引 -> 符号
  private Digraph G;
  
  public SymbolGraph(String stream, String sp) {
    st = new ST<String, Integer>();
    
    // 第一次遍历测试数据，构造索引
    In in = new In(stream);
    while (in.hasNextLine()) {
      String[] a = in.readLine().split(sp);
      for (int i = 0; i < a.length; i++) {
        if (!st.contains(a[i]))
          st.put(a[i], st.size());
      }
    }
    
    keys = new String[st.size()];
    for (String name : st.keys())
      keys[st.get(name)] = name;
    
    G = new Digraph(st.size());
    // 第二次遍历测试数据，构造图
    in = new In(stream);
    while (in.hasNextLine()) {
      String[] a = in.readLine().split(sp);
      int v = st.get(a[0]);
      for (int i = 1; i < a.length; i++)
        G.addEdge(v, st.get(a[i]));
    }
  }
  
  public boolean contains(String s) { return st.contains(s); }
  
  public int index(String s)        { return st.get(s); }
  
  public String name(int v)         { return keys[v]; }
  
  public Digraph graph()            { return G; }
  
}
