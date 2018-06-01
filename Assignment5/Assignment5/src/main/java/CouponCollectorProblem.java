package main.java;

import java.util.*;

public class CouponCollectorProblem {

    private final int INITIAL_NUMBER_SIZE = 4; // 2^2 = 4
    private final int FINAL_NUMBER_SIZE;
    private float[] dpHarmonicNumbers;

    public CouponCollectorProblem(int maxNumberSize) {
        FINAL_NUMBER_SIZE = maxNumberSize;
        dpHarmonicNumbers = new float[maxNumberSize + 1];

        dpHarmonicNumbers[1] = 1;
    }

    public List<Integer> runExperiments() {

        List<Integer> numbersGeneratedBeforeAllPossibleValues = new LinkedList<>();

        for(int n = INITIAL_NUMBER_SIZE; n <= FINAL_NUMBER_SIZE; n *= 2) {
            int numbersGenerated = couponCollectorProblem(n);
            numbersGeneratedBeforeAllPossibleValues.add(numbersGenerated);
        }

        return numbersGeneratedBeforeAllPossibleValues;
    }

    private int couponCollectorProblem(int n) {
    	SeparateChainingLiteHashST<Integer, Boolean> numbersGenerated = new SeparateChainingLiteHashST<>();

        int numbersGeneratedCount = 0;
        Random random = new Random();
        //Repeat until we generate all possible values
        while(true) {
            int number = random.nextInt(n);
            numbersGenerated.put(number, true);

            numbersGeneratedCount++;

            if (numbersGenerated.size() == n) {
                break;
            }
        }

        return numbersGeneratedCount - 1;
    }

    
    public void printResults(List<Integer> numbersGeneratedBeforeAllPossibleValues) {
        System.out.printf("%12s %21s %29s %8s\n", "M |", "Numbers Generated Before All Values |", "Result Expected by Hypothesis |",
                "Accuracy");

        int numberSize = INITIAL_NUMBER_SIZE;

        for(int numbersGenerated : numbersGeneratedBeforeAllPossibleValues) {
            int expectedResultByHypothesis = (int) Math.round(getExpectedResultByHypothesis(numberSize));

            System.out.printf("%10d %19d %33d", numberSize, numbersGenerated, expectedResultByHypothesis);
            double accuracy = (double) numbersGenerated / (double) expectedResultByHypothesis;
            System.out.printf("%25.1f\n", accuracy);

            numberSize *= 2;
        }
    }

    private double getExpectedResultByHypothesis(int numberSize) {
        return numberSize * getHarmonicNumber(numberSize);
    }

    private float getHarmonicNumber(int number) {

        if(dpHarmonicNumbers[number] != 0) {
            return dpHarmonicNumbers[number];
        }

        int index = number - 1;
        //Find last computed value
        while (dpHarmonicNumbers[index] == 0) {
            index--;
        }

        while (index <= number) {
            dpHarmonicNumbers[index] = ((float) 1 / (float) index) + dpHarmonicNumbers[index -1];
            index++;
        }

        return dpHarmonicNumbers[number];
    }
}
