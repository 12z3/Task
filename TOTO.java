package task;

import training.Methods;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class TOTO extends TotoA {

    // TODO: Пренапиши го грамотно използвайки ООП

    public static void main(String[] args) {
        timeAndData();

        // TODO: 92-и тираж:  12, 24, 26, 28, 37, 48  //  9, 24, 26, 28, 37, 46 - It's official;

        letsGo();
    }

    private static void letsGo() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Проверяваме или играем? (c / p): ");
        String answerOne = scanner.nextLine().trim();

        if ("p".equalsIgnoreCase(checkAnswer(answerOne))) {
            play();
        } else if ("c".equalsIgnoreCase(checkAnswer(answerOne))) {
            checkResults();
        }
    }

    private static String checkAnswer(String answer) {
        Scanner scanner = new Scanner(System.in);
        while (!answer.equalsIgnoreCase("c") &&              // while търси true;
                !answer.equalsIgnoreCase("p")) {             // true && true = true; true && false = false;
            System.out.println("Айде сега.... 'c' или 'p'?");
            answer = scanner.nextLine();
        }
        return answer;
    }
    private static void play() {
        Scanner scanner = new Scanner(System.in);
        List<Integer> last = new ArrayList<>(List.of(12, 14, 17, 21, 39, 48));
        System.out.print("Ще залагаме ли? (y / n) : ");

        String answer = checkAnswerAgain();
        if (answer.equalsIgnoreCase("y")) generateTotoNum(last, 3, 5);

        System.out.print("Приключваш ли?... (y / n) : ");
        String answerNew = scanner.nextLine().trim();
        if (answerNew.equalsIgnoreCase("n")){
            checkResults();
        }
    }

    private static String checkAnswerAgain() {
        Scanner scanner = new Scanner(System.in);

        String answer = scanner.nextLine();
        while (!answer.equalsIgnoreCase("y") &&              // while търси true;
                !answer.equalsIgnoreCase("n")) {             // true && true = true; true && false = false;
            System.out.println("Айде сега.... 'y' или 'n'?");
            answer = scanner.nextLine();
        }
        return answer;
    }

    private static void checkResults() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("-----------------------------------------------");
        int[] a = officialResult("Резултат от тиража: ");
        int[] b = yourSuppose("Твоят залог: ");
        System.out.println("-----------------------------------------------");
        printResult(a, b);
        System.out.println("-----------------------------------------------");

        System.out.print("Край?... (y / n) : ");
        String answerNew = scanner.nextLine().trim();
        if (answerNew.equalsIgnoreCase("n")){
            play();
        }
    }

    private static int[] yourSuppose(String s) {
        System.out.print(s);
        return result();
    }

    private static int[] officialResult(String s) {
        System.out.print(s);
        return result();
    }

    private static int[] result() {
        Scanner scanner = new Scanner(System.in);
        String[] input = scanner.nextLine().
                trim().
                split(", ");

        int[] result = new int[input.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = Integer.parseInt(input[i]);
        }
        return result;
    }

    private static void printResult(int[] a, int[] b) {

        System.out.print("Имаш " + getCounts(a, b) + " от " + a.length + " попадения: ");

        int[] tmp = Objects.requireNonNull(compareResults(a, b));                // 12, 14, 17, 21, 39, 48
        int counter = getCounts(a, b);
        for (int i = 0; i < tmp.length; i++) {
            if (tmp[i] != 0 && counter > 1) {
                System.out.print(tmp[i] + ", ");
                counter--;
            } else if (tmp[i] != 0) {
                System.out.print(tmp[i]);
                counter--;
            }
            if (counter == 0) break;
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
        boolean isMatch;
        for (int i = 0; i < a.length; i++) {
            isMatch = false;
            for (int j = 0; j < b.length; j++) {
                if (a[i] == b[j]) {
                    isMatch = true;
                }
            }
            if (isMatch) tmp[i] = a[i];
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

/*
 * public static void main(String[] args) {
 * Scanner scanner = new Scanner(System.in);
 * timeAndData();
 * <p>
 * //TODO: 92-и тираж:  12, 24, 26, 28, 37, 48  // 9, 24, 26, 28, 37, 46
 * <p>
 * System.out.print("Проверяваме или играем (c / p): ");
 * String answerOne = scanner.nextLine().trim();
 * <p>
 * <p>
 * if (answerOne.equalsIgnoreCase("p")){
 * question();
 * } else if (answerOne.equalsIgnoreCase("c")){
 * <p>
 * System.out.println("-----------------------------------------------");
 * int[] a = officialResult("Резултат от тиража: ", scanner);
 * int[] b = yourSuppose("Твоят залог: ", scanner);
 * System.out.println("-----------------------------------------------");
 * printResult(a, b);
 * System.out.println("-----------------------------------------------");
 * }
 * //question();
 * }
 * <p>
 * private static void question() {
 * List<Integer> last = new ArrayList<>(List.of(12, 14, 17, 21, 39, 48));
 * Scanner scanner = new Scanner(System.in);
 * System.out.print("Ще залагаме ли? (y / n) : ");
 * <p>
 * // while търси true;  // true && true = true; true && false = false;
 * String answer = scanner.nextLine();
 * while (!answer.equalsIgnoreCase("y") &&
 * !answer.equalsIgnoreCase("n")) {
 * System.out.println("Айде сега.... 'y' или 'n'?");
 * answer = scanner.nextLine();
 * }
 * <p>
 * if (answer.equalsIgnoreCase("y")) generateTotoNum(last, 3, 5);
 * }
 * <p>
 * private static int[] yourSuppose(String s, Scanner scanner) {
 * System.out.print(s);
 * return result(scanner);
 * }
 * <p>
 * private static int[] officialResult(String s, Scanner scanner) {
 * System.out.print(s);
 * return result(scanner);
 * }
 * <p>
 * private static int[] result(Scanner scanner) {
 * String[] input = scanner.nextLine().
 * trim().
 * split(", ");
 * <p>
 * int[] result = new int[input.length];
 * for (int i = 0; i < result.length; i++) {
 * result[i] = Integer.parseInt(input[i]);
 * }
 * return result;
 * }
 * <p>
 * private static void printResult(int[] a, int[] b) {
 * <p>
 * System.out.print("Имаш " + getCounts(a, b) + " от " + a.length + " попадения: ");
 * <p>
 * int[] tmp = Objects.requireNonNull(compareResults(a, b));   // 12, 14, 17, 21, 39, 48
 * int counter = getCounts(a,b);
 * for (int i = 0; i < tmp.length; i++) {
 * if (tmp[i] != 0 && counter > 1) {
 * System.out.print(tmp[i] + ", ");
 * counter--;
 * } else if(tmp[i] != 0)  {
 * System.out.print(tmp[i]);
 * counter--;
 * }
 * if (counter == 0) break;
 * }
 * System.out.println();
 * }
 * <p>
 * private static int[] compareResults(int[] a, int[] b) {
 * if (a.length != b.length) {
 * System.out.println("ERROR");
 * return null;
 * }
 * return getResult(a, b);
 * }
 * <p>
 * private static int[] getResult(int[] a, int[] b) {
 * int[] tmp = new int[a.length];
 * boolean isMatch = false;
 * for (int i = 0; i < a.length; i++) {
 * isMatch = false;
 * for (int j = 0; j < b.length; j++) {
 * if (a[i] == b[j]) {
 * isMatch = true;
 * }
 * }
 * if (isMatch) tmp[i] = a[i];
 * }
 * return tmp;
 * }
 * <p>
 * private static int getCounts(int[] a, int[] b) {
 * int count = 0;
 * for (int i = 0; i < a.length; i++) {
 * for (int j = 0; j < b.length; j++) {
 * if (a[i] == b[j]) {
 * count++;
 * }
 * }
 * }
 * return count;
 * }
 */