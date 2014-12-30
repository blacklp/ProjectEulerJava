package project_euler_java;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Problem18And67 {
	private long[][] tree;
	private boolean[][] calculated;
	private List<List<Long>> treeFromFile;
	private Map<Integer, Map<Integer, Boolean>> calculatedFromFile;

	public Problem18And67(String fileName) {
		treeFromFile = new ArrayList<>();
		calculatedFromFile = new HashMap<>(); 
		File f = new File(fileName);
		try {
			FileReader fr = new FileReader(f);
			BufferedReader br = new BufferedReader(fr);
			String read;
			int i = 0;
			while ((read = br.readLine()) != null) {
				String[] arr = read.split(" ");
				List<Long> list = new ArrayList<>();
				Map<Integer, Boolean> map = new HashMap<>();
				int j = 0;
				for (String s : arr) {
					list.add(Long.valueOf(s));
					map.put(j, false);
					j++;
				}
				treeFromFile.add(list);
				calculatedFromFile.put(i, map);
				i++;
			}
			br.close();
		} catch (IOException e) {
			System.out.println("Error: Couldn't read the file.");
		}
//		System.out.println("tree:");
//		System.out.println(treeFromFile);
//		System.out.println("calculated:");
//		System.out.println(calculatedFromFile);
	}
	
	public Problem18And67() {
		tree = new long[][] {
		{ 75 },
		{ 95, 64 },
		{ 17, 47, 82 },
		{ 18, 35, 87, 10 },
		{ 20, 04, 82, 47, 65 },
		{ 19, 01, 23, 75, 03, 34 },
		{ 88, 02, 77, 73, 07, 63, 67 },
		{ 99, 65, 04, 28, 06, 16, 70, 92 },
		{ 41, 41, 26, 56, 83, 40, 80, 70, 33 },
		{ 41, 48, 72, 33, 47, 32, 37, 16, 94, 29 },
		{ 53, 71, 44, 65, 25, 43, 91, 52, 97, 51, 14 },
		{ 70, 11, 33, 28, 77, 73, 17, 78, 39, 68, 17, 57 },
		{ 91, 71, 52, 38, 17, 14, 91, 43, 58, 50, 27, 29, 48 },
		{ 63, 66, 04, 68, 89, 53, 67, 30, 73, 16, 69, 87, 40, 31 },
		{ 04, 62, 98, 27, 23,  9, 70, 98, 73, 93, 38, 53, 60, 04, 23 }
//
//				{ 3 },
//				{ 7, 4 },
//				{ 2, 4, 6 },
//				{ 8, 5, 9, 3 }
		};
		int numCols = tree.length;
		int numRows = tree[numCols - 1].length;
		calculated = new boolean[numCols][numRows];
		for (int  i = 0; i < calculated.length; i++)
			Arrays.fill(calculated[i], false);
	}
	
	public long maximumPathSum() {
		recAlgorithm(0, 0);
		return tree[0][0];
	}

	private long recAlgorithm(int i, int j) {
		if (i < tree.length - 1 && !calculated[i][j]) {
			tree[i][j] += Math.max(recAlgorithm(i+1, j), recAlgorithm(i+1, j+1));
			calculated[i][j] = true;
		}
		return tree[i][j];
	}
	
	public long maximumPathSumFromFile() {
		recAlgorithmFromFile(0, 0);
		return treeFromFile.get(0).get(0);
	}

	private long recAlgorithmFromFile(int i, int j) {
		if (i < treeFromFile.size() - 1 && !calculatedFromFile.get(i).get(j)) {
			List<Long> list = treeFromFile.get(i);
			if (list == null) {
				list = new ArrayList<>();
			}
			long left = i+1 < treeFromFile.size() && j < treeFromFile.get(i+1).size() ? recAlgorithmFromFile(i+1, j) : 0;
			long right = i+1 < treeFromFile.size() && j+1 < treeFromFile.get(i+1).size() ? recAlgorithmFromFile(i+1, j+1) : 0;
			long max = Math.max(left, right); 
			list.set(j, treeFromFile.get(i).get(j) + max);
			treeFromFile.set(i, list);
			Map<Integer, Boolean> map = calculatedFromFile.get(i);
			if (map == null) {
				map = new HashMap<>();
			}
			map.put(j, true);
			calculatedFromFile.put(i, map);
		}
		return treeFromFile.get(i).get(j);
	}
	
	public static void main(String[] args) {
		String fileName = "C:/Users/Lucia/Documents/project_euler_18.txt";
		Problem18And67 o = new Problem18And67(fileName); // 7273
		long result = o.maximumPathSumFromFile();
		System.out.println("result: " + result);
		
//		Problem18And67 o = new Problem18And67(); // 1074
//		long result = o.maximumPathSum();
//		System.out.println("result: " + result);
	}
}