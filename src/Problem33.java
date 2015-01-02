package project_euler_java;

public class Problem33 {
	public int digitCancelingFractions() {
		int denProduct = 1, numProduct = 1;
		
		for (int i = 1; i < 10; i++) { // digit between 1 and 9 
			for (int den = 1; den < i; den++) {
				for (int num = 1; num < den; num++) {
					if ((num * 10 + i) * den == num * (i * 10 + den)) { // XXX changing num <-> den and i <-> (num|den)
						numProduct *= num;
						denProduct *= den;
					}
				}
			}
		}
		return denProduct /= gcd(numProduct, denProduct); 
	}
	
	/**
	 * 
	 * @param n1 > 0
	 * @param n2 > 0
	 * @return greatest common divisor of n1 and n2
	 */
	private int gcd(int n1, int n2) {
		int result = 1;
		for (int i = 2; i < Math.max(n1, n2)/2; i++) { //XXX ALWAYS STARTING WITH 2!!!!!!
			while (n1 % i == 0 && n2 % i == 0) {
				result *= i;
				n1 /= i;
				n2 /= i;
			}
		}
		return result;
	}
	
	public static void main(String[] args) {
		Problem33 o = new Problem33();
		int result = o.digitCancelingFractions();
		System.out.println("result: " + result);
	}
}
