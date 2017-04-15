import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

public class Board {
    private final int N;
    private final char[] blockArray;
    private int position0;
    //private int priorityChange;
    
    /* 
     * construct a board from an N-by-N array of blocks
     * (where blocks[i][j] = block in row i, column j)
     */ 
    public Board(int[][] blocks) {
      this.N = blocks.length;
      blockArray = new char[N*N];
      for (int i = 0; i < N; ++i) {
        for (int j = 0; j < N; ++j) {
          blockArray[i*N + j] = (char) blocks[i][j];
          if (blocks[i][j] == 0) {
            position0 = i*N + j;
          }
        }
      }
    }          
       
    // board dimension N
    public int dimension() {
      return N;
    }               
    
    // transform charArray to intArray
    private int[][] charArrayToIntArray(char[] array) {
      int length = array.length;
      int N1 = (int) Math.sqrt(length);
      int[][] blocks = new int[N1][N1];
      for (int i = 0; i < N1; ++i) {
        for (int j = 0; j < N1; ++j) {
          blocks[i][j] = (int) array[i*N1 + j];
        }
      }
      return blocks;
    }
    
    private int[] getPositionByNum(int num) {
      int[] pos = new int[2];
      if (num == 0) {
        pos[0] = N - 1;
        pos[1] = N - 1;
        return pos;
      }
      
      int y1 = (num - 1) / N;
      int x1 = 0;
      if (num % N == 0) {
        x1 = N - 1;
      } else {
        x1 = num % N - 1;
      }
      pos[0] = x1;
      pos[1] = y1;
      return pos;
    }
    
    
    //number of blocks out of place
    public int hamming() {
      int count = 0;
      for (int i = 0; i < N; ++i) {
        for (int j = 0; j < N; ++j) {
          int num = (int) blockArray[i*N + j];
          if (num != 0 && num != i*N + j + 1) {
            count++;
          }
        }
      }
      return count;
    }       
    
