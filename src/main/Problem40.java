package main;

import java.util.Stack;

public class Problem40 {
	public int champernownesConstant(int[] ds) {
		int digitCount = 0;
		int i = 1;
		int result = 1;
		int currentDIndex = 0;
		
		while (currentDIndex < ds.length) {
			Stack<Integer> digits = getDigits(i);
//			if (digitCount+1 == ds[currentDIndex]) { //XXX NOT NECESSARY! Otherwise we don't correctly increase digitCount !!!!
				while (!digits.isEmpty()) {
					Integer d = digits.pop();
					digitCount++;
					if (digitCount == ds[currentDIndex]) { // found
						result *= d;
						currentDIndex++;
						if (currentDIndex == ds.length) {
							break;
						}
					}
				}
//			}
			i++;
		}
		return result;
	}
	
	private Stack<Integer> getDigits(int n) {
		Stack<Integer> result = new Stack<>();
		while (n > 0) {
			Integer digit = n % 10;
			n /= 10;
			result.push(digit);
		}
		return result;
	}
	
	public static void main(String[] args) {
		Problem40 o = new Problem40();
		int[] ds = new int[] {1, 10, 100, 1000, 10000, 100000, 1000000};
		int result = o.champernownesConstant(ds);
		System.out.println("result: " + result);
	}
}
