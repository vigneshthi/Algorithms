package edu.neu.coe.info6205.union_find;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class WQUPC_Solutions {

	public static int count(int n) {

		int count1 = 0;

		WQUPC wqupc = new WQUPC(n);

		List<int[]> list = new ArrayList<>();
		List<int[]> final_list = new ArrayList<>();

		for (int i = 0; i < n ; ++i) {
			for (int j = 0; j < n ; ++j) {
				if(i != j) {
				list.add(new int[] { i, j });
				}
			}
		}

		Collections.shuffle(list);

		for (int[] pair : list) {

			if (!wqupc.connected(pair[0], pair[1])) {
				wqupc.union(pair[0], pair[1]);
				count1++;
				final_list.add(new int[] { pair[0], pair[1] });
			}
			System.out.println("(" + pair[0] + " " + pair[1] + ")\t");

		}
		System.out.println("No of pairs generated: "+list.size());
		System.out.println("pairs used for union: ");
		final_list.stream().forEach(pair -> System.out.println("(" + pair[0] + " " + pair[1] + ")"));

		/*
		 * 
		 * for (int i = 0; i <= n - 1; i++) {
		 * 
		 * for (int j = n - 1; j >= 0; j--) {
		 * 
		 * 
		 * 
		 * if (!wqupc.connected(i, j)) {
		 * 
		 * wqupc.union(i, j); count1++;
		 * 
		 * }
		 * 
		 * }
		 * 
		 * }
		 */

		return count1;
	}

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the value");
		int n = sc.nextInt();

		System.out.println("number of randomly generated pairs is " + count(n));

	}
}
