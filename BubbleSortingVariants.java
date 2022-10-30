package task;

import java.util.Arrays;

public class BubbleSortingVariants {
    public static void main(String[] args) {
        int[] arr = {12, 3, 4, 56, 7, 8};
//        System.out.println(Arrays.toString(arr));
//
//        bubbleForCycle(arr);
//        System.out.println(Arrays.toString(arr));
//        System.out.println();
//
//        int[] brr = {122, 13, 444, 56, 7, 8};
//        System.out.println(Arrays.toString(brr));
//
//        bubbleWhileCycle(brr);
//        System.out.println(Arrays.toString(brr));

        BubbleSortingVariants ob = new BubbleSortingVariants();
        ob.insertionSort(arr);

        printArray(arr);
    }

    private static void bubbleWhileCycle(int[] brr) {
        boolean isSorted = false;                                    // Флаагчето показва дали е сортиран масива:
        while (!isSorted) {                 // Условито за сортировка е : Да не сме преминали през редове от 25 до 29.
            isSorted = true;                //... или да не е преминато през условието: "if (brr[i] < brr[i + 1])"
            for (int i = 0; i < brr.length - 1; i++) {
                if (brr[i] > brr[i + 1]) {
                    int tmp = brr[i];
                    brr[i] = brr[i + 1];
                    brr[i + 1] = tmp;
                    isSorted = false;
                }
                //System.out.println(Arrays.toString(brr));
            }
        }
    }

    private static void bubbleForCycle(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                if (arr[i] < arr[j]) {
                    int tmp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = tmp;
                }
            }
        }
    }


    void insertionSort(int arr[]) {
        int n = arr.length;
        for (int i = 1; i < n; ++i) {
            int key = arr[i];
            int j = i - 1;

            /* Move elements of arr[0..i-1], that are
               greater than key, to one position ahead
               of their current position */
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            arr[j + 1] = key;
        }
    }

    /* A utility function to print array of size n*/
    static void printArray(int arr[]) {
        int n = arr.length;
        for (int i = 0; i < n; ++i)
            System.out.print(arr[i] + " ");

        System.out.println();
    }
}
