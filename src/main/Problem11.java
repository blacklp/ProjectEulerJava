package main;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Problem11 {
    List<List<Integer>> grid = new ArrayList<List<Integer>>();
    private int MAX_X, MAX_Y;

    public Problem11(String fileName) throws GridSetupException {
        setupGrid(fileName);
    }

    private void setupGrid(String fileName) throws GridSetupException {
        try (BufferedReader br = new BufferedReader(new FileReader(new File(fileName)))) {
            String line = "";
            while ((line = br.readLine()) != null) {
                String[] lineArr = line.split(" ");
                insertLineAsInts(lineArr);
            }
        } catch(FileNotFoundException e) {
            throw new GridSetupException("Error: the file was not found!", e);
        } catch(IOException e) {
            throw new GridSetupException("Error: couldn't read the file!", e);
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

    public static void main(String[] args) throws Exception {
        if (args.length != 1) {
            throw new IllegalArgumentException("No file location argument was given");
        }
        String fileLocation = args[0];

        Problem11 o = new Problem11(fileLocation + "project_euler_11.txt");
        int adjacencyNumber = 4;
        long result = o.largestProductInGrid(adjacencyNumber);
        System.out.println("Result: " + result);
    }

    private class GridSetupException extends Exception {
        public GridSetupException(String message, Throwable cause) {
            super(message, cause);
        }
    }
}
