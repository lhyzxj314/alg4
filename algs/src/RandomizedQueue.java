import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
  private Item[] items;
  private int length = 0;
  private int head = 0;
  private int nail = 0;
  
  public RandomizedQueue() {
    items = (Item[]) new Object[1];
  }
  
  public boolean isEmpty() {
    return length == 0;
  }
  
  public int size() {
    return length;
  }
  
  private void resize(int capacity) {
    Item[] copy = (Item[]) new Object[capacity];
    for (int i = head, j = 0; i <= nail; ++i) {
      if (items[i] != null) {
        copy[j++] = items[i];
      }
    }
    items = copy;
    head = 0;
    if (length == 0) {
      nail = 0;
    } else {
      nail = length - 1;
    }
  }
  
  public void enqueue(Item item) {
    if (item == null) {
      throw new NullPointerException("Could not insert a null into the random-queue!");
    }
    // if going to overflow, resize the array to double size
    if (items.length == size()) {
      resize(items.length * 2);
    }
    // if get to pass the last boundary, reset the head to 0
    if (nail == items.length - 1) {
      for (int i = head, j = 0; i <= nail; ++i, ++j) {
        items[j] = items[i];
        items[i] = null;
      }
      head = 0;
      nail = length - 1;
    }
    // push the item to the last of queue
    items[++nail] = item;
    ++length;
  }
  
  // remove and return a random item
  public Item dequeue() {
    if (isEmpty()) {
      throw new NoSuchElementException("The queue is empty!");
    }
    // uniformly choose a item from queue, remove it
    int index = StdRandom.uniform(head, nail + 1);
    while (items[index] == null) {
      index = StdRandom.uniform(head, nail + 1);
    }
    Item item = items[index];
    items[index] = items[nail];
    items[nail] = null;
    --nail;
    --length;
    // if size is equal to one quarter of arrays' capacity, halve the capacity
    if (length > 0 && length == items.length / 4) {
      resize(items.length / 2);
    }
    return item;
  }

  // return (but do not remove) a random item
  public Item sample() {
    if (isEmpty()) {
      throw new NoSuchElementException("The queue is empty!");
    }
    // uniformly choose a item from queue, return it
    int index = StdRandom.uniform(head, nail + 1);
    while (items[index] == null) {
      index = StdRandom.uniform(head, nail + 1);
    }
    return items[index];
  }
  
  // return an independent iterator over items in random order
  @Override
  public Iterator<Item> iterator() {
    return new QueueIterator();
  }
  
  private class QueueIterator implements Iterator<Item> {
    private int current;
    private Item[] tmp;
    
    public QueueIterator() {
      tmp = (Item[]) new Object[length];
      // store the queue into the array tmp
      for (int i = head, j = 0; i <= nail; ++i) {
        if (items[i] != null) {
          tmp[j++] = items[i];
        }
      }
      // shuffle the tmp array
      for (int i = 0; i < tmp.length; ++i) {
        int index = StdRandom.uniform(i + 1);
        Item b = tmp[index];
        tmp[index] = tmp[i];
        tmp[i] = b;
      }
      current = 0;
    }
    
    @Override
    public boolean hasNext() {
      return current < tmp.length;
    }

    @Override
    public Item next() {
      if (current == tmp.length) {
        throw new NoSuchElementException("No more element!");
      }
      return tmp[current++];
    }
    
    public void remove() {
      throw new UnsupportedOperationException("Remove operation is not supported!");
    } 
  }
  
  public static void main(String[] args) {
    
  }

  
}
