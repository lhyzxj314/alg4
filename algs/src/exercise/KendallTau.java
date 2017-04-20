package exercise;

import java.util.Arrays;

import edu.princeton.cs.algs4.StdRandom;

public class KendallTau {

	public static int inversion(Integer[] a, Integer[] b) {
		if (a.length != b.length)
			throw new IllegalArgumentException();

		int len = a.length;
		Integer[] ainv = new Integer[len];
		Integer[] bnew = new Integer[len];
		// 将问题转化为求bnew[]的逆序数
		for (int i = 0; i < len; i++)
			ainv[a[i]] = i;
		for (int i = 0; i < len; i++)
			bnew[i] = ainv[b[i]];

		return count(bnew);
	}

	public static int count(Integer[] a) {
		int len = a.length;
		Integer[] aux = new Integer[len];
		Integer[] b   = new Integer[len];
		for (int i = 0; i < len; i++)
			b[i] = a[i];
		return count(b, aux, 0, len - 1);
	}

	private static int count(Integer[] b, Integer[] aux, int lo, int hi) {
		int inversion = 0;
		if (lo >= hi) return inversion;
		
		int mid = lo + (hi - lo) / 2;
		inversion += count(b, aux, lo, mid);
		inversion += count(b, aux, mid + 1, hi);
		inversion += merge(b, aux, lo, mid, hi);
		
		return inversion;
	}

	private static int merge(Integer[] b, Integer[] aux, int lo, int mid, int hi) {
		int inversion = 0;
		for (int i = lo; i <= hi; i++)
			aux[i] = b[i];
		
		int i = lo; int j = mid + 1;
		for (int k = lo; k <= hi; k++) {
			if (i > mid) 
				b[k] = aux[j++];
			else if (j > hi)  
				b[k] = aux[i++];
			else if (aux[i] > aux[j]) {
				// 减少逆序数(mid-i+1)个
				inversion += (mid - i + 1);
				b[k] = aux[j++];
			} else {
				b[k] = aux[i++];
			}
		}
		return inversion;
	}

	private static Integer[] permution(int len) {
		Integer[] arr = new Integer[len];
		for (int i = 0; i < len; i++)
			arr[i] = i;
		StdRandom.shuffle(arr);
		return arr;
	}

	public static void main(String[] args) {
		int n = 4;
		Integer[] a = permution(n);
		Integer[] b = permution(n);
//		Integer[] a = new Integer[] {2, 1, 0};
//		Integer[] b = new Integer[] {0, 1, 2};
		System.out.println(Arrays.toString(a));
		System.out.println(Arrays.toString(b));
		int res = inversion(a, b);
		System.out.println(res);
	}
}
