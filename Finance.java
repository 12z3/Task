import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Finance {

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("");
        List<List<String>> data = readData(file);

        printFindByPeriod(getPeriod(data, "01.01.2024", "02.09.2024"));
//        printAllInfo(data);
        printTransactions(data);
//        printAllWithdrawal(data);
//        findByType(data, "1000.00");

//        printAllDebit(data);
//        printAllWithdrawal(data);
    }


    /**
     * Израза \"([^\"]*)\"
     * търси за сегменти от текст, които започват и завършват с двойна кавичка,
     * като изключва самите двойни кавички от съвпадението и улавя всичко между тях като група
     * Debit = [9] = "121,21"; Withdrawal = [10] = "23,00"; Date = [11]; Time =
     * [12];
     */

    protected static List<List<String>> readData(File file) {
        Pattern pattern = Pattern.compile("\"([^\"]*)\"");
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.out.println("НЯМА ТАКЪВ ФАЙЛ... ?");
        }
        List<List<String>> result = new ArrayList<>();
        List<String> string = null;


        int cnt = 0;
        if (scanner != null) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (cnt >= 15) {
                    string = new ArrayList<>();
                    Matcher matcher = pattern.matcher(line);
                    while (matcher.find()) string.add(matcher.group());
                    result.add(string);
                }
                cnt++;
            }
        }
        return result;
    }

    protected static Double getDebit(List<List<String>> data, int numOfRow) {
        String str = data.get(numOfRow).get(10).replaceAll("\"", "").trim();
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

        String str = data.get(numOfRow).get(9).replaceAll("\"", "").trim();
        str = str.replace(",", ".");
        return (str.isEmpty()) ? Math.ceil(Double.parseDouble("0.00")) : Math.ceil(Double.parseDouble(str));
    }

    protected static void printAllWithdrawal(List<List<String>> data) {
        System.out.println();
        double withdrawal = 0.0, allWithdrawal = 0.0;
        String transaction = null, date = null, hour = null;

        System.out.println("Баланс: " + (allDebit(data) - allWithdrawal(data) + "BGN"));

        for (int row = 0, cnt = 0; row < data.size(); row++, cnt++) {
            withdrawal = getWithdrawal(data, row);
            allWithdrawal += withdrawal;
            transaction = getTransaction(data, row);
            date = getDate(data, row);
            hour = getHour(data, row);

            if (cnt == 0) {
                spaces(179, (date + hour + transaction));
                System.out.println("Изтеглени:");
            }
            if (withdrawal != 0.0) {
                System.out.printf("%s / %s: - %s", date, hour, transaction);
                spaces(125, (date + hour + transaction));
                System.out.printf("%.2f%n", withdrawal);
            }
        }
        if (allWithdrawal != 0.0) {
            System.out.println();
            spaces(179, (date + hour + transaction));
            System.out.printf("%.2f ", allWithdrawal);
            System.out.println();
        }
    }

    protected static void printAllWithdrawalFromIntervalOfDate(List<List<String>> data, String formDate, String toDate) {
        System.out.println();
        double withdrawal = 0.0, allWithdrawal = 0.0;
        String transaction = null, date = null, hour = null;

        System.out.println("Баланс: " + (allDebit(data) - allWithdrawal(data) + "BGN"));

        Pattern p1 = Pattern.compile(formDate);
        //Matcher m1 = p1.matcher()
        Pattern p2 = Pattern.compile(toDate);


//            if (withdrawal != 0.0) {
//                System.out.printf("%s / %s: - %s", date, hour, transaction);
//                spaces(125, (date + hour + transaction));
//                System.out.printf("%.2f%n", withdrawal);
//            }
//        }
//        if (allWithdrawal != 0.0) {
//            System.out.println();
//            spaces(179, (date + hour + transaction));
//            System.out.printf("%.2f ", allWithdrawal);
//            System.out.println();
//        }
    }

    protected static void printAllDebit(List<List<String>> data) {
        System.out.println();
        double debit = 0.0, allDebit = 0.0;
        String transaction = null, date = null, hour = null;

        System.out.println("Баланс: " + (allDebit(data) - allWithdrawal(data) + "BGN"));

        for (int row = 0, cnt = 0; row < data.size(); row++, cnt++) {
            debit = getDebit(data, row);
            allDebit += debit;
            transaction = getTransaction(data, row);
            date = getDate(data, row);
            hour = getHour(data, row);
            if (cnt == 0) {
                spaces(179, (date + hour + transaction));
                System.out.println("Внесени:");
            }
            if (debit != 0.0) {
                System.out.printf("%s / %s: - %s", date, hour, transaction);
                spaces(125, (date + hour + transaction));
                System.out.printf("%.2f%n", debit);
            }
        }
        if (allDebit != 0.0) {
            System.out.println();
            spaces(179, (date + hour + transaction));
            System.out.printf("%.2f ", allDebit);
            System.out.println();
        }

    }

    protected static String getDate(List<List<String>> data, int numOfRow) {
        return data.get(numOfRow).get(11);
    }

    protected static void getAllDay(List<List<String>> data) {
        //for (List<String> row: data) // ?
    }

    protected static String getHour(List<List<String>> data, int numOfRow) {
        return data.get(numOfRow).get(12);
    }

    protected static String getTransaction(List<List<String>> data, int numOfRow) {
        return data.get(numOfRow).get(2);
    }

