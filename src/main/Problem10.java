package main;

public class Problem10 {
	public long summationOfPrimesBelow(int number) {
		long result = 0;
		for (int i = 2; i < number; i++) {
			if (isPrime(i)) {
				result += i;
			}
		}
		return result;
	}
	
	private boolean isPrime(int n) {
		for (int i = 2; i <= Math.sqrt(n); i++) {
			if (n % i == 0) {
				return false;
			}
		}
		return true;
	}
	
	public static void main(String[] args) {
		Problem10 o = new Problem10();
		long result1 = o.summationOfPrimesBelow(10);
		long start = System.currentTimeMillis();
		long result2 = o.summationOfPrimesBelow(2000000);
		long end = System.currentTimeMillis();
		System.out.println("Summation of prime number below 10: " + result1); // 17
		System.out.println("Summation of prime numbers below 2,000,000: " + result2); // 142913828922
		// XXX IT IS VERY IMPORTANT TO USE long AND NOT int!!! --> out of range in this case does not fail!!!!! 
		System.out.println("Time to calculate the second one: " + (end - start) + "ms."); // 1025ms ~ 1 sec.
	}
}
