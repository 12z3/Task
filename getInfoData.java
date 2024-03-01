
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class getInfoData {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("/Users/blagojnikolov/Documents/VSCode/reportDSK.csv");
        String elektroHold = "ЕЛЕКТРОХОЛД ПРОДАЖБИ АД";
        String evn = "ЕВН ЕЛ. ЕНЕРГИЯ - ЕЛ.КАНАЛИ";
        String netSurf = "НЕТ - СЪРФ.НЕТ";
        String vivacom = "VIVACOM АБОНАМЕНТЕН ПЛАН";
        String chez = "ЧЕЗ";
        String vik = "ВИК ВРАЦА";

        List<String[]> data = readData(file, false);
        // printCrudeDate(data);

        List<String[]> dataFromMoney = readData(file, true);
        printInfo(dataFromMoney);

        getMoneyFromDealer(dataFromMoney, vivacom);
        getMoneyFromDealer(dataFromMoney, netSurf);
        getMoneyFromDealer(dataFromMoney, vik);
        getMoneyFromDealer(dataFromMoney, evn);
        getMoneyFromDealer(dataFromMoney, elektroHold);

        findDealersPayByDay(data, " 15.03.2023");
        System.out.println();
        allDealersInfo(data);

        System.out.println();
        getMaxSum(data);

    }

    private static void printCrudeDate(List<String[]> data) {
        for (String[] el : data) {
            System.out.println(Arrays.toString(el));
        }
        System.out.println();
    }

    private static void printInfo(List<String[]> text) {
        System.out.println();
        double sum = 0;
        String date;
        String txt = "All spend sum: ", moneyTxt = "Money";

        for (String[] el : text) {
            double amount = getMoney(el);
            String dealer = el[3].trim();
            date = (el[el.length - 1]);
            sum += amount;

            System.out.printf("%s:", dealer);
            spaces(33, dealer);
            System.out.printf("%.2fBGN%n", amount);

        }
        System.out.println("-------------------------");
        System.out.print(txt);
        spaces(34, txt);
        System.out.println(sum + "BGN");
        System.out.println();
    }

    protected static void getMaxSum(List<String[]> data) {
        double money = 0.0, max = Double.MIN_VALUE;
        String dayOrg = "", day = "";
        String dealer = "";

        for (int row = 0; row < data.size(); row++) {
            String[] line = data.get(row);
            String lineToTxt = getStringToBNG(Arrays.toString(line));
            money = getMoney(lineToTxt.split(","));
            // System.out.println(money);
            if (money > max) {
                max = money;
                dayOrg = line[line.length - 1];
                dealer = line[3];
                day = dayOrg.replaceAll("(\\d{2}\\.\\d{2}\\.\\d{4}).*", "$1");
            }
        }
        System.out.printf("Max sum: %.2fBGN - %s at %s%n", max, dealer, day);
    }

    protected static List<String[]> readData(File file, boolean neededMoney) throws FileNotFoundException {
        @SuppressWarnings("resource")
        Scanner scanner = new Scanner(file);
        List<String[]> result = new ArrayList<>();
        String string;
        String[] line;
        int cnt = 0;
        double d = 0.0;
        if (!neededMoney) {
            while (scanner.hasNextLine()) {
                string = scanner.nextLine().replaceAll("[\"']", "").trim();
                if (cnt > 0) {
                    line = string.split(",");
                    result.add(line);
                }

                cnt++;
            }
            return result;
        } else {
            return readMoneyData(file);
        }
    }

    // ! Връща списък от String[]-и; Използва се само в List<String[]> readData(File
    // ! file, boolean neededMoney)
    protected static List<String[]> readMoneyData(File file) throws FileNotFoundException {
        @SuppressWarnings("resource")
        Scanner scanner = new Scanner(file);
        List<String[]> result = new ArrayList<>();
        String string;
        String[] line;
        int cnt = 0;
        double d = 0.0;

        while (scanner.hasNextLine()) {
            string = scanner.nextLine().replaceAll("[\"']", "").trim();
            if (cnt > 0) {
                String tmp = getStringToBNG(string);
                line = tmp.split(",");
                result.add(line);
            }

            cnt++;
        }
        return result;
    }

    private static String getStringToBNG(String text) {
        int idx = text.indexOf("BGN");
        return text.substring(0, idx);
    }

    // ! List<String[]> readMoneyData(File file) -> Double getMoney(String[] text),
    // ! но ред по ред
    // ! За един комкретен ред намира сумата. Реда е String[];
    protected static Double getMoney(String[] text) {
        StringBuilder stbF = new StringBuilder();
        StringBuilder stbS = new StringBuilder();
        StringBuilder stb = new StringBuilder();
        double money = 0.0;

        StringBuilder first = stbS.append(text[text.length - 2].trim());
        StringBuilder second = stbF.append(text[text.length - 1].trim());

        char[] firstAsChar = first.toString().toCharArray();
        char[] secondAsChar = second.toString().toCharArray();

        // ! Това е валидно ако нямаш 5 - цифрена сметка; <-
        // ! "BG91STSA93000004594021" = 22;
        if (firstAsChar.length > 5) {
            stb.append(second);
            money = Double.parseDouble(stb.toString());
            return money;
        } else {
            stb.append(first);
            stb.append(".");
            stb.append(second);
            money = Double.parseDouble(stb.toString());
            return money;
        }
    }

    protected static void getMoneyFromDealer(List<String[]> data, String dealer) {
        double money = 0.0, allSum = 0.0;
        String dealerInfo = "", infoTxt = "All spend sum: ";
        List<String> dealerList = new ArrayList<>();
        List<Double> moneyList = new ArrayList<>();

        for (int i = 0; i < data.size(); i++) {
            Pattern dealerPattern = Pattern.compile(dealer);
            for (int j = 0; j < data.get(i).length; j++) {
                Matcher dealerMatcher = dealerPattern.matcher(data.get(i)[j]);
                if (dealerMatcher.find()) {
                    dealerInfo = dealerMatcher.group().trim();
                    money = getMoney(data.get(i));
                    dealerList.add(dealerInfo);
                    moneyList.add(money);
                    allSum += money;
                }
            }
        }

        if (allSum == 0) {
            System.out.println("404! - Я виж какво си въвел като Търговец ;(  ->  " + dealer);
            return;
        }

        System.out.println("-------------------------");
        for (int i = 0; i < dealerList.size(); i++) {
            System.out.printf("%s: ", dealerList.get(i));

            spaces(32, dealer);
            System.out.printf("%.2fBGN%n", moneyList.get(i));
        }
        System.out.println("-------------------------");
        System.out.printf("All spend sum: ");

        spaces(34, infoTxt);
        System.out.printf("%.2fBGN%n", allSum);
        System.out.println();
    }

    protected static void spaces(int num, String word) {
        int numOfSpaces = Math.abs((num - word.length()));
        for (int k = 0; k < numOfSpaces; k++)
            System.out.print(" ");
    }

    private static void findAllDay(List<String[]> data) {
        String day = "";
        for (int row = 0; row < data.size(); row++) {
            Pattern dateRgx = Pattern.compile("\\d{2}\\.\\d{2}\\.\\d{4}");
            for (int string = 0; string < data.get(row).length; string++) {
                Matcher dayMatcher = dateRgx.matcher(data.get(row)[string]);
                if (dayMatcher.find()) {
                    day = dayMatcher.group().trim();
                    System.out.println(day);
                }
            }
        }
    }

    private static void findDealersPayByDay(List<String[]> data, String day) throws FileNotFoundException {
        File file = new File("/Users/blagojnikolov/Desktop/@tmp/reportDSK.csv");
        String res = "", txt = "All spend money: ";
        double money = 0.0, allMoney = 0.0;
        Matcher dayMatcher = null;
        day.trim();

        List<String[]> dataFromMoney = readMoneyData(file);
        for (int row = 0; row < data.size(); row++) {
            Pattern dateRgx = Pattern.compile(day.trim());
            for (int string = 0; string < data.get(row).length; string++) {
                dayMatcher = dateRgx.matcher(data.get(row)[string]);
                if (dayMatcher.find()) {
                    res = dayMatcher.group().trim();
                    money = getMoney(dataFromMoney.get(row));
                    allMoney += money;
                    System.out.print(res + "   " + data.get(row)[3].trim() + ":   ");
                    String length = (res + "   " + data.get(row)[3].trim() + ":   ");
                    spaces(43, length);
                    System.out.println(money + "BGN");
                }
            }
        }

        if (allMoney == 0) {
            System.out.println("404! - Я виж какво си въвел като дата ;(  ->  " + day);
            return;
        }
        System.out.println("-------------------------");
        System.out.print("All spend money: ");
        spaces(43, txt);
        System.out.println(allMoney + "BGN");
    }

    protected static List<String> allDealersInfo(List<String[]> readData) {
        List<String> dealers = new ArrayList<>();
        for (int row = 0; row < readData.size(); row++) {
            for (int dealer = 0; dealer < readData.get(row).length; dealer++) {
                String tmpRes = readData.get(row)[3].trim();
                if (!dealers.contains(tmpRes)) {
                    dealers.add(tmpRes);
                }
            }
        }
        printList(dealers);
        return dealers;
    }

    private static <T> void printList(List<T> data) {
        for (T el : data)
            System.out.println(el);
    }
}
