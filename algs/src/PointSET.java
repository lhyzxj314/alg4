
import java.util.TreeSet;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.RectHV;

public class PointSET {
	   private TreeSet<Point2D> set;
	   
	   public PointSET() {   // construct an empty set of points 
		   set = new TreeSet<Point2D>();
	   }
	   
	   public boolean isEmpty() {   // is the set empty? 
		   return set.size() == 0;
	   }   
	   
	   public int size() {             // number of points in the set 
		   return set.size();
	   }  
	   
	   public void insert(Point2D p) {  // add the point to the set (if it is not already in the set)
		   if (p == null) throw new NullPointerException(p +" can't be null!");
		   set.add(p);
	   }  
	   
	   public boolean contains(Point2D p) { // does the set contain point p? 
		   if (p == null) throw new NullPointerException(p +" can't be null!");
		   return set.contains(p);
	   }  
	   
	   public void draw() {   // draw all points to standard draw 
		   for (Point2D p : set) {
			   p.draw();
		   }
	   }        
	   
	   public Iterable<Point2D> range(RectHV rect) {  // all points that are inside the rectangle 
		   if (rect == null) throw new NullPointerException(rect +" can't be null!");
		   Queue<Point2D> queue = new Queue<Point2D>();
		   for (Point2D p : set) {
			   if (rect.contains(p)) queue.enqueue(p);
		   }
		   return queue;
	   }       
	   
	   public Point2D nearest(Point2D p) {      // a nearest neighbor in the set to point p; null if the set is empty 
		   if (p == null) throw new NullPointerException(p +" can't be null!");
		   
		   double minDistance = Double.MAX_VALUE;
		   Point2D target = null;
		   for (Point2D that : set) {
			   double distance = p.distanceTo(that);
			   if (distance < minDistance) {
				   minDistance = distance;
				   target = that;
			   }
		   }
		   return target;
	   }

	   public static void main(String[] args) {  // unit testing of the methods (optional) 
		   
	   }                
	
	
}
