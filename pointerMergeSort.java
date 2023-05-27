package dynamicsStructhure;

import java.util.Arrays;

public class pointerMergeSort {
    public static void main(String[] args) {
        int[] a = {1, 5, 6, 7, 8, 17, 18};
        int[] b = {2, 3, 4, 9};
        merge1(a, b);
        merge2(a, b);
    }

    private static void merge2(int[] a, int[] b) {
        int[] c = new int[a.length + b.length];
        int i = 0, j = 0, k = 0;
        while (i < a.length && j < b.length) {
            if (a[i] < b[j]) {
                c[k] = a[i];
                i++;
            } else {
                c[k] = b[j];
                j++;
            }
            k++;
        }

        while (i < a.length) {
            c[k] = a[i];
            i++;
            k++;
        }
        while (i < b.length) {
            c[k] = b[j];
            j++;
            k++;
        }
        System.out.println(Arrays.toString(c));

    }

    private static void merge1(int[] a, int[] b) {
        int[] c = new int[a.length + b.length];
        int pointerA = 0, pointerB = 0, index = 0;

        for (int i = 0; i < c.length; i++) {
            c[i] = Math.min(a[pointerA], b[pointerB]);

            if (a[pointerA] > b[pointerB]) {
                pointerB++;
            } else {
                pointerA++;

            }
            if ((pointerA > a.length - 1) || (pointerB > b.length - 1)) {
                index = i + 1;
                break;
            }
        }

        if (pointerB < b.length) {
            for (int i = index; i < c.length; i++) {
                c[i] = b[pointerB++];
            }
        } else {
            for (int i = index; i < c.length; i++) {
                c[i] = a[pointerA++];
            }
        }


        System.out.println(Arrays.toString(c));
    }

    //        for (int i = 0; i < c.length; i++) {
//            if (a[pointerA] > b[pointerB]) {
//                c[i] = b[pointerB];
//                b[pointerB] = 0;
//                pointerB++;
//
//            } else {
//                c[i] = a[pointerA];
//                a[pointerA] = 0;
//                pointerA++;
//
//            }
//            if (pointerA > a.length - 1) {
//                index = i + 1;
//                break;
//            }
//            if (pointerB > b.length - 1) {
//                index = i + 1;
//                break;
//            }
//        }
//
//        if (pointerB < b.length) {
//            for (int i = index; i < c.length; i++) {
//                c[i] = b[pointerB++];
//            }
//        } else {
//            for (int i = index; i < c.length; i++) {
//                c[i] = a[pointerA++];
//            }
//        }
//
//
//        System.out.println(Arrays.toString(c));

//        for (int i = 0; i < c.length; i++) {
//            if (pointerA >= 0 && pointerB >= 0) {
//                if (a[pointer1] > b[pointer2]) {
//                    c[i] = b[pointer2];
//                    pointerB--;
//                    b[pointer2] = 0;
//                    pointer2++;
//                } else {
//                    c[i] = a[pointer1];
//                    pointerA--;
//                    a[pointer1] = 0;
//                    pointer1++;
//                }
//            }
//            if (pointerA == 0) {
//                index = i + 1;
//                break;
//            }
//            if (pointerB == 0) {
//                index = i + 1;
//                break;
//            }
//        }
//
//        if (pointerB > 0) {
//            for (int i = index; i < c.length; i++) {
//                c[i] = b[pointer2++];
//            }
//        } else {
//            for (int i = index; i < c.length; i++) {
//                c[i] = a[pointer1++];
//            }
//        }
//
//
//        System.out.println(Arrays.toString(c));
}
