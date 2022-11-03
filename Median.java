package task;

import training.Methods;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Median extends Sorting {
    public static void main(String[] args) {

        int[][] input1 = {{1, 3, 5}, {2, 6, 9}, {3, 6, 9}};
        int[][] input = {{1}, {2}, {3}};

        int index = -1, elements = 0;
        int row = 3, col = 1, medium = 0;

        // List<Integer> ls = new ArrayList<>();

        for (row = 0; row < input.length; row++) {
            for (col = 0; col < input[row].length; col++) {
                elements++;
            }
        }

        int[] lsArr = new int[elements];
        for (col = 0; col < input.length; col++) {
            for (row = 0; row < input[col].length; row++) {
                index++;
                lsArr[index] = input[row][col];
            }
        }
        System.out.println(Arrays.toString(lsArr));
        //ls = new ArrayList<>(Arrays.asList(input));
        // 1, 2, 3, 3,  5,  6, 6, 9, 9 - 9
        // Collections.sort(ls);                               // 1, 2, 3,  3, 5,  6, 6, 9 - 8
        //  ls.remove(ls.size() - 1);
//        if (ls.size() % 2 != 0) {
//            int index = (ls.size() / 2);
//            medium = ls.get(index);
//        } else {
//            int index = (ls.size() / 2) - 1 ;
//            medium = (ls.get(index) + ls.get(index + 1)) / 2;
//        }
//
//        System.out.println(ls);
//        System.out.println(ls.size());
//        System.out.println(medium);

        directSelectionSorting(lsArr);

        if (lsArr.length % 2 != 0) {
            index = (lsArr.length / 2);
            medium = lsArr[index];
        } else {
            index = (lsArr.length / 2) - 1;
            medium = (lsArr[index] + lsArr[index + 1]) / 2;
        }

        System.out.println(Arrays.toString(lsArr));
        System.out.println(lsArr.length);
        System.out.println(medium);


    }
}
