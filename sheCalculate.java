package task;

import java.util.*;

public class sheCalculate {
    private int count;
    private int successCount;
    private int failureCount;
    private int randomNumber1;
    private int randomNumber2;
    private int allTestCount;
    private int yourTestCount;

    public void summation() {
        Scanner scanner = new Scanner(System.in);
        this.yourTestCount = countTestValidation();

        while (this.count < this.yourTestCount) {
            Random rand = new Random();
            this.randomNumber1 = rand.nextInt(1, 11);
            this.randomNumber2 = rand.nextInt(1, 11);
            int rightAnswer = (this.randomNumber1 + this.randomNumber2);

            System.out.println("........................");
            System.out.println((this.count + 1) + "-и" + " въпрос: ");
            System.out.println("Колко е " + this.randomNumber1 + " + " + this.randomNumber2 + "? ");

            printTestResults();

            System.out.print("Дай ми отговор - ЧИСЛО: ");
            String input = scanner.nextLine();

            int answer = isItADigit(input);
            if (answer == rightAnswer) {
                this.successCount++;
                System.out.println("Позна. Брой верни отговори: " + this.successCount);
            } else {
                this.failureCount++;
                System.out.println("... Лошо гошо. Брой грешни отговори до тук: " + this.failureCount);
            }
            this.count++;
        }

        System.out.println("..................................");
        System.out.println("Брой Верни отговори " + this.successCount + " от " + this.allTestCount);
        System.out.println("Брой Грешни отговори " + this.failureCount + " от " + this.allTestCount);
        double result = ((double) (this.successCount * 100) / this.allTestCount);
        System.out.print("Резултатът ти е: " + Math.ceil(result) + "%");
    }

    public void multiplication() {
        Scanner scanner = new Scanner(System.in);
        this.yourTestCount = countTestValidation();

        while (this.count < this.yourTestCount) {
            Random rand = new Random();
            this.randomNumber1 = rand.nextInt(3, 10);
            this.randomNumber2 = rand.nextInt(3, 10);
            int rightAnswer = (this.randomNumber1 * this.randomNumber2);

            System.out.println("........................");
            System.out.println((this.count + 1) + "-и" + " въпрос: ");
            System.out.println("Колко е " + this.randomNumber1 + " * " + this.randomNumber2 + "? ");

            printTestResults();

            System.out.print("Дай ми отговор - ЧИСЛО: ");
            String input = scanner.nextLine();

            int answer = isItADigit(input);
            if (answer == rightAnswer) {
                this.successCount++;
                System.out.println("Позна. Брой верни отговори: " + this.successCount);
            } else {
                this.failureCount++;
                System.out.println("... Лошо гошо. Брой грешни отговори до тук: " + this.failureCount);
            }
            this.count++;
        }

        System.out.println("..................................");
        System.out.println("Брой Верни отговори " + this.successCount + " от " + this.allTestCount);
        System.out.println("Брой Грешни отговори " + this.failureCount + " от " + this.allTestCount);
        double result = ((double) (this.successCount * 100) / this.allTestCount);
        System.out.print("Резултатът ти е: " + Math.ceil(result) + "%");
    }

    public static String choose() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("С какво ще се Мъчим днес? ;) " + "\nУмножение или Събиране?:");
        System.out.print("\nИзбери: M (за Умножение) или S (за Събиране): ");

        String result = scanner.nextLine();
        while (!result.equalsIgnoreCase("M")
                && (!result.equalsIgnoreCase("S"))) {
            System.out.print("Ко Праим... ? M или S: ");
            result = scanner.nextLine();
        }
        return result;
    }

    private int countTestValidation() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Колко опита искаш?: ");
        String input = scanner.nextLine();

        this.allTestCount = isItADigit(input);
        while (this.allTestCount < 3) {
            System.out.print("... ;) Хайде да не ХиТрУвАмЕ - Имаш не по-малко от ТРИ задачки... Дай пак: ");
            this.allTestCount = scanner.nextInt();
        }
        return this.allTestCount;
    }

    private int isItADigit(String s) {
        Scanner scanner = new Scanner(System.in);

        while ( s.length() == 0 || !Character.isDigit(s.charAt(0))) {
            System.out.print("... ;) Хайде да не ХиТрУвАмЕ - Въведи ми число, не Буква ;) ... ");
            s = scanner.nextLine();
        }
        return Integer.parseInt(s);
    }

    private void printTestResults() {
        Random rnd = new Random();
        List<Integer> chars = new ArrayList<>(List.of((this.randomNumber1 * this.randomNumber2),
                (this.randomNumber1 / this.randomNumber2), (this.randomNumber2) * 2,
                (this.randomNumber1 + this.randomNumber2)));

        for (int indexes = 0; indexes < chars.size(); indexes++) {
            int index = rnd.nextInt(0, chars.size());
            swap(chars, indexes, index);
        }

        System.out.println("A: " + chars.get(0));
        System.out.println("B: " + chars.get(1));
        System.out.println("C: " + chars.get(2));
        System.out.println("D: " + chars.get(3));
    }

    private void swap(List<Integer> list, int pos1, int pos2) {
        int tmp = list.get(pos1);
        list.set(pos1, list.get(pos2));
        list.set(pos2, tmp);

    }

    public static void main(String[] args) {
        sheCalculate test = new sheCalculate();
        String yourChoose = sheCalculate.choose();

        switch (yourChoose) {
            case ("s") -> {
                test.summation();
            }
            case ("m") -> {
                test.multiplication();
            }
            default -> System.out.println("Кво Праим... ?");
        }
    }
}
