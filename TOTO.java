package task.TOTO.Projects;

import task.TOTO.TotoPoint;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class TOTO extends TotoPoint {

    // TODO: Трябват: Метод за сравняване на резултатите.
    //                Метод за записване във файл (или файлове в зависимост от деня на залога).

    /**
     * Днешна дата:
     * Номер на тираж:
     * Резултат от тиража:
     * Твоят залог:
     */

    private final int TODAY_CIRCULATION = 101;
    private int CIRCULATION = TODAY_CIRCULATION;
    private List<Integer> result = new ArrayList<>();
    private List<Integer> yourSuppose = new ArrayList<>();
    private List<Integer> variantResult = new ArrayList<>();
    private List<List<Integer>> variants = new ArrayList<>();
    String yourVariantChoice = "";

    public TOTO() {
    }

    public void play() throws IOException {
        this.CIRCULATION++;
        setResult();
        setYourSuppose();
        writeResult(this.variants, this.result);
        printToto();
    }

    public List<Integer> getResult() {
        while (this.result == null) {
            System.out.println("Въведи резултата. Не се Ослушвай!");
            this.result = this.setResult();
        }
        return this.result;
    }

    public List<Integer> getYourSuppose() {
        return this.yourSuppose;
    }

    private List<Integer> getDigitFromInput(String[] input) {                 // TODO: Обмисло го все пак.
        List<Integer> digits = new ArrayList<>();
        for (int i = 0; i < input.length; i++) {
            digits.add(Integer.parseInt(input[i]));
        }
        return digits;
    }

    public List<Integer> setResult() {

        System.out.print("-> Валидни числа са всички положителни Двуцифрени (12) числа " +
                "от 1 до 49 разделени с запетая и интервал (', ') - (6, 15, 18, 23, 25, 39). \n" +
                "-> Комбинации от сорта: (1а,b3, г6  ааа, -98, ЗЯхF, 654, -1) се приемат за невалидни!\n");

        Scanner scanner = new Scanner(System.in);
        System.out.println();
        System.out.printf("%s (%d-и) %s", "Какъв e резултата от последният", this.CIRCULATION, "тираж: ");

        String[] input = scanner.nextLine().trim().split(", ");
        boolean isIt = inputVerification(input);

        while (!isIt) {
            System.out.print("ЗаПри се Вихъре. " +
                    "Трябва да бъде нещо от сорта: '1, 12, 34, 47, 53, 61'." +
                    "\n" + "Дай пак: ");
            input = scanner.nextLine().trim().split(", ");
            if (inputVerification(input)) isIt = true;
        }

        this.result = getDigitFromInput(input);
        return this.result;
    }

    private boolean inputVerification(String[] input) {
        return isNotAString(input) && (input.length == 6);
    }

    private boolean isNotAString(String[] input) {                       // alskjdlaks, sa - 22, a
        boolean isNotAString = false;                                    // 12, 23, 13, 15, 34, as
        for (int i = 0; i < input.length; i++) {                         // asd,as, 12, wewewew, -98, d
            if ((input[i].length() <= 2)) {
                if (isADoubleDigits(input[i])) {                         // next: 2022 12 25 18 45
                    int el = Integer.parseInt(input[i]);
                    for (int j = 58; j <= 126; j++) {
                        if (el != j) {                                   // TODO: Виж условието: if (el != j)
                            isNotAString = true;                         //  Идеята е да не минава през целия цикъл -
                            break;                                       //  -> if (el == j) false break;
                        }
                    }
                } else return false;
            } else return false;
        }
        return isNotAString;
    }

    private boolean isADoubleDigits(String input) {                      // 12
        int count = 0;
        for (int i = 0; i < input.length(); i++) {
            for (int j = 49; j <= 57; j++) {
                int el = (int) input.charAt(i);
                if (el == j) {
                    count++;
                    break;
                }
            }
        }
        return count == input.length();
    }

    public void setYourSuppose() {

        Scanner scanner = new Scanner(System.in);

        System.out.print("\nИграем ли?: (y / n) ");
        String answer = scanner.nextLine().trim();

        while (!answer.equalsIgnoreCase("y")
                && !answer.equalsIgnoreCase("n")) {
            System.out.print("Дай ми Коректен отговор: (y / n) ?: ");
            answer = scanner.nextLine().trim();
        }

        System.out.printf("%s", "Вариантите са три: ");
        this.variants = getFinalListOfNumbers();
        for (List<Integer> el : this.variants) {
            Collections.sort(el);
        }
        System.out.print("Избери между 1, 2, 3: ");

        this.yourVariantChoice = scanner.nextLine();
        while (!this.yourVariantChoice.equalsIgnoreCase("1") &&      // -> false == на 1, 2 или 3
                !this.yourVariantChoice.equalsIgnoreCase("2") &&     // -> true != от 1, 2 или 3
                !this.yourVariantChoice.equalsIgnoreCase("3")) {     // while търси true;
            System.out.print("Избери между 1, 2 и 3 вариант: ");     // true && true = true; true && false = false;
            this.yourVariantChoice = scanner.nextLine();
        }

        switch (yourVariantChoice) {
            case "1" -> this.variantResult = this.variants.get(0);
            case "2" -> this.variantResult = this.variants.get(1);
            case "3" -> this.variantResult = this.variants.get(2);
        }
        Collections.sort(this.variantResult);
        System.out.println("Избрал си: " + variantResult.toString() + "\n");
        this.yourSuppose = variantResult;
    }

    public void printToto() {
        Collections.sort(this.result);
        Collections.sort(this.yourSuppose);
        System.out.printf("Резултата от последният тираж е:  %s. \n" +
                "Залогът, който си избрал е вариант: %s %s ", this.result, this.yourVariantChoice, this.yourSuppose);
    }

    private void writeResult(List<List<Integer>> variants, List<Integer> lastResult) {
        Scanner scanner = new Scanner(System.in);
        String path = "";
        boolean choice = false;
        try {
            System.out.print("Да се запазят ли предишните резултати? (y / n): ");

            String thisAnswer = scanner.nextLine().trim();
            while (!thisAnswer.equalsIgnoreCase("y")
                    && !thisAnswer.equalsIgnoreCase("n")) {
                System.out.print("Май - май продължаваме с магариите...?: (y / n)?: ");
                thisAnswer = scanner.nextLine().trim();
            }
            if (thisAnswer.equalsIgnoreCase("y")) choice = true;

            BufferedWriter writer =
                    new BufferedWriter(new java.io.FileWriter("newTotoResult", choice));

            File file = new File("newTotoResult");
            if (file.exists()) path = file.getAbsolutePath();

            // writer.write(String.valueOf(timeAndData()));
            writer.newLine();
            writer.write("Вариантите са три: \n");
            for (List<Integer> el : variants) {
                writer.write(el.toString() + "\n");
            }
            writer.append("----------------------------------------------" + "\n");
            writer.write("Избрал си вариант " + this.yourVariantChoice + ": " +
                    this.yourSuppose.toString() + "\n"
                    + "Последен тираж: " + lastResult.toString() + "\n");
            writer.append("----------------------------------------------" + "\n");
//            writer.newLine();

            LocalDateTime resultLDT = getLocalDateTime();
            writer.write(
                    Objects.requireNonNull(whenTotoTimeIs(resultLDT)));

            writer.newLine();
            writer.close();
        } catch (IOException exp) {
            exp.printStackTrace();
        }
        if (choice) System.out.println("\nРезултатът е записан в: " + path);
    }

    private static LocalDateTime getLocalDateTime() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Кога е следващият Тираж? " +
                "\nВъведи (година месец ден час минути) разделени с интервал (2022 12 25 18 45): ");

        //TODO: Трябва да валидираш въвеждането на датата.
        String[] dataTimeFormatAnswer = scanner.nextLine()
                .trim()
                .split(" ");
        int year = Integer.parseInt(dataTimeFormatAnswer[0]);
        int month = Integer.parseInt(dataTimeFormatAnswer[1]);
        int dayOfMonth = Integer.parseInt(dataTimeFormatAnswer[2]);
        int hour = Integer.parseInt(dataTimeFormatAnswer[3]);
        int minute = Integer.parseInt(dataTimeFormatAnswer[4]);

        return LocalDateTime.of(year, month, dayOfMonth, hour, minute);
    }

    public static String whenTotoTimeIs(LocalDateTime timeOfToto) {
        DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("dd MMM yyyy, E - a c 'ден:' HH:hh:ss ч. ");

        LocalDateTime now = LocalDateTime.now();

        int count = 0;
        int dDays = timeOfToto.getDayOfMonth() - now.getDayOfMonth();

        while (dDays < 0) {
            System.out.println("... Я си оправи времената");
            dDays = getLocalDateTime().getDayOfMonth() - now.getDayOfMonth();
        }

        while (dDays != 0) {
            dDays--;
            count++;
        }
        long hour1 = now.getHour();
        long hour2 = timeOfToto.getHour();
        long dHours = Math.abs(hour1 - hour2);

        long min1 = now.getMinute();
        long min2 = timeOfToto.getMinute();
        long dMins = Math.abs(min1 - min2);

        // if (dDays == 1 && dHours == 0 && dMins == 0) count = 0;

        //System.out.println("The Day is: " + timeOfToto.format(formatDate));

        //TODO: Оправи името на деня в файла да бъде на Кирилица.
//        LocalDateTime day = null;
//        switch (timeOfToto.getDayOfWeek()){
//            case MONDAY -> day.;
//        }

        int dDay = count;
        String sDay = " дни ";
        if (dDay == 1) sDay = " ден ";

        return ("Днес е: " + now.format(formatDate) + "\n"
                + "До следващият тираж остават: "
                + count + sDay + "(денят е: " + timeOfToto.getDayOfWeek() + ") - "
                + (dHours + " часа " + "и "
                + (dMins + " минути."))
                + "\n");
    }
}
