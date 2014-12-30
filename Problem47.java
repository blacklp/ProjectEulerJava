package problems;

import java.util.ArrayList;
import java.util.List;

public class Problem47 {
	private List<Integer> primes = new ArrayList<>();
	
	public int distinctPrimeFactors(int numPrimeFactors) {
		int i = 2;
		int consecutiveCount = 0;
		int result = 0;
		boolean found = false;
		while (!found) {
			if (hasNPrimeFactors(i, numPrimeFactors)) {
				consecutiveCount++;
				if (consecutiveCount == 1) { // first one
					result = i;
				}
				if (consecutiveCount == numPrimeFactors) {
					found = true;
				}
			} else {
				consecutiveCount = 0;
			}
			i++;
		}
		return result;
	}
	
	private boolean hasNPrimeFactors(int number, int n) {
		int copy = number;
		int result = 0; // number of prime factors
		boolean wasDivisible = false;
		int prime  = 2;
		if (primes.isEmpty()) {
			primes.add(2);
		}
		
		for (Integer p : primes) {
			prime = p;
			wasDivisible = false;
			while (number % prime == 0) { // 2 is the only even prime
				number /= prime;
				if (number == 1) {
					return result+1 == n; // result+1 because wasDivisible = true
				}
				wasDivisible = true;
			}
			if (wasDivisible) {
				result++;
				if (result > n) {
					return false;
				}
			}
		}
		// check with more prime numbers
		int initialPrime = (prime == 2) ? 3 : prime+2;
		for (int i = initialPrime; i <= copy/2; i += 2) {
			if (!isPrime(i)) {
				continue;
			}
			primes.add(i); // XXX I was adding prime! instead of i, now correct and super fast!! :D
			if (number == 1){
				break;
			}
			wasDivisible = false;
			while (number % i == 0) {
				number /= i;
				wasDivisible = true;
			}
			if (wasDivisible) {
				result++;
				if (result > n) {
					return false;
				}
			}
		}
		return number == 1 && result == n;
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
		Problem47 o = new Problem47();
		int result = o.distinctPrimeFactors(4);
		System.out.println("result: " + result);
	}
}
