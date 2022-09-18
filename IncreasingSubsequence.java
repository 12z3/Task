package Task;

import training.Methods;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class IncreasingSubsequence extends Methods {

    /**
     * @Problem: Read a list of integers and find the longest increasing subsequence (LIS).
     * If several such exist, print the leftmost.
     * <p>
     * 1	                                  -> 1
     * 7 3 5 8 -1 0 6 7	                      -> 3 5 6 7
     * 1 2 5 3 5 2 4 1	                      -> 1 2 3 5
     * 0 10 20 30 30 40 1 50 2 3 4 5 6	      -> 0 1 2 3 4 5 6
     * 11 12 13 3 14 4 15 5 6 7 8 7 16 9 8	  -> 3 4 5 6 7 8 16
     * 3 14 5 12 15 7 8 9 11 10 1	          -> 3 5 7 8 9 11
     */

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //List<Integer> input = new ArrayList<>(List.of(7, 3, 5, 8, -1, 0, 6, 7));
        List<Integer> input = new ArrayList<>(List.of
                (11, 12, 13, 3, 14, 4, 15, 5, 6, 7, 8, 7, 16, 9, 8));            // 3 4 5 6 7 8 16
        increasingSubsequence(input);
    }

    private static void increasingSubsequence(List<Integer> input) {
        List<Integer> result = new ArrayList<>();
        List<List<Integer>> res = new ArrayList<>();
        StringBuilder stb = new StringBuilder();
        int max = Integer.MIN_VALUE, maxSize = Integer.MIN_VALUE;

        for (int i = 0; i < input.size(); i++) {
            if (input.get(i) > max) max = input.get(i);
        }

        for (int i = 0; i < input.size(); i++) {
            result = new ArrayList<>();
            int a = input.get(i);
            result.add(a);
            //int last = a;
            for (int j = i + 1; j < input.size(); j++) {
                int last = a;
                if (last < input.get(j) && input.get(j) != max) {
                    last = input.get(j);
                    result.add(last);
                }
            }
            res.add(result);
        }

        for (List el: res){
            System.out.println(el);
        }
    }
}

//        for (int i = 0; i < inputL.size(); i++) {
//            stb.delete(0, stb.length());
//            stb.append(inputL.get(i));
//            for (int j = i + 1; j < inputL.size(); j++) {
//                result.remove(inputL.get(i));
//                result.remove(inputL.get(j));
//                if (inputL.get(i) < inputL.get(j)) {
//                    stb.append(inputL.get(j));
//                    result.add(inputL.get(i));
//                    result.add(inputL.get(j));
//                }
//            }
//            // System.out.println(stb);
//            System.out.println(result);
//        }
