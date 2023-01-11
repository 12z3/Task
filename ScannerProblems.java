package task;

import java.util.Scanner;

public class ScannerProblems {

    /**
     scanner.nextInt(); ще върне като резултат числото след натискане на "Enter".
     scanner.nextLine(); ще върне целия текстов ред намиращ се от началото на реда до "Enter", след натискане на "Enter".

     Комбинацията: scanner.nextInt() последвана от scanner.nextLine() е по-особенна. Няма да прочете
     текста като вход от следващият ред.

     Проблемът е, че "Enter-a" след изпълнението на "scanner.nextInt()" остава в буфера на scanner-a
     и когато трябва да прочете и върне текста от новият ред до "Enter" той вижда този "Enter",
     който вече е в буфера и пропуска четенето.

     Решение:
     1. Вместо answer = scanner.nextInt() -> answer = Integer.parseInt(scanner.nextLine());
     2. След scanner.nextInt() се вмъква scanner.nextLine(); което зачиства буфера на scanner - a.
        В тази последователност:
        2.1 answer = scanner.nextInt()
        2.2 scanner.nextLine();
        2.3 String c = scanner.nextLine();
     */


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("hi ");
        int answer = 0;

        //answer = scanner.nextInt();        // scanner.nextInt() -> Integer.parseInt(scanner.nextLine());
        answer = Integer.parseInt(scanner.nextLine());

        String a = scanner.nextLine();
        //String c = scanner.nextLine();
        // scanner.nextLine();              <- Прави нешата интересни ;)
        String b  = scanner.nextLine();

        System.out.println("a = " + a + " b = " + b);

        /**
         * 23
         * сс
         * a =  b = сс
         */
    }
}
