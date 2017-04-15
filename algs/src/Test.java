import java.util.Scanner;

import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

public class Test {
  public static void main(String[] args) {
    RectHV rect = new RectHV(0.0, 0.0, 1.0, 1.0);
    
    RectHV rec = new RectHV(0.1, 0.1, 0.1, 0.7);
    rec.draw();
    StdDraw.show(0);
  }
}