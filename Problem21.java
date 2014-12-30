package project_euler_java;

import java.util.HashMap;
import java.util.Map;

public class Problem21 {
	private Map<Long, Long> numberToDivisorSum = new HashMap<>();
	
	public long amicableNumberSum(int under) {
		long result = 0;
		for (int i = 2; i <= under; i++) {
			long sum = getDivisorSum(i);
			numberToDivisorSum.put((long)i, sum);
			if ((long)i == sum) {
				continue;
			}
			Long sum2 = numberToDivisorSum.get(sum);
			if (sum2 != null && sum2.equals((long)i)) {
				// is amicable
				result += (i + sum); //XXX Remember to count both numbers: the pair!
			}
		}
		return result;
	}
	
	private long getDivisorSum(int num) {
		long result = 0;
		for (int i = 1; i <= num/2; i++) { //XXX remember to check only up to num/2!!!
			if (num % i == 0) {
				result += i;
			}
		}
		return result;
	}
	
	public static void main(String[] args) {
		Problem21 o = new Problem21();
		long start = System.currentTimeMillis();
		long result = o.amicableNumberSum(10000);
		long end = System.currentTimeMillis();
		System.out.println("took " + (end - start) + " ms."); // 180 (with i < num it takes 280!!).
		System.out.println("Result: " + result);
	}
}
