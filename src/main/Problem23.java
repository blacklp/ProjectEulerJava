package main;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Problem23 {
    private int limit = 28123; // excluded
    private List<Integer> abundantNumbers;
    private Set<Integer> abundantSums;

    public long nonAbundantSums() {
        // generate abundant numbers up to limit
        generateAbundantNumbers();
        // generate abundant number sums
        generateSums();
//		System.out.println("abundant numbers: " + abundantNumbers.size());
//		System.out.println("abundant sums: " + abundantSums.size());
        long result = 0;
        for (int i = 1; i < limit; i++) {
            if (!canBeWrittenAsAbundantNumberSum(i)) {
                result += i;
            }
        }
        return result;
    }

    private void generateAbundantNumbers() {
        abundantNumbers = new ArrayList<>();
        for (int i = 1; i < limit - 12; i++) { // smallest abundant number is 12
            // XXX Important
            // with - 12 change:
            // abundant numbers: 6961
            int divisorSum = getDivisorSum(i);
            if (divisorSum > i) { // then it is an abundant number
                abundantNumbers.add(i);
            }
        }
    }

    //XXX Probably improvement with dynamic programming could be done: if num % i == 0 -> check for num / i if already in the cached values.
    private int getDivisorSum(int num) {
        int result = 0;
        for (int i = 1; i <= num/2; i++) {
            if (num % i == 0) {
                result += i;
            }
        }
        return result;
    }

    private void generateSums()  {
        abundantSums = new HashSet<>();
        for (int i = 0; i < abundantNumbers.size(); i++) {
            for (int j = 0; j < abundantNumbers.size(); j++) {
                int sum = abundantNumbers.get(i) + abundantNumbers.get(j);
                if (sum >= limit) {
                    continue;
                } // XXX important!!!
                // old: (without continue)
                // abundant numbers: 6965
                // abundant sums: 53871
                // new: (with continue)
                // abundant numbers: 6965
                // abundant sums: 26666
                abundantSums.add(sum);
            }
        }
    }

    private boolean canBeWrittenAsAbundantNumberSum(int num) {
        return abundantSums.contains(num);
    }

    public static void main(String[] args) {
        Problem23 o = new Problem23();
        long start = System.currentTimeMillis();
        long result = o.nonAbundantSums();
        long end = System.currentTimeMillis();
        System.out.println("result: " + result);
        System.out.println("Took " + (end - start) + "ms."); // 7533 ms ---> optimize! --> got now 3673ms
    }
}
