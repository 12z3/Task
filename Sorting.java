package task;

import java.util.Arrays;

public class Sorting {
    /**
     * @directSelectionSorting: Намира най - малкото число и го слага на 1-во място.
     * Търси следващото най-малко такова като се изключва предишното от търсенето.
     */

    public static void main(String[] args) {

        /**
         * ... ??? -> j < a.length - i - 1 -> Търси най-големият елемент в масива и го поставя в края му.
         *         Изключва крайният елемент (j < a.length - i - 1) в следващото търсене (итерация на i).
         </p>
         *              [1, 15, 12, 3, 5, 7, 4, 9]
         </p>
         i = 0, j = 1:  [1, 12, 15, 3, 5, 7, 4, 9]  i = 0 -> j = [0.. 6]
         i = 0, j = 2:  [1, 12, 3, 15, 5, 7, 4, 9]
         i = 0, j = 3:  [1, 12, 3, 5, 15, 7, 4, 9]
         i = 0, j = 4:  [1, 12, 3, 5, 7, 15, 4, 9]
         i = 0, j = 5:  [1, 12, 3, 5, 7, 4, 15, 9]  ... на края на 5-та итерация и началото на 6-та.
         i = 0, j = 6:  [1, 12, 3, 5, 7, 4, 9, 15]  ... на края на 6-та итерация и 7-ма няма.
         i = 1, j = 1:  [1, 3, 12, 5, 7, 4, 9, 15]  i = 1 -> j = [0.. 5]
         i = 1, j = 2:  [1, 3, 5, 12, 7, 4, 9, 15]
         i = 1, j = 3:  [1, 3, 5, 7, 12, 4, 9, 15]
         i = 1, j = 4:  [1, 3, 5, 7, 4, 12, 9, 15]
         i = 1, j = 5:  [1, 3, 5, 7, 4, 9, 12, 15]
         i = 2, j = 3:  [1, 3, 5, 4, 7, 9, 12, 15]  i = 2 -> j = [0.. 4]
         i = 3, j = 2:  [1, 3, 4, 5, 7, 9, 12, 15]  i = 3 -> j = [0.. 3]
         */


        int[] a = {1, 15, 12, 3, 5, 7, 4, 9};
        bubbleOptimized(a);

        int[] arr = {7, 3, 8, 4, 5, 2};
        System.out.println(Arrays.toString(directSelectionSorting(arr)));
        System.out.println(Arrays.toString(directSelectionSorting1(arr)));
        System.out.println(Arrays.toString(bubbleSort(arr)));
    }

    private static int[] bubbleSort(int[] arr) {
        for (int index1 = 0; index1 < arr.length; index1++) {
            for (int index2 = 0; index2 < arr.length; index2++) {
                if (arr[index1] < arr[index2]) {
                    swap(arr, index1, index2);
                }
            }
        }
        return arr;
    }
    public static void bubbleOptimized(int[] a) {
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a.length - i - 1; j++) {       // от 1-я до последния. От 2-я до предпоследния и т.н.
                if (a[j] > a[j + 1]) {
                    int tmp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = tmp;
                    System.out.println("i = " + i +
                            ", j = " + j + ":  " + Arrays.toString(a));
                }
            }
        }
    }

    public static int[] directSelectionSorting(int[] arr) {
        int currentIndex = 0;
        while (currentIndex < arr.length) {
            int minIndex = findIndex(arr, currentIndex);
            swap(arr, minIndex, currentIndex);
           // if (arr[minIndex] < arr[currentIndex]) swap(arr, minIndex, currentIndex);
            currentIndex++;
        }
        return arr;
    }

    public static int[] directSelectionSorting1(int[] arr) {
        for (int currentIndex = 0; currentIndex < arr.length; currentIndex++) {
            int minIndex = findIndex(arr, currentIndex);
            swap(arr, minIndex, currentIndex);
            //if (arr[minIndex] < arr[currentIndex]) swap(arr, minIndex, currentIndex); // ... няма смисъл от него
        }                      // arr[minIndex] < arr[currentIndex] е проверено в метода findIndex
        return arr;
    }

    private static void swap(int[] arr, int minIndex, int currentIndex) {
        if (minIndex != currentIndex) {
            int tmp = arr[minIndex];
            arr[minIndex] = arr[currentIndex];
            arr[currentIndex] = tmp;
        }
    }

    private static int findIndex(int[] arr, int currentIndex) {
        int min = Integer.MAX_VALUE, minIndex = 0;
        for (int i = currentIndex; i < arr.length; i++) {      // Валидно е и: i = currentIndex + 1
            if (arr[i] < min) {                      // ... идеята е да не проверява текущият сам със себе си всеки път
                min = arr[i];
                minIndex = i;
            }
        }
        return minIndex;
    }
}
