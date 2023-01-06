package task;

import java.util.ArrayList;
import java.util.List;

public class leetCodeSubStrings {

    //TODO: Time Limit Exceeded - Мина на косъм                                .
    // https://leetcode.com/problems/longest-palindromic-substring/description/

    // TODO: Manacher’s Algorithm – Linear Time Longest Palindromic Substring
    // https://www.geeksforgeeks.org/manachers-algorithm-linear-time-longest-palindromic-substring-part-1/

    public static void main(String[] args) {
        String x4 = "bappabad";
        String x1 = "cbbd";
        String x8 = "aacabdkacaa";
        String x5 = "ac";
        String x6 = "babad";
        String x7 = "ccc";
        String x = "xaabacxcabaaxcabaax";
        String x9 = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaabcaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaa";

        System.out.println(palindromsLeetCode(x));


        /*
         *    xaabacxcabaaxcabaax
         * 1. xaabacx -> взима обърнатия на него стринг и проверява дали е палиндромен.
         * 2. xaabacxcabaax -> същото.
         * 2. xaabacxcabaaxcabaax -> същото

         * 1. aa -> същото
         * 2. aaba -> същото.
         * 3. aabacxca -> същото
         * .
         * .
         * .
         */
    }

    private static String palindromsLeetCode(String s) {
        StringBuilder stb = new StringBuilder();
        StringBuilder stbR;
        StringBuilder stbL;
        List<StringBuilder> stbAll = new ArrayList<>();
        int idxFrom = 0, index = 0, max = 0;
        boolean isMatch = false;


        if (s.length() == 1) return s;

        StringBuilder tmpA = new StringBuilder(s);
        StringBuilder tmpB = new StringBuilder(tmpA).reverse();

        if (tmpB.toString().equalsIgnoreCase(tmpA.toString())) return tmpA.toString();
                                                          // 0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21
        int indexX = 0, p = 0;                            // x  a  a  b  a  c  x  c  a  b  a  a  x  c  a  b  a  a  x
        while (indexX < s.length()) {                     // Обхожда по всяка една буква на стринга.
            idxFrom = indexX;
            for (int i = 0; i < s.length(); i++) {
                char y = s.charAt(p);

                if (s.indexOf(y, idxFrom) != -1) {         // За всяка една една буква обхожда стринга за съвпадение.
                    int idx = s.indexOf(y, idxFrom);
                    stbR = new StringBuilder(s.substring(p, idx + 1));
                    stbL = new StringBuilder(stbR).reverse();
                    idxFrom = idx + 1;

                    if (stbR.toString().equalsIgnoreCase(stbL.toString())) {
                        stbAll.add(stbR);
                        isMatch = true;
                    }
                }
            }
            p++;
            indexX++;
        }

        if (isMatch) {
            for (int i = 0; i < stbAll.size(); i++) {
                if (stbAll.get(i).length() > max) {
                    max = stbAll.get(i).length();
                    index = i;
                }
            }
            return stbAll.get(index).toString();
        }
        return String.valueOf(s.charAt(0));
    }

//    private static String palindromsLeetCode3(String s) {
//        StringBuilder stb = new StringBuilder();
//        StringBuilder stbR;
//        StringBuilder stbL;
//        List<StringBuilder> stbAll = new ArrayList<>();
//        int idxFrom = 0, index = 0, max = 0;
//        boolean isMatch = false;
//
//
//        if (s.length() == 1) return s;
//
//        StringBuilder tmp = new StringBuilder();
//        StringBuilder tmp1 = new StringBuilder(s);
//        for (int i = s.length() - 1; i >= 0; i--) {
//            tmp.append(tmp1.charAt(i));
//        }
//        if (tmp1.toString().equalsIgnoreCase(tmp.toString())) return tmp.toString();
//
//        int indexX = 0, p = 0;
//        while (indexX < s.length()) {                               // xaabacxcabaaxcabaax
//            idxFrom = indexX;
//            for (int i = 0; i < s.length(); i++) {
//                char y = s.charAt(p);
//
//                if (s.indexOf(y, idxFrom) != -1) {
//                    int idx = s.indexOf(y, idxFrom);
//                    stbR = new StringBuilder(s.substring(p, idx + 1));
//                    stbL = new StringBuilder();
//                    idxFrom = idx + 1;
//
//                    for (int j = stbR.length() - 1; j >= 0; j--) {
//                        stbL.append(stbR.charAt(j));
//                    }
//                    if (stbR.toString().equalsIgnoreCase(stbL.toString())) {
//                        stbAll.add(stbR);
//                        isMatch = true;
//                    }
//                }
//            }
//            p++;
//            indexX++;
//        }
//
//        if (isMatch) {
//            for (int i = 0; i < stbAll.size(); i++) {
//                if (stbAll.get(i).length() > max) {
//                    max = stbAll.get(i).length();
//                    index = i;
//                }
//            }
//            return stbAll.get(index).toString();
//        }
//        return String.valueOf(s.charAt(0));
//    }

//    private static String palindromsLeetCode2(String s) {
//        StringBuilder stb = new StringBuilder();
//        StringBuilder stbR;
//        StringBuilder stbL;
//        List<StringBuilder> stbAll = new ArrayList<>();
//        int idxFrom = 0, index = 0, max = 0;
//        boolean isMatch = false;
//
//
//        if (s.length() == 1) return s;
//
//        StringBuilder tmp = new StringBuilder();
//        StringBuilder tmp1 = new StringBuilder(s);
//        for (int i = s.length() - 1; i >= 0; i--) {
//            tmp.append(tmp1.charAt(i));
//        }
//        if (tmp1.toString().equalsIgnoreCase(tmp.toString())) return tmp.toString();
//
//
//        int indexX = 0;
//        while (indexX < s.length()){
//
//
//
//            indexX++;
//        }
//
//        for (int i = 0; i < s.length(); i++) {
//            char y = s.charAt(i);
//
//            idxFrom = i + 1;
//            if (s.indexOf(y, idxFrom) != -1) {
//                int idx = s.indexOf(y, idxFrom);
//                stbR = new StringBuilder(s.substring(i, idx + 1));
//                stbL = new StringBuilder();
//
//                for (int j = stbR.length() - 1; j >= 0; j--) {
//                    stbL.append(stbR.charAt(j));
//                }
//                if (stbR.toString().equalsIgnoreCase(stbL.toString())) {
//                    stbAll.add(stbR);
//                    isMatch = true;
//                }
//            }
//        }
//
//        if (isMatch) {
//            for (int i = 0; i < stbAll.size(); i++) {
//                if (stbAll.get(i).length() > max) {
//                    max = stbAll.get(i).length();
//                    index = i;
//                }
//            }
//            return stbAll.get(index).toString();
//        }
//        return String.valueOf(s.charAt(0));
//    }


//    private static String palindromsLeetCode1(String s) {
//        StringBuilder stb = new StringBuilder();
//        StringBuilder stbR;
//        StringBuilder stbL = new StringBuilder();
//
//
//        if (s.length() == 1) return s;
//        for (int i = 0; i < s.length(); i++) {                     // aacabdkacaa
//            stb.append(s.charAt(i));
//            char y = stb.charAt(i);
//            if (s.lastIndexOf(y) != -1 && s.lastIndexOf(y) != i) {
//                int idx = s.lastIndexOf(y);
//                stbR = new StringBuilder(s.substring(i, idx + 1));
//
//                for (int j = stbR.length() - 1; j >= 0; j--) {
//                    stbL.append(stbR.charAt(j));
//                }
//                if (stbR.toString().equalsIgnoreCase(stbL.toString())) {
//                    return stbR.toString();
//                }
//            }
//        }
//
//        return String.valueOf(s.charAt(0));
//    }
}
