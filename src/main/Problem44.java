package main;

import java.util.ArrayList;
import java.util.List;

public class Problem44 {
    public int pentagonNumbers() {
        List<Integer> list = new ArrayList<>();

        int n = 1;
        while (true) {
            int pN = (n * (3*n - 1)) / 2;
            for (int i = list.size()-1; i >=0; i--) {
                Integer current = list.get(i);
                int diff = Math.abs(current - pN);
                if (list.contains(diff)) {
                    int sum = current + pN;
                    if (isPentagonal(sum)) { // XXX we already have the right result here -> great! :)
                        return diff;
                    }
                }
            }
            list.add(pN);
            n++;
        }
    }

    public int pentagonNumbersBetter() {
        int i = 1;

        while (true) {
            i++;
            int n = i * (3 * i - 1) / 2;

            for (int j = i-1; j > 0; j--) {
                int m = j * (3 * j - 1) / 2;
                if (isPentagonal(n - m) && isPentagonal(n + m)) {
                    return n - m;
                }
            }
        }
    }

    /**
     *
     * @param pN
     * @return true is exists a number n s.t. pN = (n * (3*n - 1)) / 2 -> pN = (3*n^2 - n) / 2 ->  = 3*n^2 - n - 2*pN = 0
     */
    private boolean isPentagonal(int pN) {
        // n = (-(-1) (+/-) sqrt(1 - 4 * 3 * (-2*pN))) / (2 * 3)
        double n = (1 + Math.sqrt(1 + 24 * pN)) / 6;
        int intN = (int) n;
        return (n - intN) == 0;
    }

    public static void main(String[] args) {
        Problem44 o = new Problem44();
        long start = System.currentTimeMillis();
        int result = o.pentagonNumbersBetter();
        long end = System.currentTimeMillis();
        System.out.println("result: " + result);
        System.out.println("took " + (end - start) + " ms.");
    }
}
