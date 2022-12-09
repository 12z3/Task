package task;

import java.util.Arrays;

public class QuickSortThings {
    public static class QuickSort {
        public static void quickSort(int[] arr, int left, int right) {
            // check for empty or null array
            if (arr == null || arr.length == 0) {
                return;
            }

            if (left >= right) {
                return;
            }

            // pick the pivot
            int middle = left + (right - left) / 2;
            int pivot = arr[middle];

            // make left < pivot and right > pivot
            int i = left, j = right;
            while (i <= j) {
                //Check until all values on left side array are lower than pivot
                while (arr[i] < pivot) {
                    i++;
                }
                //Check until all values on left side array are greater than pivot
                while (arr[j] > pivot) {
                    j--;
                }
                //Now compare values from both side of lists to see if they need swapping
                //After swapping move the iterator on both lists
                if (i <= j) {
                    swap(arr, i, j);
                    i++;
                    j--;
                }
            }
            //Do same operation as above recursively to sort two sub arrays
            if (left < j) {
                quickSort(arr, left, j);
            }
            if (right > i) {
                quickSort(arr, i, right);
            }
        }

        public static void swap(int[] arr, int i, int j) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }

        public static void main(String[] args) {
            int[] arr = {9, 8, 7, 6, 5, 4, 3, 2, 1};
            quickSort(arr, 0, arr.length - 1);
            System.out.println(Arrays.toString(arr)); // [1, 2, 3, 4, 5, 6, 7, 8, 9]
        }
    }

}
