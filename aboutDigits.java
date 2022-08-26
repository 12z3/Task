package Task;

public class aboutDigits {
    /**
     * @Idea: Как се генерира число от друго число.
     */

    public static void main(String[] args) {
        int number = 12345;
        int x = number, digit = 0, digit1 = 0;

        while (number > 0) {
            digit = (digit * 10) + number % 10;
            number = number / 10;
        }
        System.out.println(x + " -> " + digit);

        String[] numbers = String.valueOf(number).split("");
        System.out.print("numbers[] = ");

        for (String el : numbers) System.out.print(el);
        System.out.println();

        for (String el : numbers) {
            digit1 = (digit1 * 10) + Integer.parseInt(el);
        }
        System.out.println("digit = " + digit1);
    }
}
