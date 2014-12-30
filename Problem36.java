package problems;

public class Problem36 {
	public long doubleBasePalindromes(int under) {
		long result = 0;
		for (int i = 1; i < under; i++) {
			if (isPalindrome(i + "") && isPalindrome(Integer.toBinaryString(i))) {
				result += i;
			}
		}
		return result;
	}
	
	private boolean isPalindrome(String number) {
		for (int i = 0; i < number.length(); i++) {
			if (number.charAt(i) != number.charAt(number.length() - i - 1)) {
				return false;
			}
		}
		return true;
	}
	
	public static void main(String[] args) {
		Problem36 o = new Problem36();
		long result = o.doubleBasePalindromes(1000000);
		System.out.println("result: " + result);
	}
}
