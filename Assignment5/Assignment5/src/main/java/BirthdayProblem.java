package main.java;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class BirthdayProblem {

    
    private final int INITIAL_NUMBER_SIZE = 4; // 2^2 = 4
    private final int FINAL_NUMBER_SIZE = 268435456; // 2^28 = 268435456

    List<Integer> runExperiments() {

        List<Integer> numbersGeneratedBeforeFirstDuplicate = new LinkedList<>();

        for(int n = INITIAL_NUMBER_SIZE; n <= FINAL_NUMBER_SIZE; n *= 2) {
            int numbersGenerated = birthdayProblem_st(n);
            numbersGeneratedBeforeFirstDuplicate.add(numbersGenerated);
        }

        return numbersGeneratedBeforeFirstDuplicate;
    }
    
    private int birthdayProblem_st(int n) {
    	SeparateChainingLiteHashST<Integer, Boolean> numbersGenerated = new SeparateChainingLiteHashST<>();

        int numbersGeneratedCount = 0;
        Random random = new Random();

        //Repeat until we find a repeated value
        while(true) {
            Integer number = random.nextInt(n);

            numbersGeneratedCount++;

            if (numbersGenerated.contains(number)) {
                break;
            } else {
                numbersGenerated.put(number, true);
            }
        }

        return numbersGeneratedCount - 1;
    }
    
    
    
    public void printResults(List<Integer> numbersGeneratedBeforeFirstDuplicate) {
        System.out.printf("%12s %17s %29s %8s\n", "M |", "Numbers Generated Before Repeat |", "Result Expected by Hypothesis |",
                "Accuracy");
        System.out.println("");
        int m = INITIAL_NUMBER_SIZE;

        for(int numbersGenerated : numbersGeneratedBeforeFirstDuplicate) {
            int resultExpectedByHypothesis = (int) Math.round(Math.sqrt(Math.PI * m / 2));

            System.out.printf("%10d %15d %29d", m, numbersGenerated, resultExpectedByHypothesis);
            double accuracy = (double) numbersGenerated / (double) resultExpectedByHypothesis;
            System.out.printf("%29.1f\n", accuracy);

            m *= 2;
        }
    }
}
