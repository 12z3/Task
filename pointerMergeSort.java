package dynamicsStructhure;

import java.util.Arrays;

public class pointerMergeSort {
    public static void main(String[] args) {
        int[] a = {1, 5, 6, 7, 8, 17, 18};
        int[] b = {2, 3, 4, 9};
        System.out.println( Arrays.toString(merge1(a, b)));;
        System.out.println(Arrays.toString(merge2(a, b)));;

        int[] c = {1, 33, 2, 5, 12, 13, 7, 8, 0, 122};
        System.out.println(Arrays.toString(c));
        System.out.println(Arrays.toString(mergeSort(c)));
    }

    // "int[] left = mergeSort(Arrays.copyOfRange(a, 0, mid));" разделя "int[] a" докато не се изпълни "(a.length == 1)"
    // след което се повтаря операцията за втората половина на масива "int[] a" ->
    //  int[] right = mergeSort(Arrays.copyOfRange(a, mid, a.length));
    // След което имаш на разположение две числа 1 и 33. Изпълнява се "merge2" като резултата е [1,33].

    private static int[] mergeSort(int[] a) {
        if (a.length == 1) return a;

        int mid = a.length / 2;                                                  // по Индекси:
        int[] left = mergeSort(Arrays.copyOfRange(a, 0, mid));              // от 0 до mid - 1;
        int[] right = mergeSort(Arrays.copyOfRange(a, mid, a.length));           // от mid до а.length - 1

        System.out.println();
        System.out.println("Left: " + Arrays.toString(left));
        System.out.println("Right: " + Arrays.toString(right));

        return merge1(left, right);
    }

    private static int[] merge2(int[] a, int[] b) {
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
        while (j < b.length) {
            c[k] = b[j];
            j++;
            k++;
        }
        System.out.println("Merged: " + Arrays.toString(c));
        System.out.println();
        return c;
    }

    private static int[] merge1(int[] a, int[] b) {
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
        return c;
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
