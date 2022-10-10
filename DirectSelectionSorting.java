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
        int arrRemainingNumbers = 0, currentIndex = 0;
        while (arrRemainingNumbers < arr.length) {
            int minIndex = findIndex(arr, currentIndex);
            if (arr[minIndex] < arr[currentIndex]) swap(arr, minIndex, currentIndex);
            currentIndex++;
            arrRemainingNumbers++;
        }
        return arr;
    }

    private static void swap(int[] arr, int minIndex, int currentIndex) {
        int tmp = arr[minIndex];
        arr[minIndex] = arr[currentIndex];
        arr[currentIndex] = tmp;
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
