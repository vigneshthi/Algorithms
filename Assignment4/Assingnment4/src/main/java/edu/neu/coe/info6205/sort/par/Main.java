package edu.neu.coe.info6205.sort.par;

import java.util.Arrays;
import java.util.Random;

public class Main {

	public static void main(String[] args) {
		//if (args.length>0) ParSort.cutoff = Integer.parseInt(args[0]);
		Random random = new Random(0L);
		int[] array = new int[10000];
		//ParSort.cutoff=500;
		
		//Parallellizing based on the cut off 
		System.out.println("CutOff"+"  "+"TimeTaken");
		int count=0;
		while(count<50) {
			//ParSort.cutoff=ParSort.cutoff+500;
			double time=0;
			for(int j=0;j<=50;j++) {
				for (int i = 0; i < array.length; i++) array[i] = random.nextInt(10000);
				double start=System.nanoTime();
				ParSort.sort(array, 0, array.length);
	        	//Arrays.sort(array, 0, array.length);
				double end=System.nanoTime();
				double timeTaken=end-start;
				time=time+timeTaken;
			}
			double average=time/200;
			System.out.println(ParSort.cutoff+"  "+average/1000000);
			count++;
		}
		 
		
		//Parallellizing based on number of threads-keeping cut off standard
		/*double time=0;
		for(int k=0;k<50;k++) {
		for(int j=0;j<=200;j++) {
			for (int i = 0; i < array.length; i++) array[i] = random.nextInt(10000);
			double start=System.nanoTime();
			ParSort.sort(array, 0, array.length);
			double end=System.nanoTime();
			double timeTaken=end-start;
			time=time+timeTaken;
		}
		double average=time/200;
		System.out.println(ParSort.cutoff+"  "+average/1000000);
		}*/
		//for (int i : array) System.out.println(i);
		if (array[0]==11) System.out.println("Success!");
	}
}
