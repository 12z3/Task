package hackerRank;

import java.util.ArrayList;
import java.util.List;

public class Randomizing {
    public static void main(String[] args) {

        List<Integer> list = new ArrayList<>(List.of(10, 5, 56, 8, 98));
        randomizing(list);
    }

    private static boolean isCheck(List<Integer> numbers, int digit) {
        for (int el : numbers) {
            if (el == digit) return true;
        }
        return false;
    }

    private static void randomizing(List<Integer> list) {
        List<Integer> numbers = new ArrayList<>();
        int min = 0, count = 0,
                previously = 0, max = list.size() - 1;

        while (true) {
            int random = (int) (Math.random() * ((max - min) + 1)) + min;

            if (!isCheck(numbers, list.get(random))) {
                System.out.print(list.get(random) + " ");
                numbers.add(list.get(random));
                count++;
                if (count == list.size()) return;
            }
        }
    }
}
