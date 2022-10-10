package hackerRank;

import java.util.Arrays;

public class Sorting {
    /**
     * @directSelectionSorting: Намира най - малкото число и го слага на 1-во място.
     * Търси следващото най-малко като се изключва намеренето
     */

    public static void main(String[] args) {

        int[] arr = {7, 3, 8, 4, 5, 2};
        System.out.println(Arrays.toString(directSelectionSorting(arr)));
        System.out.println(Arrays.toString(directSelectionSorting1(arr)));
        System.out.println(Arrays.toString(bubbleSort(arr)));
    }

    private static int[] directSelectionSorting(int[] arr) {
        int currentIndex = 0;
        while (currentIndex < arr.length) {
            int minIndex = findIndex(arr, currentIndex);
            if (arr[minIndex] < arr[currentIndex]) swap(arr, minIndex, currentIndex);
            currentIndex++;
        }
        return arr;
    }

    private static int[] directSelectionSorting1(int[] arr) {
        for (int currentIndex = 0; currentIndex < arr.length; currentIndex++) {
            int minIndex = findIndex(arr, currentIndex);
            if (arr[minIndex] < arr[currentIndex]) swap(arr, minIndex, currentIndex);
        }
        return arr;
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

    private static void swap(int[] arr, int minIndex, int currentIndex) {
        if (minIndex != currentIndex) {
            int tmp = arr[minIndex];
            arr[minIndex] = arr[currentIndex];
            arr[currentIndex] = tmp;
        }
    }

    private static int findIndex(int[] arr, int currentIndex) {
        int min = Integer.MAX_VALUE, minIndex = 0;
        for (int i = currentIndex; i < arr.length; i++) {
            if (arr[i] < min) {
                min = arr[i];
                minIndex = i;
            }
        }
        return minIndex;
    }
}
