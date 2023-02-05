package task;

import java.util.*;

public class EncryptSortAndPrintArray {
    public static void main(String[] args) {

        /*
         * @Problem: Write a program that reads a sequence of strings from the console.
         * Encrypt every string by summing:
         * •	The code of each vowel multiplied by the string length.
         * •	The code of each consonant is divided by the string length.
         * Sort the number sequence in ascending order and print it on the console.
         * On the first line, you will always receive the number of strings you must read.
         * Examples:
         Input	        Output	 Comments
         * 4
         * Peter              1032     Peter = 1071
         * Maria              1071     Maria = 1532
         * Katya              1168     Katya = 1032    ?? == 1613
         * Todor	         1532     Todor = 1168

         * 3
         * Sofia             1396     Sofia = 1601
         * London            1601     London = 1396
         * Washington	 3202     Washington = 3202
         */
        // Unsolved -> Solved.

        /*
4
Peter
Maria
Katya
Todor

3
Sofia
London
Washington

         */

        encryptNames();
    }

    private static void encryptNames() {
        String name;
        int allSum, count = 0, index = 0, p = 0;

        Scanner scanner = new Scanner(System.in);
        Map<String, Integer> mapA = new LinkedHashMap<>();
        Map<Integer, Integer> mapB = new TreeMap<>();
        Map<String, Integer> mapC = new LinkedHashMap<>();

        StringBuilder vowel = getVowel();

        int number = scanner.nextInt();
        String[] names = new String[number];

        scanner.nextLine();
        while (count < number) {
            name = scanner.nextLine();
            names[count] = name;
            allSum = getNameSum(vowel, name);
            mapA.put(name, allSum);
            mapB.put(allSum, index++);
            //index++;
            count++;
        }

        for (Map.Entry<Integer, Integer> el : mapB.entrySet()) mapC.put(names[p++], el.getKey());
        for (Map.Entry<String, Integer> el : mapC.entrySet()) System.out.println(el + " ");
    }

    private static StringBuilder getVowel() {
        char[] letters = {'a', 'e', 'i', 'o', 'u', 'y'};
        StringBuilder vowels = new StringBuilder();
        for (char el : letters) vowels.append(Character.toLowerCase(el));
        return vowels;
    }

    private static int getNameSum(StringBuilder vowels, String name) {
        int allSum = 0, sum;
        for (int i = 0; i < name.length(); i++) {
            sum = (vowels.indexOf(String.valueOf(name.charAt(i))) != -1)
                    ? name.charAt(i) * name.length() : name.charAt(i) / name.length();
            allSum += sum;
        }
        return allSum;
    }
}

//        char[] letters = {'a', 'e', 'i', 'o', 'u', 'y'};
//        int n = scanner.nextInt();
//        String x = "";
//        int a = 0;
//        boolean isMatch = false, isNoMatch = false;
//        ArrayList<Integer> result = new ArrayList<>();
//
//        for (int i = 0; i < n; i++) {
//            int sum = 0, j = 0;
//            String word = scanner.next();
//            while (j < word.length()) {
//                for (int k = 0; k < letters.length; k++) {
//                    isMatch = isNoMatch = false;
//                    if (word.charAt(j) == letters[k]) {
//                        isMatch = true;
//                        break;
//                    } else {
//                        isNoMatch = true;
//
//                    }
//                }
//                if (isMatch) {
//                    a = word.charAt(j) * letters.length;
//                } else if (isNoMatch) a = word.charAt(j) / letters.length;
//                sum += a;
//                j++;
//            }
//            System.out.printf("%d \n", sum);
//
//        }
