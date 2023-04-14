package task;

import org.apache.commons.math4.legacy.util.ComplexFormat;

import java.util.*;
import java.util.stream.Collectors;

class Some implements Comparator {
    public int compare(Object o1, Object o2) {
        if ((int) o1 > (int) o2) return -1;
        return 1;
    }
}

public class one {
    public static void main(String[] args) {
        int[] a = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int[] b = new int[a.length];
        Random rmd = new Random();
        int j = 5;

        while (j-- > 0) {
            for (int i = 0; i < a.length; i++) {
                b[i] = a[rmd.nextInt(9)];
            }
            System.out.println(Arrays.toString(b));
        }

        String s = "alskjdclakd";
        int i = 0;
        String[] split = s.split("");

        for (String el : split) {
            if (++i < s.length()) {
                System.out.print(el + ", ");
            } else System.out.print(el);
        }

        int[] c = {4, 3, 9, 2, 1};
        int p = 0;

        while (p++ < c.length) {                                // Не използва p в цикъла -> Само тогава е валидно
            for (int k = 0; k < c.length - 1; k++) {
                if (c[k] > c[k + 1]) {
                    int tmp = c[k + 1];
                    c[k + 1] = c[k];
                    c[k] = tmp;
                }
            }
        }

         System.out.println("\n" + Arrays.toString(c));

        boolean isSorted = false;                            // {4, 3, 9, 2, 1}
        while (!isSorted) {
            isSorted = true;
            for (int k = 0; k < c.length - 1; k++) {
                if (c[k] > c[k + 1]) {
                    int tmp = c[k + 1];
                    c[k + 1] = c[k];
                    c[k] = tmp;
                    isSorted = false;
                }


            }
        }
        System.out.println("\n" + Arrays.toString(c));

//
        // Comparator<Integer> com = (o1, o2) ->{};
        Comparator<Integer> com = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if (o1 > o2) {
                    return 1;
                } else if (o1.equals(o2)) return 0;
                return -1;
            }
        };

        List<Integer> list = new ArrayList<>(List.of(1, 12, 6, 3, 2, 8, 1));

        List<Integer> collect = list.stream().
                 filter(el -> el > 0)
                .sorted()
                .toList();


        System.out.println(collect.toString());

        Collections.sort(list, com);
        System.out.println(list.toString());


    }
}
