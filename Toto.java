package task;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Toto {
    /**
     * Генерира числата от 1 до 49 variants пъти. и Записва резултатите в List<List<Integer>> allNumbers.
     * Търси колко пъти се намира числото number в резултата.
     */

    public static void main(String[] args) {
        generateTotoNum(6, 44);
    }

    private static void generateTotoNum(int variants, int thisNumber) {
        Random rnd = new Random();
        List<Integer> list = new ArrayList<>();
        List<List<Integer>> allNumbers = new ArrayList<>();

        int counter = 0;
        while (counter < variants) {
            list = new ArrayList<>();
            for (int k = 0; k < 6; k++) {
                list.add(rnd.nextInt(50));
            }
            allNumbers.add(list);
            counter++;
        }
        System.out.println(allNumbers);
        System.out.println();
        System.out.println(
            "Numbers " + thisNumber + " contains " + allConsist(allNumbers, thisNumber) + " times.");
    }

    private static boolean isConsist(List<Integer> list, int number) {
        if (list.contains(number)) {
            List<Integer> newList = new ArrayList<>(list);
            System.out.println(newList);
            return true;
        }
        return false;
    }

    private static int allConsist(List<List<Integer>> allNumbers, int number) {
        int countConsist = 0;
        for (int i = 0; i < allNumbers.size(); i++) {
            if (isConsist(allNumbers.get(i), number)) countConsist++;
        }
        return countConsist;
    }
}
