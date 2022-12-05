package task;

import training.Methods;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

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

//        int a = 5;
//        int b = a++;    // b = a и след това а += 1;
//        int c = ++a;    // а += 1 и след това c = 1;
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


        //TODO: Find firstDuplicate:
        int[] a = {2, 1, 3, 1, 3, 2};
        int index = 0, sum = 0, count = 0;

        while (index < a.length) {
            sum += a[index];
            if (index != 0) {
                if (sum % a[index] == 0) {
                    count++;
                }
            }
            index++;
        }
        System.out.println(count);

    }
}
