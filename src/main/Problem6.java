package main;

//Exercise 6
public class Problem6 {
    public long sumSquareDifference(int start, int end) {
        long sumOfSquares = 0, squareOfSum = 0;
        for (int i = start; i <= end; i++) {
            sumOfSquares += (i*i);
            squareOfSum += i;
        }
        squareOfSum *= squareOfSum;
        return (squareOfSum - sumOfSquares);
    }

    public static void main(String[] args) {
        long result = new Problem6().sumSquareDifference(1, 100);
        System.out.println("result: " + result);
    }
}