    // sum of Manhattan distances between blocks and goal
    public int manhattan() {
      int sum = 0;
      for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
          int num = (int) blockArray[i*N + j];
          if (num != 0) {
            int distance = 0;
            int[] pos = getPositionByNum(num);
            int x = pos[0];
            int y = pos[1];
            distance = Math.abs(x - j) + Math.abs(y - i);
            sum += distance;
          }
        }
      }
      return sum;
    }           
    
    // is this board the goal board?
    public boolean isGoal() {
      for (int i = 0; i < N*N - 1; ++i) {
        int num = (int) blockArray[i];
        int x1 = i % N;
        int y1 = i / N;
        if ((y1*N + x1 + 1) != num) return false;
      }
      return true;
    }
    
    // a board that is obtained by exchanging two adjacent blocks in the same row
    public Board twin() {
      char[] newBlockArray = blockArray.clone();
      if (newBlockArray[0] != 0 && newBlockArray[1] != 0) {
        char temp = newBlockArray[0];
        newBlockArray[0] = newBlockArray[1];
        newBlockArray[1] = temp;
      } 
      else if (newBlockArray[N] != 0 && newBlockArray[N + 1] != 0) {
        char temp = newBlockArray[N];
        newBlockArray[N] = newBlockArray[N + 1];
        newBlockArray[N + 1] = temp;
      }
      int[][] blocks = charArrayToIntArray(newBlockArray);
      Board twin = new Board(blocks);
      return twin;
    }   
    
    // does this board equal y?
    public boolean equals(Object y) {
      if (y == null) return false;
      if (y == this) return true;
      if (y.getClass() != this.getClass()) return false;
      
      Board that = (Board) y;
      if (this.N != that.N) return false;
      else {
        
        for (int i = 0; i < N; ++i) {
          for (int j = 0; j < N; ++j) {
            if (this.blockArray[i*N + j] != that.blockArray[i*N + j]) {
              return false;
            }
          }
        }
      }
      return true;
    }        
    
    // all neighboring boards
    public Iterable<Board> neighbors() {
      // compute position of the blank square in 2D array
      int x = position0 % N;
      int y = position0 / N;
      
      Queue<Board> neighbors = new Queue<Board>();
      if (y > 0) {
        char[] newblockArray = blockArray.clone();
        int[][] newBlocks = charArrayToIntArray(newblockArray);
        newBlocks[y][x] = newBlocks[y - 1][x];
        newBlocks[y - 1][x] = 0;
        Board neighbor = new Board(newBlocks);
        
       /* int num = newBlocks[y - 1][x];
        int[] pos = getPositionByNum(num);
        int y1 = pos[1];
        if ((y - 1) < y1)     
          neighbor.priorityChange = -1;
        else 
          neighbor.priorityChange = 1; */
        
        neighbors.enqueue(neighbor);
      }
      if (x > 0) {
        char[] newblockArray = blockArray.clone();
        int[][] newBlocks = charArrayToIntArray(newblockArray);
        newBlocks[y][x] = newBlocks[y][x - 1];
        newBlocks[y][x - 1] = 0;
        Board neighbor = new Board(newBlocks);
        
       /* int num = newBlocks[y][x - 1];
        int[] pos = getPositionByNum(num);
        int x1 = pos[1];
        if ((x - 1) < x1) 
          neighbor.priorityChange = -1;
        else
          neighbor.priorityChange = 1;*/
        
        neighbors.enqueue(neighbor);
      }
      if (y < N - 1) {
        char[] newblockArray = blockArray.clone();
        int[][] newBlocks = charArrayToIntArray(newblockArray);
        newBlocks[y][x] = newBlocks[y + 1][x];
        newBlocks[y + 1][x] = 0;
        Board neighbor = new Board(newBlocks);
        
       /* int num = newBlocks[y + 1][x];
        int[] pos = getPositionByNum(num);
        int y1 = pos[1];
        if ((y + 1) > y1)
          neighbor.priorityChange = -1;
        else 
          neighbor.priorityChange = 1;*/
        
        neighbors.enqueue(neighbor);
      }
      if (x < N - 1) {
        char[] newblockArray = blockArray.clone();
        int[][] newBlocks = charArrayToIntArray(newblockArray);
        newBlocks[y][x] = newBlocks[y][x + 1];
        newBlocks[y][x + 1] = 0;
        Board neighbor = new Board(newBlocks);
        
        /*int num = newBlocks[y][x + 1];
        int[] pos = getPositionByNum(num);
        int x1 = pos[1];
        if ((x + 1) > x1)
          neighbor.priorityChange = -1;
        else 
          neighbor.priorityChange = 1;*/
        
        neighbors.enqueue(neighbor);
      }
      return neighbors;
    }   
    
    // string representation of this board (in the output format specified below)
    public String toString() {
      StringBuilder s = new StringBuilder();
      s.append(N + "\n");
      for (int i = 0; i < N; ++i) {
        for (int j = 0; j < N; ++j) {
          s.append(String.format("%2d ", (int) blockArray[i*N + j]));
        }
        s.append("\n");
      }
      return s.toString();
    }
        
    // unit tests (not graded)
    public static void main(String[] args) {
      int[][] blocks = new int[][] {{1, 2, 3}, {4, 5, 6}, {7, 8, 0}};
      Board a = new Board(blocks);
      StdOut.println("Manhattan:" + a.manhattan());
      StdOut.println(a.isGoal());
      Iterable<Board> neighbors = a.neighbors();
      int size = ((Queue<Board>) neighbors).size();
      StdOut.println("neighbors" + "("+ size + "):");
      for (Board elem : neighbors) {
        StdOut.println("Manhattan:" + elem.manhattan());
        StdOut.println(elem);
      }
      /*Board b = a.twin();
      StdOut.println("Manhattan:" + b.manhattan());
      StdOut.println(b);
      StdOut.println(a.isGoal());
      StdOut.println(b.isGoal());*/
     
    }

}