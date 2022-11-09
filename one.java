package hackerRank;

import java.util.ArrayList;
import java.util.List;

public class one {
    public static void main(String[] args) {
        List<Integer> ls;

        int[] a1 = {1, 2, 3, 6, 7, 8};
        int[] a = {3, 4, 5, 6, 7, 1, 2, 3, 4, 5, 6, 7, 8};
        int[] a2 = {1, 2, 1, 2, 1, 2};
        int count = 0;


        for (int i = 0; i < a.length - 1; i++) {
            ls = new ArrayList<>();
            int el1 = a[i], el2 = a[i + 1], index = i + 1;
            boolean add = false;

            while (compare(el1, el2) && index < a.length - 1) {
                if (add) {
                    ls.add(el2);
                    count++;
                } else {
                    ls.add(el1);
                    ls.add(el2);
                    count++;
                }

                if ((index == a.length - 2) && (a[a.length - 1] == a[a.length - 2] + 1)) {
                    ls.add(a[a.length - 1]);
                }

                add = true;
                el1 = a[index];
                el2 = a[index + 1];
                i = index;          // <-
                index++;

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
