package hackerRank;

import java.util.ArrayList;
import java.util.List;

public class WeightedUniformStrings {
    public static void main(String[] args) {

        /**
         * a       1
         * b       2
         * bb      4
         * c       3
         * cc      6
         * ccc     9
         * d       4
         * dd      8
         * ddd     12
         * dddd    16
         */

        char[] letter =
                {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
                        'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };
        int[] weight = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26};


        String input = "abbcccdddd";
        String input1 = "abccddde";

        StringBuilder stb = new StringBuilder();
        List<Integer> weightArr = new ArrayList<>();
        List<Character> list = new ArrayList<>();
        List<Integer> counts = new ArrayList<>();

        List<List<Character>> lists = new ArrayList<>();
        List<List<Integer>> allCounts = new ArrayList<>();

        int thisWeight = 0, index = 0, thisCount = 0, sumWeight = 0, previously = Integer.MIN_VALUE;
        int max = Integer.MIN_VALUE;


        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == previously) continue;

            thisCount = 0;
            stb = new StringBuilder();
            list = new ArrayList<>();
            counts = new ArrayList<>();

//            for (int j = 0; j < letter.length; j++) {
//                if (input.charAt(i) == letter[j]) {
//                    thisWeight = weight[j];
//                    break;
//                }
//            }

            thisWeight = (int)input.charAt(i) - 96;

            for (int k = 0; k < input.length(); k++) {
                if (input.charAt(i) == input.charAt(k)) {
                    thisCount++;
                    stb.append(input.charAt(k));
                    list.add(input.charAt(k));
                    counts.add(thisCount);
                }
            }
            previously = input.charAt(i);
            System.out.println(stb);
            lists.add(list);
            weightArr.add(thisCount);
            allCounts.add(counts);
        }

        for (List<Character> el: lists) System.out.print(el + " ");
        System.out.println();
        for (List<Integer> el: allCounts) System.out.print(el + " ");
        System.out.println();
        for (int el: weightArr) System.out.print(el + " ");
        System.out.println();
        for (int el: counts) System.out.print(el + " ");
    }
}
