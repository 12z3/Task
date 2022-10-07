package task;

import training.Methods;

import java.util.ArrayList;
import java.util.List;

public class GenericAgain extends Methods {

    /**
     * @Generics: Не понасят примитиви. Integer[] вместо int[].
     */

    static <T> void print(T[] elements) {
        for (Object element : elements) {
            if (!element.equals(0)) System.out.print(element + " ");
        }
        System.out.println();
    }

    static void printL(List<?> elements) {
        for (Object element : elements) {
            if (!element.equals(0)) System.out.print(element + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Integer[] inT = {1, 2, 0, 4, 5};
        Double[] douB = {1.1, 2.2, 0.0, 4.1, 5.3};
        String[] stR = {"a", "b", " "};
        Boolean[] booL = {false, true,};

        Integer[][] a = {{1, 2, 3}, {3, 4, 5}, {6, 7, 8}};
        String[][] b = {{"k", "l", "f"}, {"p", "t", "s"}, {"q", "w", "z"}};

        List<Integer> inTL = new ArrayList<>(List.of(1, 2, 0, 4, 5));
        List<Double> douBL = new ArrayList<>(List.of(1.1, 2.2, 0.0, 4.1, 5.3));
        List<String> stRL = new ArrayList<>(List.of("a", "b", " "));
        List<Boolean> booLL = new ArrayList<>(List.of(false, true, false));

        print(inT);
        print(douB);
        print(stR);
        print(booL);

        printL(inTL);
        printL(douBL);
        printL(stRL);
        printL(booLL);

        printMassive1D(inT);
        print2DMassive(a);
        print2DMassive(b);

    }
}
