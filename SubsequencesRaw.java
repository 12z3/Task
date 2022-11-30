package task;

import java.util.ArrayList;
import java.util.List;

public class SubsequencesRaw {
    /**
     * in: {3, 4, 5, 6, 7, 1, 2, 3, 4, 5, 6, 7, 8, 3, 4, 5, 6};              {1, 2, 1, 2, 1, 2};
     * out:
     * [3, 4, 5, 6, 7]                                                        [1, 2]
     * [1, 2, 3, 4, 5, 6, 7, 8]                                               [1, 2]
     * [3, 4, 5, 6]                                                           [1, 2]
     * 14                                                                     3
     */

    public static void main(String[] args) {

        int[] a2 = {1, 2, 3, 6, 7, 8};
        int[] a4 = {3, 4, 5, 6, 7, 1, 2, 3, 4, 5, 6, 7, 8, 3, 4, 6, 7};
        int[] a = {3, 4, 5, 6, 7, 1, 2, 3, 4, 5, 6, 7, 8, 3, 4, 5, 6};

        int[] a3 = {1, 2, 1, 2, 1, 2};
        int[] a1 = {1, 2, 3, 4, 1};

        consecutiveSubsequences(a);
    }

    private static void consecutiveSubsequences(int[] a) {
        List<Integer> ls;
        int count = 0;

        for (int i = 0; i < a.length - 1; i++) {
            boolean PreviouslyAdded = false;
            int sum = 0;

            ls = new ArrayList<>();
            int el1 = a[i], el2 = a[i + 1],
                    index = i + 1, endElIdx = a.length - 1;
            count++;
            while (compare(el1, el2) && index < endElIdx) {
                if (!PreviouslyAdded) ls.add(el1);
                ls.add(el2);
                PreviouslyAdded = true;
                el1 = a[index];
                el2 = a[index + 1];
                i = index;                     // <-
                index++;
            }
            if ((index == endElIdx) && (el2 == el1 + 1)) {
                if (!PreviouslyAdded) ls.add(el1);
                ls.add(el2);
            }
            for (int el : ls) sum += el;
            System.out.println(ls + " sum = " + sum);
        }
        System.out.println(count);
    }

    private static boolean compare(int el1, int el2) {
        return el1 + 1 == el2;
    }
}
