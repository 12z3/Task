package task;

import training.Methods;

import java.util.*;

public class LIS extends Methods {
    public static void main(String[] args) {
        long start = getStartTime();

        //ArrayList<Integer> input = new ArrayList<>(List.of(10, 22, 9, 33, 21, 50, 41, 60, 80));
        //                                                   10, 22,    33,     50,     60, 80
        //Collections.sort(input);
        //ArrayList<Integer> input = new ArrayList<>(List.of(3, 10, 2, 1, 20));
        ArrayList<Integer> input = new ArrayList<>(List.of(7, 3, 5, 8, -1, 0, 6, 7));
        //                                                    3, 5,           6, 7
        System.out.println(lisA(input));

        int max = 0;
        for (Map.Entry<StringBuilder, Integer> el : lisM(input).entrySet()) {
            if (el.getValue() > max) {
                max = el.getValue();
                System.out.printf("%s-> digits = %d%n", el.getKey(), max);
            }
        }

        long time = (getEndTime(start) - start) / 6000;
        if (time > 0) {
            System.out.printf("%s: %ds", "time", time);
        } else {
            System.out.printf("%s: %dms", "time", (getEndTime(start) - start));
        }

    }


    private static Map<StringBuilder, Integer> lisM(ArrayList<Integer> input) {
        StringBuilder result = null;                                         //   10, 22, 9, 33, 21, 50, 41, 60, 80
        Map<StringBuilder, Integer> numberIndex = new LinkedHashMap<>();     //   7, 3, 5, 8, -1, 0, 6, 7 -> ????
        int maxLength = 0;

        for (int i = 0; i < input.size(); i++) {
            int digitsCount = 0;
            result = new StringBuilder();
            int elA = input.get(i);
            result.append(elA).append(" ");
            digitsCount++;
            for (int j = i + 1; j < input.size() - 1; j++) {
                int elB = input.get(j);
                if (elA < elB) {
                    result.append(elB).append(" ");
                    digitsCount++;
                    // elA = elB;
                    if ((elB + 1) > elB) elA = elB;
                }
            }
            if (digitsCount > maxLength) {
                maxLength = digitsCount;
                numberIndex.put(result, maxLength);
            }
        }
        return numberIndex;
    }

    private static ArrayList<StringBuilder> lisA(ArrayList<Integer> input) {
        StringBuilder result = null;                                           // 10, 22, 9, 33, 21, 50, 41, 60, 80
        ArrayList<StringBuilder> resultA = new ArrayList<>();                  //   7, 3, 5, 8, -1, 0, 6, 7 -> ????

        for (int i = 0; i < input.size(); i++) {
            result = new StringBuilder();
            int elA = input.get(i);
            result.append(elA).append(" ");
            for (int j = i + 1; j < input.size(); j++) {
                int elB = input.get(j);
                if (elA < elB) {
                    result.append(elB).append(" ");
                    // elA = elB;
                    if ((input.get(j + 1)) > elB) elA = elB;
                }
            }
            resultA.add(result);
        }
        return resultA;
    }
}
