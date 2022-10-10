package hackerRank;

import java.util.Arrays;

public class DirectSelectionSorting {
    /**
     * Намира най - малкото число и го слага на 1-во място.
     * Търси следващото най-малко като се изключва намеренето
     */


    public static void main(String[] args) {

        int[] arr = {7, 3, 8, 4, 5, 2};
        directSelectionSorting(arr);
    }

    private static void directSelectionSorting(int[] arr) {
        int remainingNumbersInArr = 0, currentIndex = 0;

        while (remainingNumbersInArr < arr.length) {
            int minIndex = findMin(arr, currentIndex);
            swap(arr, minIndex, currentIndex);
            currentIndex++;
            remainingNumbersInArr++;
        }
        System.out.println(Arrays.toString(arr));
    }

    private static void swap(int[] arr, int minIndex, int currentIndex) {
        int tmp = arr[minIndex];
        arr[minIndex] = arr[currentIndex];
        arr[currentIndex] = tmp;
    }

    private static int findMin(int[] arr, int fromIndex) {
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
