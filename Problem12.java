package problems;


public class Problem12 {
	// Number of divisors of n:
	// 1- n = a^n + b^m + c^o + d^p + ... (= prime numbers ^ exponents)
	// (n+1) * (m+1) * (o+1) * (p+1) = number of divisors
	
	// Nth Triangular number:
	// 1+2+3+4+5+6+7+8+9+...+(N-1)+N
	// N*(N+1) / 2       or      (n+1   2)  (combinatorial number)
	
	public long highlyDivisibleTriangularNumber(int divisorsWanted) {
		boolean found = false;
		long nth = divisorsWanted; // must be greater!
		while (!found) {
			long nthTriangular = getTriangularNumber(nth);
			long numberOfDivisors = getNumberOfDivisors(nthTriangular);
			if (numberOfDivisors == divisorsWanted) {
				return nthTriangular;
			} else if (numberOfDivisors < divisorsWanted) {
				nth++;
			} else {
				System.out.println("Found triangular number " + nthTriangular + " with " + numberOfDivisors + " divisors.");
				return nthTriangular;
			}
		}
		return -1;
	}
	
	private long getNumberOfDivisors(long nth) {
		long result = 1;
		long nthDivided = nth;		
		for (int i = 2; i <= Math.sqrt(nth); i++) { // find primes
			if (isPrime(i)) {
				int exponent = 0;
				while ((nthDivided % i) == 0) {
					nthDivided = (nthDivided / i);
					exponent++;
				}
				if (exponent > 0) {
					result *= (exponent + 1);
				}
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

	private long getTriangularNumber(long nth) {
		return (nth * (nth + 1)) / 2;
	}

	public static void main(String[] args) {
		Problem12 o = new Problem12();
		long start = System.currentTimeMillis();
		long result = o.highlyDivisibleTriangularNumber(500);
		long end = System.currentTimeMillis();
		System.out.println("Took " + (end - start) + " milliseconds.");
		System.out.println("result: " + result);
	}
}
