package sort;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import edu.princeton.cs.algs4.StdRandom;

public class Merge {
	private static double[] aux;
	
	public static void sort(double[] a) {
		int len = a.length;
		aux = new double[len];
		sort(a, 0, len - 1);
	}
	
	public static void sort(double[] a, int lo, int hi) {
		if (lo >= hi) return;
		int mid = lo + (hi - lo) / 2;
		
		Map<String, Integer> context = new HashMap<String, Integer>();
		/*context.put("start", lo);
		context.put("end", hi);
		Visualizer.show(a, context);*/
		
		sort(a, lo, mid);
		sort(a, mid + 1, hi);
		merge(a, lo, mid, hi);
		
		context.put("start", lo);
		context.put("end", hi);
		Visualizer.show(a, context);
	}
	
	private static void merge(double[] a, int lo, int mid, int hi) {
		for (int index = lo; index <= hi; index++)
			aux[index] = a[index];
		
		int i = lo;
		int j = mid + 1;
		int k = lo;
		while (k <= hi) {
			if   (i > mid)				a[k++] = aux[j++];
			else if (j > hi)  			a[k++] = aux[i++];
			else if (aux[i] < aux[j]) 	a[k++] = aux[i++];
			else						a[k++] = aux[j++];
		}
	}
	
	public static void main(String[] args) {
		int N = 50;
		double[] a = new double[N];
		for (int i = 0; i < N; i++) {
			a[i] = StdRandom.uniform();
		}
		//double[] a = new double[] {3, 2, 4 , 5};
		sort(a);
		System.out.println(Arrays.toString(a));
		Visualizer.show(a);
	}
}
