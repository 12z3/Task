package task;

import java.util.Arrays;

public class ThinkThis {
    public static void main(String[] args) {

        /**
         * ... ??? -> j < a.length - i - 1
         </p>
         *              [1, 1, 12, 3, 5, 7, 4, 9]
         i = 0, j = 2:  [1, 1, 3, 12, 5, 7, 4, 9]
         i = 0, j = 3:  [1, 1, 3, 5, 12, 7, 4, 9]
         i = 0, j = 4:  [1, 1, 3, 5, 7, 12, 4, 9]
         i = 0, j = 5:  [1, 1, 3, 5, 7, 4, 12, 9]
         i = 0, j = 6:  [1, 1, 3, 5, 7, 4, 9, 12]
         i = 1, j = 4:  [1, 1, 3, 5, 4, 7, 9, 12]
         i = 2, j = 3:  [1, 1, 3, 4, 5, 7, 9, 12]
         */


        int[] a = {1, 1, 12, 3, 5, 7, 4, 9};

        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a.length - i - 1; j++) {
                if (a[j] > a[j + 1]) {
                    int tmp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = tmp;
                    System.out.println("i = " + i + ", j = " + j + ":  " + Arrays.toString(a));
                }
            }
        }


    }
}
