package problems;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class Problem11 {
	List<List<Integer>> grid = new ArrayList<List<Integer>>();
	private int MAX_X, MAX_Y;
	
	public Problem11(String fileName) {
		setupGrid(fileName);
	}
	
	private void setupGrid(String fileName) {
		try {
			Reader r = new FileReader(new File(fileName));
			BufferedReader br = new BufferedReader(r);
			String line = "";
			while ((line = br.readLine()) != null) {
				String[] lineArr = line.split(" ");
				insertLineAsInts(lineArr);
			}
			br.close();
		} catch(FileNotFoundException e) {
			System.out.println("Error: the file was not found!");
		} catch(IOException e) {
			System.out.println("Error: couldn't read the file!");
		}
		MAX_X = grid.get(0).size();
		MAX_Y = grid.size();
	}
	
	private void insertLineAsInts(String[] line) {
		List<Integer> lineList = new ArrayList<Integer>();
		for (String elem : line) {
			Integer num = Integer.valueOf(elem);
			lineList.add(num);
		}
		grid.add(lineList);
	}
	
	public long largestProductInGrid(int adjacencyNumber) {
		int result = 0;
		for (int y = 0; y < MAX_Y; y++) {
			for (int x = 0; x < MAX_X; x++) {
				// search horizontally to the right (->), vertically to the bottom (|) and diagonally to the bottom right (\)
				// ALSO DIAGONALLY TO TOP RIGHT!!! (/)
				for (Direction d : Direction.values()) {
					int product = getProductOfAjacentNumbers(x, y, adjacencyNumber, d);
					if (product > result) {
						result = product;
					}
				}
			}
		}
		return result;
	}
	
	private int getProductOfAjacentNumbers(int x, int y, int adjacencyNumber, Direction d) {
		int result = 1;
		switch (d) {
			case HORIZONTAL:
				for (int i = 0; i < adjacencyNumber; i++) {
					int newX = (x + i);// % MAX_X;
					if (newX >= MAX_X) {
						return 0;
					}
					int value = grid.get(y).get(newX);
					result *= value;
				}
			break;
			case VERTICAL:
				for (int i = 0; i < adjacencyNumber; i++) {
					int newY = (y + i);// % MAX_Y;
					if (newY >= MAX_Y) {
						return 0;
					}
					int value = grid.get(newY).get(x);
					result *= value;
				}
			break;
			case DIAGONAL_DOWNWARDS:
				for (int i = 0; i < adjacencyNumber; i++) {
					int newX = (x + i);// % MAX_X;
					int newY = (y + i);// % MAX_Y;
					if (newX >= MAX_X || newY >= MAX_Y) {
						return 0;
					}
					int value = grid.get(newY).get(newX);
					result *= value;
				}
			break;
			case DIAGONAL_UPWARDS: //// XXX KEEP IN MIND THIS DIRECTION!!!
				for (int i = 0; i < adjacencyNumber; i++) {
					int newX = (x + i);// % MAX_X;
					int newY = (y - i);// % MAX_Y;
					if (newX >= MAX_X || newY >= MAX_Y || newY < 0) {
						return 0;
					}
					int value = grid.get(newY).get(newX);
					result *= value;
				}
			break;
		}
		return result;
	}

	private enum Direction {
		HORIZONTAL,
		VERTICAL,
		DIAGONAL_DOWNWARDS,
		DIAGONAL_UPWARDS;
	}
	
	public static void main(String[] args) {
		StringBuilder sb = new StringBuilder();
		sb.append("c:");
		sb.append(File.separator);
		sb.append("Users");
		sb.append(File.separator);
		sb.append("Lucia");
		sb.append(File.separator);
		sb.append("Documents");
		sb.append(File.separator);
		sb.append("project_euler_11.txt"); 
		Problem11 o = new Problem11(sb.toString());
		int adjacencyNumber = 4;
		long result = o.largestProductInGrid(adjacencyNumber);
		System.out.println("Result: " + result);
	}
}
