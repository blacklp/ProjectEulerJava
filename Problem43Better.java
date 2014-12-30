package problems;

public class Problem43Better {
	private int[] perm = new int[10];
	public long subStringDivisibility() {
		long result = 0;
		int[] divisors = new int[] {1, 2, 3, 5, 7, 11, 13, 17};
		
		int count = 1;
		int numPerm = 3265920; // 9! * 9
		
		while (count < numPerm) {
			int n = perm.length;
			int i = (n - 1);
			while (i > 0 && perm[i-1] >= perm[i]) {
				i--;
			} // i = index s.t. perm[i] is max(perm[k]) for all  i <= k < n 
			int j = n;
			while (j > 0 && i > 0 && perm[j-1] <= perm[i-1]) {
				j--;
			} // j = index s.t. perm[j-1] is the first elem less than or equal perm[i-1] found starting from j=n.
			
			// swap values at position i-1 and j-1
			swap(i-1, j-1);
			
			i++;
			j = n;
			while (i < j) {
				swap(i-1, j-1);
				i++;
				j--;
			}
			boolean divisible = true;
			for (int k = 1; k < divisors.length; k++) {
				int num = 100*perm[k] + 10*perm[k+1] + perm[k+2];
				if (num % divisors[k] != 0) {
					divisible = false;
					break;
				}
			}
			if (divisible) {
				long num = 0;
				for (int k = 0; k < perm.length; k++) {
					num = 10*num + perm[k]; 
				}
				result += num;
			}
			count++;
		}
		return result;
	}
	
	private void swap(int i, int j) {
		if (i < 0 || j < 0) {
			return;
		}
		int aux = perm[i];
		perm[i] = perm[j];
		perm[j] = aux;
	}
	
	public static void main(String[] args) {
		Problem43Better o = new Problem43Better();
		long result = o.subStringDivisibility();
		System.out.println("result: " + result);
	}
}
