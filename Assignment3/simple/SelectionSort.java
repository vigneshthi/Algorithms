package edu.neu.coe.info6205.sort.simple;

import static edu.neu.coe.info6205.sort.simple.Helper.less;
import static edu.neu.coe.info6205.sort.simple.Helper.swap;

import java.util.Arrays;

public class SelectionSort<X extends Comparable<X>> implements Sort<X> {

    @Override
    public void sort(X[] xs, int from, int to) {
        // TODO implement selection sort
		//int N = xs.length;
//    		System.out.println();
//		System.out.println("Input Array for each run");
//		System.out.println(Arrays.toString(xs));
//		System.out.println();
    	
		for (int i = from; i < to; i++) {
			int min = i;
			for (int j = i + 1; j < to; j++)
				if (less(xs[j], xs[min]))
					min = j;
			swap(xs, i, min);

		}
//		System.out.println("Output array for each run: " + Arrays.toString(xs));
    }
}
