package hackerRank;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Randomizing {
    /**
     * @Problem: <a href="https://www.youtube.com/watch?v=H57pSYrPv-g">...</a>
     * Time: 38:46
     * @randomizingWithShifting(list): Разменя местата на текущият елемент -
     * - с число на което индекса му е произволно генериран.
     *
     * @randomizing: Избира елемент чийто индекс е произволен, записва го в масив.
     * Генерира втори елемент и проверява дали е наличен в масива. Ако не е наличен го печата записва го в масива.
     */

    public static void main(String[] args) {

        List<Integer> listInt = new ArrayList<>(List.of(10, 5, 56, 8, 98));
        List<Character> listCh = new ArrayList<>(List.of('a', 'b', 'c', 'd', 'f'));
        List<String> listStr = new ArrayList<>(List.of("Удрйй...", "Бах Го.", "Опаа.", "У бре,", "Не моЕ ли ..."));


        randomizing(listInt);
        System.out.println();
        randomizingWithShifting(listStr);
    }

    private static void randomizing(List<Integer> list) {
        Random random = new Random();
        List<Integer> numbers = new ArrayList<>();
        int min = 0, stopCriteria = 0, max = list.size() - 1;

        while (true) {
            int randomIndex1 = (int) (Math.random() * ((max - min) + 1)) + min;
            int randomIndex = random.nextInt(list.size());
            int currentElement = list.get(randomIndex);

            if (!isContained(numbers, currentElement)) {
                System.out.print(list.get(randomIndex) + " ");
                numbers.add(currentElement);
                stopCriteria++;
                if (stopCriteria == list.size()) break;
            }
        }
    }

    private static boolean isContained(List<Integer> numbers, int currentElement) {
        for (int el : numbers) {
            if (el == currentElement) return true;
        }
        return false;
    }

    private static <T> void randomizingWithShifting(List<T> list) {
        int min = 0, max = list.size() - 1;

        for (int i = 0; i < list.size(); i++) {
            int randomIndex = (int) (Math.random() * ((max - min) + 1)) + min;
            swap(list, i, randomIndex);
        }

        for (T el : list) System.out.print(el + " ");
    }

    private static <T> void swap(List<T> list, int pos1, int pos2) {
        T tmp = list.get(pos1);
        list.set(pos1, list.get(pos2));
        list.set(pos2, tmp);
    }
}
