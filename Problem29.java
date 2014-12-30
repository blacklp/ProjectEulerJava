package project_euler_java;

import java.math.BigInteger;
import java.util.HashSet;

public class Problem29 {
	/**
	 * 
	 * @param minA
	 * @param maxA
	 * @param minB
	 * @param maxB
	 * @return count of distinct elements generated with the formula a^b 
	 */
	public long distinctPowers(int minA, int maxA, int minB, int maxB) {
		HashSet<BigInteger> set = new HashSet<>(); // no need to sort them! :)
		
		for (int a = minA; a <= maxA; a++) {
			for (int b = minB; b <= maxB; b++) {
				BigInteger num = BigInteger.valueOf(a).pow(b);
				set.add(num);
			}
		}
		return set.size();
	}
	
	public static void main(String[] args) {
		Problem29 o = new Problem29();
		long result = o.distinctPowers(2, 100, 2, 100);
		System.out.println("Result: " + result);
	}
}
