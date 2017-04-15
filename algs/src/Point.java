import java.util.Comparator;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class Point implements Comparable<Point> {

    // compare points by slope
    public final Comparator<Point> SLOPE_ORDER = new SlopeComparator(this);  
    
    private static class SlopeComparator implements Comparator<Point> {  
      
      private Point outer;
      
      public SlopeComparator(Point outer) {
       this.outer = outer; 
      }
      
      @Override
      public int compare(Point p1, Point p2) {
       
        if (p1.slopeTo(outer) < p2.slopeTo(outer)) {
          return -1;
        } else if (p1.slopeTo(outer) > p2.slopeTo(outer)) {
          return 1;
        }
        return 0;
      }
      
    }      

    private final int x;                              // x coordinate
    private final int y;                              // y coordinate

    // create the point (x, y)
    public Point(int x, int y) {
        /* DO NOT MODIFY */
        this.x = x;
        this.y = y;
    }
    
    // plot this point to standard drawing
    public void draw() {
        /* DO NOT MODIFY */
        StdDraw.point(x, y);
    }

    // draw line between this point and that point to standard drawing
    public void drawTo(Point that) {
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    // slope between this point and that point
    public double slopeTo(Point that) {
        if (this.x != that.x && this.y != that.y) {
          return (double) (that.y - this.y) / (that.x - this.x);
        } else if (this.x == that.x && this.y != that.y) {
          return Double.POSITIVE_INFINITY;
        } else if (this.x != that.x && this.y == that.y) {
          return +0.0;
        }
        return Double.NEGATIVE_INFINITY;
    }

    // is this point lexicographically smaller than that one?
    // comparing y-coordinates and breaking ties by x-coordinates
    public int compareTo(Point that) {
         if (this.y < that.y) {
           return -1;
         } else if (this.y > that.y) {
           return 1;
         } else if (this.x < that.x) {
           return -1;
         } else if (this.x > that.x) {
           return 1;
         }
         return 0; // when this.x = that.x and this.y = that.y
    }

    // return string representation of this point
    public String toString() {
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }

    // unit test
    public static void main(String[] args) {
          double n = (0 - 16000f) / (0 - 23000f);
          StdOut.println(n);
    }
}