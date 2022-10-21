package task.task;

import training.Methods;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class TotoLogic extends Methods {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Integer> a = new ArrayList<>(List.of(1, 2, 3, 4));
        List<Integer> b = new ArrayList<>(List.of(1, 5, 31, 14));
        List<Integer> c = new ArrayList<>(List.of(11, 12, 13, 41));
        //List<List<Integer>> allData = new ArrayList<Integer>(List.of((1, 2, 3, 4),(1, 5, 31, 14),(11, 12, 13, 41));
        //System.out.println(addToDatabase(addToDatabase(data, a), b));
        //System.out.println(addToDatabase((addToDatabase(addToDatabase(data, a), b)), c));

        // Трябва да се запишат във файл и всеки път да се презаписва файла с новите стойностти.   <-


        List<List<Integer>> data = null;
        System.out.println(addToDatabase(data));
    }

    private static List<List<Integer>> addToDatabase(List<List<Integer>> data) {
        Scanner scanner = new Scanner(System.in);

        String[] string = scanner.nextLine().split(", ");
        Integer[] input = new Integer[string.length];

        for (int i = 0; i < input.length; i++) {
            input[i] = Integer.parseInt(string[i]);
        }
        if (data == null) {
            data = new ArrayList<>();
            data.add(Arrays.asList(input));
        } else data.add(Arrays.asList(input));
        return data;
    }

    private static int findThisElement(List<Integer> list, int thisElement) {
        int count = 0;
        for (Integer element : list) {
            if (element == thisElement) count++;
        }
        return count;
    }

    private static void repetitiveNumbers(List<List<Integer>> input, int thisElement) {
        int count = 0;
        for (int i = 0; i < input.size(); i++) {
            count = 0;
            for (int j = 0; j < input.get(i).size(); j++) {
                if (input.get(i).get(j) == thisElement) count++;
                List<Integer> result = new ArrayList<>();
                result.add(count);
            }
        }


    }
}
