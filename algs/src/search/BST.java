package search;

import java.util.NoSuchElementException;

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
	}

	public int size() {
		return size(root);
	}

	public Value get(Key key) {
		return get(root, key);
	}

	public void put(Key key, Value val) {
		root = put(root, key, val);
	}

	/*
	 * ********************************************** 有序性相关操作
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
		if (size(root) == 0) return true;
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
			return get(node.left, key);
		else if (cmp > 0)
			return get(node.right, key);
		else
			return node.val;
	}

	// 递归实现
	private Node put(Node node, Key key, Value val) {
		if (node == null)
			return new Node(key, val, 1);
		int cmp = node.key.compareTo(key);
		if (cmp < 0)
			node.left = put(node.left, key, val);
		else if (cmp > 0)
			node.right = put(node.right, key, val);
		else
			node.val = val;
		return node;
	}

	private int size(Node x) {
		if (x == null)
			return 0;
		else
			return x.N;
	}

}
