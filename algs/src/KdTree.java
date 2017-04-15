import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

public class KdTree {
	 
	   private static class Node {
		   private Point2D value;
		   private RectHV rect;    // the rectangle corresponding to the point
		   private Node left;      // left subtree
		   private Node right;     // right subtree
		   private boolean isOdd;  // tree level is odd or even
		   
		   public Node(Point2D value, RectHV rectangle, boolean isOdd) {
			   this.value = value;
			   this.rect = rectangle;
			   this.isOdd = isOdd;
		   }
	   }
	   
	   private int size;
	   private Node root;
	   
	   public KdTree() {   // construct an empty set of points 
		   size = 0;
		   root = null;
	   }   
	   
	   public boolean isEmpty() {   // is the set empty? 
		   return size == 0;
	   }      
	   
	   public int size() {          // number of points in the set 
		   return size;
	   }   
	   
	   public void insert(Point2D p) {             // add the point to the set (if it is not already in the set)
	     
		   if (p == null) throw new NullPointerException(p + "can't be null!");
		   root = put(root, p, true, new RectHV(0.0, 0.0, 1.0, 1.0));
		   
	   }  
	   
	   public boolean contains(Point2D p) {        // does the set contain point p? 
	     
		   if (p == null) throw new NullPointerException(p + "can't be null!");
		   return get(p);
		   
	   }      
	   
	   public void draw() {                        // draw all points to standard draw 
	     
		   if (root == null) return;
		   preOrderTraverse(root);
		   
	   }        
	   
	   // all points that are inside the rectangle 
	   public Iterable<Point2D> range(RectHV rect) {  
	     
		   if (rect == null) throw new NullPointerException(rect +" can't be null!");
		   
		   Queue<Point2D> queue = new Queue<Point2D>();
		   rangeSearch(rect, root, queue);
		   
		   return queue;
		   
	   } 
	   
	   // a nearest neighbor in the set to point p; null if the set is empty 
	   public Point2D nearest(Point2D p) {     
	     
	     if (p == null) throw new NullPointerException(p + "can't be null!");
	     
	     Double minDisSquare = Double.MAX_VALUE;
	     Node node = searchForNearest(root, p, minDisSquare);
		   return node.value;
		   
	   }      
	   
	   private Node searchForNearest(Node node, Point2D p, Double minDisSquare) {
	     
	     Node[] candidates = new Node[3];
	     Node target = null;
	     
	     // examine the root 
	     if (node == null)  return target;
	     else if (p.distanceSquaredTo(node.value) < minDisSquare) {
	       
	       candidates[0] = node;
	       minDisSquare = p.distanceSquaredTo(node.value);
	       
	     }
	     
	     // examine the subtrees
	     if (node.left == null && node.right == null) {
	       
	       candidates[1] = null;
	       candidates[2] = null;
	       
	     }
	     else 
	     if (node.left != null && node.right == null) {
	       
	       candidates[1] = searchForNearest(node.left, p, minDisSquare);
         candidates[2] = null;
         
	     } 
	     else 
	     if (node.left == null && node.right != null) {
	       
	       candidates[1] = null;
         candidates[2] = searchForNearest(node.right, p, minDisSquare);
         
	     } 
	     else
	     if (node.left != null && node.right != null) {
	       
	       if (node.left.rect.distanceSquaredTo(p) < node.right.rect.distanceSquaredTo(p)) {
	         
	         candidates[1] = searchForNearest(node.left, p, minDisSquare);
	         if (node.right.rect.distanceSquaredTo(p) < minDisSquare || 
	             node.right.rect.distanceSquaredTo(p) == minDisSquare) {
	           
	           candidates[2] = searchForNearest(node.right, p, minDisSquare);
	           
	         }
	         
	       } 
	       else {
	         
	         candidates[2] = searchForNearest(node.right, p, minDisSquare);
	         if (node.left.rect.distanceSquaredTo(p) < minDisSquare|| 
               node.left.rect.distanceSquaredTo(p) == minDisSquare) {
	           
	           candidates[1] = searchForNearest(node.left, p, minDisSquare);
	           
	         }
	         
	       }
	       
	     } 
	     
	     // find the nearest point in three candidates
	     double minDistance = Double.MAX_VALUE;
	     for (int i = 0; i < candidates.length; ++i) {
	       
	       if (candidates[i] != null && p.distanceSquaredTo(candidates[i].value) < minDistance) {
	         
	           minDistance = p.distanceSquaredTo(candidates[i].value);
	           target = candidates[i];
	           
	       }
	       
	     }
	     return target;
	   }
	   
	   private void preOrderTraverse(Node node) {
		   if (node == null) return;
		   
		   StdDraw.setPenColor(StdDraw.BLACK);
       StdDraw.setPenRadius(.01);
       node.value.draw();
       
       StdDraw.setPenRadius();
		   if (node.isOdd) {
		     
		     StdDraw.setPenColor(StdDraw.RED);
		     Point2D p1 = new Point2D(node.value.x(), node.rect.ymin());
		     Point2D p2 = new Point2D(node.value.x(), node.rect.ymax());
		     p1.drawTo(p2);
		     
		   } else {
		     
		     StdDraw.setPenColor(StdDraw.BLUE);
		     Point2D p1 = new Point2D(node.rect.xmin(), node.value.y());
         Point2D p2 = new Point2D(node.rect.xmax(), node.value.y());
         p1.drawTo(p2);
         
		   }
		   
		   preOrderTraverse(node.left);
		   preOrderTraverse(node.right);
	   }
	   
