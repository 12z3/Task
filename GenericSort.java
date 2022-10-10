package task;

import java.util.Arrays;

public class GenericSort {
    public static void main(String[] args) {

        Integer[] arrI = {7, 3, 8, 4, 5, 2};
        Double[] arrD = {7.2, 3.3, 8.9, 4.0, 5.2, 2.9};
        String[] arrS = {"a", "z", "d", "q", "w", "b"};

        System.out.println(Arrays.toString(directSelectionSorting(arrD)));
        System.out.println(Arrays.toString(directSelectionSorting1(arrD)));
        System.out.println(Arrays.toString(bubbleSort(arrD)));

    }
    private static <T> T[] directSelectionSorting(T[] arr) {
        int currentIndex = 0;
        while (currentIndex < arr.length) {
            int minIndex = findIndex(arr, currentIndex);
            if ((int)arr[minIndex] < (int)arr[currentIndex]) swap(arr, minIndex, currentIndex);
            currentIndex++;
        }
        return arr;
    }

    private static <T> T[] directSelectionSorting1(T[] arr) {
        for (int currentIndex = 0; currentIndex < arr.length; currentIndex++) {
            int minIndex = findIndex(arr, currentIndex);
            if ((int)arr[minIndex] < (int)arr[currentIndex]) swap(arr, minIndex, currentIndex);
        }
        return arr;
    }

    private static <T> T[] bubbleSort(T[] arr) {
        for (int index1 = 0; index1 < arr.length; index1++) {
            for (int index2 = 0; index2 < arr.length; index2++) {
                if ((int)arr[index1] < (int)arr[index2]) {
                    swap(arr, index1, index2);
                }
            }
        }
        return arr;
    }

    private static <T> void swap(T[] arr, int minIndex, int currentIndex) {
        if (minIndex != currentIndex) {
            T tmp = arr[minIndex];
            arr[minIndex] = arr[currentIndex];
            arr[currentIndex] = tmp;
        }
    }

    private static <T> int findIndex(T[] arr, int currentIndex) {
        int min = Integer.MAX_VALUE, minIndex = 0;
        for (int i = currentIndex; i < arr.length; i++) {
            if ((int)arr[i] < min) {
                min = (int)arr[i];
                minIndex = i;
            }
        }
        return minIndex;
    }
}
