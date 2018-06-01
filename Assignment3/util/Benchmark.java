/*
 * Copyright (c) 2018. Phasmid Software
 */

package edu.neu.coe.info6205.util;

import edu.neu.coe.info6205.sort.simple.InsertionSort;
import edu.neu.coe.info6205.sort.simple.SelectionSort;
import edu.neu.coe.info6205.sort.simple.ShellSort;
import edu.neu.coe.info6205.sort.simple.Sort;

import java.io.ObjectStreamClass;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

public class Benchmark<T> {

    public Benchmark(Function<T, Void> f) {
        this.f = f;
    }

	public double run(T t, int m) {
		double[] runtimes = new double[m];
		int warmUp = 50; //cannot be a negative number 
		
		IntStream.range(0, m +warmUp).forEach( (i) -> {
			updateInpArray();
			double start = System.nanoTime();
			this.f.apply(t);
			double end = System.nanoTime();
			if (i >= warmUp)
				runtimes[i-warmUp] = end - start;
		});
		//DoubleStream.of(runtimes).forEach(System.out::println);
		return DoubleStream.of(runtimes).average().getAsDouble();
	}

    private final Function<T, Void> f;

    private static Integer[] inputArray;
    private static Integer[] originalArray;
    
    private static void initInpArray(int inpArraySize, ITestDataGenerator iTDG) {
		originalArray = Arrays.stream(iTDG.generate(inpArraySize)).boxed().toArray(Integer[]::new);
		inputArray = new Integer[inpArraySize];
		System.out.println("Original Array");
		System.out.println(Arrays.toString(originalArray));
		System.out.println();
		updateInpArray();
    }
    
    private static void updateInpArray() {
    		System.arraycopy( originalArray, 0, inputArray, 0, originalArray.length );
    }
    
    @FunctionalInterface
	public static interface ITestDataGenerator {
		int[] generate(int n);
	}
	
	public static ITestDataGenerator randomCase = (n) -> {
		return new SecureRandom().ints(n, 0, 9999).toArray();//.boxed().toArray(Integer[]::new);
	};
	
	public static ITestDataGenerator sorted = (n) -> {
		int[] ret = new int[n];
		for(int i = 0; i < n; i++) {
			ret[i] = i;
		}
		return ret;
	};
	
	public static ITestDataGenerator reverseSorted = (n) -> {
		int[] ret = new int[n];
		for(int i = 0; i < n; i++) {
			ret[i] = -i;
		}
		return ret;
	};
	
	
	public static ITestDataGenerator partialSorted = (n) -> {	
		double c = 0.5d;
		int[] ret = new SecureRandom().ints(n, 0, 9999).toArray();//.boxed().toArray(Integer[]::new);
		int from = new SecureRandom().nextInt(((int)(n*c)));
		int to = from + (int)(n*c);
		System.out.println("partially Sorted from: "+from +" to:" + to);
		Arrays.sort(ret, from, to);
		return ret;
	};
    
    
    public static void main(String[] args) {
        int m = 3; // This is the number of repetitions: sufficient to give a good mean value of timing
        
        int n = 10;
        // TODO You need to apply doubling to n
        
        //number of times we call the doubling method
        int NumOfTimesDoublingSortSize = 5;
        
        int inpArraySize = (int) (n*(Math.pow(2, NumOfTimesDoublingSortSize)));
        
        System.out.println("\n Random Array Results \n");
        initInpArray(inpArraySize, randomCase);
		IntStream.range(0, NumOfTimesDoublingSortSize).forEach((i) -> {
			System.out.println("\n" + (i+1) +"th time Results");
			benchmarkSort(inputArray, (int)(n*Math.pow(2,i)), "SelectionSort", new SelectionSort<>(), m);
			benchmarkSort(inputArray, (int)(n*Math.pow(2,i)), "InsertionSort", new InsertionSort<>(), m);
		});
	       //benchmarkSort(array, n, "ShellSort", new ShellSort<>(), m); // doesnt work yet
        
		System.out.println("\n Sorted Array Results \n");
		initInpArray(inpArraySize, sorted);
		IntStream.range(0, NumOfTimesDoublingSortSize).forEach((i) -> {
			System.out.println("\n" + (i+1) +"th time Results");
			benchmarkSort(inputArray, (int)(n*Math.pow(2,i)), "SelectionSort", new SelectionSort<>(), m);
			benchmarkSort(inputArray, (int)(n*Math.pow(2,i)), "InsertionSort", new InsertionSort<>(), m);
		});
		
		
		System.out.println("\n Reverse Sorted Array Results \n");
		initInpArray(inpArraySize, reverseSorted);
		IntStream.range(0, NumOfTimesDoublingSortSize).forEach((i) -> {
			System.out.println("\n" + (i+1) +"th time Results");
			benchmarkSort(inputArray, (int)(n*Math.pow(2,i)), "SelectionSort", new SelectionSort<>(), m);
			benchmarkSort(inputArray, (int)(n*Math.pow(2,i)), "InsertionSort", new InsertionSort<>(), m);
		});

		System.out.println("\n Partial Sorted Array Results \n");
		initInpArray(inpArraySize, partialSorted);
		IntStream.range(0, NumOfTimesDoublingSortSize).forEach((i) -> {
			System.out.println("\n" + (i+1) +"th time Results");
			benchmarkSort(inputArray, (int)(n*Math.pow(2,i)), "SelectionSort", new SelectionSort<>(), m);
			benchmarkSort(inputArray, (int)(n*Math.pow(2,i)), "InsertionSort", new InsertionSort<>(), m);
		});
        
    }

    private static void benchmarkSort(Integer[] xs, Integer n, String name, Sort<Integer> sorter, int m) {
        Function<Integer, Void> sortFunction = (x) -> {
            sorter.sort(xs, 0, x);
            return null;
        };
        Benchmark<Integer> bm = new Benchmark<>(sortFunction);
        double x = bm.run(n, m);
        System.out.println(name + ": " + (x / (1000000)) + " millisecs for n=" + n);
    }
}
