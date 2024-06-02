package exlFinance;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class eFinance {

    // Изважда информация за всички приходи, разходи и движение по категории.
    // todo: Файла от ДСК е екселски. Преформатирай го в csv. -> https://cloudconvert.com/xls-to-csv
    // Галимацията с конвертирането е необходима щото не подкарах apachePOI като хората. Омазах зависимостите.
    // Имплементацията е BRUTE FORCE в целия си блясък, но работи.
    // Приема csv файл. Чете го ред по ред, форматира стринговете подобаващо и връща Лист от стрингове.
    // Търси данни по произволна дума в текста. Думите трябва да бъдат с главни букви. Работи и с части от думите.
    // Реализирана е проверка за валидността на датите.
    // Ако дадена дата не се среща в Листа търси
    // следващата валидна такава. Формата на датата трябва да бъде: "21.01.2024"

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("");
        List<String> allData = readData(file);

        //printListData(allData);
        //print(allData);
        findByType(allData, "ЗАПЛАТА");
        findByType(allData, "АВАНС");
        //findByType(allData, "VIVA");
        //printListDataTmp(allData, 66, 2);

        findByDate(allData, "30.12.2023", "01.05.2024");
        //findNextValidDate2(allData, "30.12.2023");
        //findNextValidDate1(allData, "01.05.2024");
        //findNextValidDate(allData, "19.05.2024");
    }


    static List<String> readData(File file) {
        ArrayList<String> list = new ArrayList<>();
        Pattern pattern = Pattern.
                // Намира запетаи, които не са част от десетично число с формат “xxx,yy”.
                        compile(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)(?!(?<=\\d)\\d{2},\\d{2}(?=\\d))");
        Matcher matcher;
        int cnt = 0;

        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                matcher = pattern.matcher(line);

                String[] lineWord = line.trim().split(",");
                if (cnt > 3 && !lineWord[0].contains(" ,")) {
                    if (!lineWord[0].isEmpty() && matcher.find()) {
                        // Заменя всички намерени запетайки с "-".
                        String res = matcher.replaceAll("-");
                        String resT = res.replaceAll("(\\d),(\\d{2})", "$1,$2");
                        //String[] tmp = res.split(" ");
                        list.add(resT);
                    }
                }
                cnt++;
            }
        } catch (FileNotFoundException ex) {
            System.out.println("Няма ти го Файла пиЧ ;)");
        }
        return list;
    }


    static void findByDate(List<String> list, String fromDate, String toDate) {
        double fromIdx, toIdx;
        int lastIdx = 0;

        System.out.println();
        System.out.println("Oт дата " + fromDate + " до дата " + toDate + ": ");
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
                sum += getMoneyByLine(list, (int) i);
                lastIdx = (int) i;
            }
            System.out.print("\nОбщо: ");
            //rowDataPrint(list, (int) i, true): печата printCalcSpaces(data, spacesOnThisLine, -15) интервала.
            // printSpaces: Доотпечатва останалите проклети интервали.
            int tmpIdx = rowDataPrint(list, lastIdx, false);
            printSpaces(tmpIdx - "Общо".length());
            System.out.printf("%.2f BGN%n", sum);
        }
    }

    // На тез дати съответстват ли валидни индекси?
    static double[] getIdxByDate(List<String> list, String fromDate, String toDate) {
        Pattern pattern1 = Pattern.compile(fromDate);
        Pattern pattern2 = Pattern.compile(toDate);
        Matcher matcher1 = null, matcher2;

        double[] idxs = new double[2];
        int cnt1 = 0, cnt2 = 0, fromIdx = 0, toIdx = 0;
        boolean fromDateIdxIsFind = false;

        for (int i = 0; i < list.size(); i++) {
            matcher1 = pattern1.matcher(list.get(i));
            matcher2 = pattern2.matcher(list.get(i));
            if (!fromDateIdxIsFind && matcher1.find()) {
                cnt1++;
                fromIdx = i;
                fromDateIdxIsFind = true;
            } else if (fromDateIdxIsFind && matcher2.find()) {
                cnt2++;
                toIdx = i;
            }
        }

        if (cnt1 == 0) {
            System.out.println(fromDate + " е невалидна дата");
            findNextValidDate2(list, fromDate);
        }

        if (cnt2 == 0) {
            System.out.println(toDate + " е невалидна дата");
            findNextValidDate2(list, toDate);
        }
        if (cnt1 == 0 || cnt2 == 0) return null;

        idxs[0] = fromIdx;
        idxs[1] = toIdx;
        return idxs;
    }

    // Съществува ли != null масив с индекси на датите значи датата е валидна
    static boolean isValidDate(List<String> list, String fromDate, String toDate) {
        return getIdxByDate(list, fromDate, toDate) != null;
    }

    // Търси следващата валидна дата в листа.
    // Взима първите два литерала на датата(ден и месец), парсва ги към int, увеличава стойноста на int с 1 и връща нова дата.
    //
    static void findNextValidDate(List<String> list, String date) {
        String rgx = date;
        int tmpInt;

        try {
            tmpInt = Integer.parseInt(date.substring(0, 2)); // От 0 до 1 [1,2) <-
        } catch (NumberFormatException ex) {
            System.out.println("Формата на датата " + date + " ти е Незаконен ;)");
            return;
        }

        String tmpStr = date.substring(2);

        // Взима датата от Стринга на този ред в Листа.
        String thisDate = (list.getFirst().split("-")[0]);
        while (!thisDate.equals(rgx)) {
            tmpInt++;

            // todo: ? Не взима правилно thisDate = (list.get(i).split("-")[0]);
            //       ? Не конструира правилно rgx / tmpInt.
            // Обхожда всеки път Листа за да търси съвпадение.
            for (int i = 0; i < list.size(); i++) {
                thisDate = (list.get(i).split("-")[0]);
                if (thisDate.equals("Общо")) continue;
                //System.out.println(list.get(i));                 // 02.05.2024 - .......


                // todo: ? Евентуално проверка ?< 10 и добавяне на една 0-чка
                rgx = String.valueOf(tmpInt) + tmpStr;             // 02.05.2024
                //System.out.println(rgx);                         // 2.05.2024 <- todo: Това бърка Филма ;)


                //System.out.println(thisDate);                    // 02.05.2024
                //if (thisDate.equals(rgx)) break;
                if (thisDate.equals(rgx)) break;
            }
        }

        System.out.println(rgx + " е следващата валидна дата");
    }


    static void findNextValidDate1(List<String> list, String date) {
        int intDate, intMonth, intYear;

        try {
            intDate = Integer.parseInt(date.substring(0, 2));
            intMonth = Integer.parseInt(date.substring(3, 5));
            intYear = Integer.parseInt(date.substring(6));
        } catch (NumberFormatException ex) {
            System.out.println(STR."Формата на датата \{date} ти е Незаконен ;)");
            return;
        }

        while (!searchingMatchesDates(list, date)) {
            String tmpStr = date.substring(2);
            String tmpStrM = date.substring(5);
            String tmpDate = null;

            if (intDate >= 10 && intDate < 31) {
                intDate++;
                tmpDate = intDate + tmpStr;
            } else if (intDate > 0 && intDate < 10) {
                intDate++;
                tmpDate = STR."0\{intDate}\{tmpStr}";
            } else if (intDate >= 31) {
                intDate = 1;
                intMonth++;
                if (intMonth < 10) {
                    tmpDate = STR."0\{intDate}.0\{intMonth}\{tmpStrM}";
                } else {
                    tmpDate = intDate + intMonth + tmpStrM;
                }

            }
            date = tmpDate;
        }
        System.out.println(STR."\{date} е следващата валидна дата");
    }

    static void findNextValidDate2(List<String> list, String date) {
        int intDate, intMonth, intYear;

        try {
            intDate = Integer.parseInt(date.substring(0, 2));
            intMonth = Integer.parseInt(date.substring(3, 5));
            intYear = Integer.parseInt(date.substring(6));
        } catch (NumberFormatException ex) {
            System.out.println(STR."Формата на датата \{date} ти е Незаконен ;)");
            return;
        }

        while (!searchingMatchesDates(list, date)) {
            String tmpStr = date.substring(2);
            String tmpStrM = date.substring(5);
            String tmpDate = null;

            if (intDate >= 10 && intDate < 31) {
                intDate++;
                tmpDate = intDate + tmpStr;
            } else if (intDate > 0 && intDate < 10) {
                intDate++;
                tmpDate = STR."0\{intDate}\{tmpStr}";
            } else if (intDate >= 31) {
                intDate = 1;

                if (intMonth < 12) {
                    intMonth++;
                    if (intMonth < 10) {
                        tmpDate = STR."0\{intDate}.0\{intMonth}\{tmpStrM}";
                    } else {
                        tmpDate = intDate + intMonth + tmpStrM;
                    }
                } else {
                    intMonth = 1;
                    intYear++;
                    tmpDate = "0" + intDate + "." + "0" + intMonth + "." + intYear;
                }

            }
            date = tmpDate;
        }
        System.out.println(STR."\{date} е следващата валидна дата");
    }


    static boolean searchingMatchesDates(List<String> list, String date) {
        Pattern pattern = Pattern.compile(date);
        Matcher matcher;
        String res = null;

        for (int i = 0; i < list.size(); i++) {
            matcher = pattern.matcher(list.get(i));
            if (matcher.find()) {
                res = matcher.group();
                return true;
            }
        }
        return false;
    }

    static void findByType(List<String> list, String thisWord) {
        Pattern pattern = Pattern.compile(thisWord);
        Matcher matcher;
        String lastLine = "";
        int lastLineLength = 0, cnt = 0;
        double sum = 0.0;

        System.out.println();
        System.out.println("Търсенето е по: " + thisWord);
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
        System.out.printf("%.2f BGN%n", sum);
    }

    static void print(List<String> data) {
        for (int i = 0; i < data.size(); i++) {
            rowDataPrint(data, i, true);
        }
        System.out.println();
    }

    // Печата инфото от даден ред. idx - поредният индекс на реда в Листа.
    static int rowDataPrint(List<String> data, int idx, boolean isPrint) {
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

    // Печата елементите на масива от реда с индекс idx от List
    static void printListDataTmp(List<String> list, int idx, int idxA) {
        String[] tmp = list.get(idx).trim().split("-");
        for (int i = 0; i < tmp.length; i++) {
            System.out.println(i + ": " + tmp[i]);
        }
        System.out.println(idxA + ": " + tmp[idxA]);
        System.out.println();
    }
}
