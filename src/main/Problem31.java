package main;

public class Problem31 {
//	private static final int[] coins = new int[] {200, 100, 50, 20, 10, 5, 2, 1};
//	private int sum = 0;
//	
//	public int coinSums(int sum) {
//		coinSumForCoin(0, sum);
//		return this.sum;
//	}
//	
//	private void coinSumForCoin(int index, int target) {
//		int coin = coins[index];
//		for (int i = target; i >= 0; i -= coin) {
//			if (index < coins.length - 2) { // XXX no loop for coin = 1p ...?
//				coinSumForCoin(index + 1, i);
//			}
//			else {
//				sum++;
//			}
//		}
//	}

    // Dynamic Programming:
    private static final int[] coins = new int[] {1, 2, 5, 10, 20, 50, 100, 200};

    public int coinSums(int target) {
//		int target = 200;
        int[] ways = new int[target+1];
        ways[0] = 1;

        for (int i = 0; i < coins.length; i++) {
            for (int j = coins[i]; j <= target; j++) {
                ways[j] += ways[j-coins[i]]; // emulates the number of ways (at each iteration of giving change of j if they pay with coins[i]
                                            // in order to make this work, we have to add ways[0] = 1 (only 1 way if it costs 0 � -> 0 coins?).
                                            // in order to make this work, we have to add ways[0] = 1 (only 1 way if it costs 0 � -> 0 coins?).
            }
        }
//		System.out.println();
//		for (int way : ways) {
//			System.out.print(way + " ");
//		}
//		System.out.println();
        return ways[ways.length-1];
    }

    public static void main(String[] args) {
        Problem31 o = new Problem31();
        long start = System.currentTimeMillis();
        int result = o.coinSums(200);
        long end = System.currentTimeMillis();
        System.out.println("Result: " + result);
        System.out.println("Took " + (end - start)); // 9, 10, 12 ms.
    }
}
