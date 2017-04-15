import edu.princeton.cs.algs4.QuickFindUF;

public class Percolation {
    //private WeightedQuickUnionUF qu;      // a union-find object
    private QuickFindUF qu;        // a quick-find object
    private int N;                 // N by N grid
    private boolean[] flags;       // flags of sites:  OPEN=true  BLOCKED=false
    
    public Percolation(int N) {
        if (N < 1)   {  
            throw new IllegalArgumentException("System size " + N  +" is less than 1!"); 
        }
        this.N = N;
        //qu = new WeightedQuickUnionUF(N*N + 2);
        qu = new QuickFindUF(N*N + 2);
        flags = new boolean[N*N + 2];            // All sites are blocked at start
        for (int i = 1; i <= N; ++i) {           // Point sites on first row to the vitual top
            qu.union(0, i);
        } 
    }
    
    public void open(int i, int j) {
        validate(i, j);
        int arrayIndex = transferIndex(i, j);
        
        if (!flags[arrayIndex]) {
            flags[arrayIndex] = true;         // open the site
            
            if (i >= 2 && flags[ transferIndex(i - 1, j) ]) {              // Site is not on the top
                qu.union(arrayIndex, transferIndex(i - 1, j));
            } 
            if (j >= 2 && flags[ transferIndex(i, j - 1) ]) {               // Site is not on the left board
                qu.union(arrayIndex, transferIndex(i, j - 1));
            }
            if (j <= N - 1 && flags[ transferIndex(i, j + 1) ]) {         // Site is not on the right board
                qu.union(arrayIndex, transferIndex(i , j + 1));
            } 
            if (i <= N - 1 && flags[ transferIndex(i + 1, j) ]) {     // the site is not on bottom
                qu.union(arrayIndex, transferIndex(i + 1, j));
            } else if (i == N) {
                qu.union(arrayIndex, flags.length - 1);
            }
        }
    }
    
    public boolean isOpen(int i, int j) {
        validate(i, j);
        int arrayIndex = transferIndex(i,  j);
        return flags[arrayIndex];
    }
    
    public boolean isFull(int i, int j) {
        validate(i, j);
        int arrayIndex = transferIndex(i, j);
        if (flags[arrayIndex]) {
            return qu.connected(0, arrayIndex);
        } else {
            return false;
        }
    }
    
    public boolean percolates() {
        return qu.connected(0, flags.length - 1);
    }
    
    private void validate(int i, int j) {
        if (i < 1 || i > N || j < 1 || j > N) {
            throw new IndexOutOfBoundsException("Index i or j is not between 1 and " + N + "!");
        }
    }
    
    /***************************************************************************
     * tranfer the index i, j to the corresponding index of array flags[] 
     ***********************************************************************************/
    private int transferIndex(int i, int j) {
        return N*(i - 1) + j;
    }

}
