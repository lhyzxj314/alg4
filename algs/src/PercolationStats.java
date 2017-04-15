import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.Stopwatch;

public class PercolationStats {
    private double[] fractions; 
  
    public PercolationStats(int N, int T) {           // perform T independent experiments on an N-by-N grid
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException("N or T is less than 0!");
        }
        fractions = new double[T];
        
        // get the the threshold of each experiment
        double sum = (double) N*N;
        for (int k = 0; k < T; ++k) {
            Percolation perc = new Percolation(N);
            double opened = 0;
            while (!perc.percolates()) {
                int i = StdRandom.uniform(1, N + 1);
                int j = StdRandom.uniform(1, N + 1);
                if (!perc.isOpen(i, j)) {
                    perc.open(i, j);
                    opened++;
                }
            }
            fractions[k] = opened / sum;
        }
    }
    
    public double mean() {                       // sample mean of percolation threshold
       double mean = StdStats.mean(fractions);
       return mean;
    }
    
    public double stddev() {                   // sample standard deviation of percolation threshold
        double deviation = StdStats.stddev(fractions);
        return deviation;
    }
   
    public double confidenceLo() {        // low  endpoint of 95% confidence interval
      double sqrT = Math.sqrt(fractions.length);
      double lo = mean() - (1.96 * stddev() / sqrT);
      return lo;
    }            
    
    public double confidenceHi() {            // high endpoint of 95% confidence interval
      double sqrT = Math.sqrt(fractions.length);
      double hi = mean() + (1.96 * stddev() / sqrT);
      return hi;
    }             
    
    public static void main(String[] args) {
       int N = Integer.parseInt(args[0]);
       int T = Integer.parseInt(args[1]);
       Stopwatch sw = new Stopwatch();
       PercolationStats perst = new PercolationStats(N, T);
       double mean = perst.mean();
       double dev = perst.stddev();
       double lo = perst.confidenceLo();
       double hi = perst.confidenceHi();
       StdOut.println("mean     = " + mean);
       StdOut.println("stddev = " + dev);
       StdOut.println("95% confidence interval  = " + lo + ", " + hi);
       StdOut.println("timecost is:  " + sw.elapsedTime());
    }
    
}
