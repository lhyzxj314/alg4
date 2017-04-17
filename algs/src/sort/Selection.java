package sort;

import java.util.HashMap;
import java.util.Map;

import edu.princeton.cs.algs4.StdRandom;

public class Selection {
	public static void sort(double[] a) {
		int len = a.length;
		for (int i = 0; i < len; i++) {
			int minIndex = i;
			for (int j = i; j < len; j++) {
				if (a[minIndex] > a[j])
					minIndex = j;
			}
			
			Map<String, Integer> context = new HashMap<String, Integer>();
			context.put("selected", minIndex);
			context.put("start", 0);
			context.put("end", i - 1);
			Visualizer.show(a, context);
			
			double temp = a[i];
			a[i] = a[minIndex];
			a[minIndex] = temp;
			
			context.put("selected", i);
			context.put("start", 0);
			context.put("end", i);
			Visualizer.show(a, context);
		}
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
