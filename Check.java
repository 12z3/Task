package task;

import java.util.Scanner;

public class Check {
    public static void main(String[] args) {
        String answer = "yes";
        System.out.println(checking(answer));
    }

    private static String checking(String answer) {
        Scanner scanner = new Scanner(System.in);
        String str = answer;

        while (!str.equalsIgnoreCase("y")
                && !str.equalsIgnoreCase("n")) {
            System.out.println("ERROR");
            str = scanner.nextLine();
        }
        return str;
    }
}
