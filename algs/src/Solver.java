import java.util.Comparator;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class Solver {
  private SearchNode start;
  private SearchNode twin;
  private SearchNode goal;
  
  // inner wrapper class SearchNode
  private class SearchNode {
    private Board board;
    private SearchNode previous;
    private int manhattan;
    private int move;
    private int priority;
    
    public SearchNode(Board board, SearchNode previous) {
      this.board = board;
      this.previous = previous;
      if (previous == null) {
        this.move = 0;
      } else {
        this.move = previous.move + 1;
      }
      this.manhattan = board.manhattan();
      this.priority = this.move + manhattan;
    }
    
    @Override
    public String toString() {
      return board.toString();
    }
  }
  
  private static class SearchNodeComparator implements Comparator<SearchNode> {
    @Override
    public int compare(SearchNode n1, SearchNode n2) {
      
      if (n1.priority > n2.priority) return 1;
      if (n1.priority < n2.priority) return -1;
      if (n1.priority == n2.priority && n1.manhattan < n2.manhattan) return -1;
      if (n1.priority == n2.priority && n1.manhattan > n2.manhattan) return 1;
      return 0;
    }
  }
  
  //find a solution to the initial board (using the A* algorithm)
  /**
   * @param initial
   */
  public Solver(Board initial) {
    if (initial == null) {
      throw new NullPointerException("Input can't be null!");
    }
    
    MinPQ<SearchNode> pq = new MinPQ<SearchNode>(1, new SearchNodeComparator());
    start = new SearchNode(initial, null);
    twin = new SearchNode(initial.twin(), null);
    pq.insert(start);
    while (!pq.isEmpty()) {
      SearchNode sNode = pq.delMin();
      if (pq.isEmpty()) {
        pq.insert(twin);
      }
      if (sNode.board.isGoal()) {       //have found the goal square
        goal = sNode;
        return;
      } else {
        Iterable<Board> neighbors = sNode.board.neighbors();
        for (Board board : neighbors) {
          if (sNode.previous == null) {
            SearchNode neighbor = new SearchNode(board, sNode);
            pq.insert(neighbor);
          }
          if (sNode.previous != null && !board.equals(sNode.previous.board)) {
            SearchNode neighbor = new SearchNode(board, sNode);
            pq.insert(neighbor);
          }
        }
      }
      
    }
  }          
  
  // is the initial board solvable?
  public boolean isSolvable() {
    SearchNode node = goal;
    while (!(node.previous == null)) {
      node = node.previous;
    }
    if (node == start) return true;
    return false;
  } 
  
  // min number of moves to solve initial board; -1 if unsolvable
  public int moves() {
    if (isSolvable()) {
      return goal.move;
    }
    return -1;
  }      
  
  //sequence of boards in a shortest solution; null if unsolvable
  public Iterable<Board> solution() {
    if (isSolvable()) {
      Stack<Board> solution = new Stack<Board>();
      SearchNode node = goal;
      solution.push(node.board);
      while (!(node.previous == null)) {
        solution.push(node.previous.board);
        node = node.previous;
      }
      return solution;
    } 
    return null;
  }    
  
  //solve a slider puzzle (given below)
  public static void main(String[] args) {
    // create initial board from file
    In in = new In(args[0]);
    int N = in.readInt();
    int[][] blocks = new int[N][N];
    for (int i = 0; i < N; i++)
        for (int j = 0; j < N; j++)
            blocks[i][j] = in.readInt();
    Board initial = new Board(blocks);

    // solve the puzzle
    Solver solver = new Solver(initial);

    // print solution to standard output
    if (!solver.isSolvable())
        StdOut.println("No solution possible");
    else {
        StdOut.println("Minimum number of moves = " + solver.moves());
        for (Board board : solver.solution())
            StdOut.println(board);
    }
  }
}
