package task;

import java.util.ArrayList;
import java.util.List;

public class FirstDuplicated {
    public static void main(String[] args) {

        //TODO: Find firstDuplicate:
        int[] a = {2, 1, 3, 1, 3, 2};    // 2 ?= 1 No -> 21 - 1 ?= 3 No -> 213
        int result = a[0], count = 0;

        duplicate1(a, count);
    }
    private static void duplicate2(int[] a, int count) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < a.length - 1; i++) {


        }
        System.out.println(count);
    }
    private static void duplicate1(int[] a, int count) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < a.length - 1; i++) {
            list.add(a[i]);
            if (list.contains(a[i+1])) count++;
        }
        System.out.println(count);
    }
}
