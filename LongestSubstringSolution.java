package hackerRank;

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
         * @longestSubstringSolution1:
         */

        String str1 = "abcvxztabcfghliopbb";
        String str = "aabccbaabcd";

        //longestSubstringSolution(str);
        longestSubstringSolution1(str);
    }

    private static void longestSubstringSolution1(String str) {

        for (int i = 0; i < str.length(); i++) {
            StringBuilder stb = new StringBuilder();
            stb.append(str.charAt(i));

            for (int j = i + 1; j < str.length(); j++) {
                boolean isContained = false;
                isContained = isContainedInSTB(str, i, stb, j);
                if (isContained) {
                    stb.delete(0, stb.length());
                    break;
                }
                addToSTB(str, stb, j);
            }
            if (stb.length() != 0) System.out.print(stb + " ");
        }

    }

    private static void addToSTB(String str, StringBuilder stb, int currentIndex) {
        int countI = 1;
        if (stb.length() >= 1) {
            stb.append(str.charAt(currentIndex));
            countI++;
        }
    }


    private static boolean isContainedInSTB(String str, int index, StringBuilder stb, int currentIndex) {
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
