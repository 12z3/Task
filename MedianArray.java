package task;

import java.util.Arrays;

public class MedianArray extends Sorting {

    /**
     * @Problem: <a href="https://practice.geeksforgeeks.org/problems/median-in-a-row-wise-sorted-matrix1527/1">...</a>
     * Given a row wise sorted matrix of size R*C where R and C are always odd, find the median of the matrix.
     */

    public static void main(String[] args) {

        int[][] input = {{1, 3, 5}, {2, 6, 9}, {3, 6, 9}};
        int[][] input1 = {{1}, {133}, {2}};                     // {{1}, {2}, {3}}          //  row = 3, col = 1;
        int row = 3, col = 3;
        // int row = 1, col = 3;

        System.out.println("median: " + median(input, row, col));
    }

    private static int median(int[][] matrix, int R, int C) {
        int index = -1, matrixSize = 0, median = 0;

        matrixSize = getMatrixSize(matrix, matrixSize);

        int[] array = new int[matrixSize];
        filledArray(matrix, R, C, index, matrixSize, array);

        Arrays.sort(array);

        median = getMedian(array);
        return median;
    }

    private static int getMedian(int[] array) {
        int median, index;
        if (array.length % 2 != 0) {
            index = (array.length / 2);
            median = array[index];
        } else {
            index = (array.length / 2) - 1;
            median = (array[index] + array[index + 1]) / 2;
        }
        return median;
    }

    private static void filledArray(int[][] matrix, int R, int C, int index, int matrixSize, int[] array) {
        if (R == 1) {                           // 0-колона и n-редове.
                                                // ... покрил си единственно случаят -> 1 ред и n-колони.
            for (int i = 0; i < C; i++) {       // n-реда и 1 колона липсва.
                index++;                        // т.е. необходима е и проверка на условието: "? R < C".
                array[index] = matrix[i][R - 1];
            }
        } else if (C == 1) {                    // 0-ред и n-колони.
                                                // ... покрил си единственно случаят -> 1 ред и n-колони.
            for (int i = 0; i < R; i++) {       // n-реда и 1 колона липсва.
                index++;                        // т.е. необходима е и проверка на условието: "? R < C".
                array[index] = matrix[C - 1][i];
            }
        } else {
            for (int col = 0; col < C; col++) {
                for (int row = 0; row < R; row++) {
                    index++;
                    array[index] = matrix[row][col];
                }
            }
        }
    }

    private static int getMatrixSize(int[][] matrix, int matrixSize) {
        for (int row1 = 0; row1 < matrix.length; row1++) {
            for (int col1 = 0; col1 < matrix[row1].length; col1++) {
                matrixSize++;
            }
        }
        return matrixSize;
    }


    private static int medianOld(int[][] matrix, int R, int C) {
        int index = -1, matrixSize = 0, median = 0;

        for (int row1 = 0; row1 < matrix.length; row1++) {
            for (int col1 = 0; col1 < matrix[row1].length; col1++) {
                matrixSize++;
            }
        }
        System.out.println(matrixSize);

        int[] array = new int[matrixSize];
        if (matrixSize <= 3) {
            int j = R - 1;
            for (int i = 0; i < C; i++) {
                index++;
                array[index] = matrix[i][j];
                System.out.println(Arrays.toString(array));
            }
        } else {
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[i].length; j++) {
                    index++;
                    array[index] = matrix[i][j];
                }
            }
        }

        Arrays.sort(array);

        if (array.length % 2 != 0) {
            index = (array.length / 2);
            median = array[index];
        } else {
            index = (array.length / 2) - 1;
            median = (array[index] + array[index + 1]) / 2;
        }
        return median;
    }

    // -----------------------------------------------------------------------------------------------------------------

    int median4(int matrix[][], int R, int C) {
        // code here
        int index = -1, matrixSize = 0, median = 0;

        for (int row1 = 0; row1 < matrix.length; row1++) {
            for (int col1 = 0; col1 < matrix[row1].length; col1++) {
                matrixSize++;
            }
        }
        System.out.println(matrixSize);

        int[] array = new int[matrixSize];
        if (R == 1) {

            for (int i = 0; i < C; i++) {
                index++;
                array[index] = matrix[R - 1][i];
            }
        } else if (C == 1) {

            for (int i = 0; i < R; i++) {
                index++;
                array[index] = matrix[i][C - 1];
            }
        } else {
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[i].length; j++) {
                    index++;
                    array[index] = matrix[i][j];
                }
            }
        }

        Arrays.sort(array);

        if (array.length % 2 != 0) {
            index = (array.length / 2);
            median = array[index];
        } else {
            index = (array.length / 2) - 1;
            median = (array[index] + array[index + 1]) / 2;
        }
        return median;
    }
}
