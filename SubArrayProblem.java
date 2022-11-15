package hackerRank;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SubArrayProblem {

    /**
     * @Source: <a href="https://www.youtube.com/watch?v=5QKoQzR0iGY">...</a>
     * @Problem: Дефинираме подпоследователност като всяко подмножество на масив.
     * Ние дефинираме подмасив като непрекъсната подпоследователност в масив.
     * Даден е масив, намерете максималната възможна сума сред:
     *<p>
     * всички непразни подмасиви.
     * всички непразни подпоследователности.
     * Отпечатайте двете стойности като цели числа, разделени с интервал на един ред.
     *<p>
     * Имайте предвид , че празните подмасиви/подпоследователности не трябва да се вземат предвид.
     * </p>
     */
    public static void main(String[] args) {

        List<Integer> arr = new ArrayList<>(List.of(-1, 2, 3, -4, 5, 10));

        maxSubArray(arr);
        System.out.println(maxSubArrays(arr));
        System.out.println(maxSubArray1(arr));
    }

    private static void maxSubArray(List<Integer> arr) {
        List<Integer> maxLs = null, ls = null, idxLs = null, maxIdxLs = null;
        int max = Integer.MIN_VALUE, maxIdxSum = Integer.MIN_VALUE, idxSum = 0, sum = 0;
        boolean isBreak = false;

        for (int i = 0; i < arr.size() - 1; i++) {
            isBreak = false;
            ls = new ArrayList<>();
            idxLs = new ArrayList<>();

            ls.add(arr.get(i));
            idxLs.add(i);
            sum = arr.get(i);
            idxSum = i;
            int index = i + 1;
            while (index < arr.size()) {
                if (arr.get(i) > 0) {
                    if (arr.get(index - 1) < arr.get(index)) {
                        sum += arr.get(index);
                        idxSum += index;
                        ls.add(arr.get(index));
                        idxLs.add(index);
                    }
                } else {
                    isBreak = true;
                    break;
                }
                index++;
            }
            if (!isBreak && sum > max) {
                maxLs = new ArrayList<>();
                maxIdxLs = new ArrayList<>();

                max = sum;
                maxIdxSum = idxSum;
                maxLs = ls;
                maxIdxLs = idxLs;

            }
        }
        System.out.println(maxLs + " Max Sum of elements is: " + max);
        System.out.println(maxIdxLs + " Sum of element indexes is: " + maxIdxSum);

        List<Integer> result = new ArrayList<>();
        result.add(maxIdxSum);
        result.add(max);
        System.out.println(result);
    }

    // Solutions from Discussions:
    // Maximum Subarray (Kadane's) Algorithm: SA-> SubArray, SS -> SubSequences.
    public static List<Integer> maxSubArrays(List<Integer> arr) {
        int maxSA = arr.get(0); // max sub array
        int maxSS = arr.get(0); // max sub sequences
        int max = arr.get(0);

        for (int i = 1; i < arr.size(); i++) {               // (-1, 2, 3, -4, 5, 10)
            max = Math.max(max + arr.get(i), arr.get(i));    // max стойност на подмасива до текущия индекс
            maxSA = Math.max(maxSA, max);                    // maxSA = Math.max(-1, 2) = 2 -> сумата на подредицата?

            maxSS = Math.max(Math.max(maxSS, arr.get(i)), maxSS + arr.get(i));
        }                                                    // maxSS = Math.max(Math.max(-1, 2), 2 + 2) = 4;

        ArrayList<Integer> list = new ArrayList<>();
        list.add(maxSA);
        list.add(maxSS);
        return list;
    }

    public static List<Integer> maxSubArray1(List<Integer> arr) {
        ArrayList<Integer> al = new ArrayList<>();
        int sum = 0, j = 0, maxI = -999999;

        for (int i = 0; i < arr.size(); i++) {
            sum += arr.get(i);                                // Сумата от всички последователни елементи.
            if (sum < 0) sum = 0;
            maxI = Math.max(maxI, sum);
            if (arr.get(i) > 0)                              // Сумата от всички положителни елементи
                j += arr.get(i);
        }
        if (j == 0) {
            j = Collections.max(arr);
            maxI = j;
        }
        al.add(maxI);
        al.add(j);
        return al;
    }
}
