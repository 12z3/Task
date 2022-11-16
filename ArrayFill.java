package task;

import java.util.Scanner;

public class ArrayFill {
    /**
     4
     4
     i = 3; j = 0 -> 1
     startRowIdx = 3 startColIdx = 0
     i = 2; j = 0 -> 2
     startRowIdx = 2 startColIdx = 0
     i = 3; j = 1 -> 3
     startRowIdx = 2 startColIdx = 0
     i = 1; j = 0 -> 4
     startRowIdx = 1 startColIdx = 0
     i = 2; j = 1 -> 5
     startRowIdx = 1 startColIdx = 0
     i = 3; j = 2 -> 6
     startRowIdx = 1 startColIdx = 0
     i = 0; j = 0 -> 7
     startRowIdx = 0 startColIdx = 0
     i = 1; j = 1 -> 8
     startRowIdx = 0 startColIdx = 0
     i = 2; j = 2 -> 9
     startRowIdx = 0 startColIdx = 0
     i = 3; j = 3 -> 10
     startRowIdx = 0 startColIdx = 0
     i = 0; j = 1 -> 11
     startRowIdx = 0 startColIdx = 1
     i = 1; j = 2 -> 12
     startRowIdx = 0 startColIdx = 1
     i = 2; j = 3 -> 13
     startRowIdx = 0 startColIdx = 1
     i = 0; j = 2 -> 14
     startRowIdx = 0 startColIdx = 2
     i = 1; j = 3 -> 15
     startRowIdx = 0 startColIdx = 2
     i = 0; j = 3 -> 16
     startRowIdx = 0 startColIdx = 3

     7 11 14 16
     4 8 12 15
     2 5 9 13
     1 3 6 10
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int rows = sc.nextInt();
        int cols = sc.nextInt();
        int[][] arr = new int[rows][cols];

        int startRowIdx = arr.length - 1;             // 3
        int startColIdx = 0;
        int valueToFill = 1;
        // while (startCol <= arr[0].length - 1) {
        while (valueToFill <= rows * cols) {
            int i = startRowIdx;                      // 3
            int j = startColIdx;                      // 0
            while (i < rows && j < cols) {
                arr[i][j] = valueToFill;              // 30->20->31
                valueToFill++;
                System.out.println( "i = " + i + "; j = " + j + " -> " + arr[i][j]);
                System.out.println("startRowIdx = " + startRowIdx + " startColIdx = " + startColIdx);
                i++;
                j++;
            }
            if (startRowIdx > 0) {                    // ..не съм опрял в горният край на мсива
                startRowIdx--;
            } else {
                startColIdx++;
            }
        }
        for (int k = 0; k < arr.length; k++) {           // Не случайно печата масива тук....
            for (int p = 0; p < arr[0].length; p++) {
                System.out.print(arr[k][p] + " ");
            }
            System.out.println();
        }
        System.out.println(arr.length);
    }
}


