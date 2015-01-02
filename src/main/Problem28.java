package main;

import java.util.Arrays;

public class Problem28 {
//	public void drawSpiral(int maxX, int maxY) {
//		int x = 0, y = 0;
//		int dx = 0, dy = -1;
//		
//		for (int i = 0; i < Math.pow(Math.max(maxX, maxY), 2); i++) {
//			System.out.println("x=" + x + ",y=" + y);
//			boolean xBetweenHalfX = (x > -maxX / 2 && x <= maxX / 2);
//			boolean yBetweenHalfY = (y > -maxY / 2 && y <= maxY / 2);
//			if (xBetweenHalfX && yBetweenHalfY) {
////				System.out.println(x + " " + y);
//			}
//			if (x == y || (x < 0 && x == -y) || (x > 0 && x == 1-y)) {
//				int copy = dx;
//				dx = -dy;
//				dy = copy; // old dx
//			}
//			x += dx;
//			y += dy;
//		}
//	}
	
		private int[][] spiral;
		private int size;
		LastMove lastMove = null;
		
		public Problem28(int size) {
			spiral = new int[size][size];
			fillSpiralWith0s();
			this.size = size;
		}
		
		private void fillSpiralWith0s() {
			for (int  i = 0; i < size; i++) {
				int[] arr = new int[size];
				Arrays.fill(arr, 0);
				spiral[i] = arr;
			}
//			System.out.println("spiral:");
//			for (int[] arr : spiral) {
//				for (int n : arr) {
//					System.out.print(n + " ");
//				}
//				System.out.println();
//			}
		}
		
		public long numberSpiralDiagonals() {
			int middlePoint = size / 2;
			int i = middlePoint, j = middlePoint;
			int number = 1;
			while (i < size && j < size) { // not last position (size-1, 0)
//				if (j == (size - 1) && i == 0) {
//					break;
//				}
				spiral[i][j] = number;
				// new position for i and j
				int[] ij = getNewIAndJ(i, j);
				i = ij[0];
				j = ij[1];
				number++;
			}
//			System.out.println("spiral:");
//			for (int[] arr : spiral) {
//				for (int n : arr) {
//					if (n < 10) {
//						System.out.print(" ");
//					}
//					System.out.print(n + "  ");
//				}
//				System.out.println();
//			}
			return getDiagonalSum();
		}
		
		private long getDiagonalSum() {
			long result = 0;
			for (int i = 0; i < size; i++) {
				result += spiral[i][i]; // from top left to bottom right
				result += spiral[size-1-i][i]; // from top right to bottom left
			}
			return result - 1; // summed twice!
		}
		
		private int[] getNewIAndJ(int i, int j) {
//			System.out.println("last move: " + lastMove);
			if (lastMove == null) {
				lastMove = LastMove.RIGHT;
				return new int[] { i, j+1 };
			}
			switch (lastMove) {
				case RIGHT:
					if (spiral[i+1][j] != 0) { // if down is already filled, then going right again
						return new int[] { i, j+1 };
					}				
					lastMove = LastMove.DOWN;
					return new int[] { i+1, j };
				case DOWN:
//					System.out.println("last move was down. left is " + spiral[i][j-1]);
					if (spiral[i][j-1] != 0) { // if left is already filled, then going down again
						return new int[] { i+1, j };
					}
					lastMove = LastMove.LEFT;
					return new int[] { i, j-1 };
				case LEFT:
					if (spiral[i-1][j] != 0) { // if up is already filled, then going left again
						return new int[] { i, j-1 };
					}
					lastMove = LastMove.UP;
					return new int[] { i-1, j };
				case UP:
					if (spiral[i][j+1] != 0) { // if right is already filled, then going up again
						return new int[] { i-1, j };
					}
					lastMove = LastMove.RIGHT;
					return new int[] { i, j+1 };
			}
			return new int[] {};
		}
		
		private enum LastMove {
			RIGHT,
			LEFT,
			UP,
			DOWN
		}
	
	public static void main(String[] args) {
		Problem28 o = new Problem28(1001);
		long result = o.numberSpiralDiagonals();
		System.out.println("result: " + result);
	}
}
