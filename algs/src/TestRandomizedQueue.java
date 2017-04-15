import edu.princeton.cs.algs4.StdOut;

public class TestRandomizedQueue {

  public static void main(String[] args) {
    int a = 9999;
    char s = (char) a;
    int b = (int) s;
    StdOut.println(s);
    StdOut.println(b);
    StdOut.println(Integer.MAX_VALUE);
  }
  
  private static void test() {
    int[] a = new int[]{1, 2, 3, 4, 5};
    int N = a.length;
    int count = 0;
    for (int i = 0; i < N; ++i) {
      for (int j = i + 1; j < N; ++j) {
        for (int k = j + 1; k < N; ++k) {
          StdOut.print("(" + a[i] + ", " + a[j] + ", " + a[k] + ")");
          count++;
        }
      }
    }
    StdOut.println();
    StdOut.println(count);
  }
  
  
  
  
  
  

}
