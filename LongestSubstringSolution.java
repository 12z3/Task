package hackerRank;

public class LongestSubstringSolution {
    public static void main(String[] args) {
        /**
         * @Problem: https://www.youtube.com/watch?v=GS9TyovoU4c
         * Търси най-дългият низ от неповтарящи се символи.
         *
         * Ако буквата с индекс i не се съдържа в стринга stb то добави я към него.
         * В противен случай разпечатай stb и го изтрий.
         * Добави i-та буква към стринга stb и повтори всичко отново.
         */

        String str1 = "abcvxztabcfghliopbb";
        String str = "aabccbaabcd";

        //longestSubstringSolution(str);
        longestSubstringSolution1(str);
    }

    private static void longestSubstringSolution1(String str) {
        StringBuilder stb1 = new StringBuilder();
        int countI, maxLength = Integer.MIN_VALUE;
        boolean isBreak = false;

        for (int i = 0; i < str.length(); i++) {
            StringBuilder stb = new StringBuilder();
            stb.append(str.charAt(i));
            countI = 1;
            for (int j = i + 1; j < str.length(); j++) {
                boolean isContainsInSB = false;
                if (str.charAt(i) != str.charAt(j)) {
                    for (int k = 0; k < stb.length(); k++) {
                        if (str.charAt(j) == stb.charAt(k)) {
                            isContainsInSB = true;
                            break;
                        }
                    }
                } else isContainsInSB = true;
                if (isContainsInSB) {
                    stb.delete(0, stb.length());
                    break;
                }
                if (stb.length() >= 1) {
                    stb.append(str.charAt(j));
                    countI++;
                }
            }
            if (stb.length() != 0) System.out.print(stb + " ");
        }

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
