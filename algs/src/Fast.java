import java.util.Arrays;

import edu.princeton.cs.algs4.BinaryDump;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class Fast {

  public static void main(String[] args) {
    StdDraw.setXscale(0, 32768);
    StdDraw.setYscale(0, 32768);
    StdDraw.show(0);
    StdDraw.setPenRadius(0.01);  // make the points a bit larger
    
    String filename = args[0];
    In in = new In(filename);
    int N = in.readInt();
    
    // get the array of points from the input file
    Point[] points = new Point[N];
    for (int i = 0; i < N; ++i) {
      int x = in.readInt();
      int y = in.readInt();
      points[i] = new Point(x, y);
      //points[i].draw();
    }
    StdDraw.show(0);
    StdDraw.setPenRadius();
    
    for (int i = 0; i < N; ++i) {
      
      // copy the original array to the auxiliary array
      Point[] copy = points.clone();
      // choose a point as the invoking point, exchange it with the last element
      Point p = copy[i];
      copy[i] = copy[N - 1];
      copy[N - 1] = p;
      
      /*
       * determine the slope every point makes with the invoking point 
       * and sort the points array by slope
       */
      Arrays.sort(copy, 0, N - 1, p.SLOPE_ORDER);
      
      for (int j = 1, count = 1; j < N - 1; ++j) {
        
        if (copy[j].slopeTo(p) == copy[j - 1].slopeTo(p)) {
          count++;
        } 

        if (copy[j].slopeTo(p) != copy[j + 1].slopeTo(p) || j == N - 2) {
          // find the lineSegment
          if (count >= 3) {
            int length = count + 1;
            int index = j;
            
            Point[] lineSeg = new Point[length];
            lineSeg[0] = copy[N - 1];
            for (int k = 1; k < length; ++k) {
              lineSeg[k] = copy[index--];
            }
            // iff the original point is the smallest in lineSegment, print out the segment
            Arrays.sort(lineSeg);
            if (copy[N - 1].compareTo(lineSeg[0]) == 0) {
              for (int k = 0; k < length; ++k) {
                StdOut.print(lineSeg[k]);
                if (k < length - 1) {
                  StdOut.print(" -> ");
                }
              }
              StdOut.println();
             
              lineSeg[0].drawTo(lineSeg[length - 1]);
              StdDraw.show(0);
            }
            
          }
          count = 1; // reset the number of same slope points
        }
        
      }
      
    }
    
    //StdOut.println("--search end--");
  }
  

}
