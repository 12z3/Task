import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class findMethods extends DocMake {
    @SuppressWarnings("unused")
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("/Users/blagojnikolov/Desktop/@tmp/reportDSK.csv");
        String dateRgx = "\\d{2}\\.\\d{2}\\.\\d{4}";
        String money = "\\d+\\.\\d+";
        String dealer = "VIVACOM АБОНАМЕНТЕН ПЛАН";
        String dealer1 = "ЕЛЕКТРОХОЛД ПРОДАЖБИ АД";
        String date = "15.04.2022";

        find(file, dealer1);
        findMoneyFromDealer(file, dealer1);
        find(file, dealer);
        findMoneyFromDealer(file, dealer);

    }

    protected static void printDealerSearchResult(List<List<Double>> result) {
        for (int i = 0; i < result.size(); i++) {
            for (int j = 1; j < result.get(i).size() - 1; j++) {
                double el = result.get(i).get(j);
                if (el != 0.0) {
                    System.out.println(j + ": " + el + " ");
                }
            }

        }
    }

    protected static void printAllInfoFromDealer(List<List<Double>> list) {
        for (List<Double> el : list) {
            if (!el.isEmpty()) {
                System.out.println(el + " ");
            }
        }
    }

    protected static boolean find(File file, String regEx) throws FileNotFoundException {
        System.out.println();
        boolean result = false;
        Matcher matcher = null;
        String res = "";
        int cnt = 0;

        Pattern pattern = Pattern.compile(regEx);
        for (int i = 0; i < readFile(file).size(); i++) {
            for (int j = 0; j < readFile(file).get(i).size(); j++) {
                matcher = pattern.matcher(readFile(file).get(i).get(j));
                if (matcher.find()) {
                    result = true;
                    res = matcher.group();
                    cnt++;
                }
            }
        }
        String tmp = (result) ? res : "No such matches";
        System.out.println(tmp + " - " + cnt + "times");
        return result;
    }

    protected static List<List<Double>> findMoneyFromDealer(File file, String dealer) throws FileNotFoundException {
        System.out.println("\n" + dealer + ": ");
        List<Double> money = null;
        List<List<Double>> resultMoney = new ArrayList<>();
        Matcher dealerMatcher = null;
        Pattern pattern1 = Pattern.compile("\\d+\\.\\d+");
        double totalSum = 0.0;

        Pattern pattern = Pattern.compile(dealer);
        for (int row = 0; row < readFile(file).size(); row++) {
            money = new ArrayList<>();
            double sum = 0.0;
            for (int string = 0; string < readFile(file).get(row).size(); string++) {
                dealerMatcher = pattern.matcher(readFile(file).get(row).get(string));
                if (dealerMatcher.find()) {
                    for (int i = 0; i < readFile(file).get(i).size(); i++) {
                        String line = readFile(file).get(row).get(i);
                        Matcher monyMatcher = pattern1.matcher(line);
                        if (monyMatcher.find()) {
                            String res1 = monyMatcher.group();
                            money.add(Double.parseDouble(res1));
                            sum = Double.parseDouble(readFile(file).get(row).get(1));
                        }
                    }
                }
            }
            totalSum += sum;
            resultMoney.add(money);
        }
        System.out.println("Total Sum: " + totalSum);
        return resultMoney;
    }

    protected static List<List<Double>> findMoneyFromDate(File file, String date) throws FileNotFoundException {
        List<Double> money = null;
        List<List<Double>> resultMoney = new ArrayList<>();
        Matcher dateMatcher = null;
        Pattern moneyPattern = Pattern.compile("\\d+\\.\\d+");

        Pattern datePattern = Pattern.compile(date);
        for (int row = 0; row < readFile(file).size(); row++) {
            money = new ArrayList<>();
            for (int string = 0; string < readFile(file).get(row).size(); string++) {
                dateMatcher = datePattern.matcher(readFile(file).get(row).get(string));
                if (dateMatcher.find()) {
                    for (int i = 0; i < readFile(file).get(i).size(); i++) {
                        String line = readFile(file).get(row).get(i);
                        Matcher moneyMatcher = moneyPattern.matcher(line);
                        if (moneyMatcher.find()) {
                            String res1 = moneyMatcher.group();
                            money.add(Double.parseDouble(res1));
                        }
                    }
                }
            }
            resultMoney.add(money);
        }
        return resultMoney;
    }
}
