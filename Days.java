package training;

import java.util.Scanner;

public class Days {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        /**
         В зависимост от въведените ден и месец
         пресмята денят след 5 дни.
         */

        System.out.print("Input Day: ");
        int day = Integer.parseInt(scan.nextLine());
        while (day > 31) {
            System.out.print("Day must be less then 31, try again: ");
            day = Integer.parseInt(scan.nextLine());
        }

        System.out.print("Input Month: ");
        int month = Integer.parseInt(scan.nextLine());
        while (month > 12) {
            System.out.print("Month must be less than 12, try again: ");
            month = Integer.parseInt(scan.nextLine());
        }

        // Месеци = 1,3,5,7,9,11
        if (!((month % 2) == 0)) {
            day = day + 5;
            if (day > 31) {
                System.out.println(day - 31 + "." + (month + 1) + ".2021");
            } else System.out.println(day + "." + month + ".2021");
        }

        // Месеци = 4,6,8,10
        if ((month % 2) == 0 && !(month == 2) && !(month == 12)) {
            System.out.print("Day will be: ");
            day = day + 5;
            if (day > 30) {
                System.out.println(day - 30 + "." + (month + 1) + ".2021");
            } else System.out.println(day + "." + month + ".2021");
        }

        // Февруари
        if ((month == 2)) {
            while (day > 28) {
                System.out.print("Days of February must be less than 28: ");
                day = Integer.parseInt(scan.nextLine());
            }
            day = day + 5;
            if (day >= 28) {
                System.out.print("O'k. Day will be: ");
                System.out.println(day - 28 + "." + (month + 1) + ".2021");
            } else System.out.println(day + "." + month + ".2021");
        }

        // Декемвмри
        if ((month == 12)) {
            System.out.print("Day will be: ");
            day = day + 5;
            if (day > 31) {
                System.out.println(day - 31 + "." + "1" + ".2022");
            } else System.out.println(day + "." + month + ".2021");
        }
    }
}
