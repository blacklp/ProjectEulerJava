package problems;

public class Problem45 {
	public long triangularPentagonalAndHexagonal(int start) {
		long i = start;

		while (true) {
			if (isTriangular(i) && isPentagonal(i) && isHexagonal(i)) {
				return i;
			}
			i++;
		}
	}
	
	/**
	 * 
	 * @param n
	 * @return true if exists i s.t. n = i * (i+1) / 2 -> i^2 + i - 2*n = 0 -> i = (-1 + Math.sqrt(1 + 8*n)) / 2
	 */
	private boolean isTriangular(long n) {
		double i = (-1 + Math.sqrt(1 + 8*n)) / 2d;
		int intI = (int)i;
		return (double)(i - intI) == 0d; // i is int
	}
	
	/**
	 * 
	 * @param n
	 * @return true if exists i s.t. n = i * (3*i - 1) / 2
	 */
	private boolean isPentagonal(long n) {
		// 2*n = 3*i^2 - i -> 3*i^2 - i - 2*n -> i = (1 (+/-) sqrt(1 - 4 * 3 * (-2*n))) / 2*3 -> i = (1 + sqrt(1 + 24*n)) / 6
		double i = (1 + Math.sqrt(1 + 24 * n)) / 6;
		int intI = (int)i;
		return (double)(i - intI) == 0d; // i is int
	}
	
	/**
	 * 
	 * @param n
	 * @return true if exists i s.t. n = i * (2*i - 1)
	 */
	private boolean isHexagonal(long n) {
		// n = 2*i^2 - i -> 2*i^2 - i - n -> i = (1 (+/-) sqrt(1 - 4 * 2 * (-n))) / (2*2) -> i = (1 (+/-) sqrt(1 + 8 * n)) / 4
		double i = (1 + Math.sqrt(1 + 8 * n)) / 4;
		int intI = (int)i;
		return (double)(i - intI) == 0d; // i is int
	}
	
	public static void main(String[] args) {
		Problem45 o = new Problem45();
		long start = System.currentTimeMillis();
		long result = o.triangularPentagonalAndHexagonal(40756);
		long end = System.currentTimeMillis();
		System.out.println("result: " + result);
		System.out.println("took " + (end - start) + "ms."); // 28423ms.
	}
}
 /*
  * package problems;

public class Problem45 {
	public long triangularPentagonalAndHexagonal(int start) {
		long i = start;

		while (true) {
			long triangular = i * (2*i - 1);
			// isTriangular(i) && 
			if (isPentagonal(triangular)) { // && isHexagonal(i)) { // hexagonal is a subset of triangular, therefore no need to check
																	// (if the number is triangular, it is also hexagonal). :)!
													 				// BUT!: only hexagonal numbers based on an ODD n are also triangular!!!
				return i;
			}
			i += 2;
		}
	}
	
	
	 * 
	 * @param n
	 * @return true if exists i s.t. n = i * (i+1) / 2 -> i^2 + i - 2*n = 0 -> i = (-1 + Math.sqrt(1 + 8*n)) / 2
	 *
	private boolean isTriangular(long n) {
		double i = (-1 + Math.sqrt(1 + 8*n)) / 2d;
		int intI = (int)i;
		return (double)(i - intI) == 0d; // i is int
	}
	
	 * 
	 * @param n
	 * @return true if exists i s.t. n = i * (3*i - 1) / 2
	private boolean isPentagonal(long n) {
		// 2*n = 3*i^2 - i -> 3*i^2 - i - 2*n -> i = (1 (+/-) sqrt(1 - 4 * 3 * (-2*n))) / 2*3 -> i = (1 + sqrt(1 + 24*n)) / 6
		double i = (1 + Math.sqrt(1 + 24 * n)) / 6;
		int intI = (int)i;
		return (double)(i - intI) == 0d; // i is int
	}
	
	 * 
	 * @param n
	 * @return true if exists i s.t. n = i * (2*i - 1)
	private boolean isHexagonal(long n) {
		// n = 2*i^2 - i -> 2*i^2 - i - n -> i = (1 (+/-) sqrt(1 - 4 * 2 * (-n))) / (2*2) -> i = (1 (+/-) sqrt(1 + 8 * n)) / 4
		double i = (1 + Math.sqrt(1 + 8 * n)) / 4;
		int intI = (int)i;
		return (double)(i - intI) == 0d; // i is int
	}
	
	public static void main(String[] args) {
		Problem45 o = new Problem45();
		long start = System.currentTimeMillis();
		long result = o.triangularPentagonalAndHexagonal(287);//40756);
		long end = System.currentTimeMillis();
		System.out.println("result: " + result);
		System.out.println("took " + (end - start) + "ms."); // 28423ms.
	}
}
*/
