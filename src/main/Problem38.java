package main;

import java.util.HashSet;
import java.util.Set;

public class Problem38 {
	public int pandigitalMultiples() {
		int numDigits = 9;
		int multiplier = 9;
		int multiplierDigits = 1; 
//		int largestPandigital = 987654321; //XXX not needed!
		
		int currentPandigital = 0;
		int number = 0;
		while (multiplierDigits < 5) { // as n > 1, multiplier < 5 XXX important!
			int i = 1;
			while (true) {
				number = getConcatOfMultiplierTimesN(multiplier, i);
				if (isPandigital(number, numDigits)) {
					currentPandigital = number;
//					System.out.println(number);
					break;
				}
				if (number == 0) { // long
					break;
				}
				i++;
			}
			multiplier++;
			if (!isCorrectMultiplier(multiplier)) {
				multiplier *= 9; // 10 -> 90, 100 -> 900
				multiplierDigits++;
			}
		}
		return currentPandigital; // last pandigital number found
	}

	private boolean isCorrectMultiplier(int multiplier) {
		int digit = 0;
		while (multiplier > 0) {
			digit = multiplier % 10;
			multiplier /= 10;
		}
		return digit == 9; // first digit
	}
	
	private int getConcatOfMultiplierTimesN(int multiplier, int n) {
		StringBuilder result = new StringBuilder();
		for (int i = 1; i <= n; i++) {
			int newPart = (multiplier * i);
			result.append(newPart);
		}
		int intResult = 0;
		try {
			intResult = Integer.valueOf(result.toString());
		} catch(NumberFormatException e) {
			// ignored because it is a long (so probably repeated digits) 
		}
		return intResult;
	}
	
	private boolean isPandigital(int n, int numDigits) {
		Set<Integer> digits = new HashSet<>();
		while (n > 0) {
			int digit = n % 10;
			if (digit == 0 || digits.contains(digit)) { // digit 0 (using only 1-9) OR repeated digit
				return false;
			}
			digits.add(digit);
			n /= 10;
		}
		return digits.size() == numDigits;
	}
	
	public static void main(String[] args) {
		Problem38 o = new Problem38();
		int result = o.pandigitalMultiples();
		System.out.println("result: " + result);
	}
}
