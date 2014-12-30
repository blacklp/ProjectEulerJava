package project_euler_java;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Problem42 {
	public int codedTriangleNumbers(String fileName) {
		int result = 0;
		try {
			FileReader fr = new FileReader(new File(fileName));
			BufferedReader br = new BufferedReader(fr);
		
			String line = "";
			while ((line = br.readLine()) != null) {
				String[] words = line.split(",");
				for (String word : words) {
					word = word.substring(1, word.length()-1); //remove ""
					if (isTriangle(word)) {
						result++;
					}
				}
			}
			br.close();
		} catch (IOException e) {
			System.out.println("Could not read the file, IOException found.");
		}
		return result;
	}
	
	private boolean isTriangle(String word) {
		int letterSum = getLetterSum(word);
		// check whether a value n exists s.t. letterSum = (1/2)*n*(n+1) -> 2*(LS) = n*(n+1) -> n^2 + n - 2*(LS) = 0
		int fourAC = 1 + 8 * letterSum;//1^2 - 4 * 1 * (-2) * letterSum; 
		double n = (-1 + Math.sqrt(fourAC)) / 2;
		int intN = (int)n;
		if (n - intN == 0) { // n has no decimals
			return true;
		}
		return false;
	}
	
	private int getLetterSum(String word) {
		int result = 0;
		for (int i = 0; i < word.length(); i++) {
			char c = word.charAt(i);
			result += (c - 'A' + 1); // all upper case
		}
		return result;
	}
	
	public static void main(String[] args) {
		Problem42 o = new Problem42();
		int result = o.codedTriangleNumbers("C:/Users/Lucia/Documents/words.txt");
		System.out.println("result: " + result);
	}
}
