package main;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Problem14 {
    private long[] cache;
    public long longestCollatzSequenceUnder(long maxStartValue) {
        return generateAllCollatzSequencesUnder(maxStartValue);
    }

    public long generateAllCollatzSequencesUnder(long maxStartValue) {
        cache = new long[(int)maxStartValue + 1];
        Arrays.fill(cache, -1);
        cache[1] = 1;
        long max = 0;
        long startingNumber = 0;
        for (int i = 2; i < maxStartValue; i++) {
            long sequenceLength = generateSequenceStartingWith(i);
            cache[i] = sequenceLength;
            if (cache[i] > max) {
                max = cache[i];
                startingNumber = i;
            }
        }
        return startingNumber;
    }

    private long generateSequenceStartingWith(int startValue) {
        List<Long> result = new LinkedList<Long>();
        long value = startValue;
        result.add(value);
        int  k = 0;
        while (value != 1 && value >= startValue) {
            k++;
            value = calculateNewValue(value);
            result.add(value);
        }
        return k + cache[(int)value]; // if found 34, ..., 23 we already have 23! this is why we don't accept values < start value.
    }

    private long calculateNewValue(long previousValue) {
        if (previousValue % 2 == 0) { // even
            return previousValue / 2;
        } else { // odd
            return (3 * previousValue) + 1;
        }
    }

    public static void main(String[] args) {
        Problem14 o = new Problem14();
        long start = System.currentTimeMillis();
        long result = o.longestCollatzSequenceUnder(1000000);
        long end = System.currentTimeMillis();
        System.out.println("time: " + (end - start) + "ms.");
        System.out.println("result: " + result);
    }
}
