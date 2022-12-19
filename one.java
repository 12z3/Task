package task;

import training.Methods;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatterBuilder;
import java.util.*;

public class one extends Methods {
    /**
     * 4-4:
     * 7 11 14 16
     * 4 8 12 15
     * 2 5 9 13
     * 1 3 6 10               // 30 - 20 - 31
     */                        // 10 -
    public static void main(String[] args) {
        timeAndData();
//        int[][] arr = new int[4][4];
//
//        int val = 1, startRow = arr.length - 1, startCol = 0; //col = 0;
//        boolean flag = false;

//        for (int row = startRow; row >= 0; row--) {      // 30 -> 21 -> 31
//            for (col = startCol; col < arr[row].length; col++) {
//                   arr[row][col] = val++;
//
//
//            }
//        }
//
//        for (int i = 0; i < arr.length; i++) {
//            for (int j = 0; j < arr[i].length; j++) {
//                System.out.print(arr[i][j] + " ");
//            }
//            System.out.println();
//        }
//
//        int a = 5;
//        int b = a++;    // b = a и след това а += 1;
//        int c = ++a;    // а += 1 и след това c = 1;
//
//        boolean t = true;
//        String ans = c > 222 ? "YES" : "NO";
//        String ans1 = t ? "YES" : "NO";
//
//        System.out.println(ans);
//        System.out.println(ans1);
//
//        System.out.println(a);
//        System.out.println(b);
//        System.out.println(c);
//
//        String s = " 23@5@2021@2024  ";
//        System.out.println(s.length());
//        String[] sa = s.trim().split("@", 22);    // "limit" -> ограничава броят на изведените субстрингове.
//        System.out.println(Arrays.toString(sa));
//        System.out.println(sa.length);
//        System.out.println();
//
//        LocalDateTime dt = LocalDateTime.now();
//        System.out.println(dt);
//
//        DateTimeFormatterBuilder date = new DateTimeFormatterBuilder();


        //String str = "asta la vista baby";
        String str = "astasta la vista babyastasta";
        String line = str.substring(0, 7);
        System.out.println(line);

        char targetCH = 'y';
        String targetSTR = "asta";

        int count = 0, j = 0, fromIndexCH = j, fromIndexSTR = j;
        while (j < str.length()) {
           // int chars = str.indexOf(targetCH, fromIndexCH);
            int strings = str.indexOf(targetSTR, fromIndexSTR);
            if (strings != -1) {
               // fromIndexCH = str.indexOf(targetCH, fromIndexCH) + 1;
                fromIndexSTR  = str.indexOf(targetSTR, fromIndexSTR) + 1;
                count++;
            }
            j++;
        }

        System.out.println(count);
    }
}
