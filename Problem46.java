package project_euler_java;

import java.util.LinkedList;
import java.util.List;

public class Problem46 {
	private List<Integer> primes = new LinkedList<>();
	
	public int goldbachsOtherConjecture() {
		int i = 3;
		while (true) {
			if (!isPrime(i) && !canBeWrittenAsFormula(i)) { // !isPrime(i) equals isCompisite(i)
				return i;
			}
			i += 2;
		}
	}
	
	/**
	 * 
	 * @param n
	 * @return true if n can be written as (prime + number^2)
	 */
	private boolean canBeWrittenAsFormula(int n) {
		int result = 0;
		int i = 1;
		while (2*i*i < n) { // XXX improvement: 2*i^2 < n instead of i < n :) 
			int prime = 2;
			for (Integer p : primes) {
				result = 2*i*i + p;
				if (result == n) {
					return true;
				}
				prime = p;
			}
			while (result < n) {
				prime = getNextPrime(prime);
				result = 2*i*i + prime;
				primes.add(prime);
				if (result == n) {
					return true;
				}
			}
			i++;
		}
		return result == n;
	}
	
	/**
	 * 
	 * @param prime: last prime, it is a prime number so prime >= 2
	 * @return: the next prime number, e.g for 2 returns 3, for 3 returns 5 and so on. 
	 */
	private int getNextPrime(int prime) {
		int result = prime == 2 ? (prime + 1) : prime + 2; // result is >= 3 because prime is >= 2 and result is a prime too.
		while (!isPrime(result)) {
			result += 2;
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
		Problem46 o = new Problem46();
		int result = o.goldbachsOtherConjecture();
		System.out.println("result: " + result);
	}
}
