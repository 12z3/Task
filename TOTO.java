package task.TOTO.Projects;

import java.util.*;

public class TOTO {

    // TODO: Трябват: Метод за сравняване на резултатите.
    //                Метод за генериране на залог.
    //                Метод за записване във файл (или файлове в зависимост от деня на залога).
    //

    public static void main(String[] args) {
        TOTO toto = new TOTO();

        toto.setResult();
        toto.setYourSuppose();
        toto.printToto();

    }

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

    public void setResult() {

        System.out.print("Валидни числа са всички положителни Двуцифрени (12) числа " +
                "от 1 до 49 разделени с ', ' \n" +
                "Комбинациите от сорта: (1а,а1,  ааа, -98, ЗЯхF, 654, -1) се приемат за невалидни!\n");

        Scanner scanner = new Scanner(System.in);
        System.out.printf("%s", "Какъв резултата от последният тираж: ");
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
    }

    private boolean isNotAString(String[] input) {                       // alskjdlaks, sa - 22, a
        boolean isNotAString = false;                                    // 12, 23, 12, 12, 34, 56
        for (int i = 0; i < input.length; i++) {                         // asd,as, 12, wewewew, -98, d
            if ((input[i].length() < 2)) {
                if (isADoubleDigits(input[i])) {
                    int el = Integer.parseInt(input[i]);
                    for (int j = 58; j <= 126; j++) {
                        if (el != j) isNotAString = true;
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
        Scanner scanner = new Scanner(System.in);
        System.out.printf("%s", "Какъв е твоят залог: ");
        String[] input = scanner.nextLine().trim().split(", ");
        boolean isIt = digitVerification(input);

        while (!isIt) {
            System.out.print(
                    "Погледни Каква Чудесия въвеждаш. " +
                            "Трябва да бъде нещо от сорта: '1, 12, 34, 47, 53, 61''." +
                    "\n" + "Дай пак:");
            input = scanner.nextLine().trim().split(", ");
            if (digitVerification(input)) isIt = true;

        }
        this.yourSuppose = getDigitFromInput(input);
    }

    public void printToto() {
        System.out.printf("Last Result is %s. Your suppose are %s ", this.result, this.yourSuppose);
    }
}
