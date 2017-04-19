package exercise;

import edu.princeton.cs.algs4.StdRandom;

public class KendallTau {
  
  public static int inversion(Integer[] a, Integer[] b) {
    if (a.length != b.length)
      throw new IllegalArgumentException();
    
    int len = a.length;
    Integer[] ainv = new Integer[len];
    Integer[] bnew = new Integer[len];
    // 很妙
    for (int i = 0; i < len; i++)
      ainv[a[i]] = i;
    for (int i = 0; i < len; i++)
      bnew[i] = ainv[b[i]];
    
    return count(bnew, 0, len - 1);
  }
  
  private static int count(Integer[] a, int lo, int hi) {
    int inversion = 0;
    
    int mid = lo + (hi - lo) / 2;
    //inversion += count(a, )
    return 0;
  }
  
  
  private static Integer[] permution(int len) {
    Integer[] arr = new Integer[len];
    for (int i = 0; i < len; i++)
      arr[i] = i;
    StdRandom.shuffle(arr);
    return arr;
  }
  
  public static void main(String[] args) {
    int n = Integer.parseInt(args[0]);
    Integer[] a = permution(n);
    Integer[] b = permution(n);
    int res = inversion(a, b);
    System.out.println(res);
  }
}
