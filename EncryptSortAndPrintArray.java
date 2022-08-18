package Task;

import java.util.ArrayList;
import java.util.Scanner;

public class EncryptSortAndPrintArray {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        /**
         * @Problem: Write a program that reads a sequence of strings from the console.
         * Encrypt every string by summing:
         * •	The code of each vowel multiplied by the string length.
         * •	The code of each consonant is divided by the string length.
         * Sort the number sequence in ascending order and print it on the console.
         * On the first line, you will always receive the number of strings you must read.
         * Examples
         Input	        Output	 Comments
         * 4
         * Peter        1032     Peter = 1071
         * Maria        1071     Maria = 1532
         * Katya        1168     Katya = 1032
         * Todor	    1532     Todor = 1168

         * 3
         * Sofia        1396     Sofia = 1601
         * London       1601     London = 1396
         * Washington	3202     Washington = 3202
         */

        // Unsolved!!!

        char[] letters = {'a', 'e', 'i', 'o', 'u', 'y'};
        int n = scanner.nextInt();
        String x = "";
        int a = 0;
        boolean isMatch = false, isNoMatch = false;
        ArrayList<Integer> result = new ArrayList<Integer>();

        for (int i = 0; i < n; i++) {
            int sum = 0, j = 0;
            String word = scanner.next();
            while (j < word.length()) {
                for (int k = 0; k < letters.length; k++) {
                    isMatch = isNoMatch = false;
                    if (word.charAt(j) == letters[k]) {
                        isMatch = true;
                        break;
                    } else {
                        isNoMatch = true;

                    }
                }
                if (isMatch) {
                    a = word.charAt(j) * letters.length;
                } else if (isNoMatch) a = word.charAt(j) / letters.length;
                sum += a;
                j++;
            }
            System.out.printf("%d \n", sum);

        }


    }
}
