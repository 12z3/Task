package task;

import java.util.*;

public class MixedLists {
    public static void main(String[] args) {
        List<Integer> l1 = new ArrayList<>(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9));

        long start1 = getStartTime();
        mixedList(l1);
        long end1 = getEndTime();
        System.out.println("mixedList -> "
                + Math.ceil((double) (end1 - start1) / 1000) + " мкрСек.");

        long start = getStartTime();
        mixIt(l1);
        long end = getEndTime();
        System.out.println("mixIt -> "
                + Math.ceil((double) (end - start) / 1000) + " мкрСек.");
    }


    private static void mixIt(List<Integer> l1) {
        List<Integer> result = new ArrayList<>();
        int[] tmp = new int[l1.size()];
        Arrays.fill(tmp, -1);
        Random rdm = new Random();

        for (int i = 0; i < tmp.length; i++) {
            int index = rdm.nextInt(0, l1.size());
            if (tmp[index] != -1) {
                i--;
            } else {
                result.add(l1.get(index));
                tmp[index] = index;
            }
        }
        //for (int el : result) System.out.print(el + " ");
        result.forEach(el -> System.out.print(el + " "));
    }

    private static void mixedList(List<Integer> l1) {
        Random rdm = new Random();
        for (int el : l1) System.out.print(el + " ");
        System.out.println();


        for (int i = 0; i < l1.size(); i++) {
            int index = rdm.nextInt(0, l1.size());
            swap(l1, i, index);
        }
        for (int el : l1) System.out.print(el + " ");
    }

    private static void swap(List<Integer> list, int pos1, int pos2) {
        int el = list.get(pos1);
        list.set(pos1, list.get(pos2));
        list.set(pos2, el);
    }

    public static long getStartTime() {
        return System.nanoTime();
    }

    public static long getEndTime() {
        return System.nanoTime();
    }
}
