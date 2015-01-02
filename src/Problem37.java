package project_euler_java;

public class Problem37 {
	public int truncatablePrimes(int max) {
		int i = 10;
		int numTruncatablePrimes = 0;
		int result = 0;
		while (numTruncatablePrimes < max) {
			if (isTruncatablePrime(i)) {
				numTruncatablePrimes++;
				result += i;
//				System.out.println(i);
			}
			i++;
		}
		return result;
	}
	
	private boolean isTruncatablePrime(int n) {
		if (!isPrime(n)) {
			return false;
		}
		int divisor = 10;
		while (n % divisor != n) {
			if (!isPrime(n % divisor)) {
				return false;
			}
			divisor *= 10;
		}
		while (n > 0) {
			n /= 10;
			if (!isPrime(n)) {
				return false;
			}
		}
		return true;
	}
	
	private boolean isPrime(int n) {
		if (n == 1) { //XXX otherwise, 1 < 2 and return true !!
			return false;
		}
		for (int i = 2; i <= Math.sqrt(n); i++) {
			if (n % i == 0) {
				return false;
			}
		}
		return true;
	}
	
	public static void main(String[] args) {
		Problem37 o = new Problem37();
		int result = o.truncatablePrimes(11);
		System.out.println("result: " + result);
	}
}
