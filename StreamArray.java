package task;

import java.util.Arrays;

public class StreamArray {

    /**
     * @WhatIsThis: Arrays.stream(arr11).mapToInt(Integer : : parseInt).sorted().toArray();
     * <p>
     * Arrays.stream(arr11) -> Връща последователността arr11 във вид на поток от тип String.
     * mapToInt(Integer::parseInt) -> преобразува потока от String -> int
     * <p>
     */


    public static void main(String[] args) {
        int[] arr1 = {1, 2, 3, 4, 5};
        int[] arr3 = {1, 2, 3, 4, 5};
        int[] arr2 = {13, 2, 23, 14, 45};

        String[] arr11 = {"1", "2", "3", "4", "5"};
        String[] arr31 = {"1", "2", "3", "4", "5"};

        arraysComparator(arr1, arr2);
        streamArraysComparator(arr11, arr31);
    }

    private static void streamArraysComparator(String[] arr11, String[] arr31) {
        int[] arr1Sorted = Arrays.stream(arr11).mapToInt(Integer::parseInt).sorted().toArray();
        int[] arr2Sorted = Arrays.stream(arr31).mapToInt(Integer::parseInt).sorted().toArray();
        System.out.println(Arrays.equals(arr1Sorted, arr2Sorted) ? "YES" : "NO");
    }

    private static void arraysComparator(int[] arr1, int[] arr2) {
        int allCounts = 0;
        for (int i = 0; i < arr1.length; i++) {
            int localCount = 0, el1 = arr1[i];
            for (int j = 0; j < arr2.length; j++) {
                int el2 = arr2[j];
                if (arr1.length != arr2.length) {
                    System.out.print("NO");
                    return;
                }
                if (el1 == el2) localCount++;
                if (localCount > 1) {
                    System.out.print("NO");
                    return;
                }
            }
            allCounts += localCount;
        }
        if (allCounts == arr1.length) {
            System.out.println("YES");
        } else System.out.print("NO");
    }
}
