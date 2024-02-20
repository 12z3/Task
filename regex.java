package Documents;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class regex {
    public static void main(String[] args) throws FileNotFoundException {
        // "Плащане на сметка,БЛАГОЙ НИКОЛОВ НИКОЛОВ ,6607342,ЕВН ЕЛ. ЕНЕРГИЯ - ЕЛ.КАНАЛИ ,BG91STSA93000004594021,185,39"
        readFile();
    }

    private static Double getMoney(String string) {
        Double res = null;
        String regex = "\\d+,\\d+"; // 185,3 as String
        String stringly;
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(string);

        while (matcher.find()) {
            stringly = matcher.group();
            String normalized = stringly.replace(',', '.');
            res = (Double.parseDouble(normalized));
        }

        return res;
    }


    private static void readFile() throws FileNotFoundException {
        File file = new File("/Users/blagojnikolov/Documents/VSCode/reportDSK.csv");
        Scanner scanner = new Scanner(file);
        String[] line;
        int cnt = 0;

        String rowData;

        while (scanner.hasNextLine()) {    // idx = 3, 5
            rowData = scanner.nextLine();
            Double result = getMoney(rowData);
             if (result != null) System.out.printf("$%.2f%n", result);
        }
    }

    private static void readFileOld() throws FileNotFoundException {
        File file = new File("/Users/blagojnikolov/Documents/VSCode/reportDSK.csv");
        Scanner scanner = new Scanner(file);
        String[] line;
        int cnt = 0;

        String rowData;

        while (scanner.hasNextLine()) {    // idx = 3, 5
            rowData = scanner.nextLine();
            if (cnt > 0) {
                String string = extract(rowData, "BGN");
                if (checkIsValidString(string)) return;
                line = string.trim().split(",");
                //System.out.println(line[3]);
                System.out.println(string);
            }
            System.out.println();
            cnt++;
        }
    }

    private static boolean checkIsValidString(String string) {
        if (string.equalsIgnoreCase("No such matches")) {
            System.out.println("404");
            return true;
        }
        return false;
    }

    private static String extract(String input, String pivot) {
        int idx = input.indexOf(pivot);
        return (idx != -1) ? input.substring(0, idx) : "No such matches";
    }
}
