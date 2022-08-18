package Task;

import java.util.ArrayList;
import java.util.Scanner;

public class EncryptSortAndPrintArray {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


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
