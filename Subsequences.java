package task;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Subsequences {

    // TODO: Time limit exceeded <-

    /**
     *Jigar got a sequence of n positive integers as his birthday present!
     * He likes consecutive subsequences whose sum is divisible by k.
     * He asks you to write a program to count them for him.
     * <p>
     * Input Format
     * The first line contains T, the number of testcases.
     * T testcases follow. Each testcase consists of 2 lines.
     * The first line contains n and k separated by a single space.
     * And the second line contains n space separated integers.
     *<p>
     * Output Format
     * For each test case, output the number of consecutive -
     * - subsequenences whose sum is divisible by k in a newline.
     *<p>
     * Constraints
     * 1 ≤ T ≤ 20
     * 1 ≤ n ≤ 106
     * 1 ≤ k ≤ 100
     * 1 ≤ a[i] ≤ 104
     *<p>
     * Sample Input
     *<p>
     * 2
     * 5 3
     * 1 2 3 4 1
     * 6 2
     * 1 2 1 2 1 2
     * Sample Output
     *<p>
     * 4
     * 9
     */

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int testCases = Integer.parseInt(scanner.nextLine());
        List<Integer> allCount  = new ArrayList<>();

        while (testCases > 0) {
            int cases = testCases;
            String[] line1 = scanner.nextLine().split(" ");
            int n = Integer.parseInt(line1[0]);
            int k = Integer.parseInt(line1[1]);

            String[] line2 = scanner.nextLine().split(" ");
            List<Integer> input = new ArrayList<>();
            List<Integer> result = null;

            for (int i = 0; i < line2.length; i++) {
                input.add(Integer.parseInt(line2[i]));
            }

            int count = 0, sumA = 0;
            for (int i = 0; i < n; i++) {
                result = new ArrayList<>();

                result.add(input.get(i));
                sumA = input.get(i);
                if (input.get(i) % k == 0) {
                    count++;
                    //System.out.println(result);
                }
                for (int j = i + 1; j < n; j++) {
                    sumA += input.get(j);
                    result.add(input.get(j));

                    if (sumA % k == 0) {
                        count++;
                        //System.out.println(result);
                    }
                }
            }
            System.out.println(count);
            allCount.add(count);
            testCases--;
        }
        //for (int el: allCount) System.out.println(el);
    }
}