	   // search a Point from the 2D-tree
	   private boolean get(Point2D p) {
		   Node node = root;
		   
		   while (node != null) {
		     
		     double key1 = node.isOdd ? p.x() : p.y();
         double key2 = node.isOdd ? node.value.x() : node.value.y();
			   
			   if (key1 < key2) {
			     
				   node = node.left;
				   
			   } else if (key1 > key2) {
			     
				   node = node.right;  
				   
			   } else if (p.equals(node.value)) {
			     
					   return true;
					   
				   } else {
				     
					   node = node.right;
					   
				 }
		   }
		   return false;
	   }
	   
	   // insert a Point to the 2D-tree
	   private Node put(Node node, Point2D p, boolean isOdd, RectHV rect) {
	     
		   if (node == null) {
		     size++;
			   return new Node(p, rect, isOdd);
		   }
		   
		   // tree level is odd
		   if (isOdd) {
		     
		     if (p.x() < node.value.x()) {
		       
		       RectHV newRect = new RectHV(rect.xmin(), rect.ymin(), node.value.x(), rect.ymax());
		       node.left = put(node.left, p, !isOdd, newRect);
		       
		     } else if (p.x() > node.value.x()) {
		       
		       RectHV newRect = new RectHV(node.value.x(), rect.ymin(), rect.xmax(), rect.ymax());
		       node.right = put(node.right, p, !isOdd, newRect);
		       
		     } else if (p.equals(node.value)) {
		       
		       node.value = p;
		       
		     } else {
		       
		       RectHV newRect = new RectHV(node.value.x(), rect.ymin(), rect.xmax(), rect.ymax());
		       node.right = put(node.right, p, !isOdd, newRect);
		       
		     }
		     
		   }
		   
		   // tree level is even
		   if (!isOdd) {
		     
		     if (p.y() < node.value.y()) {
		       
		       RectHV newRect = new RectHV(rect.xmin(), rect.ymin(), rect.xmax(), node.value.y());
           node.left = put(node.left, p, !isOdd, newRect);
           
         } else if (p.y() > node.value.y()) {
           
           RectHV newRect = new RectHV(rect.xmin(), node.value.y(), rect.xmax(), rect.ymax());
           node.right = put(node.right, p, !isOdd, newRect);
           
         } else if (p.equals(node.value)) {
           
           node.value = p;
           
         } else {
           
           RectHV newRect = new RectHV(rect.xmin(), node.value.y(), rect.xmax(), rect.ymax());
           node.right = put(node.right, p, !isOdd, newRect);
           
         }
		     
		   }
		   
		   return node;
	   }
	   
	   // range search the points in the rectangle
     private void rangeSearch(RectHV rect, Node node, Queue<Point2D> queue) {
       
       if (node == null)              return;
       
       if (rect.contains(node.value)) queue.enqueue(node.value);
       
       if (node.isOdd) {
         
         RectHV splitline = new RectHV(node.value.x(), node.rect.ymin(), node.value.x(), node.rect.ymax()); 
         if (rect.intersects(splitline)) {
           
           rangeSearch(rect, node.left, queue);
           rangeSearch(rect, node.right, queue);
           
         } else if (rect.xmin() > node.value.x()) {
           
           rangeSearch(rect, node.right, queue);
           
         } else if (rect.xmax() < node.value.x()) {
           
           rangeSearch(rect, node.left, queue);
           
         }
         
       }
       
       if (!node.isOdd) {
         
         RectHV splitline = new RectHV(node.rect.xmin(), node.value.y(), node.rect.xmax(), node.value.y()); 
         if (rect.intersects(splitline)) {
           
           rangeSearch(rect, node.left, queue);
           rangeSearch(rect, node.right, queue);
           
         } else if (rect.ymin() > node.value.y()) {
           
           rangeSearch(rect, node.right, queue);
           
         } else if (rect.ymax() < node.value.y()) {
           
           rangeSearch(rect, node.left, queue);
           
         }
         
       }
       
     } 
	   
	   public static void main(String[] args) {     // unit testing of the methods (optional) 
	     RectHV rect = new RectHV(0.0, 0.0, 1.0, 1.0);
	     KdTree t = new KdTree();
	     Point2D p1 = new Point2D(0.4, 0.5);
	     Point2D p2 = new Point2D(0.3, 0.4);
	     Point2D p3 = new Point2D(0.6, 0.7);
	     Point2D p4 = new Point2D(0.8, 0.4);
	     Point2D p5 = new Point2D(0.2, 0.3);
	     Point2D p6 = new Point2D(0.1, 0.3);
	     t.insert(p1);
	     t.insert(p2);
	     t.insert(p3);
	     t.insert(p4);
	     t.insert(p5);
	     t.insert(new Point2D(0.2, 0.3));
	     //t.insert(p5);
	     rect.draw();
	     System.out.println(t.contains(p6));
	     System.out.println(t.contains(p1));
	     System.out.println(t.contains(p2));
	     System.out.println(t.contains(p3));
	     
	     Point2D p = new Point2D(0.85, 0.4);
	     System.out.println(t.nearest(p));
	     System.out.println(t.size());
	     t.draw();
	   }    
}
