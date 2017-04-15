import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Subset {

  public static void main(String[] args) {
    int K = Integer.parseInt(args[0]);
    int count = 0;
    RandomizedQueue<String> rq = new RandomizedQueue<String>();
    while (!StdIn.isEmpty()) {
      String s = StdIn.readString();
      ++count;           // count the number of input items
      if (count <= K) {  // fill the RandomiedQueue
        rq.enqueue(s);
      } 
      /*
       * When count is bigger than K, replace the RandomiedQueue 
       * with gradually decreasing probability.
       * At the end, every items in standard input
       * has the same probability K / length(input array)
       * staying in the RandomiedQueue.
       * */ 
      if (count > K) {   
        int i = StdRandom.uniform(count);
        if (i < K) {
          rq.dequeue();
          rq.enqueue(s);
        }
      }
    }
    
    while (!rq.isEmpty()) {
      StdOut.println(rq.dequeue());
    }
  }

}
