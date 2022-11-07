package task;

import java.io.IOException;
import java.util.Arrays;

public class MergeArray {
    public static void main(String[] args) throws IOException {

        int[] a = {6, 7, 8, 1, 2, 3, 4};
        int[] b = {1, 2, 5, 7, 8};

        mergeArray(a, b);
        mergeArray1(a, b);
    }

    private static void mergeArray(int[] a, int[] b) {
        int[] newArr = new int[a.length + b.length];
        int sizeA = a.length;

        for (int idxA = 0, idxB = (-sizeA); idxA < newArr.length; idxA++, idxB++) {
            if (idxA < sizeA) {
                newArr[idxA] = a[idxA];
            } else {
                newArr[sizeA++] = b[idxB];
            }
        }

        System.out.println(Arrays.toString(newArr));
        System.out.printf("Length: a-%d; b-%d;%n", a.length, b.length);
    }

    private static void mergeArray1(int[] a, int[] b) {
        int[] newArr = new int[a.length + b.length];
        int sizeA = a.length;

        for (int idx = 0; idx < a.length; idx++) {
            newArr[idx] = a[idx];
        }
        for (int idx = sizeA, idxB = 0; idx < newArr.length; idx++, idxB++) {
            newArr[idx] = b[idxB];
        }


        System.out.println(Arrays.toString(newArr));
        System.out.printf("Length: a-%d; b-%d;%n", a.length, b.length);
    }

    private static <T> void mergeGenericArray(T[] a, T[] b) {
        int[] newArr = new int[a.length + b.length];
        int sizeA = a.length;

        for (int indexA = 0, indexB = (-a.length), j = sizeA; indexA < newArr.length; indexA++, indexB++) {
            if (indexA < a.length) {
                T tmp = null;
             //   newArr[indexA] = a[indexA];                     // Required type: "int" -> Provided: "T" ???
            } else {
             //   newArr[sizeA++] = b[indexB];                    // Required type: "int" -> Provided: "T" ???
            }
        }

        System.out.println(Arrays.toString(newArr));
        System.out.printf("Length: a-%d; b-%d;%n", a.length, b.length);
    }
}
