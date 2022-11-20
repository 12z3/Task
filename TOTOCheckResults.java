package task;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class TOTOCheckResults extends Toto {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        timeAndData();

        // 92-и тираж:  9, 24, 26, 28, 37, 46

        System.out.println("----------------------------------------");
        int[] a = officialResult("Резултат от тиража: ", scanner);
        int[] b = yourSuppose("Твоят залог: ", scanner);
        System.out.println("----------------------------------------");
        printResult(a, b);
        System.out.println("----------------------------------------");
        question();
    }

    private static void question() {
        List<Integer> last = new ArrayList<>(List.of(17, 21, 39, 41, 42, 44));
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ще залагаме ли? : ");
        String answer = scanner.nextLine();
        switch (answer) {
            case "y" -> {
                generateTotoNum(last, 3, 5);
            }
            case "n" -> {
            }
        }
    }

    private static int[] yourSuppose(String s, Scanner scanner) {
        System.out.print(s);
        return result(scanner);
    }

    private static int[] officialResult(String s, Scanner scanner) {
        System.out.print(s);
        return result(scanner);
    }

    private static int[] result(Scanner scanner) {
        String[] input = scanner.nextLine().split(", ");
        int[] result = new int[input.length];

        for (int i = 0; i < result.length; i++) {
            result[i] = Integer.parseInt(input[i]);
        }
        return result;
    }

    private static void printResult(int[] a, int[] b) {

        System.out.print("Имаш " + getCounts(a, b) + " попадения: ");

        for (int i = 0; i < Objects.requireNonNull(compareResults(a, b)).length; i++) {
            if (Objects.requireNonNull(compareResults(a, b))[i] != 0 &&
                    i < Objects.requireNonNull(compareResults(a, b)).length - 1) {
                System.out.print(a[i] + ", ");
            } else System.out.print(a[i]);
        }
        System.out.println();
    }

    private static int[] compareResults(int[] a, int[] b) {
        if (a.length != b.length) {
            System.out.println("ERROR");
            return null;
        }
        return getResult(a, b);
    }

    private static int[] getResult(int[] a, int[] b) {
        int[] tmp = new int[a.length];
        for (int i = 0; i < a.length; i++) {
            int count = 0;
            for (int j = 0; j < b.length; j++) {
                if (a[i] == b[j]) {
                    count++;
                }
            }
            tmp[i] = count;
        }
        return tmp;
    }

    private static int getCounts(int[] a, int[] b) {
        int count = 0;
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < b.length; j++) {
                if (a[i] == b[j]) {
                    count++;
                }
            }
        }
        return count;
    }
}
