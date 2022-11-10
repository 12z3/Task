package hackerRank;

import java.util.ArrayList;
import java.util.List;

public class Subsequences {

    /**
     * in: {3, 4, 5, 6, 7, 1, 2, 3, 4, 5, 6, 7, 8, 3, 4, 5, 6};              {1, 2, 1, 2, 1, 2};
     * out:
     * [3, 4, 5, 6, 7]                                                        [1, 2]
     * [1, 2, 3, 4, 5, 6, 7, 8]                                               [1, 2]
     * [3, 4, 5, 6]                                                           [1, 2]
     * 14                                                                     3
     */

    public static void main(String[] args) {
        List<Integer> ls;

        int[] a2 = {1, 2, 3, 6, 7, 8};
        int[] a1 = {3, 4, 5, 6, 7, 1, 2, 3, 4, 5, 6, 7, 8, 3, 4, 5, 6};
        int[] a = {3, 4, 5, 6, 7, 1, 2, 3, 4, 5, 6, 7, 8, 3, 4, 5, 6};
        int[] a3 = {1, 2, 1, 2, 1, 2};
        int count = 0;


        for (int i = 0; i < a.length - 1; i++) {
            boolean add = false;

            ls = new ArrayList<>();
            int el1 = a[i], el2 = a[i + 1], index = i + 1, endEl = a.length - 1;

            while (compare(el1, el2) && index < endEl) {
                if (add) {
                    ls.add(el2);
                    count++;
                } else {
                    ls.add(el1);
                    ls.add(el2);
                    count++;
                }

                add = true;
                el1 = a[index];
                el2 = a[index + 1];
                i = index;          // <-
                index++;
            }
            if ((index == endEl) && (a[endEl] == a[endEl - 1] + 1)) {
                if (!add) ls.add(a[endEl - 1]);
                ls.add(a[endEl]);
                count++;
            }
            System.out.println(ls);
        }

        System.out.println(count);
//        System.out.println(ls);                          // [1, 2, 2, 3, 3, 4, 6, 7, 2, 3, 3, 4, 6, 7, 6, 7]
    }

    private static boolean compare(int el1, int el2) {
        return el1 + 1 == el2;
    }
}
