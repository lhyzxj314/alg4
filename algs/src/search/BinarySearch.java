package search;

public class BinarySearch<Key extends Comparable<Key>> {
	private Key[] keys;
	
	public BinarySearch(Key[] a) {
		this.keys = a;
	}
	
	// 递归实现
	public int rank(Key key, int lo, int hi) {
		if (lo > hi) return lo;
		
		int mid = lo + (hi - lo) / 2;
		int cmp = key.compareTo(keys[mid]);
		if      (cmp == 0) return mid;
		else if (cmp < 0)  return rank(key, lo, mid - 1);
		else			   return rank(key, mid + 1, hi);
	}
	
	// 迭代实现
	public int rank(Key key) {
		int lo = 0;
		int hi = keys.length - 1;
		
		while (lo <= hi) {
			int mid = lo + (hi - lo) / 2;
			int cmp = key.compareTo(keys[mid]);
			if      (cmp == 0) return mid;
			else if (cmp < 0)  hi = mid - 1;
			else               lo = mid + 1;
		}
		return lo;
	}
	
	public static void main(String[] args) {
		Integer[] a = new Integer[] {1, 2, 3, 4, 6};
		
		//int pos = new BinarySearch<Integer>(a).rank(6, 0, a.length - 1);
		int pos = new BinarySearch<Integer>(a).rank(4);
		System.out.println(pos);
	}
}
