package task.TOTO.Projects;

import task.TOTO.TotoPoint;

import java.util.*;

public class TOTO extends TotoPoint {

    // TODO: Трябват: Метод за сравняване на резултатите.
    //                Метод за записване във файл (или файлове в зависимост от деня на залога).

    private List<Integer> result = new ArrayList<>();
    private List<Integer> yourSuppose = new ArrayList<>();

    public TOTO() {

    }

    //TODO: Оправи го, че е меко  казано Интересно.
    public List<Integer> getResult() {
        if (this.result != null) {
            return this.result;
        } else System.out.println("Въведи резултата. Не се Ослушвай!");
        return Collections.singletonList(-1);
    }

    //TODO: Виж какво пише отгоре.
    public List<Integer> getYourSuppose() {
        return this.yourSuppose;
    }

    private List<Integer> getDigitFromInput(String[] input) {
        List<Integer> digits = new ArrayList<>();
        for (int i = 0; i < input.length; i++) {
            digits.add(Integer.parseInt(input[i]));
        }
        return digits;
    }

    public List<Integer> setResult() {

        System.out.print("-> Валидни числа са всички положителни Двуцифрени (12) числа " +
                "от 1 до 49 разделени с запетая и интервал. ', ' \n" +
                "Комбинациите от сорта: (1а,b3, г6  ааа, -98, ЗЯхF, 654, -1) се приемат за невалидни!\n");


        Scanner scanner = new Scanner(System.in);
        System.out.println();
        System.out.printf("%s", "Какъв e резултата от последният тираж: ");
        String[] input = scanner.nextLine().trim().split(", ");
        boolean isIt = digitVerification(input);

        while (!isIt) {
            System.out.print("ЗаПри се Вихъре. " +
                    "Трябва да бъде нещо от сорта: '1, 12, 34, 47, 53, 61'." +
                    "\n" + "Дай пак:");
            input = scanner.nextLine().trim().split(", ");
            if (digitVerification(input)) isIt = true;
        }
        this.result = getDigitFromInput(input);
        return this.result;
    }

    private boolean isNotAString(String[] input) {                       // alskjdlaks, sa - 22, a
        boolean isNotAString = false;                                    // 12, 23, 12, 12, 34, 56
        for (int i = 0; i < input.length; i++) {                         // asd,as, 12, wewewew, -98, d
            if ((input[i].length() <= 2)) {
                if (isADoubleDigits(input[i])) {
                    int el = Integer.parseInt(input[i]);
                    for (int j = 58; j <= 126; j++) {
                        if (el != j) {
                            isNotAString = true;
                            break;
                        }
                    }
                }
            } else return false;
        }
        return isNotAString;
    }

    private boolean isADoubleDigits(String input) {
        boolean isADigit = false;
        int count = 0;

        for (int i = 0; i < input.length(); i++) {
            for (int j = 49; j <= 57; j++) {
                int el = (int) input.charAt(i);
                if (el == j && el > 0) {
                    count++;
                    break;
                }
            }
        }
        return count == input.length();
    }

    private boolean digitVerification(String[] input) {
        return isNotAString(input) && (input.length == 6);
    }

    public void setYourSuppose() {
        List<Integer> variantResult = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        boolean condition = false;

        System.out.print("\nИграем ли?: (y / n) ");
        String answer = scanner.nextLine().trim();

        while (!answer.equalsIgnoreCase("y")
                && !answer.equalsIgnoreCase("n")) {
            System.out.println("Дай ми Коректен отговор: (y / n) ?");
            answer = scanner.nextLine().trim();
        }

        System.out.printf("%s", "Вариантите са три: ");
        List<List<Integer>> variant = getFinalListOfNumbers();
        System.out.print("Избери между 1, 2, 3: ");

        String yourChoice = scanner.nextLine();
        while (!yourChoice.equalsIgnoreCase("1") &&      // -> false == на 1, 2 или 3
                !yourChoice.equalsIgnoreCase("2") &&     // -> true != от 1, 2 или 3
                !yourChoice.equalsIgnoreCase("3")) {     // while търси true;
            System.out.print("Избери между 1, 2 и 3 вариант: ");     // true && true = true; true && false = false;
            yourChoice = scanner.nextLine();
        }

        switch (yourChoice) {
            case "1" -> variantResult = variant.get(0);
            case "2" -> variantResult = variant.get(1);
            case "3" -> variantResult = variant.get(2);
        }
        System.out.println("Избрал си: " + variantResult.toString() + "\n");
        this.yourSuppose =  variantResult;
    }

    public void printToto() {
        Collections.sort(this.result);
        Collections.sort(this.yourSuppose);
        System.out.printf("Резултата от последният тираж е:  %s. \n" +
                "Залогът, който си избрал е: %s ", this.result, this.yourSuppose);
    }
}
