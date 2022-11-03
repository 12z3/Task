package task;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MedianList {
    /**
     * @Problem: <a href="https://practice.geeksforgeeks.org/problems/median-in-a-row-wise-sorted-matrix1527/1">...</a>
     * Given a row wise sorted matrix of size R*C where R and C are always odd, find the median of the matrix.
     */

    public static void main(String[] args) {

        int[][] input1 = {{1, 3, 5}, {2, 6, 9}, {3, 6, 9}};
        int[][] input = {{1}, {133}, {3}};                        // {{1}, {2}, {3}}          //  row = 3, col = 1;
        int row = 1, col = 3;
        // int row = 1, col = 3;

        System.out.println("median: " + median(input, row, col));
    }

    private static int median(int[][] matrix, int R, int C) {
        int index = -1, matrixSize = 0, median = 0;

        for (int row1 = 0; row1 < matrix.length; row1++) {
            for (int col1 = 0; col1 < matrix[row1].length; col1++) {
                matrixSize++;
            }
        }

        List<Integer> ls = new ArrayList<>();
        if (matrixSize <= 3) {
            int j = R - 1;
            for (int i = 0; i < C; i++) {
                ls.add(matrix[i][j]);
            }
        } else {
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[i].length; j++) {
                    index++;
                    ls.add(matrix[i][j]);
                }
            }
        }

        Collections.sort(ls);

        if (ls.size() % 2 != 0) {
            index = (ls.size() / 2);
            median = ls.get(index);
        } else {
            index = (ls.size() / 2) - 1;
            median = (ls.get(index) + ls.get(index + 1)) / 2;
        }
        return median;
    }


}
