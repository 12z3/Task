package hackerRank;

import java.util.ArrayList;
import java.util.List;

public class LongestSubstringSolution {
    public static void main(String[] args) {
        /**
         * @Problem: https://www.youtube.com/watch?v=GS9TyovoU4c
         * Търси най-дългият низ от неповтарящи се символи.
         *
         * @longestSubstringSolution: Взима "а" сравнява я с всяка една от следващите "x".
         * Ако "x" не се съдържа в "а" добава "x" към "а"
         * В противен случай ги трие. И това се повтаря за всяка една буква.
         * Не отчита приноса на последната буква ако съвпада с предпоследната.
         *
         * @longestSubstringSolution1: Сравнява ги Последователно.
         * Ако буквата с индекс i не се съдържа в стринга stb то добави я към него.
         * В противен случай разпечатай stb и го изтрий.
         * Добави i-та буква към стринга stb и повтори всичко отново.
         */

        String str = "aabcvxztabfghlioop";
        String str1 = "aabcvxztabfghliopp";
        String str2 = "aabccbaabcd";


        longestSubstringSolution(str);
        longestSubstringSolution1(str2);
    }

    private static void longestSubstringSolution(String str) {
        System.out.printf("In: %s -> %d%n", str, str.length());

        List<Integer> counts = new ArrayList<>();
        List<String> strings = new ArrayList<>();
        int max = Integer.MIN_VALUE;

        for (int index = 0; index < str.length(); index++) {
            int countAddedElements = 0;
            StringBuilder stb = new StringBuilder();
            stb.append(str.charAt(index));
            countAddedElements++;

            for (int nextIndex = index + 1; nextIndex < str.length(); nextIndex++) {
                boolean isCriteria = false;
                isCriteria = isThisContainedInSTB(str, index, stb, nextIndex);
                if (isCriteria) {
                    int lastIndex = str.length() - 1;
                    if (nextIndex < lastIndex) {
                        counts.add(countAddedElements);
                        strings.add(String.valueOf(stb));
                        stb.delete(0, stb.length());
                    } else break;
                } else {
                    addToSTB(str, stb, nextIndex);
                    countAddedElements++;
                }
            }
            if (stb.length() != 0) {
                counts.add(countAddedElements);
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
        System.out.printf("Out: %s -> %d ", maxString, max);
    }

    private static void addToSTB(String str, StringBuilder stb, int nextIndex) {
        if (stb.length() >= 1) {
            stb.append(str.charAt(nextIndex));
        }
    }

    private static boolean isThisContainedInSTB(String str, int index, StringBuilder stb, int nextIndex) {
        boolean isContainedInSB = false;
        if (str.charAt(index) != str.charAt(nextIndex)) {
            for (int k = 0; k < stb.length(); k++) {
                if (str.charAt(nextIndex) == stb.charAt(k)) {
                    isContainedInSB = true;
                    break;
                }
            }
        } else isContainedInSB = true;
        return isContainedInSB;
    }


    private static void longestSubstringSolution1(String str) {
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
