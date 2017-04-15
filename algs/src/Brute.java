import java.util.Arrays;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class Brute {
  
  public static void main(String[] args) {
    
    StdDraw.setXscale(0, 32768);
    StdDraw.setYscale(0, 32768);
    StdDraw.show(0);
    StdDraw.setPenRadius(0.01);  // make the points a bit larger
    
    String filename = args[0];
    In in = new In(filename);
    int N = in.readInt();
    
    Point[] points = new Point[N];
    for (int i = 0; i < N; ++i) {
      int x = in.readInt();
      int y = in.readInt();
      points[i] = new Point(x, y);
      points[i].draw();
    }
    StdDraw.show(0);
    
    // reset the pen radius
    StdDraw.setPenRadius();
    for (int i = 0; i < N; ++i) {
      for (int j = i + 1; j < N; ++j) {
        for (int k = j + 1; k < N; ++k) {
          
          if (points[i].slopeTo(points[j]) != points[j].slopeTo(points[k])) {
            continue;
          }
          
          for (int m = k + 1; m < N; ++m) {
            
            if (points[j].slopeTo(points[k]) == points[k].slopeTo(points[m])) {
              
              Point[] p = new Point[] {points[i], points[j], points[k], points[m]};
              Arrays.sort(p);     // find endpoints by sort
              StdOut.println(p[0] + " -> " + p[1] + " -> " 
                             + p[2] + " -> " + p[3]);
              p[0].drawTo(p[3]);
              StdDraw.show(0);
            }
            
          }
        
        }
      }
    }
    
  }
}
