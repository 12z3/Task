package dynamicsStructhure;

import java.util.Map;
import java.util.TreeMap;

public class doStringsMatch {
    public static void main(String[] args) {
        //String s = "sdkalabalakanalaalab";
        String s = "sdkalabalakanalaalabalanewsoulalaala";
        String target = "ala";
        doTheseMatch_Map(s, target);
        //doTheseMatch(s, target);
        System.out.println();
        doTheseMatch_IndexOf(s, target);
        doTheseMatch_SubString(s, target);
        doTheseMatch_SubString1(s, target);
        doTheseMatch_Replace(s, target);
    }

    private static void doTheseMatch_Map(String string, String target) {
        int n = (target.length() - 1), size = (string.length() - n);
        long start = System.nanoTime();

        TreeMap<Integer, String> numberOfString = new TreeMap<>();
        StringBuilder tmp = new StringBuilder();

        for (int i = 0; i < size; i++) {
            tmp.append(string.charAt(i));
            for (int j = i + 1; j <= (i + n); j++) {
                tmp.append(string.charAt(j));
            }
            if (tmp.toString().equals(target)) numberOfString.put(i, tmp.toString());
            tmp.delete(0, tmp.length());
        }
        System.out.printf("\"%s\" -> matches %d times in string: \"%s\";%n", target, numberOfString.size(), string);
        for (Map.Entry<Integer, String> el : numberOfString.entrySet()) {
            System.out.printf("On idx: %d -> %s;%n", el.getKey(), el.getValue());
        }
        long end = System.nanoTime();
        System.out.println((end - start) / 1000 + " mSec" + "\n");
    }

    private static void doTheseMatch(String string, String target) {
        int n = (target.length() - 1), size = (string.length() - n);
        long start = System.nanoTime();

        TreeMap<Integer, String> numberOfString = new TreeMap<>();
        StringBuilder tmp = new StringBuilder();

        for (int i = 0; i < size; i++) {
            tmp.append(string.charAt(i)).append(string.charAt(i + 1)).append(string.charAt(i + 2));
            if (tmp.toString().equals(target)) numberOfString.put(i, tmp.toString());
            tmp.delete(0, tmp.length());
        }
        System.out.printf("\"%s\" -> matches %d times in string: \"%s\";%n", target, numberOfString.size(), string);
        for (Map.Entry<Integer, String> el : numberOfString.entrySet()) {
            System.out.printf("On idx: %d -> %s;%n", el.getKey(), el.getValue());
        }
        long end = System.nanoTime();
        System.out.println((end - start) / 1000 + " mSec");
    }

    private static void doTheseMatch_IndexOf(String string, String target) {        // sdk alabalakanalaalab
        long start = System.nanoTime();
        int cnt = 0, fromIdx = 0;

        for (int i = 0; i < string.length(); i++) {
            if (string.indexOf(target, fromIdx) != -1) {
                System.out.printf
                        ("string \"%s\" is matches %d on index: %d%n", target, cnt, string.indexOf(target, fromIdx));
                //fromIdx = s.indexOf(target, fromIdx) + (target.length());
                fromIdx = string.indexOf(target, fromIdx) + 1;
                cnt++;
            }
        }
        long end = System.nanoTime();
        System.out.println((end - start) / 1000 + " mSec" + "\n");
    }

    private static void doTheseMatch_SubString(String string, String target) {        // sdk alabalakanalaalab
        String tmp;
        long start = System.nanoTime();
        int cnt = 0, fromIdx = 0, toIdx = target.length(), idx = 0;

        for (int i = 0; i < string.length(); i++) {
            if (toIdx <= string.length()) {
                tmp = string.substring(fromIdx, toIdx);
                if (tmp.equals(target)) {
                    System.out.printf
                            ("string \"%s\" is matches %d on index: %d%n", target, cnt, fromIdx);
                    fromIdx += (target.length() - 1);
                    toIdx = fromIdx + (target.length());
                    cnt++;
                } else {
                    fromIdx++;
                    toIdx = fromIdx + (target.length());
                }
            } else break;
            idx = i;
        }
        System.out.println(idx);
        long end = System.nanoTime();
        System.out.println((end - start) / 1000 + " mSec" + "\n");
    }

    private static void doTheseMatch_SubString1(String string, String target) {        // sdk alabalakanalaalab
        String tmp;
        long start = System.nanoTime();
        int cnt = 0, fromIdx = 0, toIdx = target.length(), k = 0, idx = 0;

        while (k < string.length()) {
            if (toIdx <= string.length()) {
                tmp = string.substring(fromIdx, toIdx);
                if (tmp.equals(target)) {
                    System.out.printf
                            ("string \"%s\" is matches %d on index: %d%n", target, cnt, fromIdx);
                    fromIdx += (target.length() - 1);
                    toIdx = fromIdx + (target.length());
                    cnt++;
                    // k = toIdx;
                } else {
                    fromIdx++;
                   // k++;
                    toIdx = fromIdx + (target.length());
                }
            } else break;
            k = toIdx - 1;                               // -1 защото substring(fromIdx, toIdx] -> не включва toIdx
           // k = fromIdx;
            idx++;
        }
        System.out.println(idx);
        long end = System.nanoTime();
        System.out.println((end - start) / 1000 + " mSec" + "\n");
    }

    private static void doTheseMatch_Replace(String string, String target) {
        long start = System.nanoTime();

        String t = string.replace(target, " " + target.toUpperCase() + " ");
        System.out.println(t);

        long end = System.nanoTime();
        System.out.println((end - start) / 1000 + " mSec" + "\n");
    }
}

