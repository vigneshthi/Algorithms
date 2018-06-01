/*
 * Copyright (c) 2017. Phasmid Software
 */

package edu.neu.coe.info6205.randomwalk;

import java.util.Random;

public class RandomWalk {
    private int x = 0;
    private int y = 0;
    private int xLamppost = 0;
    private int yLamppost = 0;

    private final Random random = new Random();

    public void move(int dx, int dy) {
    		x += dx;
    		y += dy;
        // TODO you need to implement this
    }

    private void randomWalk(int n) {
        for (int i = 0; i < n; i++)
            randomMove();
    }

    private void randomMove() {
        // TODO you need to implement this
    		//int rand = random.nextnt((max - min) + 1) + min;
    	int rand= random.nextInt((4 - 1) + 1) + 1;
    		switch (rand) {
			case 1:
				move(1,0);
				System.out.println("EAST " + x + " " + y);
				break;
			case 2:
				move(-1,0);
				System.out.println("WEST " + x + " " + y);
				break;
			case 3:
				move(0,1);
				System.out.println("NORTH " + x + " " + y);
				break;
			case 4:
				move(0,-1);
				System.out.println("SOUTH " + x + " " + y);
				break;
    		}
    }

    public double distance() {
    		double distance=Math.sqrt(((x-xLamppost)*(x-xLamppost))+((y-yLamppost)*(y-yLamppost)));
    		return distance;
    		//   return 0; // TODO you need to implement this
    }

    public static void main(String[] args) {
    		
    		int i=0;
    		//The while loop is getting the argument values
		while (i < args.length) {
			int n = Integer.parseInt(args[i]);
			RandomWalk walk = new RandomWalk();
			for (int j = 0; j < 5; j++) {
				walk.randomWalk(n);
				System.out.println("Steps: " + n + " Distance: " + walk.distance());
			}
			i++;
			System.out.println("\n");
		}
    }
}
