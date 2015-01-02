package main;

public class Problem34 {
	private int[] factorials = new int[10];
	
	public Problem34() {
		preComputeFactorials();
//		for (int f : factorials) {
//			System.out.print(f + " ");
//		}
	}
	
	private void preComputeFactorials() {
		factorials[0] = 1;
		for (int i = 1; i < 10; i++) {
			factorials[i] = computeFactorialAtPosition(i);
		}
	}
	
	private int computeFactorialAtPosition(int pos) {
		if (pos == 0) {
			return factorials[pos];
		}
		return pos * computeFactorialAtPosition(pos-1);
	}
	
	public long digitFactorials() {
		long result = 0; //XXX sum not product!!!
		int upperbound = 3628800;//100000000; // max 7 digits (like 7*9!, 8*9! 9*9!, 10*9!...)
		// 3628800; // 10 times digit 9
		for (int i = 3; i < upperbound; i++) {
			if (isCuriousNumber(i)) {
//				System.out.println(i + " is curious");
				result += i;//XXX sum, not product!!!
			}
		}
		return result;
	}
	
	private boolean isCuriousNumber(int n) {
		int nCopy = n;
//		if (n / 10 == 0) {
//			return false;
//		} // not necessary
		int factorialSum = 0;
		while (n > 0) {
			int digit = n % 10;
			int factorial = factorial(digit);
			if (factorial > nCopy) {
				return false;
			}
			factorialSum += factorial;
			if (factorialSum > nCopy) {
				return false;
			}
			n /= 10;
		}
		return factorialSum == nCopy;
	}
	
	private int factorial(int n) {
		return factorials[n];
	}
	
	public static void main(String[] args) {
		Problem34 o = new Problem34();
		long result = o.digitFactorials();
		System.out.println("result: " + result);
	}
}
