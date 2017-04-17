package sort;

import java.util.HashMap;
import java.util.Map;

import edu.princeton.cs.algs4.StdRandom;

public class Insertion {
	
	public static void sort(double[] a) {
		int len = a.length;
		for (int i = 1; i < len; i++) {
			for (int j = i; j > 0 && a[j - 1] > a[j]; j--) {
				double temp = a[j];
				a[j] = a[j - 1];
				a[j - 1] = temp;
				
				Map<String, Integer> context = new HashMap<String, Integer>();
				context.put("selected", j - 1);
				context.put("start", 0);
				context.put("end", i);
				Visualizer.show(a, context);
			}
		}
	}
	
	public static void main(String[] args) {
		int N = 150;
		double[] a = new double[N];
		for (int i = 0; i < N; i++) {
			a[i] = StdRandom.uniform();
		}
		sort(a);
		Visualizer.show(a);
	}
}
