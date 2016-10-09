package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.TreeSet;

public class Problem22 {
    private TreeSet<String> names = new TreeSet<>();

    public long nameScores(String fileName) throws IOException {
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

    private void readFromFile(String fileName) throws IOException {
        File f = new File(fileName);
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String read = "";

            while ((read = br.readLine()) != null) {
                String[] arr = read.split(",");
                for (String name : arr) {
                    name = name.replace("\"", "");
                    names.add(name);
                }
            }
        } finally {
        }
    }

    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            throw new IllegalArgumentException("No file location argument found.");
        }
        Problem22 o = new Problem22();
        String fileLocation = args[0];
        String fileName = fileLocation + "/project_euler_22.txt";
        long result = o.nameScores(fileName);
        System.out.println("Result: " + result);
    }
}
