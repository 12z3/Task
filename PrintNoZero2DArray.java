package hackerRank;

import training.Methods;
import java.util.Scanner;

public class PrintNoZero2DArray extends Methods {


    public static void printNoZero2DArray(int[][] arr) {
        int countZero = 0;
        System.out.print("[");
        for (int thisRow = 0; thisRow < arr.length; thisRow++) {
            for (int thiCol = 0; thiCol < arr[thisRow].length; thiCol++) {
                String indexStr = "" + thisRow + thiCol;
                int indexInt = Integer.parseInt(indexStr);

                if (arr[thisRow][thiCol] == 0) {
                    countZero++;
                } else {
                    if (indexInt > countZero) {               //  ????   23 > 3; Безсмисленно е, но Работи.
                        System.out.print(", ");
                    }
                    System.out.print(arr[thisRow][thiCol]);
                }
            }
        }
        System.out.print("]");
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        timeAndData();

        int[][] a = {{33, 33, 33},
                     {3, 3, 3},
                     {3, 33, 33}};

        int[][] aa = {{33, 0, 33},
                      {3, 0, 3},
                      {3, 33, 0}};

        int[][] b = new int[][]{{0, 0, 3}, {3, 0, 3}, {4, 0, 6}};

        printNoZero2DArray(b);
    }
}
