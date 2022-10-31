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
        // ob.insertionSort(arr);
        ob.mainInsertionSort(arr);

        //printArray(arr);
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
               of their current position */                                 // key = 3; j = 0;

            while (j >= 0 && arr[j] > key) {                                // {12, 3, 4, 56, 7, 8};
                arr[j + 1] = arr[j];                                        // ... -> {12, 12, 4, 56, 7, 8};
                j = j - 1;                                                  // j = -1;
            }                                                               // {12, 12, 4, 56, 7, 8};
            arr[j + 1] = key;                                               // arr[0] = 3 ... -> {3, 12, 4, 56, 7, 8};
        }
    }

    /* A utility function to print array of size n*/
    static void printArray(int arr[]) {
        int n = arr.length;
        for (int i = 0; i < n; ++i) {
            if (i > 0) System.out.print(", ");
            System.out.print(arr[i]);
        }
        System.out.println();
    }

    void mainInsertionSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int current = arr[i];
            int next = arr[i + 1];

            if (current > next) {                                          // {12, 3, 4, 56, 7, 1};
                int tmp = arr[i];                                          // ... {3, 4, 12, 56, 7, 8};
                arr[i] = arr[i + 1];
                arr[i + 1] = tmp;
            }
        }
    }
}
