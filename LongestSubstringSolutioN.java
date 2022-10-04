package hackerRank;

import java.util.ArrayList;
import java.util.List;

public class LongestSubstringSolutioN {
    public static void main(String[] args) {
        /**
         * @Problem: https://www.youtube.com/watch?v=GS9TyovoU4c
         * Търси най-дългият низ от неповтарящи се символи.
         *
         * @longestSubstringSolution: Взима "а" сравнява я с всяка една от следващите "x".
         * Ако "x" не се съдържа в "а" добава "x" към "а"
         * В противен случай ги трие. И това се повтаря за всяка една буква.
         * Не отчита приноса на последната буква ако съвпада с предпоследната.
         */

        String str = "aabcvxztabfghlioop";
        String str1 = "aabcvxztabfghliopp";
        String str2 = "aabccbaabcd";

        //longestSubstringSolution(str);
        removeRepetitiveElements(str);
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

    private static void removeRepetitiveElements(String str) {            // [a, b, c, d, a], f, g, k]
        System.out.println(str);
        StringBuilder stb = new StringBuilder();
        List<Integer> list = new ArrayList<>();
        List<String> delList = new ArrayList<>();

        for (int i = 0; i < str.length(); i++) {
            String element = String.valueOf(str.charAt(i));

            if (i == 0) stb.append(element);
            if (stb.indexOf(element) == -1 && i != 0) {
                stb.append(element);
            } else {
                list.add(stb.indexOf(element));
                delList.add(element);
                stb.delete(stb.length(), stb.length());
            }
        }
        System.out.println(stb);
        System.out.println(delList);
        System.out.println(list);
    }
}
