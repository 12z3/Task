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
        matchCheckers(memo, 11);
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

    // [32, 17, 32, 27, 22, 9] [34, 47, 37, 11, 12, 36] [32, 36, 5, 34, 14, 46] -> 11
    // [0, 0, 0, 0, 0, 0]      [0, 0, 0, 1, 0, 0]       [0, 0, 0, 0, 0, 0]
    // [32, 17, 32, 27, 22, 9] [34, 47, 37, 22, 12, 36] [32, 36, 5, 34, 14, 46]
    private static List<List<Integer>> matchCheckers(List<List<Integer>> list, int el) {
        List<List<Integer>> matchers = new ArrayList<>();
        Random rnd = new Random();
        for (int i = 0; i < list.size(); i++) {
            List<Integer> matcher = new ArrayList<>();
            for (int j = 0; j < list.get(i).size(); j++) {
                int digit = list.get(i).get(j);
                if (digit != el) {
                    matcher.add(j, 0);
                } else {
                    digit = rnd.nextInt(1, 49);
                    list.get(i).add(j, digit);                                    // Добавя digit на позиция j
                    list.get(i).remove(j + 1);                              // Премахва 11 от позиция j + 1
                    matcher.add(j, 1);
                }
            }

            matchers.add(i, matcher);
        }
        System.out.println();
        for (List<Integer> e : matchers) System.out.print(e + " ");
        System.out.println();
        for (List<Integer> e : list) System.out.print(e + " ");
        return matchers;
    }
}
