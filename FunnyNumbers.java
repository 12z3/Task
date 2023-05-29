package task;

import java.util.Scanner;

public class FunnyNumbers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int sumA = 0, sumB = 0, count = 0, sum = 0, num;

        System.out.print("Get me four digits number: ");

        String line = scanner.nextLine();
        int size = line.length();

        while (size > 4) {
            System.out.print("Input wright four digits number: ");
            line = scanner.nextLine();
            size = line.length();
        }

        int input = Integer.parseInt(line);
        while (input > 0) {
            num = input % 10;
            sum += num;
            input = input / 10;
            count++;
            if (count == 2) {
                sumA = sum;
                sum = 0;
            } else if (count == 4) {
                sumB = sum;
            }
        }
        if (sumA == sumB) {
            System.out.println("Happy ;)");
        } else System.out.println("  ;(");


        for (int i = 1000; i < 10000; i++) {
            int Num1 = i % 10;                      // 1234 % 10 -> 4;
            int Num2 = (i / 10) % 10;               // 1234 / 10 = 123 % 10 = 3;
            int Num3 = (i / 100) % 10;              // 1234 / 100 = 12 % 10 = 2;
            int Num4 = (i / 1000);                  // 1234 / 1000 = 1;

            if ((Num1 + Num2) == (Num3 + Num4)) System.out.print(i + " ");
        }
    }
}
