package project_euler_java;

//Problem7
public class Problem7 {
	public int getNthPrime(int n) {
		if (n == 1) {
			return 2;
		}
		int currentNum = 3;
		int counter = 2; // 2nd prime is 3
		while (counter < n) {
			currentNum += 2; 
			if (isPrime(currentNum)) {
				counter++;
			}
		}
		return currentNum;
	}
	
	private boolean isPrime(int n) {
		for (int i = 2; i <= Math.sqrt(n); i++) { // XXX we only need to test for factors less than or equal to the square root of n.
			if ((n % i) == 0) {
				return false;
			}
		}
		return true;
	}
	
	public static void main(String[] args) {
		Problem7 o = new Problem7();
// 		int sixthPrime = o.getNthPrime(6);
//		System.out.println("6th prime: " + sixthPrime);
	
		long start = System.currentTimeMillis();
		int result = o.getNthPrime(10001);
		long end = System.currentTimeMillis();
		System.out.println("10001th prime: " + result);
		System.out.println("The algorithm took " + (end - start) + " milliseconds."); // 2455 -> 2.4 sec
	}
}