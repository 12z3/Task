package exlFinance;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class eFinance {

    // todo: Файла от ДСК е екселски. Преформатирай го в csv.

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("");
        List<String> allData = readData(file);

        //printListData(allData);
        //print(allData);
        findByType(allData, "ЗАПЛАТА");
        //printListDataTmp(allData, 66, 2);

        findByDate(allData, "27.05.2024", "20.04.2024");
    }


    static List<String> readData(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        ArrayList<String> list = new ArrayList<>();
        Pattern pattern = Pattern.
                compile(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)(?!(?<=\\d)\\d{2},\\d{2}(?=\\d))");
        Matcher matcher;
        int cnt = 0;

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            matcher = pattern.matcher(line);

            String[] lineWord = line.trim().split(",");
            if (cnt > 3 && !lineWord[0].contains(" ,")) {
                if (!lineWord[0].isEmpty() && matcher.find()) {
                    String res = matcher.replaceAll("-");
                    String resT = res.replaceAll("(\\d),(\\d{2})", "$1,$2");
                    //String[] tmp = res.split(" ");
                    list.add(resT);
                }
            }
            cnt++;
        }
        return list;
    }


    static void findByDate(List<String> list, String fromDate, String toDate) {
        double fromIdx, toIdx;
        int lastIdx = 0;

        System.out.println();
        if (isValidDate(list, fromDate, toDate)) {
            double idx1 = Objects.requireNonNull(getIdxByDate(list, fromDate, toDate))[0];
            double idx2 = Objects.requireNonNull(getIdxByDate(list, fromDate, toDate))[1];
            double sum = 0.0;

            if (idx1 > idx2) {
                fromIdx = idx2;
                toIdx = idx1;
            } else {
                fromIdx = idx1;
                toIdx = idx2;
            }

            for (double i = fromIdx; i <= toIdx; i++) {
                rowDataPrint(list, (int) i, true);
                 sum += getMoneyByLine(list,(int) i);
                 lastIdx = (int) i;
            }
            System.out.print("\nОбщо: ");
            //rowDataPrint(list, (int) i, true): печата printCalcSpaces(data, spacesOnThisLine, -15) интервала.
            // printSpaces: Доотпечатва останалите проклети интервали.
            int tmpIdx = rowDataPrint(list,lastIdx,false);
            printSpaces(tmpIdx - "Общо".length());
            System.out.printf("%.2f%n", sum);
        }
    }

    static double[] getIdxByDate(List<String> list, String fromDate, String toDate) {
        Pattern pattern1 = Pattern.compile(fromDate);
        Pattern pattern2 = Pattern.compile(toDate);
        Matcher matcher1, matcher2;

        double[] idxs = new double[2];
        int cnt1 = 0, cnt2 = 0, fromIdx = 0, toIdx = 0;
        double sum = 0;
        boolean fromDateIsFind = false;

        for (int i = 0; i < list.size(); i++) {
            String t = list.get(i);
            matcher1 = pattern1.matcher(list.get(i));
            matcher2 = pattern2.matcher(list.get(i));
            if (!fromDateIsFind && matcher1.find()) {
                cnt1++;
                fromIdx = i;
                fromDateIsFind = true;
            } else if (fromDateIsFind && matcher2.find()) {
                cnt2++;
                toIdx = i;
            }
        }

        if (cnt1 == 0) {
            System.out.println(fromDate + " е невалидна");
            return null;
        } else if (cnt2 == 0) {
            System.out.println(toDate + " е невалидна");
            return null;
        }

        idxs[0] = fromIdx;
        idxs[1] = toIdx;
        return idxs;
    }

    static boolean isValidDate(List<String> list, String fromDate, String toDate) {
        return getIdxByDate(list, fromDate, toDate) != null;
    }

    static void findByType(List<String> list, String thisWord) {
        Pattern pattern = Pattern.compile(thisWord);
        Matcher matcher;
        String lastLine = "";
        int lastLineLength = 0, cnt = 0;
        double sum = 0.0;
        for (int i = 0; i < list.size(); i++) {
            matcher = pattern.matcher(list.get(i));
            if (matcher.find()) {
                rowDataPrint(list, i, true);
                sum += getMoneyByLine(list, i);
                lastLineLength = getDate(list, i, false) + getTransaction(list, i, false);
                cnt++;
            }
        }
        if (cnt == 0) {
            System.out.println("Няма налични данни");
            return;
        }
        System.out.println();
        System.out.print("Общо: ");
        // rowDataPrint в този си вид когато е извикан печата допълнително printSpaces(data, spacesOnThisLine, -15); <-
        // За това когато изпълниш System.out.println(); и след това printSpaces(spaces); има разминавания
        int spaces = rowDataPrint(list, lastLineLength, false) + 2;
        //System.out.println();

        printSpaces(spaces - "Общо: ".length());
        System.out.printf("%.2f%n", sum);
    }

    static void print(List<String> data) {
        for (int i = 0; i < data.size(); i++) {
            rowDataPrint(data, i, true);
        }
        System.out.println();
    }

    // Печата инфото от даден ред. idx - поредният индекс на реда в Листа.
    private static int rowDataPrint(List<String> data, int idx, boolean isPrint) {
        if (isPrint) {
            getDate(data, idx, true);
            getTransaction(data, idx, true);
        }
        int spacesOnThisLine = getDate(data, idx, false) + getTransaction(data, idx, false);
        printCalcSpaces(data, spacesOnThisLine, -15);
        if (isPrint) {
            printMoney(data, idx);
            System.out.println();
        }
        return spacesOnThisLine;
    }

    static int getDate(List<String> list, int line, boolean isPrint) {
        String[] dataTmp = list.get(line).split("-");
        if (isPrint && !dataTmp[0].isEmpty()) {
            System.out.print(dataTmp[0] + " ");
        }
        return dataTmp[0].length();
    }

    static int getTransaction(List<String> list, int line, boolean isPrint) {
        String[] dataTmp = list.get(line).split("-");
        if (isPrint && !dataTmp[1].isEmpty()) {
            System.out.print(dataTmp[1] + " ");
        }
        return dataTmp[1].length();
    }

    static void printMoney(List<String> list, int line) {
        String[] dataTmp = list.get(line)
                .replace("\"", "")
                .replace(",", ".")
                .split("-");
        if (!dataTmp[dataTmp.length - 1].isEmpty()) {
            System.out.print(dataTmp[dataTmp.length - 1] + " ");
        }
    }

    static double getMoneyByLine(List<String> list, int line) {
        String[] dataTmp = list.get(line)
                .replace("\"", "")
                .replace(",", ".")
                .split("-");
        if (!dataTmp[dataTmp.length - 1].isEmpty()) {
            return Double.parseDouble(dataTmp[dataTmp.length - 1]);
        }
        return 0.0;
    }

    static double getAllMoney(List<String> data) {
        double sum = 0.0;
        for (int i = 0; i < data.size(); i++) {
            sum += getMoneyByLine(data, data.size() - 1);
        }
        return sum;
    }

    static int maxLineLength(List<String> list) {
        int maxLength = Integer.MIN_VALUE;

        for (int i = 0; i < list.size(); i++) {
            if (!list.get(i).isEmpty() && list.get(i).length() > maxLength) {
                maxLength = list.get(i).length();
            }
        }
        return maxLength;
    }

    static int getLineLength(List<String> list, int idx) {
        return list.get(idx).length();
    }

    // Печата пресметнатия брой интервали за всеки ред
    static void printCalcSpaces(List<String> list, int thisLineWordsLength, int aditionSpaces) {
        int maxLength = maxLineLength(list);
        for (int i = 0; i < (maxLength - thisLineWordsLength) + aditionSpaces; i++) {
            System.out.print(" ");
        }
    }

    // Печата space на брой интервали на един ред
    static void printSpaces(int spaces) {
        for (int i = 0; i < spaces; i++) {
            System.out.print(" ");
        }
    }

    static void printListData(List<String> list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.println(i + ": " + list.get(i));
        }
        System.out.println();
    }

    // Печата ел на масива от реда с индекс idx от List
    static void printListDataTmp(List<String> list, int idx, int idxA) {
        String[] tmp = list.get(idx).trim().split("-");
        for (int i = 0; i < tmp.length; i++) {
            System.out.println(i + ": " + tmp[i]);
        }
        System.out.println(idxA + ": " + tmp[idxA]);
        System.out.println();
    }
}
