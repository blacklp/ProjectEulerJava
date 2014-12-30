package project_euler_java;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.TreeSet;

public class Problem22 {
	private TreeSet<String> names = new TreeSet<>();
	
	public long nameScores(String fileName) {
		long result = 0;
		readFromFile(fileName);
		// now names contains the names in fileName
		Iterator<String> it = names.iterator();
		int i = 1;
		while (it.hasNext()) {
			String name = it.next();
			int wordScore = getWordScore(name);
			int positionScore = i;
			result += (wordScore * positionScore);
			i++;
		}
		return result;
	}
	
	private int getWordScore(String name) {
		int score = 0;
		for (int i = 0; i < name.length(); i++) {
			char c = name.charAt(i);
			score += (c - 'A' + 1); // All words are upper-case
		}
		return score;
	}
	
	private void readFromFile(String fileName) {
		File f = new File(fileName);
		try {
			FileReader fr = new FileReader(f);
			BufferedReader br = new BufferedReader(fr);
			String read = "";
			
			while ((read = br.readLine()) != null) {
				String[] arr = read.split(",");
				for (String name : arr) {
					name = name.replace("\"", "");
					names.add(name);
				}
			}
			br.close();
		} catch (IOException e) {
			System.out.println("Error: Couldn't read the file.");
			return;
		}
	}
	
	public static void main(String[] args) {
		Problem22 o = new Problem22();
		String fileName = "C:/Users/Lucia/Documents/project_euler_22.txt";
		long result = o.nameScores(fileName);
		System.out.println("Result: " + result);
	}
}
