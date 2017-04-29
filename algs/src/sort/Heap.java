package sort;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import edu.princeton.cs.algs4.StdRandom;

public class Heap {
	public static void sort(double[] a) {
		int N = a.length - 1;
		// 构造堆
		for (int i = N / 2; i >= 1; i--) {
			Visualizer.show(a);
			sink(a, i, N);
		}
		// 下沉排序
		Map<String, Integer> context = new HashMap<String, Integer>();
		while (N >= 1) {
			context.put("start", 1);
			context.put("end", N);
			Visualizer.show(a, context);
			
			exch(a, 1, N--);
			sink(a, 1, N);
			context.put("end", N);
			Visualizer.show(a, context);
		}
	}

	private static void sink(double[] a, int i, int n) {
		while (2 * i <= n) {
			int j = 2 * i;
			if (j < n && a[j] < a[j + 1])
				j++;
			if (a[i] > a[j])
				break;
			exch(a, i, j);
			i = j;
		}
	}

	private static void exch(double[] a, int i, int j) {
		double temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}

	public static void main(String[] args) {
		int N = 150;
		double[] a = new double[N + 1];
		for (int i = 1; i <= N; i++)
			a[i] = StdRandom.uniform();
		// double[] a= new double[] {0, 8, 9, 3, 2, 4, 5, 1, 6};
		sort(a);
		Visualizer.show(a);
		System.out.println(Arrays.toString(a));
	}
}
