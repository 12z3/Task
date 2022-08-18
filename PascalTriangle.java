package Task;

import java.util.Scanner;

public class PascalTriangle {
    /**
     * @Problem: The triangle may be constructed in the following manner:
     * In row 0 (the topmost row), there is a unique nonzero entry 1.
     * Each entry of each subsequent row is constructed by adding the number above and to the left
     * with the number above and to the right, treating blank entries as 0.
     * For example, the initial number in the first (or any other) row is 1
     * (the sum of 0 and 1), whereas the numbers 1 and 3 in the third row are added
     * to produce the number 4 in the fourth row.
     * @Web: : https://en.wikipedia.org/wiki/Pascal's_triangle
     * <p>
     * n = 4:
     * 1
     * 1 1
     * 1 2 1
     * 1 3 3 1
     * <p>
     * n = 13:
     * 1
     * 1 1
     * 1 2 1
     * 1 3 3 1
     * 1 4 6 4 1
     * 1 5 10 10 5 1
     * 1 6 15 20 15 6 1
     * 1 7 21 35 35 21 7 1
     * 1 8 28 56 70 56 28 8 1
     * 1 9 36 84 126 126 84 36 9 1
     * 1 10 45 120 210 252 210 120 45 10 1
     * 1 11 55 165 330 462 462 330 165 55 11 1
     * 1 12 66 220 495 792 924 792 495 220 66 12 1
     */

    public static void pascalTriangle() {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int k = n + 2;
        int[][] a = new int[k][k];

        for (int row = 1; row < k - 1; row++) {
            for (int col = 1; col < row + 1; col++) {
                a[row][1] = 1;
                if (row > 1) {
                    a[row][col] = a[row - 1][col - 1] + a[row - 1][col];
                }
                System.out.print(" " + a[row][col] + " ");

            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        pascalTriangle();
    }
}
