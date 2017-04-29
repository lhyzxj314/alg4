package sort;

import java.awt.Color;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;

public class Visualizer {

	public static void show(double[] a, Map<String, Integer> context) {
		StdDraw.clear();
		
		double max = Double.MIN_EXPONENT;
		int len = a.length;
		for (int i = 0; i < len; i++)
			if (a[i] > max) max = a[i];
		
		StdDraw.setXscale(-0.5d, len - 0.5);
		StdDraw.setYscale(0, 2);
		double halfWidth = 0.4d;
		StdDraw.setPenColor(Color.BLUE);
		
		Integer selected = context.get("selected");
		int start = context.get("start");
		int end = context.get("end");
		for (int i = 0; i < len; i++) {
			double halfHeight = a[i] / 2d;
			if (selected != null && i == selected) 
				StdDraw.setPenColor(Color.RED);
			else if (i >= start && i <= end)
				StdDraw.setPenColor(Color.BLUE);
			else 
				StdDraw.setPenColor(Color.gray);
			StdDraw.filledRectangle(i, halfHeight, halfWidth, halfHeight);
		}
		StdDraw.show(0);
		try {
			TimeUnit.MILLISECONDS.sleep(30);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void show(double[] a) {
		StdDraw.clear();
		
		double max = Double.MIN_EXPONENT;
		int len = a.length;
		for (int i = 0; i < len; i++)
			if (a[i] > max) max = a[i];
		
		StdDraw.setXscale(-0.5d, len - 0.5);
		StdDraw.setYscale(0, 2);
		double halfWidth = 0.4d;
		StdDraw.setPenColor(Color.BLUE);
		
		for (int i = 0; i < len; i++) {
			double halfHeight = a[i] / 2d;
			StdDraw.filledRectangle(i, halfHeight, halfWidth, halfHeight);
		}
		StdDraw.show(0);
		try {
			TimeUnit.MILLISECONDS.sleep(30);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		int N = 50;
		double[] a = new double[N];
		for (int i = 0; i < N; i++) {
			a[i] = StdRandom.uniform();
		}
		show(a);
	}
}
