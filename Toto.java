package task;

import training.Methods;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Toto extends Methods {
    /**
     * Генерира числата от 1 до 49 "variants" пъти и записва резултатите в "List<List<Integer>> allNumbers".
     * Търси колко пъти се намира числото "thisNumber" в резултата.
     */

    public static void main(String[] args) {
        timeAndData();
        generateTotoNum(5, 4);
        //allMatchesForThisNumber();
    }

    private static void allMatchesForThisNumber() {
        List<Integer> count = new ArrayList<>();
        int i = 0;
        while (i < 100) {
            count.add(allMatches(6, 39));
            i++;
        }
        System.out.println(count);
    }

    private static int allMatches(int variants, int thisNumber) {
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
        return allThisContains(allNumbers, thisNumber);
    }

    private static void generateTotoNum(int variants, int thisNumber) {
        Random rnd = new Random();
        List<Integer> list = new ArrayList<>();
        List<Integer> last = new ArrayList<>(List.of(3, 5, 8, 12, 16, 36));
        List<List<Integer>> allNumbers = new ArrayList<>();

        int counter = 0;
        while (counter < variants) {
            list = new ArrayList<>();
            for (int k = 0; k < 6; k++) {
                int el = rnd.nextInt(50);
                if (!check(list, el) && el != 0 && !check(last,el)) {
                    list.add(el);
                } else {
                    if (k != 0 && k > -1) {
                        k--;
                    }
                }
            }
            allNumbers.add(list);
            counter++;
        }
        System.out.println("Last number is: " + last);
        System.out.println("New number: ");
        printResult(allNumbers, thisNumber, last);
    }

    private static boolean check(List<Integer> l1, int l2) {
        boolean check = false;
        for (int i = 0; i < l1.size(); i++) {
            if (l1.get(i) == l2) {
                check = true;
                break;
            }
        }
        return check;
    }

    private static void printResult(List<List<Integer>> allNumbers, int thisNumber, List<Integer> last) {
        System.out.println(allNumbers + "\n");
        System.out.println(
                "Numbers " + thisNumber + " contains " + allThisContains(allNumbers, thisNumber) + " times.");
        System.out.print("is 'allNumbers' - elements match with 'last' - element? : ");
        for (List<Integer> el: allNumbers) System.out.print(compareTwoIntLists(last, el) + " ");
    }

    private static int isThisContains(List<Integer> list, int thisNumber) {
        int count = 0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).equals(thisNumber)) {
                count++;
            }
        }
        if (count != 0) {
            return count;
        } else return -1;
    }

    private static int allThisContains(List<List<Integer>> allNumbers, int thisNumber) {
        int allCounts = 0;
        for (int i = 0; i < allNumbers.size(); i++) {
            if ((isThisContains(allNumbers.get(i), thisNumber)) != -1) {
                allCounts += (isThisContains(allNumbers.get(i), thisNumber));
                System.out.println(allNumbers.get(i));
            }
        }
        return allCounts;
    }
}
