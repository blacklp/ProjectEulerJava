package main;

import java.math.BigInteger;

public class Problem15 {
    public BigInteger latticePaths(int gridWidth, int gridHeight) {
        return combinatorialNumber(gridWidth + gridHeight, gridWidth);
    }

    private BigInteger combinatorialNumber(int a, int b) {
        return factorial(a).divide(factorial(a - b).multiply(factorial(b)));
    }

    private BigInteger factorial(int num) {
        if (num == 0 || num == 1) {
            return BigInteger.ONE;
        } else {
            return BigInteger.valueOf(num).multiply(factorial(num - 1));
        }
    }

    public static void main(String[] args) {
        Problem15 o = new Problem15();
        long start = System.currentTimeMillis();
        BigInteger result1 = o.latticePaths(20, 20); // 2x2
        long end = System.currentTimeMillis();
        System.out.println("result with 20x20: " + result1);
        System.out.println("took " + (end - start) + " ms.");
    }
}
