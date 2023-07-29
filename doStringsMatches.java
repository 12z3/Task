package dynamicsStructhure;

import java.util.Map;
import java.util.TreeMap;

public class doStringsMatches {
    public static void main(String[] args) {
        String s = "alabalakanala";
        String target = "ala";
        doTheseMatches(s, target);
        doTheseMatches1(s, target);
    }

    private static void doTheseMatches(String s, String target) {
        int n = (target.length() - 1), size = (s.length() - n);
        long start = System.nanoTime();

        TreeMap<Integer, String> numberOfString = new TreeMap<>();
        StringBuilder tmp = new StringBuilder();

        for (int i = 0; i < size; i++) {
            tmp.append(s.charAt(i));
            for (int j = i + 1; j <= (i + n); j++) {
                tmp.append(s.charAt(j));
            }
            if (tmp.toString().equals(target)) numberOfString.put(i, tmp.toString());
            tmp.delete(0, tmp.length());
        }
        System.out.printf("'%s' -> matches %d times in string: '%s';%n", target, numberOfString.size(), s);
        for (Map.Entry<Integer, String> el : numberOfString.entrySet()) {
            System.out.printf("On idx: %d -> %s;%n", el.getKey(), el.getValue());
        }
        long end = System.nanoTime();
        System.out.println((end - start) / 1000 + " mSec" + "\n");
    }

    private static void doTheseMatches1(String s, String target) {
        int n = (target.length() - 1), size = (s.length() - n);
        long start = System.nanoTime();

        TreeMap<Integer, String> numberOfString = new TreeMap<>();
        StringBuilder tmp = new StringBuilder();

        for (int i = 0; i < size; i++) {
            tmp.append(s.charAt(i)).append(s.charAt(i + 1)).append(s.charAt(i + 2));
            if (tmp.toString().equals(target)) numberOfString.put(i, tmp.toString());
            tmp.delete(0, tmp.length());
        }
        System.out.printf("'%s' -> matches %d times in string: '%s';%n", target, numberOfString.size(), s);
        for (Map.Entry<Integer, String> el : numberOfString.entrySet()) {
            System.out.printf("On idx: %d -> %s;%n", el.getKey(), el.getValue());
        }
        long end = System.nanoTime();
        System.out.println((end - start) / 1000 + " mSec");
    }
}

