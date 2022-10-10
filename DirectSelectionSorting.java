package hackerRank;

import java.util.Arrays;

public class DirectSelectionSorting {
    /**
     * Намира най - малкото число и го слага на 1-во място.
     * Търси следващото най-малко като се изключва намеренето
     */

    public static void main(String[] args) {

        int[] arr = {7, 3, 8, 4, 5, 2};
        System.out.println(Arrays.toString(directSelectionSorting(arr)));
    }

    private static int[] directSelectionSorting(int[] arr) {
        int remainingNumbersInArr = 0, currentIndex = 0;

        while (remainingNumbersInArr < arr.length) {
            int minIndex = findMinIndex(arr, currentIndex);
            if (arr[minIndex] < arr[currentIndex]) swap(arr, minIndex, currentIndex);
            currentIndex++;
            remainingNumbersInArr++;
        }
        return arr;
    }

    private static void swap(int[] arr, int minIndex, int currentIndex) {
        int tmp = arr[minIndex];
        arr[minIndex] = arr[currentIndex];
        arr[currentIndex] = tmp;
    }

    private static int findMinIndex(int[] arr, int fromIndex) {
        int min = Integer.MAX_VALUE, minIndex = 0;
        for (int i = fromIndex; i < arr.length; i++) {
            if (arr[i] < min) {
                min = arr[i];
                minIndex = i;
            }
        }
        return minIndex;
    }
}
