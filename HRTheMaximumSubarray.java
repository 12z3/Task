package hackerRank;

import java.util.ArrayList;
import java.util.List;

public class HRTheMaximumSubarray {

    /**
     * @Source: <a href="https://www.youtube.com/watch?v=5QKoQzR0iGY">...</a>
     * @Problem: We define subsequence as any subset of an array.
     * We define a subarray as a contiguous subsequence in an array.
     * Given an array, find the maximum possible sum among:
     * all nonempty subarrays.
     * all nonempty subsequences.
     * Print the two values as space-separated integers on one line.
     * Note that empty subarrays/subsequences should not be considered.
     * <p>
     * https://www.hackerrank.com/challenges/maxsubarray/
              problem?utm_campaign=challenge-recommendation&utm_medium=email&utm_source=24-hour-campaign
     *</p>
     * @Discussions: <a href="https://www.hackerrank.com/challenges/maxsubarray/forum">...</a>
     * @Issue: Time limit exceeded
     */

    public static void main(String[] args) {

        List<Integer> arr1 = new ArrayList<>(List.of(3, 4, 5, 6, 7, 1, 2, 3, 4, 5, 6, 7, 8, 3, 4, 6, 7));
        List<Integer> arr2 = new ArrayList<>(List.of(1, 2, 3, 4));
        List<Integer> arr = new ArrayList<>(List.of(-1, 2, 3, -4, 5, 10));

        consecutiveSubsequences(arr);
    }

    /**
     * Time limit exceeded
     * Your code did not execute within the time limits.
     * Please optimize your code.
     */
    private static void consecutiveSubsequences(List<Integer> arr) {
        List<Integer> ls;
        List<Integer> maxLs = new ArrayList<>();
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < arr.size() - 1; i++) {
            boolean PreviouslyAdded = false;

            ls = new ArrayList<>();
            int el1 = arr.get(i), el2 = arr.get(i + 1),
                    index = i + 1, endElIdx = arr.size() - 1;

            while (index < endElIdx) { // compare(el1, el2) &&
                if (!PreviouslyAdded) ls.add(el1);
                PreviouslyAdded = true;
                ls.add(el2);
                System.out.println(ls + " = " + sumOfListEl(ls));
                el1 = arr.get(index);
                el2 = arr.get(index + 1);
                //i = index;                     // <-
                index++;
            }
            if ((index == endElIdx)) { // && (el2 == el1 + 1)
                if (!PreviouslyAdded) ls.add(el1);
                ls.add(el2);
            }

            System.out.println(ls + " -> sum = " + sumOfListEl(ls));
            System.out.println("------------------------------- ");

            if (sumOfListEl(ls) > max) {
                max = sumOfListEl(ls);
                maxLs = ls;
            }
        }
        System.out.println(maxLs + " -> sum = " + max);
    }

    private static boolean compare(int el1, int el2) {
        if ((Math.abs(el1) + 1) == Math.abs(el2)) return true;
        return false;
    }

    private static int sumOfListEl(List<Integer> list) {
        int sum = 0;
        for (Integer el : list) sum += el;
        return sum;
    }
}
