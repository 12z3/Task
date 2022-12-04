package task.Time;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Scanner;

public class TimeWorks {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Кога е следващият Тираж? " +
                "\nВъведи (година месец ден час минути) разделени с интервал: ");         //  2022 12 4 18 45

        String[] dataTimeFormatAnswer = scanner.nextLine()
                .trim()
                .split(" ");
        int year = Integer.parseInt(dataTimeFormatAnswer[0]);
        int month = Integer.parseInt(dataTimeFormatAnswer[1]);
        int dayOfMonth = Integer.parseInt(dataTimeFormatAnswer[2]);
        int hour = Integer.parseInt(dataTimeFormatAnswer[3]);
        int minute = Integer.parseInt(dataTimeFormatAnswer[4]);

//        LocalDateTime ldt2 = LocalDateTime.of(year, month, dayOfMonth, hour, minute);
//        int ld2 = ldt2.getDayOfMonth();
//        int lt2 = ldt2.getHour();
//
//        LocalDateTime ldt1 = LocalDateTime.now();
//        int ld1 = ldt1.getDayOfMonth();
//        int lt1 = ldt1.getHour();
//
//        // TODO: Не върши работа тази магария:
//        System.out.println("between days: " + Math.abs((ld2 - ld1)));
//        System.out.println("between hours: " + Math.abs(lt2 - lt1));


    }
}
