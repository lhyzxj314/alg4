package sort;

import java.util.HashMap;
import java.util.Map;

import edu.princeton.cs.algs4.StdRandom;

public class Quick {
	public static void sort(double[] a) {
		int len = a.length;
		sort(a, 0, len - 1);
	}
	
	public static void sort(double[] a, int lo, int hi) {
		if (hi <= lo) return;
		
		double v = a[lo]; // 切分元素
		int i = lo;
		int j = hi + 1;
		while (true) {
			while (a[++i] < v)  if (i == hi) break;
			while (a[--j] > v)  if (j == lo) break;
			if (i >= j) break;
			
			double temp = a[j];
			a[j] = a[i];
			a[i] = temp;
		}
		double temp = a[j];
		a[j] = a[lo];
		a[lo] = temp;
		
		Map<String, Integer> context = new HashMap<String, Integer>();
		context.put("selected", j);
		context.put("start", lo);
		context.put("end", hi);
		Visualizer.show(a, context);
		
		sort(a, lo, j - 1);
		sort(a, j + 1, hi);
	}
	
	public static void main(String[] args) {
		int N = 150;
		double[] a = new double[N];
		for (int i = 0; i < N; i++) {
			a[i] = StdRandom.uniform();
		}
		//double[] a = new double[] {3, 2, 4 , 5};
		sort(a);
		Visualizer.show(a);
	}
}
