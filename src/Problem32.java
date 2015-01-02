package project_euler_java;

import java.util.HashSet;
import java.util.Set;

public class Problem32 {
	private Set<Long> pandigitals = new HashSet<>();
	private int numDigits;

	public int pandigitalProducts(int numDigits) {
		this.numDigits = numDigits;
		int result = 0;
		for (int i = 1; i < 9876; i++) {
			for (int j = 1; j < 9876; j++) {
				long product = i*j;
				if (product > 100000) { // only valid combinations -> (1-digit + 4-digit) and (2-digit + 3-digit) -> both require product: 4-dig 
					break;
				}	
				if (!pandigitals.contains(product) && isPandigital(product, i, j)) {
					pandigitals.add(product);
					result += product;
				}
			}
		}
		return result;
	}
	
	private boolean isPandigital(long product, int multiplicand, int multiplier) {
		Set<Long> digits = new HashSet<>();
		while (multiplicand > 0 || multiplier > 0 || product > 0) {
			if (multiplicand > 0 && containsDigit(multiplicand, digits)) {
				return false;
			}
			if (multiplier > 0 && containsDigit(multiplier, digits)) {
				return false;
			}
			if (product > 0 && containsDigit(product, digits)) {
				return false;
			}
			multiplicand /= 10;
			multiplier /= 10;
			product /= 10;
		}
		return digits.size() == numDigits; // to avoid false positives like e.g. 12345678 or 1234
	}
	
	private boolean containsDigit(long num, Set<Long> digits) {
		long digit = num % 10;
		if (digit < 1 || digit > numDigits) {
			return true; // not contains but, not to be counted.
		}
		if (digits.contains(digit)) {
			return true;
		}
		digits.add(digit);
		return false;
	}
	
	public static void main(String[] args) {
		Problem32 o = new Problem32();
		int result = o.pandigitalProducts(9);
		System.out.println("Result: " + result);
//		System.out.println("is pandigital(39 ? 186 = 7254): " + o.isPandigital(7254, 39, 186));
	}
}
