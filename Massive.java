package dynamicsStructhure;

import training.Methods;

import java.util.Scanner;

public class Massive extends Methods {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Give me a number of rows: ");
        int rows = scanner.nextInt();
        System.out.print("... and a number of cols: ");
        int cols = scanner.nextInt();

        matrixB(rows, cols);
        System.out.println();
        matrix(rows, cols);
        System.out.println();
        matrixA(rows, cols);
    }

    private static void matrixB(int rows, int cols) {
        int[][] a = new int[rows][cols];
        int row = rows - 1, col = 0, counter = 0, countCol = 0, countRow = 1;

        while (counter < rows * cols) {
            a[row++][col] = ++counter;
            while (row < rows && col < cols - 1) {
                a[row++][++col] = ++counter;
            }
            row = rows - (++countRow);                  // Държи правилната брой на редовете за следващата итерация.
            col = 0;                                    // Този ред ще се изпълни след като си стигнал последния ред,
            // но не последната колона на последният ред.
            if (row <= 0) {                             // Това е валидно когато си стигнал до първия ред -
                row = 0;                                // и на предишната итерация си запълнил и последният елемент -
                col = countCol++;                       // от последната колона на последният ред.
            }
        }
        printMatrix(a);
    }

    private static void matrix(int rows, int cols) {
        int[][] a = new int[rows][cols];
        int allCount = 1, countRColumElements, start = 1, leftRow, rightRow;


        for (int row = rows - 1; row >= 0; row--) {
            leftRow = row;
            for (int col = 0; col < a[row].length; col++) {
                a[row][col] = allCount++;
                if ((row == rows - 1)) break;
                row++;
            }
            row = leftRow;
            if ((a[0][0] != 0)) break;                   // за матрици от рода на [3][5] и [5][3]
        }

        for (int row = 0; row <= rows; row++) {
            countRColumElements = 0;
            rightRow = row;
            for (int col = start; col < a[0].length; col++) {
                a[row][col] = allCount++;
                row++;
                countRColumElements++;
                if (row == a.length) break;
            }
            if (allCount <= (rows * cols)) {
                row = (rightRow == 0) ? -1 : row - countRColumElements;
                start++;
            } else break;
        }
        printMatrix(a);
    }

    private static void matrixA(int rows, int cols) { // 3,5 и 5,3 не му понасят много много.
        int[][] a = new int[rows][cols];
        int counter = 1, row = rows - 1, col = 0, colCount = 0, numberOfRow = rows - 1;
        boolean flag = false;

        try {
            while (counter < rows * cols) {
                if (!flag) {
                    col = 0;
                } else {
                    col = col - 1;
                    flag = false;
                }
                while (row <= rows - 1 && col <= cols - 1) {
                    if (numberOfRow > -1) {
                        a[row++][col++] = counter++;
                    } else if (col < cols - 1) {
                        a[++row][++col] = counter++;
                    } else {
                        flag = true;
                        break;
                    }
                }
                numberOfRow = flag ? 0 : --numberOfRow;
                row = numberOfRow;
            }
        } catch (Exception ex) {
            System.out.println(" ;(  Някъде към края нещо се счупи.");
        }
        printMatrix(a);
    }

    private static void printMatrix(int[][] a) {
        for (int[] arr : a) {
            for (int el : arr) {
                System.out.print(el + " ");
            }
            System.out.println();
        }
    }

}
