package project_euler_java;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Problem26 {
	/**
	 * 
	 * @param upper
	 * @return the denominator d < upper for which 1/d has the longest recurring cycle 
	 */
	public int reciprocalCycles(int upper) {
		int result = -1;
		int currentLength = 0; // no cycle
		
		for (int i = 2; i < upper; i++) {
			System.out.println("trying 1/" + i + "=" + (1/(double)i));
			int cycleLength = getCycleLength(1, i);
			if (cycleLength > currentLength) {
				currentLength = cycleLength;
				result = i;
			}
		}
		System.out.println("result is case 1/" + result + " with cycle of length " + currentLength);
		return result;
	}
	
//	private int getCycleLength(int num, int den) {
//		BigDecimal value = BigDecimal.valueOf(num).divide(BigDecimal.valueOf(den), new MathContext(100));
//		System.out.println("value: " + value);
//		int decimal = 0;
//		int startPosition = 0, endPosition = 0;
//		List<Integer> digitsRead = new ArrayList<>();
//		
//		while (true) {
//			decimal = (value.multiply(BigDecimal.valueOf(10))).intValue();
//			int digit = (decimal % 10);
//			if (digitsRead.size() == 100) { // check for irrational numbers
//				return 0;
//			}
//			if (digitsRead.contains(digit)) {
//				// determine if from digitsRead.indexOf(digit) until digitsRead.size() the digits are the same to be generated from now on...
//				boolean isCycle = determineIfCycle(digitsRead, digitsRead.indexOf(digit) + 1, value.multiply(BigDecimal.valueOf(10)));
//				if (isCycle) {
//					if (digitsRead.get(digitsRead.size() - 1).equals(0)) { // if last one is 0 it is a false cycle: e.g. 0.25000000000...
//						isCycle = false;
//						return 0;
//					}
//					startPosition = digitsRead.lastIndexOf(digit);
//					endPosition = digitsRead.size();
//					return (endPosition - startPosition);
//				}
//			}
//			digitsRead.add(digit);
//			value = value.multiply(BigDecimal.valueOf(10));
//		}
//	}
//		
//	private boolean determineIfCycle(List<Integer> digitsRead, int startPosition, BigDecimal value) {
////		System.out.println("list: " + digitsRead);
//		int i = startPosition;
//		while (i < digitsRead.size()) {
//			long decimal = (value.multiply(BigDecimal.valueOf(10))).longValue();
//			int digit = (int) (decimal % 10);
////			System.out.println("digit decimal % 10 =  (" + decimal + " % " + 10
////					+ ") =" + digit + " compared to get(" + i + ")" + digitsRead.get(i));
//			if (!digitsRead.get(i).equals(digit)) {
//				return false;
//			}
//			value = value.multiply(BigDecimal.valueOf(10));
//			i++;
//		}
//		return true;
//	}
	
	private int getCycleLength(int num, int den) {
		List<Integer> fraction = new LinkedList<>();
		Map<Integer, Integer> reminders = new HashMap<>();
		while (true) {
			num *= 10;
			int r = num % den;
			int q = (num - r) / den;
			if (r == 0) { // exact division
				fraction.add(q);
				break;
			}
			if (reminders.containsKey(r)) {
				boolean foundCycle = false;
				for (Map.Entry<Integer, Integer> entry : reminders.entrySet()) {
					if (entry.getValue().equals(r) && fraction.get(entry.getKey()).equals(q)) {
						// mark the cycle
						fraction.add(entry.getKey(), -1); // -1 represents here "("
						fraction.add(-2); // -2 represents here ")"
						foundCycle = true;
//						System.out.print("found cycle: ");
//						for (Integer i : fraction) {
//							String str = i + "";
//							if (str.equals("-1")) {
//								str = "(";
//							} else if (str.equals("-2")) {
//								str = ")";
//							}
//							System.out.print(str);
//						}
						System.out.println();
						break;
					}
				}
				if (foundCycle) {
					break;
				}
			}
			reminders.put(fraction.size(), r);
			fraction.add(q);
			num = r;
		}
		return fraction.indexOf(-2) - fraction.indexOf(-1);
	}	
	/// Binary (does not work...)
//	private int getCycleLength(int num, int den) {
//		int digit = -1;
//		while (num >= den) {
//			den <<= 1;
//			digit++;
//		}
//		Map<Integer, Integer> states = new HashMap<Integer, Integer>();
//		boolean found = false;
//		while (num > 0 || digit >= 0) {
//			if (digit == -1) {
//				System.out.print(".");
//			}
//			num <<= 1;
//			if (states.containsKey(num)) {
//				System.out.println(states + " contains " + num);
//				
//				System.out.println(digit >= 0 ? (digit + 1) : "");
//				System.out.println("Repeat from digit {" + states.get(num) + "} length {" + (states.get(num) - digit) + "}.");
//				found = true;
//				break;
//			}
//			states.put(num, digit);
//			if (num < den) {
//				System.out.print("0");
//			} else {
//				System.out.print("1");
//				num -= den;
//			}
//			digit--;
//		}
//		if (!found) {
//			System.out.println();
//			System.out.println("No repeat.");
//		}
//		return digit;
//	}
	
	public static void main(String[] args) {
		Problem26 o = new Problem26();
		int result = o.reciprocalCycles(1000);
		System.out.println("result: " + result);
//		int result = o.getCycleLength(1, 5);
//		System.out.println("result for " + (1d / 5d) + ": " + result);
	}
}
