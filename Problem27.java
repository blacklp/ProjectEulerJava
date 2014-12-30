package project_euler_java;

public class Problem27 {
	/**
	 * 
	 * @param maxA
	 * @param maxB
	 * @return a * b for which n^2  + a*n + b produces the maximum number of consecutive primes
	 */
	public int quadraticPrimes(int maxA, int maxB) {
		// brute force
		int result = 0;
		int[] ab = new int[] { 0, 0 };
		int aInd = 0, bInd = 1;
		for (int a = -999; a < 1000; a+=2) { // n^2 + a*n + b for n=1 -> 1 + a + b -> for b odd (below) -> a must be odd too! 
			for (int b = -999; b < 1000; b+=2) { // n^2 + a*n + b for n=0 -> 0 + 0 b -> b must be odd!
				int numConsecutivePrimes = getNumberOfConsecutivePrimes(a, b);
				if (numConsecutivePrimes > result) {
					result = numConsecutivePrimes;
					ab[aInd] = a;
					ab[bInd] = b;
				}
			}
		}
		System.out.println("max number of consecutive primes is " + result + ". Found with a=" + ab[aInd] + " and b=" + ab[bInd]);
		return ab[aInd] * ab[bInd];
	}
	
	private int getNumberOfConsecutivePrimes(int a, int b) {
		int n = 0;
		int prime = 2;
		while (isPrime(prime)) {
			prime = n*n + a*n + b;// formula: n^2 + a*n + b
			n++;
		}
		return n - 1;
	}
	
	private boolean isPrime(int n) {
		n = Math.abs(n);
		for (int i = 2; i <= Math.sqrt(n); i++) {
			if (n % i == 0) {
				return false;
			}
		}
		return true;
	}
	
	public static void main(String[] args) {
		Problem27 o = new Problem27();
		long start = System.currentTimeMillis();
		int result = o.quadraticPrimes(1000, 1000);
		long end = System.currentTimeMillis();
		System.out.println("Result: " + result);
		System.out.println("Took " + (end - start)); // 139 ms. (without a+=2 -> 222 ms), (without b+=2 -> 264)
	}
}
