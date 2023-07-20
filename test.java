package task;

import java.util.Arrays;

public class test {
    public static void main(String[] args) {
        int pos = 0, neg = 1, tmpPosIdx = -1, tmpNegIdx = -1, tmpPos = -111, tmpNeg = -111, tmp = -333;
        int[] a = {-1, 2, -3, 4, -5, 6, -7, 8};



        System.out.println(Arrays.toString(a));

        for (int i = 0; i < (a.length - 1); i++) {


        }
        System.out.println(Arrays.toString(a));


//        -1, 2, -3, 4, -5, 6, -7, 8
//        for (int i = 0; i < a.length; i++) {
//            if (a[i] < 0 && (i % 2) == 0) {
//                tmpPosIdx = i;
//            } else if (a[i] > 0 && (i % 2) != 0) {
//                tmpNegIdx = i + 1;
//            } else {
//                if (a[i] > 0) {
//                    a[tmpPosIdx] = a[i];
//                } else a[tmpNegIdx] = a[i];
//
//            }
//        }
//        System.out.println(Arrays.toString(a));
    }
}
