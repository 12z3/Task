package task;

import training.Methods;

import java.util.Objects;
import java.util.Scanner;

public class TOTOCheckResults extends Methods {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 92-и тираж:  9, 24, 26, 28, 37, 46 //

        int[] a = OfficialResult("Резултат от тиража: ", scanner);
        int[] b = yourInput("Твоя залог: ", scanner);
        System.out.println("------------------------------------");
        printResult(a, b);
    }

    private static int[] yourInput(String s, Scanner scanner) {
        System.out.print(s);
        return result(scanner);
    }

    private static int[] OfficialResult(String s, Scanner scanner) {
        System.out.print(s);
        return result(scanner);
    }

    private static void printResult(int[] a, int[] b) {

        System.out.println("Имаш " + getCounts(a, b) + " попадения: ");

        for (int i = 0; i < Objects.requireNonNull(compareResults(a, b)).length; i++) {
            if (Objects.requireNonNull(compareResults(a, b))[i] != 0) {
                System.out.println("Елемента " + a[i] + " се среща " +
                        Objects.requireNonNull(compareResults(a, b))[i] + " пъти.");
            }
        }
    }

    private static int[] result(Scanner scanner) {
        String[] input = scanner.nextLine().split(", ");
        int[] result = new int[input.length];

        for (int i = 0; i < result.length; i++) {
            result[i] = Integer.parseInt(input[i]);
        }
        return result;
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
            int count = 0, el = 0;
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
