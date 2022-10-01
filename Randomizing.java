package hackerRank;

import java.util.ArrayList;
import java.util.List;

public class Randomizing {
    /**
     * @Problem: <a href="https://www.youtube.com/watch?v=H57pSYrPv-g">...</a>
     * Time: 38:46
     */

    public static void main(String[] args) {

        List<Integer> list = new ArrayList<>(List.of(10, 5, 56, 8, 98));
        randomizing(list);
        System.out.println();
        shifting(list);
    }

    private static void randomizing(List<Integer> list) {
        List<Integer> numbers = new ArrayList<>();
        int min = 0, stopCriteria = 0, max = list.size() - 1;

        while (true) {
            int random = (int) (Math.random() * ((max - min) + 1)) + min;

            if (!isMatch(numbers, list.get(random))) {
                System.out.print(list.get(random) + " ");
                numbers.add(list.get(random));
                stopCriteria++;
                if (stopCriteria == list.size()) return;
            }
        }
    }

    private static boolean isMatch(List<Integer> numbers, int digit) {
        for (int el : numbers) {
            if (el == digit) return true;
        }
        return false;
    }

    private static void shifting(List<Integer> list) {
        int min = 0, max = list.size() - 1;
        int randomIndex = (int) (Math.random() * ((max - min) + 1)) + min;

        for (int i = 0; i < list.size(); i++) {
            int tmp = list.get(i);
            list.set(i, list.get(randomIndex));
            list.set(randomIndex, tmp);
        }

        for (int el : list) System.out.print(el + " ");
    }
}
