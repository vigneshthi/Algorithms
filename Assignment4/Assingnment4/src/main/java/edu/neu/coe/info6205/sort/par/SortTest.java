/*
 * Copyright (c) 2017. Phasmid Software
 */

package edu.neu.coe.info6205.sort.par;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

@SuppressWarnings("ALL")
public class SortTest {

    @Test
    public void sort() throws Exception {
		Random random = new Random(0L);
		int[] array = new int[3000];
		for (int i = 0; i < array.length; i++) array[i] = random.nextInt(10000); 
        ParSort.sort(array, 0, array.length);

        int[] array1= {93,26,11,144,55,22};
        ParSort.sort(array1, 0, array1.length);
        int[] toCompare= {11,22,26,55,93,144};
        assertEquals(array[0], 11);
        assertArrayEquals(array1, toCompare);
    }
    
    @Test
    public void sort1() throws Exception {
        int[] array1= {93,26,11,144,55,22};
        ParSort.sort(array1, 0, array1.length);
        int[] toCompare= {11,22,26,55,93,144};
        assertArrayEquals(array1, toCompare);
    }

}