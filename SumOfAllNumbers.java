package task;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SumOfAllNumbers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Integer> number = new ArrayList<>(List.of(0, 8, 8, 6, 3, 8, 3, 5, 4, 3));
        //List<Integer> number = new ArrayList<>
               // (List.of(0, 8, 8, 6, 3, 8, 3, 5, 4, 3, 3, 1222, 345));

        int sumOfAllDigits = getSum(fillList());
        System.out.printf("The finally result is: %d", sumOfAllDigits);
    }

    private static List<Integer> fillList(){
        Scanner scanner = new Scanner(System.in);
        List<Integer> list = new ArrayList<>();

        System.out.print("Input sequences of digits: ");
        String[] data = scanner.nextLine().split("");
        for (String datum : data) list.add(Integer.parseInt(datum));
        return list;
    }

    private static int getSum(List<Integer> number) {
        int sumA = 0, sumB, sum, a = 0;

        for (Integer el : number) System.out.printf("%d ", el);
        for (Integer integer : number) sumA += integer;            // 123
        System.out.printf("\nThe Sum of these all numbers is: %d%n", sumA);

        do {
            sumB = 0;
            while (sumA > 0) {
                sum = sumA;
                a = sumA % 10;
                sumB += a;
                sumA /= 10;
                if (sumA != 0) System.out.printf("%d -> %d + %d = %d%n", sum, a, sumA, a + sumA);
            }
            sumA = sumB;
        } while (String.valueOf(sumB).length() != 1);
        return (sumB == 0) ? -1 : sumB;
    }
}
