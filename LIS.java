package task;

import training.Methods;

import java.util.*;

public class LIS extends Methods {
    public static void main(String[] args) {
        long start = getStartTime();

        //ArrayList<Integer> input = new ArrayList<>(List.of(10, 22, 9, 33, 21, 50, 41, 60, 80));
        //                                                   10, 22,    33,     50,     60, 80
        //Collections.sort(input);
        //ArrayList<Integer> input = new ArrayList<>(List.of(3, 10, 2, 1, 20));
        ArrayList<Integer> input = new ArrayList<>(List.of(7, 3, 5, 8, -1, 0, 6, 7));
        //                                                    3, 5,           6, 7

        int[] nums = {7, 3, 5, 8, -1, 0, 6, 7};

        System.out.println(lisA(input));
        System.out.println(longestIncreasingSubsequence(nums));
        System.out.println(longestIncreasingSubsequenceA(nums));
        //System.out.println(lis(Arrays.toString(nums)));
        System.out.println(lengthOfLIS(nums));


        //System.out.println(lisA(input));

        int max = 0;
        for (Map.Entry<StringBuilder, Integer> el : lisM(input).entrySet()) {
            if (el.getValue() > max) {
                max = el.getValue();
                System.out.printf("%s-> digits = %d%n", el.getKey(), max);
            }
        }

        long time = (getEndTime() - start) / 6000;
        if (time > 0) {
            System.out.printf("%s: %d ms", "time", time);
        } else {
            System.out.printf("%s: %d ms", "time", (getEndTime() - start));
        }
    }


    private static Map<StringBuilder, Integer> lisM(ArrayList<Integer> input) {
        StringBuilder result = null;                                         //   10, 22, 9, 33, 21, 50, 41, 60, 80
        Map<StringBuilder, Integer> numberIndex = new LinkedHashMap<>();     //   7, 3, 5, 8, -1, 0, 6, 7 -> ????
        int maxLength = 0;

        for (int i = 0; i < input.size(); i++) {
            int digitsCount = 0;
            result = new StringBuilder();
            int elA = input.get(i);
            result.append(elA).append(" ");
            digitsCount++;
            for (int j = i + 1; j < input.size() - 1; j++) {
                int elB = input.get(j);
                if (elA < elB) {
                    result.append(elB).append(" ");
                    digitsCount++;
                    // elA = elB;
                    if ((elB + 1) > elB) elA = elB;
                }
            }
            if (digitsCount > maxLength) {
                maxLength = digitsCount;
                numberIndex.put(result, maxLength);
            }
        }
        return numberIndex;
    }

    private static ArrayList<StringBuilder> lisA(ArrayList<Integer> input) {
        StringBuilder result = null;                                           // 10, 22, 9, 33, 21, 50, 41, 60, 80
        ArrayList<StringBuilder> resultA = new ArrayList<>();                  //   7, 3, 5, 8, -1, 0, 6, 7 -> ????

        for (int i = 0; i < input.size(); i++) {
            result = new StringBuilder();
            int elA = input.get(i);
            result.append(elA).append(" ");
            for (int j = i + 1; j < input.size() - 1; j++) {
                int elB = input.get(j);
                if (elA < elB) {
                    result.append(elB).append(" ");
                    elA = elB;
                    //if ((input.get(j + 1)) > elB) elA = elB;
                }
            }
            resultA.add(result);
        }
        return resultA;
    }                      // 1,1,2,3,1,2,3,4

    public static int longestIncreasingSubsequence(int[] nums) {
        ArrayList<StringBuilder> x = new ArrayList<>();
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);

        for (int i = 1; i < n; i++) {                           // Трябва да мине последователно през всички елементи
            for (int j = 0; j < i; j++) {                       // Сравнява Всяко със Всички предходни.
                if (nums[i] > nums[j]) {                                        // nums[i] = 7, 3, 5, 8, -1, 0, 6, 7
                    dp[i] = Math.max(dp[i], dp[j] + 1);                         // dp[i] =   1, 1, 2, 3,  1, 2, 3, 4
                }
            }
        }

        int max = 1;
        for (int i = 0; i < n; i++) {
            max = Math.max(max, dp[i]);
        }
        return max;
    }

    public static int longestIncreasingSubsequenceA(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        int len = 0;

        for (int num : nums) {
            int i = Arrays.binarySearch(dp, 0, len, num);
            if (i < 0) {
                i = -(i + 1);
            }
            dp[i] = num;
            if (i == len) {
                len++;
            }
        }

        return len;
    }

    public static String lis(String str) {
        int n = str.length();
        int[] dp = new int[n];
        int[] prev = new int[n];
        int maxLen = 1;
        int endIndex = 0;

        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            prev[i] = -1;
            for (int j = 0; j < i; j++) {
                if (str.charAt(j) < str.charAt(i) && dp[j] + 1 > dp[i]) {
                    dp[i] = dp[j] + 1;
                    prev[i] = j;
                }
            }
            if (dp[i] > maxLen) {
                maxLen = dp[i];
                endIndex = i;
            }
        }

        StringBuilder sb = new StringBuilder();
        while (endIndex != -1) {
            sb.insert(0, str.charAt(endIndex));
            endIndex = prev[endIndex];
        }
        return sb.toString();
    }


    public static int lengthOfLIS(int[] nums) {
        List<Integer> dp = new ArrayList<>();
        for (int num : nums) {
            int i = Collections.binarySearch(dp, num);
            if (i < 0) {
                i = -(i + 1);
            }
            if (i == dp.size()) {
                dp.add(num);
            } else {
                dp.set(i, num);
            }
        }
        return dp.size();
    }
}
