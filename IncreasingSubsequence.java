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
        System.out.println(increasingSubsequence(input));                     // 3, 14, 4, 15, 5, 6, 7, 8, 7, 9, 8 - My
    }

    private static List increasingSubsequence(List<Integer> input) {
        List<Integer> result;
        List<List<Integer>> res = new ArrayList<>();
        int max = Integer.MIN_VALUE;

        input.add(-100);
        for (int i = 0; i < input.size(); i++) {
            if (input.get(i) > max) max = input.get(i);
        }

        for (int i = 0; i < input.size(); i++) {
            result = new ArrayList<>();
            int a = input.get(i);
            result.add(a);
            //int last = a;                                                      // 3, 14, 15, 16
            for (int j = i + 1; j < input.size() - 1; j++) {
                int last = a;                                                   // 3, 14, 4, 15, 5, 6, 7, 8, 7, 9, 8
                int next = input.get(j + 1);
                if (last < input.get(j)) {
                    last = input.get(j);
                    result.add(last);
                }
            }
            res.add(result);
            System.out.println(result);
        }
         return  getResult(res);
    }

    private static List getResult(List<List<Integer>> res) {
        int maxSize = Integer.MIN_VALUE;
        for (List el : res) {
            if (el.size() > maxSize) maxSize = el.size();
        }
        for (List el : res) {
            if (el.size() == maxSize) {
                //System.out.println(el);
                return el;
            }
        }
        return new ArrayList(List.of(0));
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
