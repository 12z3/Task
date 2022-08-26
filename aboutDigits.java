package Task;

public class aboutDigits {
    public static void main(String[] args) {
        int number = 12345;
        String[] numbers = String.valueOf(number).split("");
        int x = number;
        int digit = 0;
        int digit1 = 0;

        while ( number > 0){
            digit = (digit * 10) + number % 10;
            number = number/10;
        }
        System.out.println(x + " -> " + digit);
        System.out.print("numbers[] = ");

        for (String el: numbers) System.out.print(el);
        System.out.println();
        for (String el: numbers){
            digit1 = (digit1 * 10) + Integer.parseInt(el);
        }
        System.out.println("digit = " + digit1);
    }
}
