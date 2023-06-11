package task;

import java.util.*;

public class CountsDigitMatches {
    public static void main(String[] args) {
        int[] a = {1, 1, 2, 3, 3, 3, 2, 2, 4, 5, 6, 6, 7, 8, 9, 9, 3};
        countDigitsArray(a);
        cuntDigitsList(a);
        cuntDigitsMaps(a);
    }

    private static void cuntDigitsMaps(int[] a) {
        Map<Integer, Integer> digitCounts = new HashMap<>();
        int counts;

        for (int i = 0; i < a.length; i++) digitCounts.put(a[i], 0);
        for (int i = 0; i < a.length; i++) {
            if (digitCounts.containsKey(a[i])) {
                counts = digitCounts.get(a[i]);
                //counts++;
                digitCounts.put(a[i], ++counts);
            }
        }

        for (Map.Entry<Integer, Integer> el: digitCounts.entrySet()){
            System.out.printf("%d is matches %d times.%n", el.getKey(), el.getValue());
        }
    }

    private static void cuntDigitsList(int[] a) {
        List<Integer> tmp = new ArrayList<>();
        List<Integer> tmpNum = new ArrayList<>();
        int count;

        for (int i = 0; i < a.length; i++) {
            count = 0;
            if (!tmpNum.contains(a[i])) {
                tmpNum.add(a[i]);
                for (int j = 0; j < a.length; j++) {
                    if (a[i] == a[j]) count++;
                }
                tmp.add(count);
            }
        }

        for (int i = 0; i < tmp.size(); i++) {
            if (tmpNum.get(i) != 0) System.out.println(tmpNum.get(i) + " = " + tmp.get(i));
        }
    }

    private static void countDigitsArray(int[] a) {
        int[] tmp = new int[a.length];
        int[] tmpNum = new int[a.length];
        boolean isContains;
        int count;

        for (int i = 0; i < a.length; i++) {
            isContains = false;
            count = 0;
            for (int k = 0; k < tmpNum.length; k++) {
                if (a[i] == tmpNum[k]) {
                    isContains = true;
                    break;
                }
            }
            if (isContains) continue;
            for (int j = 0; j < a.length; j++) {
                if (a[i] == a[j]) tmp[i] = ++count;
                tmpNum[i] = a[i];
            }
        }

        for (int i = 0; i < a.length; i++) {
            if (tmp[i] != 0) System.out.println(a[i] + " matches " + tmp[i] + " times");
        }
    }
}
