package edu.neu.coe.info6205.sort.simple;

import static edu.neu.coe.info6205.sort.simple.Helper.*;

import java.util.Arrays;

public class InsertionSort<X extends Comparable<X>> implements Sort<X> {
    @Override
    public void sort(X[] xs, int from, int to) {
        // TODO implement insertionSort
		//int N = xs.length;
//		System.out.println();
//		System.out.println("Input Array for each run");
//		System.out.println(Arrays.toString(xs));
//		System.out.println();
	
		for (int i = from; i < to; i++)
			for (int j = i; j > from; j--)
				if (less(xs[j], xs[j - 1]))
					swap(xs, j, j - 1);
				else
					break;
		
//		System.out.println("Output array for each run: " + Arrays.toString(xs));
    }
}
