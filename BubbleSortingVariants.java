package task;

import java.util.Arrays;

public class BubbleSortingVariants {
    public static void main(String[] args) {
        int[] arr = {12, 3, 4, 56, 7, 8};
        System.out.println(Arrays.toString(arr));

        bubbleForCycle(arr);
        System.out.println(Arrays.toString(arr));

        int[] brr = {122, 13, 444, 56, 7, 8};
        System.out.println(Arrays.toString(brr));

        bubbleWhileCycle(brr);
        System.out.println(Arrays.toString(brr));
    }

    private static void bubbleWhileCycle(int[] brr) {
        boolean isSorted = false;                                    // Флаагчето показва дали е сортиран масива:
        while (!isSorted) {                 // Условито за сортировка е : Да не сме преминали през редове от 25 до 29.
            isSorted = true;                //... или да не е преминато през условието: "if (brr[i] < brr[i + 1])"
            for (int i = 0; i < brr.length - 1; i++) {
                if (brr[i] < brr[i + 1]) {
                    int tmp = brr[i];
                    brr[i] = brr[i + 1];
                    brr[i + 1] = tmp;
                    isSorted = false;
                }
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
}
