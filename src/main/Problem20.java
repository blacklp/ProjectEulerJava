package main;

import java.math.BigInteger;

public class Problem20 {
	// XXX O(100^100) needs a BigInteger!!!
	public long factorialDigitSum(int num) {
		long result = 0; 
		BigInteger fact = factorial(num);
		String stringFact = fact.toString();
		for (int i = 0; i < stringFact.length(); i++) {
			char c = stringFact.charAt(i);
			result += Long.valueOf(c + "");
		} // XXX Better with / 10 and % 10 and without toString() !! :)
		return result;
	}
	
	private BigInteger factorial(int num) {
		if (num == 0 || num == 1) {
			return BigInteger.ONE;
		}
		return BigInteger.valueOf(num).multiply(factorial(num-1));
	}
	
	public static void main(String[] args) {
		Problem20 o = new Problem20();
		long result = o.factorialDigitSum(100);
		System.out.println("result: " + result);
	}
}
