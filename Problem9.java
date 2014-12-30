package project_euler_java;

public class Problem9 {
	public int specialPythagoreanTriplet(int desiredNumber) {
		int a = 0, b = 1, c = 2;
		boolean found = false;
		int n = 1, m = 2; 
		while (!found) {
			while (lessThanDesiredNumber(m, n, desiredNumber)) {
				found = isDesiredNumber(m, n, desiredNumber);
				if (found) {
					break;
				}
				m++;
			}
			found = isDesiredNumber(m, n, desiredNumber);
			if (found) {
				break;
			}
			n++;
			m = (n + 1);
			if (moreThanDesiredNumber(m, n, desiredNumber)) {
				System.out.println("Not found!!");
				return -1;
			}
		}
		if (found) {
			System.out.println("found: m = " + m + ", n = " + n);
		}
		a = m*m - n*n;
		b = 2*m*n;
		c = m*m + n*n;
		return a * b * c;
	}
	
	/**
	 * 
	 * @param m
	 * @param n
	 * @param desiredNumber
	 * @return whether we can get @param desiredNumber when using the formula or not   
	 */
	private boolean isDesiredNumber(int m, int n, int desiredNumber) {
		int result = getNumberFromFormula(m, n);
		return result == desiredNumber;
	}
	
	private boolean lessThanDesiredNumber(int m, int n, int desiredNumber) {
		int result = getNumberFromFormula(m, n);
		return result < desiredNumber;
	}
	
	private boolean moreThanDesiredNumber(int m, int n, int desiredNumber) {
		int result = getNumberFromFormula(m, n);
		return result > desiredNumber;
	}
	
	/**
	 * 
	 * @param m
	 * @param n
	 * @return 2m*(m + n)
	 */
	private int getNumberFromFormula(int m, int n) {
		return 2 * m * (m + n);
	}

	public static void main(String[] args) {
		int result = new Problem9().specialPythagoreanTriplet(1000);
		System.out.println("result: " + result);
	}
}
