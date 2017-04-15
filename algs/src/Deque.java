import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Deque<Item> implements Iterable<Item> {
  private Node first;
  private Node last;
  private int length;
  
  // inner class node
  private class Node {
    private Item elem;
    private Node next;
    private Node previous;
    
    public Node(Item a) {
      elem = a;
      next = null;
      previous = null;
    }
  }
  
  public Deque() {
    length = 0;
    // create a header node
    Node node1 = new Node(null);
    first = node1;
    // create a nail node
    Node node2 = new Node(null);
    last = node2;
    // link the header and nail node
    first.next = last;
    last.previous = first;
  }
  
  public boolean isEmpty() {
    return first.next == last;
  }
  
  public int size() {
    return length;
  }
  
  public void addFirst(Item obj) {
    if (obj == null) {
      throw new NullPointerException("Could not add a null to the deque!"); 
    } 
    Node node = new Node(obj);
    node.next = first.next;
    first.next.previous = node;
    node.previous = first;
    first.next = node;
    ++length;
  }
  
  public void addLast(Item obj) {
    if (obj == null) {
      throw new NullPointerException("Could not add a null to the deque!");
    }
    Node node = new Node(obj);
    node.previous = last.previous;
    last.previous.next = node;
    node.next = last;
    last.previous = node;
    ++length;
  }
  
  public Item removeFirst() {
    if (isEmpty()) {
      throw new NoSuchElementException("Deque is empty!");
    }
    Node node = first.next;
    first.next = node.next;
    node.next.previous = first;
    --length;
    return node.elem;
  }
  
  public Item removeLast() {
    if (isEmpty()) {
      throw new NoSuchElementException("Deque is empty!");
    }
    Node node = last.previous;
    last.previous = node.previous;
    node.previous.next = last;
    --length;
    return node.elem;
  }
  
  
  @Override
  public Iterator<Item> iterator() {
    return new ListIterator();
  }
  
  private class ListIterator implements Iterator<Item> {
    private Node current = first.next;

    @Override
    public boolean hasNext() {
      return current != last;
    }
    
    public void remove() {
      throw new UnsupportedOperationException("Remove operation is not supported!");
    } 

    @Override
    public Item next() {
      if (current == last) {
        throw new NoSuchElementException("No more element!");
      }
      Item item = current.elem;
      current = current.next;
      return item;
    }
  }
  
  public static void main(String[] args) {
    Deque<Integer> dq = new Deque<Integer>();
    int a = 0;
    while (a != -1) {
      a = StdIn.readInt();
      if (a != -1) {
        dq.addLast(a);
      }
    }
    
    for (Integer i : dq) {
      StdOut.print(i + " ");
    }
    
    StdOut.println("size = " + dq.size());
    
    while (!dq.isEmpty()) {
      a = dq.removeLast();
      StdOut.print(a + " ");
    }
    StdOut.println("size = " + dq.size() + " ");
    
    Iterator<Integer> it = dq.iterator();
    while (it.hasNext()) {
      a = it.next();
      StdOut.print(a);
    }
  }

}