//    protected static void findByType(List<List<String>> data, String type) {
//        System.out.println();
//        double debit = 0, withdrawal, allDebit = 0.0, allWithdrawal = 0.0;
//        String transactions = null, hour = null,
//                wordA = null, wordB = null, date = null;
//        int spacesA = 0, spacesB = 0, constA = 0, constB = 0;
//
//        Pattern datePattern = Pattern.compile(type);
//
//        for (int i = 0; i < data.size(); i++) {
//            Matcher dateMatcher = datePattern.matcher(String.valueOf(data.get(i)));
//            if (dateMatcher.find()) {
//                transactions = getTransaction(data, i);
//                debit = getDebit(data, i);
//                allDebit += Math.ceil(debit);
//                withdrawal = getWithdrawal(data, i);
//                allWithdrawal += Math.ceil(withdrawal);
//                date = getDate(data, i);
//                hour = getHour(data, i);
//
//                wordA = (type + ": " + date + " " + transactions + " - " + hour);
//                System.out.printf("%s: %s %s - %s", type, date, transactions, hour);
//
//                constA = 129;
//                spacesA = constA - wordA.length();
//                spaces(constA, wordA);
//                System.out.printf("Изтеглени: %.2f", Math.ceil(debit));
//
//                spaces(18, "Изтеглени " + debit);
//                System.out.printf("Постъпили: %.2f%n", Math.ceil(withdrawal));
//            }
//        }
//    }


    protected static void findByType(List<List<String>> data, String type) {
        System.out.println();
        double debit = 0, withdrawal, allDebit = 0.0, allWithdrawal = 0.0;
        String transactions = null, hour = null, date = null, spacesWordA = null, spacesWordB = null, spacesWordC, spacesWordD;
        int idxA = 0, idxB = 0;

        Pattern typePattern = Pattern.compile(type);

        int maxLength = maxLength(data);
        for (int row = 0; row < data.size(); row++) {
            Matcher typeMatcher = typePattern.matcher(String.valueOf(data.get(row)));
            if (typeMatcher.find()) {
                transactions = getTransaction(data, row);
                debit = getDebit(data, row);
                allDebit += Math.ceil(debit);
                withdrawal = getWithdrawal(data, row);
                allWithdrawal += Math.ceil(withdrawal);
                date = getDate(data, row);
                hour = getHour(data, row);

                System.out.printf("%s: %s %s - %s", type, date, hour, transactions);
                spacesWordA = (type + date + hour + transactions);
                idxA = spacesA(maxLength, spacesWordA, 25);
                System.out.printf("Постъпили: %.2f", Math.ceil(debit));

                spacesWordB = ("Постъпили " + debit);
                idxB = spacesA(-1, spacesWordB, 20);
                System.out.printf("Изтеглени: %.2f%n", Math.ceil(withdrawal));
            }
        }

        spacesWordC = ("Постъпили ");
        spacesA(maxLength + 46, spacesWordC, 6);
        System.out.printf("%.2f", allDebit);

        spacesWordD = ("Изтеглени: " + allDebit);
        spacesA(0, spacesWordD, 31);
        System.out.printf("%.2f%n", allWithdrawal);
    }


    protected static List<List<String>> getPeriod(List<List<String>> data, String fromDate,
                                                  String toDate) {
        List<List<String>> lsRes = new ArrayList<>();
        int cnt = 0;

        if (!validatePeriods(data, fromDate, toDate)) return null;

        Pattern p1 = Pattern.compile(fromDate);
        Pattern p2 = Pattern.compile(toDate);

        LOOP:
        for (List<String> datum : data) {

            Matcher m1 = p1.matcher(String.valueOf(datum));
            Matcher m2 = p2.matcher(String.valueOf(datum));

            if (m1.find() && !m2.find()) {
                lsRes.add(datum);
            } else if (m2.find()) {
                lsRes.add(datum);
            }
        }
        return lsRes;
    }


    protected static boolean validatePeriods(List<List<String>> data, String fromDate, String toDate) {
        boolean flag1 = false, flag2 = false;
        for (List<String> datum : data) {
            String x = datum.get(0).replaceAll("\"", "");
            if (x.equalsIgnoreCase(fromDate)) {
                flag1 = true;
            } else if (x.equalsIgnoreCase(toDate)) {
               flag2 = true;
            }
            if (flag1 && flag2) return true;
        }
        return false;
    }

    protected static void printFindByPeriod(List<List<String>> periodRes) {
        if (periodRes == null) {
            System.out.println("\n404: НЕВАЛИДНИ ДАТИ");
            return;
        }
        double withdrawal = 0.0, allWithdrawal = 0.0, debit = 0, allDebit = 0.0;
        String transactions = null, hour = null, date = null;
        int maxLength = 0;

        for (int i = 0; i < periodRes.size(); i++) {
            date = getDate(periodRes, i);
            hour = getHour(periodRes, i);
            transactions = getTransaction(periodRes, i);
            debit = getDebit(periodRes, i);
            allDebit += debit;
            withdrawal = getWithdrawal(periodRes, i);
            allWithdrawal += withdrawal;

            System.out.printf("%s %s - %s", date, hour, transactions);
            maxLength = maxLength(periodRes);
            int z = String.valueOf(periodRes.get(i).size()).length();
            int cnt = (maxLength - (date.length() + hour.length() + transactions.length() + 4)) + 30;
            spacesB(cnt);
            System.out.printf("Постъпили: %.2f", Math.ceil(debit));
            spacesB(10 - String.valueOf(debit).length());
            System.out.printf("Изтеглени: %.2f%n", Math.ceil(withdrawal));
        }

        assert date != null;
        spacesB((maxLength + date.length() + hour.length() + 4) + 9 + ("Постъпили: ").length() - 2);
        System.out.printf("%.2f ", allDebit);
        spacesB(("Изтеглени: ").length() + 2);
        System.out.printf("%.2f ", allWithdrawal);
        System.out.println();

    }


    protected static void printAllInfo(List<List<String>> data) {
        int cnt = 0;
        for (int i = 0; i < data.size(); i++) {

            System.out.printf("%s: %s - %s;", getDate(data, i), getTransaction(data, i), getHour(data, i));

            double debit = getDebit(data, i);
            String debitWordSpaces = getTransaction(data, i);
            spaces(105, debitWordSpaces);
            System.out.printf("Постъпили: %.2f", debit);

            double withdrawal = getWithdrawal(data, i);
            String withdrawalWordSpaces = "Постъпили:  " + debit;

            spaces(20, withdrawalWordSpaces);
            System.out.printf("Изтеглени: %.2f%n", withdrawal);

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

    protected static int spacesB(int num) {
        int cnt;
        for (cnt = 0; cnt < num; cnt++) {
            System.out.print(" ");
        }
        return cnt;
    }

    static int spacesA(int maxLength, String currentLineLength, int spaces) {
        int cnt = 0;
        int numSpaces = (maxLength - currentLineLength.length()) + spaces;
        for (cnt = 0; cnt < numSpaces; cnt++) {
            System.out.print(" ");

        }
        return maxLength + currentLineLength.length() + spaces + cnt;
    }

    static int maxLength(List<List<String>> list) {
        int maxLength = Integer.MIN_VALUE;
        for (List<String> strings : list) {
            if (strings.get(2).length() > maxLength) maxLength = strings.get(2).length();
        }
        return maxLength;
    }

    static int maxLengthB(List<List<String>> list) {
        int maxLength = Integer.MIN_VALUE, length = 0, a;
        for (int i = 0; i < list.size(); i++) {
            length = 0;
            for (int j = 0; j < list.get(i).size(); j++) {
                a = list.get(i).get(j).length();
                length += a;
            }
            if (length > maxLength) maxLength = length;
        }
        return maxLength;
    }

    static int maxLengthA(List<List<String>> list) {
        int maxLength = Integer.MIN_VALUE;
        for (List<String> strings : list) {
            if (String.valueOf(strings).length() > maxLength) maxLength = strings.size();
        }
        return maxLength;
    }

    protected static <T> void printList(List<T> list) {
        for (T el : list) {
            System.out.println(el);
        }
    }

    protected static void printTransactions(List<List<String>> data) {
        List<String> types = new ArrayList<>(List.of("ЗАПЛАТА", "АВАНС", "ЕВН", "ЕЛЕКТРОХОЛД", "ЕЛ ЕНЕРГИЯ", "VIVACOM", "ПЛАЩАНЕ ПО ЗАЕМ", "ЛИХВА ОВЪРДРАФТ", "ИНТЕРНЕТ", "BREZOVO", "VIBER"));

        for (String type : types) findByType(data, type);
    }
}

