package main;

public class Problem39 {
	public int integerRightTriangles(int maxP) {
		int result = 1;
		int currNumSolutions = 0;
		for (int p = 1; p <= maxP; p++) {
			int numSols = getNumSolutions(p);
			if (numSols > currNumSolutions) {
				currNumSolutions = numSols;
				result = p;
			}
		}
		return result;
	}
	
	private int getNumSolutions(int perimeter) {
		// perimeter = h + c1 + c2     and   h = sqrt(c1^2 + c2^2)
		int result = 0;
		for (int c1 = 1; c1 < perimeter; c1++) {
			for (int c2 = c1; c2 < perimeter; c2++) {
				if (c1 + c2 >= perimeter) {
					break;
				}
				if (existsHypotenuse(c1, c2, perimeter)) {
					result++;
				}
			}
		}
		return result;
	}
	
	private boolean existsHypotenuse(int c1, int c2, int perimeter) {
		// h^2 = (perimeter - c1 - c2)^2 == c1^2 + c2^2 ?
		int h = perimeter - c1 - c2;
		int h2 = (h * h);
		return h2 == (c1 * c1 + c2 * c2);
	}
	
	public static void main(String[] args) {
		Problem39 o = new Problem39();
		int result = o.integerRightTriangles(1000);
		System.out.println("result: " + result);
	}
}
