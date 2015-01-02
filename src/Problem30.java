package project_euler_java;

public class Problem30 {
	public long digitNthPowers(int n) {
		long result = 0;
//		int min = (int) Math.pow(10, n-1); // at least n=5 digits (9^5 = 59049 -> 5 digits)
//		int max = (int) Math.pow(10, n); // at most n+1=6 digits (5*9^5 = 295245 -> 6 digits) -> with 6 digits we can have 6*9^5=354294
		// upperbound (according to 354294) = 355000   
		int upperbound = 355000;//10000000;
		
		for (int i = 2; i < upperbound; i++) {
			int digitSum = getDigitSum(i, n);
			if (digitSum == i) {
				result += i;
				System.out.println(i);
			}
		}
		return result;
	}
	
	private int getDigitSum(int num, int numDigits) {
		int sum = 0;
//		System.out.print("digit: 0");
//		for (int i = 1; i <= numDigits; i++) {
//			int power1 = (int) Math.pow(10, i); //XXX remember Math.pow(double, double) => returns double
//			// XXX -> faster if we use10*10*10...!!!
//			int power2 = (int) Math.pow(10, i-1);
//			int digit = (num % power1) / power2;
			int n = num;
			while (n > 0) {
				int digit = n % 10; 
				int digitPower = digit;
				for (int j = 1; j < numDigits; j++) {
					digitPower *= digit;
				}
				n /= 10;
				sum += digitPower;
			}
//			System.out.print("+" + digit + "^" + numDigits);
//			sum += Math.pow(digit, numDigits); //XXX digit^numDigits !!!! (not only digit !!!)
			
//		}
//		System.out.println();
		return sum;
	}
	
	public static void main(String[] args) {
		Problem30 o = new Problem30();
		long result = o.digitNthPowers(5);
		System.out.println("result: " + result);
	}
}
