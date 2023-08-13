package dynamicsStructhure;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RearrangeBySign {

    // https://www.youtube.com/watch?v=h4aBagy4Uok

    public static void main(String[] args) {
        int[] b = {3, -1, -2, 2, 4, -5};
        int[] a = {3, -1, -2, 2, 4, -5, -7, -4, -11, -12, -14, -15, 16};


        System.out.println(Arrays.toString(rearrangeBySign1(b)));
        System.out.println(Arrays.toString(rearrangeBySign2(b)));
        System.out.println(Arrays.toString(rearrangeBySign3(a)));
        System.out.println(Arrays.toString(rearrangeBySign4(a)));
    }

    private static int[] rearrangeBySign1(int[] a) {
        int posIdx = 0, negIdx = posIdx + 1;
        int[] res = new int[a.length];

        for (int i = 0; i < a.length; i++) {
            int el = a[i];
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

    private static int[] rearrangeBySign3(int[] a) {
        int posIdx = 0, negIdx = posIdx + 1, idx = 0;
        int[] res = new int[a.length];
        List<Integer> pos = new ArrayList<>();
        List<Integer> neg = new ArrayList<>();

        for (int el : a) {
            if (el > 0) {
                pos.add(el);
            } else {
                neg.add(el);
            }
        }

        boolean isPositive = pos.size() > neg.size();
        int bound = isPositive ? neg.size() : pos.size();
        int max = Math.max(pos.size(), neg.size());

        for (int i = 0; i < max; i++) {
            if (!isPositive && i >= bound) {
                res[++idx] = neg.get(i);
            } else if (isPositive && i >= bound) {
                res[++idx] = pos.get(i);
            } else {
                res[i * 2] = pos.get(i);
                res[(i * 2) + 1] = neg.get(i);
                idx = (i * 2) + 1;
            }
        }
        return res;
    }

    private static int[] rearrangeBySign4(int[] a) {
        int idx = 0;
        int[] res = new int[a.length];
        List<Integer> pos = new ArrayList<>();
        List<Integer> neg = new ArrayList<>();

        for (int el : a) {
            if (el > 0) {
                pos.add(el);
            } else {
                neg.add(el);
            }
        }

        boolean isPositive = pos.size() > neg.size();
        if (isPositive) {
            for (int i = 0; i < pos.size(); i++) {
                if (i < neg.size()) {
                    res[i * 2] = pos.get(i);
                    res[i * 2 + 1] = neg.get(i);
                    idx = i * 2 + 1;
                } else res[++idx] = pos.get(i);
            }
        } else {
            for (int i = 0; i < neg.size(); i++) {
                if (i < pos.size()) {
                    res[i * 2] = pos.get(i);
                    res[i * 2 + 1] = neg.get(i);
                    idx = i * 2 + 1;
                } else res[++idx] = neg.get(i);
            }
        }
        return res;
    }
}
