import java.io.File;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class findByDate extends Finance {
    public static void main(String[] args) {
        File file = new File("/Users/blagojnikolov/Documents/VSCode/reportDSK3.csv");
        List<List<String>> data = readData(file);

        //printAllInfo(data);

        List<List<String>> res = getPeriod(data, "15.02.2024", "15.01.2024");
        printInfoByPeriod(data, res);

        findByType(data, "ЕЛ ");

    }

    protected static List<List<String>> getPeriodA(List<List<String>> data, String fromDate,
                                                   String toDate) {
        List<List<String>> lsRes = new ArrayList<>();
        HashMap<Integer, List<String>> tmp = new HashMap<>();
        Pattern p1 = Pattern.compile(fromDate);
        Pattern p2 = Pattern.compile(toDate);


        for (int i = 0; i < data.size(); i++) {
            String date = getDate(data, i).replaceAll("\"", "");
            if (date.equalsIgnoreCase(fromDate)) {
                lsRes.add(data.get(i));
            }

            if (date.equalsIgnoreCase(toDate)) {
                lsRes.add(data.get(i));
            }
        }

        return lsRes;
    }

    protected static List<List<String>> getPeriod(List<List<String>> data, String fromDate,
                                                  String toDate) {
        List<List<String>> periodRes = new ArrayList<>();
        int idx1 = 0, idx2 = 0;
        boolean flag = false;

        if (!validatePeriods(data, fromDate, toDate)){
            System.out.println();
            System.out.print(fromDate + " - " + toDate);
            return null;
        }
        Pattern p1 = Pattern.compile(fromDate);
        Pattern p2 = Pattern.compile(toDate);

        for (int i = 0; i < data.size(); i++) {

            // Matcher m1 = p1.matcher(String.valueOf(data.get(i)).replaceAll("\"", ""));
            // - намира не само първото, но и второто и третото съвпадение;
            // "18.01.2024","18.01.2024","516849xxxxxx5165  ТЕГЛЕНЕ НА ATM     -> 17.01.2024 <-    16:18<br/>Авт. код:
            // B61841<br/>Номер на у-во: AFIB3322","BGR","","КАРТОВА ОПЕРАЦИЯ","","","","400,00","","18.01.2024","17:01"
            // е валиден отговор.

            Matcher m1 = p1.matcher((data.get(i).get(0)).replaceAll("\"", ""));
            Matcher m2 = p2.matcher((data.get(i).get(0)).replaceAll("\"", ""));

            if (m1.find() && !flag) {
                idx1 = i;
                flag = true;
            }
            if (m2.find()) {
                idx2 = i;
            }
        }

        for (int j = idx1; j <= idx2; j++) {
            periodRes.add(data.get(j));
        }
        return periodRes;
    }


    protected static boolean validatePeriodsA(List<List<String>> data, String fromDate, String toDate) {
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

    protected static boolean validatePeriods(List<List<String>> data, String fromDate, String toDate) {
        boolean find1 = false, find2 = false;
        int idx1 = -1, idx2 = -1;

        if (data.isEmpty()) return false;

        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).toString().contains(fromDate) && !find1) {
                idx1 = i;
                find1 = true;
            }
            if (data.get(i).toString().contains(toDate)) {
                idx2 = i;
                find2 = true;
            }
        }

        if (idx2 < idx1) return false;
        return find1 && find2;
    }

    protected static void printInfoByPeriod(List<List<String>> data, List<List<String>> periodRes) {
        double withdrawal, allWithdrawal = 0.0, debit, allDebit = 0.0;
        String transactions, hour = null, date = null;
        int maxLength = 0;
        StringBuilder stb;


        if (periodRes == null) {
            System.out.println("\n404: НЕВАЛИДНИ ДАТИ" + "\n" +
                    "Диапазаноа е от: " + getDate(data, 0) + " до: " + getDate(data, data.size() - 1));
            return;
        } else System.out.println();

        for (int i = 0; i < periodRes.size(); i++) {
            stb = new StringBuilder();
            date = getDate(periodRes, i);
            hour = getHour(periodRes, i);
            transactions = getTransaction(periodRes, i);
            debit = getDebit(periodRes, i);
            allDebit += debit;
            withdrawal = getWithdrawal(periodRes, i);
            allWithdrawal += withdrawal;
            stb.append(date).append(" ").append(hour).append(" - ").append(transactions);

            System.out.printf("%s %s - %s", date, hour, transactions);
            maxLength = maxLength(periodRes);

            int cnt = (maxLength - (date.length() + hour.length() + transactions.length() + 4)) + 30;
            spacesB(cnt);

            System.out.printf("Постъпили: %.2f", Math.ceil(debit));
            spacesB(10  - String.valueOf(debit).length());
            System.out.printf("Изтеглени: %.2f%n", Math.ceil(withdrawal));
        }

        // -> AssertionError
        assert hour != null;
        spacesB(maxLength + date.length() + hour.length() + 4 + "Постъпили: ".length() + 7);
        System.out.print(allDebit);

        spacesB(String.valueOf(allDebit).length() + 8);
        System.out.print(allWithdrawal);
    }
}
