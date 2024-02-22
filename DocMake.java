import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DocMake {
    public static void main(String[] args) throws FileNotFoundException {
        // "Плащане на сметка","БЛАГОЙ НИКОЛОВ НИКОЛОВ ","6607342","ЕВН ЕЛ. ЕНЕРГИЯ -
        // ЕЛ.КАНАЛИ ",
        // "BG91STSA93000004594021","185,39 BGN","15.12.2023 г. 4:58:18 15.12.2023 г.
        // 4:58:34","15.12.2023 г. 4:58:18",
        // "Операцията е изпълнена 000000204","Аб.N. 1000321755","15.12.2023 ЕЛ ЕНЕРГИЯ"
        // 3 - Търговец; 4- сметка; 11(7) - дата;
        // String tmp = "This is a date 12.01.2024";

        File file = new File("/Users/blagojnikolov/Desktop/@tmp/reportDSK.csv");
        readFile(file);
    }

    private static void readFile(File file) throws FileNotFoundException {
        List<String> docInfo = new ArrayList<>();
        Scanner scanner = new Scanner(file);
        String rowData, tmpLine;
        String[] tmpArr;
        double allSum = 0.0;
        int cnt = 0;
        while (scanner.hasNextLine()) {
            rowData = scanner.nextLine();
            if (cnt > 0) {
                // System.out.println(getMoney(rowData) + "BGN");
                // System.out.println(getSubstring(tmpLine));

                allSum += getAllSum(getMoney(rowData));
//                System.out.printf("-%s:   Price: %.2fBGN.  Dealer: %s;   Total:  %.2fBGN;%n",
//                        getDateRgx(rowData),getMoney(rowData), getDealer(rowData), allSum);

                docInfo.add(getDateRgx(rowData));
                docInfo.add(String.valueOf(getMoney(rowData)));
                docInfo.add(getDealer(rowData));
                docInfo.add(String.valueOf(allSum));
            }
            cnt++;
        }
           printDocInfo(docInfo);
    }

    private static Double getMoney(String string) {
        double amount = 0.0;
        String moneyRegex = "\\d+,\\d+";
        Pattern pattern = Pattern.compile(moneyRegex);
        Matcher matcher = pattern.matcher(string);
        if (matcher.find()) {
            String str = matcher.group();
            String str1 = str.replace(",", ".");
            amount = Double.parseDouble(str1);
        }
        return amount;
    }

    private static double getAllSum(double sum) {
        double allSum = 0.0;
        return allSum += sum;
    }

    private static String getDateRgx(String string) {
        String result = "";
        String dateRegex = "\\b\\d{2}\\.\\d{2}\\.\\d{4}\\b";
        Pattern pattern = Pattern.compile(dateRegex);
        Matcher matcher = pattern.matcher(string);
        if (matcher.find()) {
            result = matcher.group();
        }
        return result;
    }

    private static String removeString(String string, String targetRgx, String replacement) {
        String result = "";
        Pattern pattern = Pattern.compile(targetRgx);
        Matcher matcher = pattern.matcher(string);
        if (matcher.find()) {
            result = matcher.group();
        }
        return result.replace(targetRgx, replacement);
    }

    private static String getSubstring(String string) {
        int idx = string.indexOf("BGN");
        return string.substring(0, idx);
    }

    private static String removeRegex(String string, String regex) {
        return string.replaceAll(regex, "");
    }

    private static String[] getArr(String string, String regex) {
        return string.trim().split(regex);
    }

    private static void printStrArr(String[] arr) { // 3 - Търговец; 4- сметка; 11(7) - дата;
        for (int i = 0; i < arr.length; i++) {
            System.out.printf("%s%n ", arr[4]);
        }
    }

    private static void printDocInfo(List<String> list){
        //for (String el: list) System.out.println(el);
        for (int i = 0; i < list.size(); i++) {
            System.out.printf("-%s:   Price: %sBGN.  Dealer: %s;   Total:  %sBGN;%n",
                        list.get(0),list.get(1), list.get(2), list.get(3));
        }
    }

    private static String getDealer(String rowData) {
        String[] tmp = getArr(rowData, ",");
        return removeRegex(tmp[3], "[\" ]");
    }

    private static String getAccount(String rowData) {
        String[] tmp = getArr(rowData, ",");
        return removeRegex(tmp[4], "\"");
    }

    private static String getDate(String rowData) {
        String[] tmp = getArr(rowData, ",");
        return tmp[tmp.length - 1].replaceAll("[\",;]", "");
    }
}
