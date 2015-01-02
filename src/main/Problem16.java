package main;

import java.math.BigInteger;

public class Problem16 {
	public BigInteger powerDigitSum(int base, int exponent) {
		BigInteger digitSum = BigInteger.ZERO;
		BigInteger result = BigInteger.valueOf(base).pow(exponent);
		
		String resultStr = result.toString();
		for (int i = 0; i < resultStr.length(); i++) {
			char c = resultStr.charAt(i);
			int digit = Integer.valueOf(c + "");
			digitSum = digitSum.add(BigInteger.valueOf(digit));
		} //XXX Better with / and % !! :)
		
		return digitSum;
	}
	
	public static void main(String[] args) {
		Problem16 o = new Problem16();
		BigInteger result = o.powerDigitSum(2, 1000);
		System.out.println("result: " + result);
	}
}
