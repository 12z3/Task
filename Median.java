package task;

import java.util.Arrays;

public class Median extends Sorting {

    /**
     * @Problem: <a href="https://practice.geeksforgeeks.org/problems/median-in-a-row-wise-sorted-matrix1527/1">...</a>
     * Given a row wise sorted matrix of size R*C where R and C are always odd, find the median of the matrix.
     */

    public static void main(String[] args) {

        int[][] input = {{1, 3, 5}, {2, 6, 9}, {3, 6, 9}};
        int[][] input1 = {{1}, {2}, {3}};                         //  row = 3, col = 1;
        int row = 3, col = 3;
       // int row = 1, col = 3;

        System.out.println(median(input, row, col));
    }

    private static int median(int[][] matrix, int R, int C) {
        int index = -1, matrixSize = 0, median = 0;

        for (int row1 = 0; row1 < matrix.length; row1++) {
            for (int col1 = 0; col1 < matrix[row1].length; col1++) {
                matrixSize++;
            }
        }

        int[] lsArr = new int[matrixSize];
        if (matrixSize <= 3) {
            int j = C - 1;
            for (int i = 0; i < R; i++) {
                index++;
                lsArr[index] = matrix[i][j];

            }
        } else {
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[i].length; j++) {
                    index++;
                    lsArr[index] = matrix[i][j];
                }
            }
        }

        Arrays.sort(lsArr);

        if (lsArr.length % 2 != 0) {
            index = (lsArr.length / 2);
            median = lsArr[index];
        } else {
            index = (lsArr.length / 2) - 1;
            median = (lsArr[index] + lsArr[index + 1]) / 2;
        }
        return median;
    }
}
