package problems;

import java.math.BigInteger;

public class Problem48 {
	public BigInteger selfPowers(int n, int numDigits) {
		BigInteger result = BigInteger.ZERO;
		BigInteger nAsBigInt = BigInteger.valueOf(n);
		
		for (BigInteger i = BigInteger.ONE; i.compareTo(nAsBigInt) <= 0; i = i.add(BigInteger.ONE)) {
			result = result.add(i.pow(i.intValue())); // XXX I was using i^2 !!! -> now correct!! :)
		}
		return getNLastDigits(result, numDigits);
	}
	
	private BigInteger getNLastDigits(BigInteger number, int numDigits) {
		BigInteger divisor = BigInteger.ONE;
		for (int i = 0; i < numDigits; i++) {
			divisor = divisor.multiply(BigInteger.TEN);
		}
		return number.mod(divisor);
	}
	
	public static void main(String[] args) {
		Problem48 o = new Problem48();
		BigInteger result = o.selfPowers(1000, 10);
		System.out.println("result: " + result);
	}
}
