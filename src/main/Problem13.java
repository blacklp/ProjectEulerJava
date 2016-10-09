package main;

import java.io.*;
import java.math.BigInteger;

public class Problem13 {
    public BigInteger firstDigitsOfSum(int numDigits, String fileName) throws IOException {
        BigInteger result = BigInteger.ZERO;

        File f = new File(fileName);
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String read;
            while ((read = br.readLine()) != null) {
                BigInteger num = new BigInteger(read);
                result = result.add(num);
            }
        } finally {
        }
        String allDigitStr = result.toString();
        String numDigitStr = allDigitStr.length() < numDigits ? allDigitStr : allDigitStr.substring(0, numDigits);

        return new BigInteger(numDigitStr);
    }

    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            throw new IllegalArgumentException("no file location argument was given");
        }
        Problem13 o = new Problem13();
        String fileLocation = args[0];
        BigInteger result = o.firstDigitsOfSum(10, fileLocation+ "/project_euler_13.txt");
        System.out.println("result: " + result);
    }
}
