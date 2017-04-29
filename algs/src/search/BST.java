package search;

import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * 二叉搜索树
 * 
 * @author xiaojun
 * @version 1.0.0
 * @param <Key>
 * @param <Value>
 * @date 2017年4月29日
 */
public class BST<Key extends Comparable<Key>, Value> {
	private Node root;

	private class Node {
		private Key key;
		private Value val;
		private Node left;
		private Node right;
		private int N;

		public Node(Key key, Value val, int N) {
			this.key = key;
			this.val = val;
			this.N = N;
		}
		
		public String toString() {
			return key.toString();
		}
	}

	/* ********************************************** 
	 * 符号表基本操作
	 ************************************************/
	public int size() {
		return size(root);
	}

	public Value get(Key key) {
		return get(root, key);
	}

	public void put(Key key, Value val) {
		root = put(root, key, val);
	}

	/* ********************************************** 
	 * 有序性相关操作
	 ************************************************/
	public Key max() {
		return max(root).key;
	}

	public Key min() {
		return min(root).key;
	}

	public Key floor(Key key) {
		Node x = floor(root, key);
		if (x == null)
			return null;
		return x.key;
	}

	public Key ceiling(Key key) {
		Node x = ceiling(root, key);
		if (x == null)
			return null;
		return x.key;
	}

	public Key select(int k) {
		return select(root, k).key;
	}

	public int rank(Key key) {
		return rank(root, key);
	}

	public boolean isEmpty() {
		if (size(root) == 0)
			return true;
		return false;
	}

	public void deleteMin() {
		if (isEmpty())
			throw new NoSuchElementException("Symbol table underflow");
		root = deleteMin(root);
	}

	public void deleteMax() {
		if (isEmpty())
			throw new NoSuchElementException("Symbol table underflow");
		root = deleteMax(root);
	}

	public void delete(Key key) {
		root = delete(root, key);
	}

	/* ********************************************** 
	 * 范围查找操作
	 ************************************************/
	public Iterable<Key> keys() {
		return keys(min(), max());
	}

	public Iterable<Key> keys(Key lo, Key hi) {
		Queue<Key> queue = new Queue<Key>();
		keys(root, queue, lo, hi);
		return queue;
	}
	
	public Iterable<Key> levelOrder() {
		Queue<Key> keys = new Queue<Key>();
		Queue<Node> queue = new Queue<Node>();
		queue.enqueue(root);
		while (!queue.isEmpty()) {
			Node x = queue.dequeue();
			if (x == null) continue;
			keys.enqueue(x.key);
			queue.enqueue(x.left);
			queue.enqueue(x.right);
		}
		return keys;
	}

	private void keys(Node node, Queue<Key> queue, Key lo, Key hi) {
		if (node == null)
			return;
		if (node.key.compareTo(lo) < 0)
			keys(node.right, queue, lo, hi);
		else if (node.key.compareTo(hi) > 0)
			keys(node.left, queue, lo, hi);
		else {
			queue.enqueue(node.key);
			//FIXME
			keys(node.left, queue, lo, hi);
			keys(node.right, queue, lo, hi);
		}
	}

	private Node delete(Node x, Key key) {
		if (x == null)
			return null;
		int cmp = key.compareTo(x.key);
		if (cmp < 0)
			x.left = delete(x.left, key);
		else if (cmp > 0)
			x.right = delete(x.right, key);
		else {
			if (x.left == null)
				return x.right;
			if (x.right == null)
				return x.left;

			Node t = x;
			x = min(x.right);
			x.right = deleteMin(t.right);
			x.left = t.left;
		}
		x.N = size(x.left) + size(x.right) + 1;
		return x;
	}

	private Node deleteMax(Node node) {
		if (node.right == null)
			return node.left;
		node.right = deleteMax(node.right);
		node.N = size(node.left) + size(node.right) + 1;
		return node;
	}

	private Node deleteMin(Node node) {
		if (node.left == null)
			return node.right;
		node.left = deleteMin(node.left);
		node.N = size(node.left) + size(node.right) + 1;
		return node;
	}

	private int rank(Node node, Key key) {
		if (node == null)
			return 0;
		int cmp = key.compareTo(node.key);
		if (cmp < 0)
			return rank(node.left, key);
		else if (cmp > 0)
			return rank(node.right, key) + size(node.left) + 1;
		else
			return size(node.left);
	}

	private Node select(Node node, int k) {
		if (node == null)
			return null;
		int t = size(node.left);
		if (t == k)
			return node;
		else if (t > k)
			return select(node.left, k);
		else
			return select(node.right, k - t - 1);
	}

	private Node ceiling(Node node, Key key) {
		if (node == null)
			return null;
		int cmp = key.compareTo(node.key);
		if (cmp == 0)
			return node;
		else if (cmp > 0)
			return floor(node.right, key);
		else {
			Node x = ceiling(node.left, key);
			if (x != null)
				return x;
			else
				return node;
		}
	}

	private Node floor(Node node, Key key) {
		if (node == null)
			return null;
		int cmp = key.compareTo(node.key);
		if (cmp == 0)
			return node;
		else if (cmp < 0)
			return floor(node.left, key);
		else {
			Node x = floor(node.right, key);
			if (x != null)
				return x;
			else
				return node;
		}
	}

	private Node min(Node node) {
		if (node == null)
			return null;
		if (node.left == null)
			return node;
		else
			return min(root.left);
	}

	private Node max(Node node) {
		if (node == null)
			return null;
		if (node.right == null)
			return node;
		else
			return max(node.right);
	}

	private Value get(Node node, Key key) {
		if (node == null)
			return null;
		int cmp = node.key.compareTo(key);
		if (cmp < 0)
			return get(node.right, key);
		else if (cmp > 0)
			return get(node.left, key);
		else
			return node.val;
	}

	// 递归实现
	private Node put(Node node, Key key, Value val) {
		if (node == null)
			return new Node(key, val, 1);
		int cmp = node.key.compareTo(key);
		if (cmp < 0)
			node.right = put(node.right, key, val);
		else if (cmp > 0)
			node.left = put(node.left, key, val);
		else
			node.val = val;
		node.N = size(node.left) + size(node.right) + 1;
		return node;
	}

	private int size(Node x) {
		if (x == null)
			return 0;
		else
			return x.N;
	}

	/**
	 * Unit tests the <tt>BST</tt> data type.
	 */
	public static void main(String[] args) {
		edu.princeton.cs.algs4.BST<String, Integer> st = new edu.princeton.cs.algs4.BST<String, Integer>();
		for (int i = 0; !StdIn.isEmpty(); i++) {
			String key = StdIn.readString();
			st.put(key, i);
		}
	
		for (String s : st.levelOrder())
			StdOut.println(s + " " + st.get(s));
		
		StdOut.println();
		
		for (String s : st.keys("b", "e"))
			StdOut.println(s + " " + st.get(s));
		
		StdOut.println();
		
		System.out.println(st.floor("f"));
	}

}
