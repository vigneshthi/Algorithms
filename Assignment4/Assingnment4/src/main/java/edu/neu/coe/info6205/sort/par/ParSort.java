package edu.neu.coe.info6205.sort.par;


import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


class ParSort {

    public static int cutoff = 6000;
    
    //public static int depth=0;
    
    public static ExecutorService es= Executors.newFixedThreadPool(64);
    
    public static void sort(int[] array, int from, int to) {
        int size = to - from;
        if (size < cutoff) { 
        	//System.out.println(ParSort.cutoff+"   "+depth);
        	Arrays.sort(array, from, to);
        }
        else {
    		int mid=from+(to-from)/2;
            CompletableFuture<int[]> parsort1 = parsort(array, from, mid,es); 
            CompletableFuture<int[]> parsort2 = parsort(array, mid+1, to,es); 
            CompletableFuture<int[]> parsort = parsort1.
                    thenCombine(parsort2, (xs1, xs2) -> {
                        int[] result = new int[xs1.length + xs2.length];
                        int i=0;
                        int j=0;
                        int k=0;
                        while (i < xs1.length && j < xs2.length)
                        {
                            if (xs1[i] <= xs2[j])
                            {
                                result[k] = xs1[i];
                                i++;
                            }
                            else
                            {
                                result[k] = xs2[j];
                                j++;
                            }
                            k++;
                        }
                        
                        while (i < xs1.length)
                        {
                            result[k] = xs1[i];
                            i++;
                            k++;
                        }
                        while (j < xs2.length)
                        {
                            result[k] = xs2[j];
                            j++;
                            k++;
                        }
                        return result;
                    });

            parsort.whenComplete((result, throwable) -> {
            	for(int i=0;i<result.length;i++) 
            		array[i]=result[i];
            	}); 
            parsort.join();
        }
    }
    
    private static CompletableFuture<int[]> parsort(int[] array, int from, int to, ExecutorService es) {
        return CompletableFuture.supplyAsync(
                () -> {
                	//depth++;
                    int[] result = new int[to  - from];
                    int k=0;
                    int i=0;
                    while(i<result.length) {
                    	result[i]=array[from+i];
                    	i++;
                    }
                    sort(result,0,result.length);
                    return result;
                },es
        );
    }
}
