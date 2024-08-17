
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class getData extends findMethods {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("/Users/blagojnikolov/Desktop/@tmp/reportDSK.csv");

        List<String[]> resToArr = read(file);
        // printOneLine(resToArr, 4);

        printDealers(file);

        System.out.println();
        for (int row = 1; row < resToArr.size(); row++) {
            // String string = getMoneyToStr(resToArr.get(row), "(\\d{1,3} \\d{1,3} BGN)");
            String string = getMoneyToStr(resToArr.get(row), "(\\d{1,3} \\d{1,3}BGN)|(\\d{1,2} BGN)");
            if (!string.equalsIgnoreCase("No such matches")) {
                System.out.println(
                        resToArr.get(row)[3].trim() + ": " + string);
            }
        }

        // for (Map.Entry<Integer, String> el : findInLine(resToArr, "ЕВН ЕЛ. ЕНЕРГИЯ
        // -ЕЛ.КАНАЛИ").entrySet())
        // System.out.println(el.getKey() + " - " + el.getValue());

        System.out.print("-----------------------------------");

        vivacomSpendMoney(file);
        electroHoldSpendMoney(file);
        evnSpendMoney(file);
        netSurfSpendMoney(file);
        chezSpendMoney(file);
        System.out.print("-----------------------------------");
        System.out.println();
        allSpendMoneyA(file);
        System.out.print("-----------------------------------");
    }

    protected static void printDealers(File file) throws FileNotFoundException {
        System.out.println();
        String vivacom = "VIVACOM АБОНАМЕНТЕН ПЛАН";
        String electrohold = "ЕЛЕКТРОХОЛД ПРОДАЖБИ АД";
        String evn = "ЕВН ЕЛ. ЕНЕРГИЯ - ЕЛ.КАНАЛИ";
        String netsurf = "НЕТ - СЪРФ.НЕТ";
        String chez = "ЧЕЗ";
        String date = "15.04.2022";

        System.out.print("-----------------------------------");
        find(file, electrohold);
        electroHoldSpendMoney(file);
        find(file, vivacom);
        vivacomSpendMoney(file);
        find(file, evn);
        evnSpendMoney(file);
        find(file, netsurf);
        netSurfSpendMoney(file);
        chezSpendMoney(file);
        System.out.println("-----------------------------------");
        System.out.printf("All spend sum: %.2fBGN%n", allSpendMoney(file));
        System.out.println("-----------------------------------");

    }

    protected static void printOneLine(List<String[]> line, int lineNumber) {
        System.out.println();
        for (int string = 0; string < line.get(lineNumber).length; string++) {
            System.out.print(line.get(lineNumber)[string] + " ");
        }
        System.out.println();
    }

    protected static void print(List<String[]> readFromFile, String dealer) {
        Map<Integer, String> result = findInLine(readFromFile, dealer);
        for (Map.Entry<Integer, String> el : result.entrySet())
            System.out.println("row: " + el.getKey() + " - " + el.getValue() + " ");
    }

    // ! Търси колко пъти "some" се среща във всеки ред(Масив от Str) в листа "line"
    // * Виждаш какво връща метода */
    // То хубаво, но се замисли какви ползи имаш от структурата Map ;)? Пренапиши го.
    protected static Map<Integer, String> findInLine(List<String[]> line, String some) {
        some.trim();
        System.out.println();
        int lineNumber = -1, cnt;

        Map<Integer, String> result = new HashMap<>();
        for (int row = 0; row < line.size(); row++) {
            cnt = 0;
            for (int string = 0; string < line.get(row).length; string++) {
                String thisLine = line.get(row)[string].trim();
                if (some.equalsIgnoreCase(thisLine)) {
                    lineNumber = row;
                    result.put(lineNumber, some);
                    cnt++;

                }
            }
        }
        // System.out.println();
        return result;
    }

    // ! Връща List. Всеки един ред от List е масив от Str:
    protected static List<String[]> read(File file) throws FileNotFoundException {
        @SuppressWarnings("resource")
        Scanner scanner = new Scanner(file);
        List<String[]> readList = new ArrayList<>();
        List<String> oneLine;
        String[] line = null;

        while (scanner.hasNext()) {
            oneLine = new ArrayList<>();
            line = scanner.nextLine()
                    .replaceAll("[\"']", "").trim().split(",");
            readList.add(line);
        }
        return readList;
    }

    protected static String findDate(String input) {
        String dateRgx = "\\d{2}\\.\\d{2}\\.\\d{4}";

        Pattern datePattern = Pattern.compile(dateRgx);
        Matcher dateMatcher = datePattern.matcher(input);
        if (dateMatcher.find()) {
            return dateMatcher.group();
        } else
            return "No such matches";
    }

    // ! Стринга "109 78 BGN" се форматира в "109 78 BGN"
    // ! но при 36BGN stb.append(input[6]) = глупости.
    // ! stb.append(input[6]) просто липсва.
    protected static String formatMoney(String[] input) {
        String problemRgx = "\\d{1,3} BGN";
        Pattern pattern = Pattern.compile(problemRgx);
        Matcher matcher = pattern.matcher(input[5]);
        StringBuilder stb = new StringBuilder();

        if (matcher.find()) {
            String tmp = input[5].replace(" BGN", " ");
            stb.append(tmp);
            stb.append(" ");
            stb.append(0);
            stb.append(" BGN");
        } else {
            stb.append(input[5]);
            stb.append(" ");
            stb.append(input[6]);
        }
        return stb.toString();
    }

    // ! "\\d{2,3} \\d{2} BGN" -> Не намира 38BGN и 1BGN <-
    // ! Намира всяко едно съвпадение на "109 37 BGN" за даден ред.
    // * Връща "109.37" */
    protected static String getMoneyToStr(String[] input, String some) {
        // * За всеки един ред от масива търси съвпадението - (109 37 BGN) */
        // System.out.println(Arrays.toString(input));

        Pattern dataPattern = Pattern.compile(some);
        for (int i = 0; i < input.length; i++) {
            String formatM = formatMoney(input);
            // ! Търси в formatM dataPattern-a.
            Matcher dataMatcher = dataPattern.matcher(input[i]);
            if (dataMatcher.find()) { // ! 109 37 BGN
                String problemRgx = "\\d{1,3} BGN";
                Pattern pattern1 = Pattern.compile(problemRgx);
                Matcher matcher1 = pattern1.matcher(formatM);

                StringBuilder stb = new StringBuilder();
                if (matcher1.find()) { // ! 37 BGN
                    stb.append(input[5]);
                    stb.append(".");
                    stb.append(0);
                    return stb.toString();
                } else {
                    stb.append(input[5]);
                    stb.append(".");
                    stb.append(input[6]);
                    return stb.toString();
                }
            }
        }
        return "No such matches";
    }

    protected static Double allSpendMoney(File file) throws FileNotFoundException {
        List<String> tmp = new ArrayList<>();
        List<String[]> input = read(file);
        double allSum = 0.0;

        for (int i = 0; i < input.size(); i++) {
            for (int j = 0; j < input.get(j).length; j++) {
                String money = getMoneyToStr(input.get(i), "(\\d{1,3} \\d{1,3} BGN)|(\\d{1,3} BGN)")
                        .replaceAll(" BGN", "");
                if (!money.equalsIgnoreCase("No such matches")) {
                    allSum += Double.parseDouble(money);
                    tmp.add(money);
                    break;
                }
            }
        }
        System.out.println(tmp);
        return allSum;
    }

    // ! Работи: Смята всички похарчени суми по категорий.
    protected static Double allSpendMoneyA(File file) throws FileNotFoundException {
        List<String> tmp = new ArrayList<>();
        List<String[]> input = read(file);
        double allSum = 0.0;

        String rgx = ("VIVACOM АБОНАМЕНТЕН ПЛАН|ЕЛЕКТРОХОЛД ПРОДАЖБИ АД|ЕВН ЕЛ. ЕНЕРГИЯ - ЕЛ.КАНАЛИ|НЕТ - СЪРФ.НЕТ|ЧЕЗ");
        for (int i = 0; i < input.size(); i++) {
            if (!find(input.get(i), rgx).equalsIgnoreCase("No such matches")) {
                for (int j = 0; j < input.get(j).length; j++) {
                    String money = getMoneyToStr(input.get(i), "\\d{2,3} \\d{2} BGN")
                            .replaceAll(" BGN", "");
                    if (!money.equalsIgnoreCase("No such matches")) {
                        allSum += Double.parseDouble(money);
                        break;
                    }
                }
            }
        }
        System.out.printf("Total spend sum: %.2fBGN%n", allSum);
        return allSum;
    }

    // ! Тези 4-и метода трябва да могат да се заместят са с ЕДИН?
    protected static Double netSurfSpendMoney(File file) throws FileNotFoundException {
        System.out.println();
        List<String> tmp = new ArrayList<>();
        List<String[]> input = read(file);
        double allSum = 0.0;

        String rgx = ("НЕТ - СЪРФ.НЕТ");
        for (int i = 0; i < input.size(); i++) {
            if (!find(input.get(i), rgx).equalsIgnoreCase("No such matches")) {
                for (int j = 0; j < input.get(j).length; j++) {
                    String money = getMoneyToStr(input.get(i), "\\d{2,3} \\d{2} BGN")
                            .replaceAll(" BGN", "");
                    if (!money.equalsIgnoreCase("No such matches")) {
                        allSum += Double.parseDouble(money);
                        tmp.add(money);
                        break;
                    }
                }
            }
        }

        System.out.printf("НЕТ - СЪРФ.НЕТ : %.2fBGN%n", allSum);

        return allSum;
    }

    protected static Double chezSpendMoney(File file) throws FileNotFoundException {
        System.out.println();
        List<String> tmp = new ArrayList<>();
        List<String[]> input = read(file);
        double allSum = 0.0;

        String rgx = ("ЧЕЗ");
        for (int i = 0; i < input.size(); i++) {
            if (!find(input.get(i), rgx).equalsIgnoreCase("No such matches")) {
                for (int j = 0; j < input.get(j).length; j++) {
                    String money = getMoneyToStr(input.get(i), "\\d{2,3} \\d{2} BGN")
                            .replaceAll(" BGN", "");
                    if (!money.equalsIgnoreCase("No such matches")) {
                        allSum += Double.parseDouble(money);
                        tmp.add(money);
                        break;
                    }
                }
            }
        }

        System.out.printf("ЧЕЗ: %.2fBGN%n", allSum);
        return allSum;
    }

    protected static Double evnSpendMoney(File file) throws FileNotFoundException {
        System.out.println();
        List<String> tmp = new ArrayList<>();
        List<String[]> input = read(file);
        double allSum = 0.0;

        String rgx = ("ЕВН ЕЛ. ЕНЕРГИЯ - ЕЛ.КАНАЛИ");
        for (int i = 0; i < input.size(); i++) {
            if (!find(input.get(i), rgx).equalsIgnoreCase("No such matches")) {
                for (int j = 0; j < input.get(j).length; j++) {
                    String money = getMoneyToStr(input.get(i), "\\d{2,3} \\d{2} BGN")
                            .replaceAll(" BGN", "");
                    if (!money.equalsIgnoreCase("No such matches")) {
                        allSum += Double.parseDouble(money);
                        tmp.add(money);
                        break;
                    }
                }
            }
        }

        System.out.printf("ЕВН ЕЛ. ЕНЕРГИЯ - ЕЛ.КАНАЛИ: %.2fBGN%n", allSum);
        return allSum;
    }

    protected static Double electroHoldSpendMoney(File file) throws FileNotFoundException {
        System.out.println();
        List<String> tmp = new ArrayList<>();
        List<String[]> input = read(file);
        double allSum = 0.0;

        String rgx = ("ЕЛЕКТРОХОЛД ПРОДАЖБИ АД");
        for (int i = 0; i < input.size(); i++) {
            if (!find(input.get(i), rgx).equalsIgnoreCase("No such matches")) {
                for (int j = 0; j < input.get(j).length; j++) {
                    String money = getMoneyToStr(input.get(i), "\\d{2,3} \\d{2} BGN")
                            .replaceAll(" BGN", "");
                    if (!money.equalsIgnoreCase("No such matches")) {
                        allSum += Double.parseDouble(money);
                        tmp.add(money);
                        break;
                    }
                }
            }
        }

        System.out.printf("ЕЛЕКТРОХОЛД ПРОДАЖБИ АД: %.2fBGN%n", allSum);
        return allSum;
    }

    protected static Double vivacomSpendMoney(File file) throws FileNotFoundException {
        System.out.println();
        List<String> tmp = new ArrayList<>();
        List<String[]> input = read(file);
        double allSum = 0.0;

        String rgx = ("VIVACOM АБОНАМЕНТЕН ПЛАН");
        for (int i = 0; i < input.size(); i++) {
            if (!find(input.get(i), rgx).equalsIgnoreCase("No such matches")) {
                for (int j = 0; j < input.get(j).length; j++) {
                    String money = getMoneyToStr(input.get(i), "\\d{2,3} \\d{2} BGN")
                            .replaceAll(" BGN", "");
                    if (!money.equalsIgnoreCase("No such matches")) {
                        allSum += Double.parseDouble(money);
                        tmp.add(money);
                        break;
                    }
                }
            }
        }

        System.out.printf("VIVACOM АБОНАМЕНТЕН ПЛАН: %.2fBGN%n", allSum);
        return allSum;
    }

    //// protected static String getEachMoneyToStr(String[] input, String some){
    //// Pattern datePattern = Pattern.compile(some);

    //// Matcher dateMatcher = datePattern.matcher(formatMoney(input));
    //// if (dateMatcher.find()) {
    //// StringBuilder stb = new StringBuilder();
    //// return dateMatcher.group();
    //// stb.append(input[5]);
    //// stb.append(",");
    //// stb.append(input[6]);
    //// return stb.toString();
    //// }

    //// return "No such matches";
    //// }

    // * protected static Double getMoneyLikeDigit(){}

    // ! Търси "some" само в един ред от масива
    protected static String find(String[] input, String some) {
        Pattern datePattern = Pattern.compile(some);

        for (int i = 0; i < input.length; i++) {
            Matcher dateMatcher = datePattern.matcher(input[i]);
            if (dateMatcher.find()) {
                return dateMatcher.group();
            }
        }
        return "No such matches";
    }

}
