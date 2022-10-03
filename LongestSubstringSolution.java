package hackerRank;

import java.util.ArrayList;
import java.util.List;

public class LongestSubstringSolution {
    public static void main(String[] args) {
        /**
         * @Problem: https://www.youtube.com/watch?v=GS9TyovoU4c
         * Търси най-дългият низ от неповтарящи се символи.
         *
         * @longestSubstringSolution: Ако буквата с индекс i не се съдържа в стринга stb то добави я към него.
         * В противен случай разпечатай stb и го изтрий.
         * Добави i-та буква към стринга stb и повтори всичко отново.
         *
         * @longestSubstringSolution1: Взима "а" сравнява я с "b". Ако "b" не се съдържа в "а" добава "b" към "а"
         * В противен случай ги трие. И това се повтаря за всяка една буква.
         */

        String str = "abcvxztabfghliopp";
        String str1 = "aabccbaabcd";

        //longestSubstringSolution(str);
        longestSubstringSolution1(str);
    }

    private static void longestSubstringSolution1(String str) {
        List<Integer> counts = new ArrayList<>();
        List<String> strings = new ArrayList<>();
        int max = Integer.MIN_VALUE;

        for (int index = 0; index < str.length(); index++) {
            int countSize = 0;
            StringBuilder stb = new StringBuilder();
            stb.append(str.charAt(index));
            countSize++;

            for (int currentIndex = index + 1; currentIndex < str.length(); currentIndex++) {
                boolean isCriteriaFalse = false;
                isCriteriaFalse = isThisContainedInSTB(str, index, stb, currentIndex);
                if (isCriteriaFalse && index < str.length() - 1) {
                    addToSTB(str, stb, currentIndex);
                    countSize++;
                    counts.add(countSize);
                    strings.add(String.valueOf(stb));

                    stb.delete(0, stb.length());
                    break;
                } else {
                    addToSTB(str, stb, currentIndex);
                    countSize++;
                }
            }
            if (stb.length() != 0) {
                //System.out.print(stb + " ");
                counts.add(countSize);
                strings.add(String.valueOf(stb));
            }
        }

        String maxString = "";
        for (int i = 0; i < strings.size(); i++) {
            if (counts.get(i) > max) {
                max = counts.get(i);
                maxString = strings.get(i);
            }
        }
        System.out.printf("%s -> %d ", maxString, max);
    }

    private static void addToSTB(String str, StringBuilder stb, int currentIndex) {
        if (stb.length() >= 1) {
            stb.append(str.charAt(currentIndex));
        }
    }


    private static boolean isThisContainedInSTB(String str, int index, StringBuilder stb, int currentIndex) {
        boolean isContainsInSB = false;
        if (str.charAt(index) != str.charAt(currentIndex)) {
            for (int k = 0; k < stb.length(); k++) {
                if (str.charAt(currentIndex) == stb.charAt(k)) {
                    isContainsInSB = true;
                    break;
                }
            }
        } else isContainsInSB = true;
        return isContainsInSB;
    }

    private static void longestSubstringSolution(String str) {
        StringBuilder stb = new StringBuilder();
        for (int i = 0; i < str.length() - 1; i++) {
            boolean isMatch = false;

            char strEl = str.charAt(i);
            for (int j = 0; j < stb.length(); j++) {
                char stbEl = stb.charAt(j);
                if (strEl == stbEl && i != 0) {
                    isMatch = true;
                    break;
                }
            }

            if (str.charAt(i) != str.charAt(i + 1) && !isMatch) {
                stb.append(str.charAt(i));
            } else {
                System.out.println(stb);
                stb.delete(0, stb.length());
                stb.append(str.charAt(i));
            }
        }
    }
}
