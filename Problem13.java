package problems;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;

public class Problem13 {
	public BigInteger firstDigitsOfSum(int numDigits, String fileName) {
		BigInteger result = BigInteger.ZERO;
		
		File f = new File(fileName);
		try {
			FileReader fr = new FileReader(f);
			BufferedReader br = new BufferedReader(fr);
			String read;
			while ((read = br.readLine()) != null) {
				BigInteger num = new BigInteger(read);
				result = result.add(num);
			}
			br.close();
		} catch(IOException e) {
			System.out.println("Error when reading the file.");
			return BigInteger.valueOf(-1);
		}
		String allDigitStr = result.toString();
		String numDigitStr = allDigitStr.length() < numDigits ? allDigitStr : allDigitStr.substring(0, numDigits);
		
		return new BigInteger(numDigitStr);
	}
	
	public static void main(String[] args) {
		Problem13 o = new Problem13();
		BigInteger result = o.firstDigitsOfSum(10, "C:/Users/Lucia/Documents/project_euler_13.txt");
		System.out.println("result: " + result);
	}
}
