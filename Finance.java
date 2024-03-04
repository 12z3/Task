import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Finance {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("/Users/blagojnikolov/Documents/VSCode/reportDSK3.csv");
        List<List<String>> data = readData(file);

        // printAllInfo(data);
        // findByType(data, "02.01.2024");
        findByType(data, "ЗАПЛАТА");
        findByType(data, "АВАНС");
        findByType(data, "ЕВН");
        findByType(data, "ЕЛЕКТРОХОЛД");
        findByType(data, "ЕЛ ЕНЕРГИЯ");
        findByType(data, "VIVACOM");
        findByType(data, "ПЛАЩАНЕ ПО ЗАЕМ");
        findByType(data, "ЛИХВА ОВЪРДРАФТ");
        findByType(data, "ИНТЕРНЕТ");
        findByType(data, "BREZOVO");
        findByType(data, "SMS/VIBER");
        findByType(data, "SMS/VIBER");

    }

    /*
     * matcher.find() се използва за намиране на всички съвпадения, и за
     * всяко съвпадение,
     * извлеченият стринг (без кавичките) се добавя в масива с
     * words.add(matcher.group(1)).
     *
     * List<String>,съдържа всички думи от входния стринг, които бяха в кавички,
     * и можете да се отпечата или да обработи този масив по желание.
     *
     * Debit = [9] = "121,21"; Withdrawal = [10] = "23,00"; Date = [11]; Time =
     * [12];
     */

    protected static List<List<String>> readData(File file) throws FileNotFoundException {
        Pattern pattern = Pattern.compile("\"([^\"]*)\"");
        Scanner scanner = new Scanner(file);
        List<List<String>> result = new ArrayList<>();
        List<String> string = null;

        int cnt = 0;
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (cnt >= 15) {
                string = new ArrayList<>();
                Matcher matcher = pattern.matcher(line);
                while (matcher.find())
                    string.add(matcher.group());
                result.add(string);
            }
            cnt++;
        }
        return result;
    }

    protected static Double getDebit(List<List<String>> data, int numOfRow) {
        String str = data.get(numOfRow).get(9).replaceAll("\"", "").trim();
        str = str.replace(",", ".");
        return (str.isEmpty()) ? Math.ceil(Double.parseDouble("0.00")) : Math.ceil(Double.parseDouble(str));
    }

    protected static double allDebit(List<List<String>> data) {
        double allDebit = 0.0;
        for (int i = 0; i < data.size(); i++) {
            allDebit += getDebit(data, i);
        }
        return allDebit;
    }

    protected static double allWithdrawal(List<List<String>> data) {
        double allWithdrawal = 0.0;
        for (int i = 0; i < data.size(); i++) {
            allWithdrawal += getWithdrawal(data, i);
        }
        return allWithdrawal;
    }

    protected static Double getWithdrawal(List<List<String>> data, int numOfRow) {

        String str = data.get(numOfRow).get(10).replaceAll("\"", "").trim();
        str = str.replace(",", ".");
        return (str.isEmpty()) ? Math.ceil(Double.parseDouble("0.00")) : Math.ceil(Double.parseDouble(str));
    }

    protected static void printAllWithdrawal(List<List<String>> data) {
        System.out.println();
        double withdrawal = 0.0, allWithdrawal = 0.0;
        String transaction = null, date = null, hour = null;
        for (int i = 0, cnt = 0; i < data.size(); i++, cnt++) {
            withdrawal = getWithdrawal(data, i);
            allWithdrawal += withdrawal;
            transaction = getTransaction(data, i);
            date = getDate(data, i);
            hour = getHour(data, i);
            if (cnt == 0) {
                spaces(179, (date + hour + transaction));
                System.out.println("Изтеглени:");
            }
            System.out.printf("%s / %s: - %s", date, hour, transaction);
            spaces(125, (date + hour + transaction));
            System.out.printf("%.2f%n", withdrawal);
        }
        System.out.println();
        spaces(178, (date + hour + transaction));
        System.out.printf("%.2f ", allWithdrawal);
    }

    protected static void printAllDebit(List<List<String>> data) {
        System.out.println();
        double debit = 0.0, allDebit = 0.0;
        String transaction = null, date = null, hour = null;
        for (int i = 0, cnt = 0; i < data.size(); i++, cnt++) {
            debit = getDebit(data, i);
            allDebit += debit;
            transaction = getTransaction(data, i);
            date = getDate(data, i);
            hour = getHour(data, i);
            if (cnt == 0) {
                spaces(179, (date + hour + transaction));
                System.out.println("Внесени:");
            }
            System.out.printf("%s / %s: - %s", date, hour, transaction);
            spaces(125, (date + hour + transaction));
            System.out.printf("%.2f%n", debit);
        }
        System.out.println();
        spaces(178, (date + hour + transaction));
        System.out.printf("%.2f ", allDebit);
    }

    protected static String getDate(List<List<String>> data, int numOfRow) {
        return data.get(numOfRow).get(11);
    }

    protected static String getHour(List<List<String>> data, int numOfRow) {
        return data.get(numOfRow).get(12);
    }

    protected static String getTransaction(List<List<String>> data, int numOfRow) {
        return data.get(numOfRow).get(2);
    }

    protected static void findByType(List<List<String>> data, String type) {
        System.out.println();
        double debit = 0, withdrawal, allDebit = 0.0, allWithdrawal = 0.0;
        String transactions = null, hour = null,
                wordA = null, wordB = null, date = null;
        int spacesA = 0, spacesB = 0, constA = 0, constB = 0;

        Pattern datePattern = Pattern.compile(type);

        for (int i = 0; i < data.size(); i++) {
            Matcher dateMatcher = datePattern.matcher(String.valueOf(data.get(i)));
            if (dateMatcher.find()) {
                transactions = getTransaction(data, i);
                debit = getDebit(data, i);
                allDebit += Math.ceil(debit);
                withdrawal = getWithdrawal(data, i);
                allWithdrawal += Math.ceil(withdrawal);
                date = getDate(data, i);
                hour = getHour(data, i);

                wordA = (type + ": " + date + " " + transactions + " - " + hour);
                System.out.printf("%s: %s %s - %s", type, date, transactions, hour);

                constA = 129;
                spacesA = constA - wordA.length();
                spaces(constA, wordA);
                System.out.printf("Изтеглени: %.2f", Math.ceil(debit));

                spaces(18, "Изтеглени " + debit);
                System.out.printf("Постъпили: %.2f%n", Math.ceil(withdrawal));
            }
        }

        spaces(-spacesA - 17, transactions + " " + hour + type + "Изтеглени: ");
        System.out.printf("%.2f", allDebit);

        constB = constA - 99;
        wordB = ("Изтеглени: " + allDebit);
        spaces(constB, wordB);
        System.out.printf("%.2f%n", allWithdrawal);
    }

    protected static void printAllInfo(List<List<String>> data) {
        int cnt = 0;
        for (int i = 0; i < data.size(); i++) {

            System.out.printf("%s: %s - %s;",
                    getDate(data, i), getTransaction(data, i), getHour(data, i));

            double debit = getDebit(data, i);
            String debitWordSpaces = getTransaction(data, i);
            spaces(105, debitWordSpaces);
            System.out.printf("Изтеглени: %.2f", debit);

            double withdrawal = getWithdrawal(data, i);
            String withdrawalWordSpaces = "Изтеглени:  " + debit;

            spaces(20, withdrawalWordSpaces);
            System.out.printf("Постъпили: %.2f%n", withdrawal);

            if (cnt == 10) {
                System.out.println();
                cnt = 0;
            }
            cnt++;
        }
        double allDebit = allDebit(data);
        spaces(131, " ");
        System.out.print("----------------");
        spaces(5, " ");
        System.out.println("----------------");

        spaces(142, " ");
        System.out.printf("%.2f", allDebit);
        spaces(18, String.valueOf(allDebit));
        System.out.printf("%.2f%n", allWithdrawal(data));
    }

    protected static void spaces(int num, String word) {
        int spaceCount = Math.abs(num - word.length());

        for (int i = 0; i < spaceCount; i++) {
            System.out.print(" ");
        }
    }

    protected static <T> void printList(List<T> list) {
        for (T el : list) {
            System.out.println(el);
        }
    }
}
