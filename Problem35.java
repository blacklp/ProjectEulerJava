package problems;

public class Problem35 {
	public int circularPrimes(int under) {
		int result = 1; // 2
		for (int i = 3; i < under; i+=2) { // XXX optimization!: comparisons /= 2 :)!!
			int numDigits = 0;
			int num = i;
			while (num > 0) {
				numDigits++;
				num /= 10;
			}
			if (isCircularPrime(i, numDigits, 0)) {
				result++;
			}
		}
		return result;
	}
	
	private boolean isCircularPrime(int n, int numDigits, int numVariationsChecked) {
//		System.out.println("is circular for " + n);
		if (!isPrime(n)) {
			return false;
		}
		// check digit circular move combinations: xyzk -> kxyz -> zkxy -> yzkx -> first one (end).
		if (numVariationsChecked < numDigits-1) {
			int newN = (int)((n % 10) * Math.pow(10, numDigits-1) + n/10); //optimize using a loop instead of Math.pow (double->int conversion)
			//XXX important: 10^(numDigits-1) !!!
//			System.out.println("variation " + newN);
			return isCircularPrime(newN, numDigits, numVariationsChecked + 1);
		}
		return true;
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
		Problem35 o = new Problem35();
		int result = o.circularPrimes(1000000);
		System.out.println("result: " + result);
	}
}
