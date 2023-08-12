package dynamicsStructhure;

import java.util.Arrays;

public class RearrangeBySign {

    // https://www.youtube.com/watch?v=h4aBagy4Uok

    public static void main(String[] args) {
        int[] a = {3, -1, -2, 2, 4, -5};


        System.out.println(Arrays.toString(rearrangeBySign1(a)));
        System.out.println(Arrays.toString(rearrangeBySign2(a)));
    }

    private static int[] rearrangeBySign1(int[] a) {
        int posIdx = 0, negIdx = posIdx + 1;
        int[] res = new int[a.length];

        for (int el : a) {
            if (el > 0) {
                res[posIdx] = el;
                posIdx += 2;
            } else {
                res[negIdx] = el;
                negIdx += 2;
            }
        }
        return res;
    }

    private static int[] rearrangeBySign2(int[] a) {
        int posIdx = 0, negIdx = 0;
        int[] res = new int[a.length];
        int[] pos = new int[a.length / 2];
        int[] neg = new int[a.length / 2];

        for (int el : a) {
            if (el > 0) {
                pos[posIdx++] = el;
            } else {
                neg[negIdx++] = el;
            }
        }

        for (int i = 0; i < a.length / 2; i++) {
            res[i * 2] = pos[i];
            res[(i * 2) + 1] = neg[i];
        }
        return res;
    }
}
