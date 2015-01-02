package main;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Problem5 {
	public int smallestMultiple(int start, int end) {
		Map<Integer, Integer> numToExponent = new HashMap<>();
		if (start == 0 || start == 1) {
			start++;
		}
		for (int i = start; i <= end; i++) {
			boolean inserted = false;
			for (Entry<Integer, Integer> entry : numToExponent.entrySet()) {
				Integer key = entry.getKey();
				if ((i % key) == 0) {
					int exponent = (int)(Math.log(i) / Math.log(key)); // log_i(key)
					numToExponent.put(key, exponent);//entry.getValue() + 1);
					inserted = true;
					break;
				}
			}
			if (!numToExponent.containsKey(i) && !inserted) { // first one to be inserted, therefore exponent = 1
				numToExponent.put(i, 1);
			}
		}
		return mapMultWithExp(numToExponent);
	}
	
	private int mapMultWithExp(Map<Integer, Integer> map) {
		int result = 1;
		for (Entry<Integer, Integer> entry : map.entrySet()) {
			int key = entry.getKey();
			int exp = entry.getValue();
			result *= (Math.pow(key, exp));
		}
		return result;
	}
	
	public static void main(String[] args) {
//		int result = new Problem5().smallestMultiple(1, 10);
		int result = new Problem5().smallestMultiple(1, 20);
		System.out.println("result: " + result);
	}
}
