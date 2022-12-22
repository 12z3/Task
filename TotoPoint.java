package task;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TotoPoint {
    public static void main(String[] args) {
        List<List<Integer>> memo = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            memo.add(generateNumbers());
        }
        for (List<Integer> el : memo) System.out.print(el + " ");
        matchCheckers(memo, 12);
    }

    // Генерира резултат 1 @ count
    private static List<Integer> generateNumbers() {
        List<Integer> numbers = new ArrayList<>();
        int count = 5, step = 0;
        while (count >= 0) {
            numbers = generateDigits();
            count--;
        }
        return numbers;
    }

    // Генерира масив от 6 числа от 1 - 49
    private static List<Integer> generateDigits() {
        Random rnd = new Random();
        List<Integer> a = new ArrayList<>();
        int digit = 0;

        for (int i = 0; i < 6; i++) {
            digit = rnd.nextInt(50);
            if (digit != 0) {
                a.add(digit);
            } else i--;
        }
        //for (int el: a) System.out.print(el + " ");
        return a;
    }

    // [20, 29, 29, 24, 13, 26] [24, 48, 44, 45, 7, 12] [17, 38, 11, 32, 43, 1]
    // [0, 0, 0, 0, 0, 0]       [0, 0, 0, 0, 0, 1]      [0, 0, 0, 0, 0, 0]
    private static boolean matchCheckers(List<List<Integer>> list, int el) {
        List<List<Integer>> matchers = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            List<Integer> matcher = new ArrayList<>();
            for (int j = 0; j < list.get(i).size(); j++) {
                if (list.get(i).get(j) != el) {
                    matcher.add(j, 0);
                } else matcher.add(j, 1);
            }
            matchers.add(i, matcher);
        }
        System.out.println();
        for (List<Integer> e : matchers) System.out.print(e + " ");
        return false;
    }
}
