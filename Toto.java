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

        //allMatchesForThisNumber();
        generateTotoNum(5,45);

    }

    private static void allMatchesForThisNumber() {
        List<Integer> count = new ArrayList<>();
        int i = 0;
        while (i < 100){
            count.add(generateTotoNum(6,39));
            i++;
        }
        System.out.println(count);
    }

    private static int generateTotoNum(int variants, int thisNumber) {
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
        printResult(thisNumber, allNumbers);
        return allContains(allNumbers,thisNumber);
    }

    private static void printResult(int thisNumber, List<List<Integer>> allNumbers) {
        System.out.println(allNumbers);
        System.out.println();
        System.out.println(
            "Numbers " + thisNumber + " contains " + allContains(allNumbers, thisNumber) + " times.");
    }

    private static boolean isContains(List<Integer> list, int thisNumber) {
        if (list.contains(thisNumber)) return true;
        return false;
    }

    private static int allContains(List<List<Integer>> allNumbers, int thisNumber) {
        int countConsist = 0;
        for (List<Integer> allNumber : allNumbers) {
            if (isContains(allNumber, thisNumber)) {
                countConsist++;
                System.out.println(allNumber);
            }
        }
        return countConsist;
    }
}
