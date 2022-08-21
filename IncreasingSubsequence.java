package Task;
import training.Methods;

import java.util.Scanner;
public class IncreasingSubsequence extends Methods {

    /**
     * @Problem: Read a list of integers and find the longest increasing subsequence (LIS).
     * If several such exist, print the leftmost.
     *
     * 1	                                  -> 1
     * 7 3 5 8 -1 0 6 7	                      -> 3 5 6 7
     * 1 2 5 3 5 2 4 1	                      -> 1 2 3 5
     * 0 10 20 30 30 40 1 50 2 3 4 5 6	      -> 0 1 2 3 4 5 6
     * 11 12 13 3 14 4 15 5 6 7 8 7 16 9 8	  -> 3 4 5 6 7 8 16
     * 3 14 5 12 15 7 8 9 11 10 1	          -> 3 5 7 8 9 11
     */

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] input = scanner.nextLine().split(" ");
        int[] arrInput = new int[input.length];

        for (int i = 0; i < arrInput.length; i++) {
            arrInput[i] = Integer.parseInt(input[i]);
        }



    }
}
