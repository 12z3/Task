package hackerRank;

import java.util.Arrays;

public class DirectSelectionSorting {

    /**
     * Намира най - малкото число и го слага на 1-во място.
     * Търси следващото най-малко като се изключва намеренето
     */


    public static void main(String[] args) {

        int[] arr = {7, 3, 8, 4, 5, 2};
        sorting(arr);
    }

    private static void sorting(int[] arr) {
        int numbersInArr = 0, fromIndex = 0;

        while (numbersInArr < arr.length) {
            int minIndex = findMin(arr, fromIndex);
            swap(arr, minIndex, fromIndex);
            fromIndex++;
            numbersInArr++;

        }
        System.out.println(Arrays.toString(arr));
    }

    private static void swap(int[] arr, int index1, int index2) {
        int el1 = arr[index1], el2 = arr[index1], tmp = 0;
        tmp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = tmp;
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
