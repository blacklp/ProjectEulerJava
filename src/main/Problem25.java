package main;

import java.math.BigInteger;
import java.util.LinkedList;
import java.util.List;

public class Problem25 {
	private List<BigInteger> cache = new LinkedList<>();
	
	public int nDigitFibonacciNumber(int n) {
		return generateFibonacciList(n);
	}
	
	private int generateFibonacciList(int n) {
		cache.add(0, BigInteger.ZERO);
		cache.add(1, BigInteger.ONE);
		int i = 2; // index
		while (true) {
			BigInteger beforePrevious = cache.get(i - 2);
			BigInteger previous = cache.get(i - 1);
			BigInteger generatedNumber = beforePrevious.add(previous);
			cache.add(i, generatedNumber);
			if (hasNDigits(generatedNumber, n)) {
				return i;
			}
			i++;
		}
	}
	
	private boolean hasNDigits(BigInteger number, int numDigits) {
		return number.divide(BigInteger.valueOf(10).pow(numDigits - 1)).compareTo(BigInteger.ONE) >= 0;
		// this.compareTo(...) returning > 0 means this bigger
		// XXX BigInteger.pow(...) and ... is an int!!!
	}
	
	public static void main(String[] args) {
		Problem25 o = new Problem25();
		int result = o.nDigitFibonacciNumber(1000);
		System.out.println("Result: The " + result + "th" + " Fibonacci number is the first one to contain 1000 digits.");
	}
}
