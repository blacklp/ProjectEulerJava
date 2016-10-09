package main;

import java.util.HashSet;
import java.util.Set;

public class Problem43 {
    private Long currentNum = 0L;

    public long subStringDivisibility() {
        Long first = 1000000000L; // 0123456789
        Long last  = 9876543210L;
        Long result = 0L;

        for (currentNum = first; currentNum <= last; currentNum++) {
            if (isPandigitalAndHasProperty(currentNum)) {
                result += currentNum;
            }
        }
        return result;
    }

    private boolean isPandigitalAndHasProperty(Long num) {
        Set<Integer> digitSet = new HashSet<>();
        int[] digits = new int[10];
        long n = num; // copy
        int digitCount = 9;
        while (n > 0) {
            int digit = (int)(n % 10);
            if (digitCount == 3 && digit % 2 != 0) { // 4th
                currentNum = increaseNumberDigit(num, 4);
                return false;
            }
            if (digitCount == 5 && digit != 5 && digit != 0) { // 6th
                currentNum = increaseNumberDigit(num, 6);
                return false;
            }
            if (digitCount == 2 && (digit + digits[3] + digits[4]) % 3 != 0) { // 3rd + 4th + 5th
                currentNum = increaseNumberDigit(num, 5);
                return false;
            }
            if (digitSet.contains(digit)) {
                return false;
            }
            digitSet.add(digit);
            digits[digitCount] = digit;
            n /= 10;
            digitCount--;
        }
        if (digitSet.size() == 10) { // [0-9]
            return hasProperty(digits);
        }
        return false;
    }

    private Long increaseNumberDigit(Long num, int digit) {
        int multiplier = 1;
        int last = (10 - digit);
        int count = 0;
        while (count < last) {
            multiplier *= 10;
            count++;
        }
        long firstPart = num / multiplier;
        firstPart++; // increase
        long secondPart = num % multiplier;
        return firstPart * multiplier + secondPart;
    }

    private boolean hasProperty(int[] digits) {
        int[] primes = new int[] {7, 11, 13, 17}; // removed 2, 3, 5 (already checked)
        int j = 4; // from the 5th
        for (int prime : primes) {
            if (!getNumberDivisible(digits, j, j+1, j+2, prime)) {
                return false;
            }
            j++;
        }
        return true;
    }

    private boolean getNumberDivisible(int[] digits, int i, int j, int k, int divisor) {
        int digit1 = digits[i];
        int digit2 = digits[j];
        int digit3 = digits[k];
        int num = digit1 * 100 + digit2 * 10 + digit3;
        return num % divisor == 0;
    }

    public static void main(String[] args) {
        Problem43 o = new Problem43();
        long start = System.currentTimeMillis();
        long result = o.subStringDivisibility();
        long end = System.currentTimeMillis();
        System.out.println("took " + (end - start) + "ms."); // 121956 ms.
        System.out.println("result: " + result);
    }
}
