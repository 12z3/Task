import java.io.File;
import java.util.List;

public class prnTest extends Finance {
    public static void main(String[] args) {
        File file = new File("/Users/blagojnikolov/Documents/VSCode/reportDSK3.csv");
        List<List<String>> data = readData(file);

        //printAllInfo(data);


        print(data);
    }

    protected static void print(List<List<String>> data) {
        int maxLength = maxLength(data), spacesNum, stringLength, max = -1;
        String date, hour, transactions;
        double debit, withdrawal, allDebit = 0, allWithdrawal = 0;

        for (int i = 0; i < data.size(); i++) {
            //System.out.println(String.valueOf(data.get(i)).length());
            date = getDate(data, i);
            hour = getHour(data, i);
            transactions = getTransaction(data, i);
            debit = getDebit(data, i);
            allDebit += debit;
            withdrawal = getWithdrawal(data, i);
            allWithdrawal += withdrawal;

            System.out.printf("%s - %s: %s", date, hour, transactions);
            int x = (String.valueOf(data.get(i))).length();
            int y = maxLength;
            stringLength = (date.length() + hour.length() + transactions.length());
            max = Math.max(stringLength, max);
            String z = String.valueOf(data.get(i));
            //spacesNum = maxLength - (date.length() + hour.length() + transactions.length());
            spacesNum = 130 - stringLength;
            spaces(spacesNum);
            System.out.printf("Постъпили: %.2f", debit);
            spacesB(10 - String.valueOf(debit).length());
            System.out.printf("Изтеглени: %.2f%n", withdrawal);

        }

        String xyz = "\"01.03.2024\" - \"19:02\": \"ТРАНСФЕР МЕЖДУ СВОИ СМЕТКИ\"    " +
                "                                                            " +
                "                   Постъпили: ";
        spaces(xyz.length());
        //spaces(max + "Постъпили: ".length() + 16);
        System.out.printf("%.2f", allDebit);
        spaces(14);
        System.out.printf("%.2f%n", allWithdrawal);
    }


    protected static int maxLength(List<List<String>> data) {
        int maxLength = Integer.MIN_VALUE;
        for (List<String> datum : data) {
            int x = ((String.valueOf(datum).length()));
            if (x > maxLength) maxLength = x;
        }
        return maxLength;
    }

    protected static void spaces(int spacesNum) {
        for (int i = 0; i < spacesNum; i++) {
            System.out.print(" ");
        }
    }
}
