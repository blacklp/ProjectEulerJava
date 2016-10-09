package main;


public class Problem49 {
    public String primePermutations(int numDigits) {
        int first = getFirst(numDigits);
        int last = getLast(numDigits);

        System.out.println("first: " + first + ", last: " + last);

        for (int i = first; i <= last; i++) {
            int[] result = hasPropertyWithNumbers(i, numDigits);
            if (result != null && !isAlreadyKnown(i)) {
                StringBuilder sb = new StringBuilder();
                for (int elem : result) {
                    sb.append(elem);
                    System.out.println("elem: " + elem);
                }
                return sb.toString();
            }
        }
        return "Not found"; // error
    }

    private int[] hasPropertyWithNumbers(int n, int numDigits) {
        int neededNumbers = 3;
        int[] found = new int[neededNumbers];
        if (!isPrime(n)) {
            return null;
        }
        int count = 0;
        for (int i = numDigits; i > 0; i--) {
            for (int j = i+1; j <= numDigits; j++) {
                int permutation = swapDigit(n, i, j);
                System.out.println("permutation of " + n + ": " + permutation);
                if (isPrime(permutation)) {
                    found[count] = permutation;
                    count++;
                    if (found.length == neededNumbers) {
                        return found;
                    }
                }
            }
        }
        return null;
    }

    private int swapDigit(int n, int d1, int d2) {
        System.out.println("swap digits " + d1 + " and " + d2 + " from " + n);
        int count = Math.max(d1, d2);
        int div1 = 1, div2 = 1;
        for (int i = count; i > 0; i--) {
            if (i >= d1) {
                div1 *= 10;
            }
            if (i >= d2) {
                div2 *= 10;
            }
        }
        int digit1 = (n % div1) / (div1 == 1 ? 1 : div1/10), digit2 = (n % div2) / (div2 == 1 ? 1 : div2/10);
        System.out.println("dig1 " + " for " + d1 + ": " + digit1 + " ... dig2 for " + d2 + ": " + digit2);
        int max = Math.max(div1, div2);
        int min = Math.min(div1, div2);
        int firstPart = n / max * max;
        int middlePart = ((n / min) * min) % (max/10); // TODO: merge both firstPart and middlePart concepts into 1!
        System.out.println(firstPart + " + " + middlePart + "+" + "((" + (div2 > div1) + ")" + "(" + digit1 + "*" + div2 + "+" + digit2 + "*" + div1 + "))");
        return firstPart + middlePart + ((div2 > div1) ? (digit1*div2/10 + digit2*div1/10) : (digit2*div1/10 + digit1*div2/10));
    }

    private boolean isPrime(int n) {
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    private boolean isAlreadyKnown(int num) {
        return num == 1487 || num == 4817 || num == 8147;
    }

    private int getFirst(int numDigits) { // for numDigits = 4 -> returns 1234
        int result = 0;
        int multiplier = 1;
        for (int i = numDigits; i > 0; i--) {
            result += multiplier * i;
            multiplier *= 10;
        }
        return result;
    }

    private int getLast(int numDigits) { // for numDigits = 4 -> returns 9876
        int result = 0;
        int multiplier = 1;
        for (int i = 9; (9-i) < numDigits; i--) {
            result += multiplier * i;
            multiplier *= 10;
        }
        return result;

    }

    public static void main(String[] args) {
        Problem49 o = new Problem49();
        String result = o.primePermutations(4);
        System.out.println("result: " + result);
    }
}
