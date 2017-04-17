package sort;

import java.util.HashMap;
import java.util.Map;

import edu.princeton.cs.algs4.StdRandom;

public class Quick3way {
	public static void sort(double[] a) {
		int len = a.length;
		sort(a, 0, len - 1);
	}
	
	public static void sort(double[] a, int lo, int hi) {
		if (hi <= lo) return;
		
		double v = a[lo]; // 切分元素
		int i = lo;
		int j = hi;
		int k = lo + 1;
		while (k <= j) {
			if (a[k] < v) {
				double temp = a[k];
				a[k] = a[i];
				a[i] = temp;
				i++;
				k++;
			} else if (a[k] > v) {
				double temp = a[k];
				a[k] = a[j];
				a[j] = temp;
				j--;
			} else {
				k++;
			}
		}
		
		Map<String, Integer> context = new HashMap<String, Integer>();
		context.put("start", lo);
		context.put("end", hi);
		Visualizer.show(a, context);
		
		sort(a, lo, i - 1);
		sort(a, j + 1, hi);
	}
	
	public static void main(String[] args) {
		int N = 150;
		double[] a = new double[N];
		for (int i = 0; i < N; i++) {
			if (i % 4 == 0)
				a[i] = 0.5;
			else
				a[i] = StdRandom.uniform();
		}
		//double[] a = new double[] {3, 2, 4 , 5};
		Visualizer.show(a);
		sort(a);
		Visualizer.show(a);
	}
}
