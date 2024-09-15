package exlFinance;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class eDSK extends eFinance {

	// todo: Файла от ДСК е екселски. Преформатирай го в csv. -> https://cloudconvert.com/xls-to-csv
	// findByDateN:
	// areThereValidDates(getFirstAndLastRowsFromList; getIntValuesFromDateSTR);
	// getIntValuesFromDateSTR;
	// getFirstAndLastRowsFromList;
	// searchingMatchesDates
	// isDateIntoBorders(getIntValuesFromDateSTR);
	// findNextValidDateFrom(searchingMatchesDates; getIntValuesFromDateSTR);
	// getDateIdx;
	// rowDataPrint;
	// getMoneyByLine

	public static void main(String[] args) {
		File file = new File("" + "");
		List<String> allData = readData(file);

		//findNextValidDateM(allData, "13.06.2024");
		//String[] a = getFirstAndLastRowsFromList(allData);

		//System.out.println(areDatesValid(allData, "02.03.2021", "15.06.2024"));
		//System.out.println(findNextValidDateM(allData, "08.06.2024"));

		getTransactions(file);
		findByType(allData, "заплата");
		findByType(allData, "евн");
		findByDateN(allData, "30.12.2023", "13.06.2024");
	}

	private static void findByDateN(List<String> list, String fromDate, String toDate) {
		int cnt = 0, cnt1 = 0, cnt2 = 0, lastIdx = 0;
		double sum = 0.0;
		String fromDate1, fromDate2, toDate2, toDate1;
		System.out.println();

		if (!areThereValidDates(list, fromDate, toDate)) {
			System.out.printf("404: Омазал си датите ;) ! (%s до %s)%n", fromDate, toDate);
			return;
		}
		System.out.println("Oт дата " + fromDate + " до дата " + toDate + ": ");

        //Масив: [ден, месец,година]
		int[] intArrFromDate = getIntValuesFromDateSTR(fromDate);
		int[] intArrToDate = getIntValuesFromDateSTR(toDate);

		String[] pivotDate = getFirstAndLastRowsFromList(list);
		//int[] intArrPivotFromDate = getIntValuesFromDateSTR(pivotDate[0]);
		//int[] intArrPivotToDate = getIntValuesFromDateSTR(pivotDate[1]);

        // Подережда ел на листа в възходящ ред по дати 2023->2024->2025
		if (intArrFromDate[2] > intArrToDate[2]) {    // 2024, 2023 -> 2023, 2024
			String tmp = fromDate;
			fromDate = toDate;
			toDate = tmp;
		} else if ((intArrFromDate[2] == intArrToDate[2]) && (intArrFromDate[1] > intArrToDate[1])) {
			String tmp = fromDate;
			fromDate = toDate;
			toDate = tmp;
		} else if (((intArrFromDate[2] == intArrToDate[2]) && (intArrFromDate[1] == intArrToDate[1])) &&
				intArrFromDate[0] > intArrToDate[0]) {
			String tmp = fromDate;
			fromDate = toDate;
			toDate = tmp;
		}

		if (searchingMatchesDates(list, fromDate) == -1) {
			System.out.printf
					("\"%s\" е невалидна дата. Интервала от дати е: от %s до %s%n"
							, fromDate, pivotDate[1], pivotDate[0]);
			fromDate1 = findNextValidDateDown(list, fromDate);
			fromDate2 = findNextValidDateUp(list, fromDate);
			System.out.printf("Следващата валидна дата е \"%s\" или \"%s\"%n", fromDate1, fromDate2);
			//System.out.println("Следващата валидна дата е " + "\"" + fromDate + "\"");
			cnt1++;
		}
		if (searchingMatchesDates(list, toDate) == -1) {
			System.out.printf
					("%n\"%s\" е невалидна дата. Интервала от дати е: от %s до %s%n"
							, toDate, pivotDate[1], pivotDate[0]);
			//findNextValidDateTo(list, toDate){}
//            if (isDateIntoBorders(toDate, pivotDate[0], pivotDate[1])) {
//                toDate = findNextValidDateFrom(list, toDate);
//            } else toDate = findNextValidDateTo(list, toDate);

			toDate1 = findNextValidDateDown(list, toDate);
			toDate2 = findNextValidDateUp(list, toDate);

			System.out.printf("Следващата валидна дата е \"%s\" или \"%s\"%n", toDate1, toDate2);
			//System.out.println("Следващата валидна дата е " + "\"" + toDate + "\"" + " или " + "\"");
			cnt2++;
		}
		if (cnt1 != 0 || cnt2 != 0) return;

		int fromIdx = getDateIdx(list, fromDate, toDate)[0];
		int toIdx = getDateIdx(list, fromDate, toDate)[1];
		printResByDate(list, fromIdx, toIdx, sum, lastIdx, cnt);
	}


	private static void printResByDate(List<String> list, int fromIdx, int toIdx,
									   double sum, int lastIdx, int cnt) {
		for (double i = fromIdx; i <= toIdx; i++) {
			rowDataPrint(list, (int) i, true);
			sum += getMoneyByLine(list, (int) i);
			lastIdx = (int) i;
			cnt++;
		}
		System.out.printf(" - %d транзакции%n", cnt);
		System.out.print("Общо: ");
		// rowDataPrint(list, (int) i, true): печата printCalcSpaces(data, spacesOnThisLine, -15) интервала.
		// printSpaces: Доотпечатва останалите проклети интервали.
		int tmpIdx = rowDataPrint(list, lastIdx, false);
		printSpaces(tmpIdx - "Общо".length());
		System.out.printf("%.2f BGN%n", sum);
	}

	private static void swap(int[] arrFrom, int[] arrTo, String fromDate, String toDate) {
		if (arrFrom[2] > arrTo[2]) {    // 2024, 2023 -> 2023, 2024
			String tmp = fromDate;
			fromDate = toDate;
			toDate = tmp;
		} else if ((arrFrom[2] == arrTo[2]) && (arrFrom[1] > arrTo[1])) {
			String tmp = fromDate;
			fromDate = toDate;
			toDate = tmp;
		} else if (((arrFrom[2] == arrTo[2]) && (arrFrom[1] == arrTo[1])) &&
				arrFrom[0] > arrTo[0]) {
			String tmp = fromDate;
			fromDate = toDate;
			toDate = tmp;
		}
	}

	// Въпросът е една дата от горната страна на горната граница ли е, между двете граници ли е или е от долната страна
	// на долната граница. Това определя как ще се търси следващата валидна дата.
	private static boolean isDateIntoBorders(String date, String highBorderDate, String lowBorderDate) {
		int[] intArrDate = getIntValuesFromDateSTR(date);
		int[] intArrHighBorderDate = getIntValuesFromDateSTR(highBorderDate);
		int[] intArrLowBorderDate = getIntValuesFromDateSTR(lowBorderDate);

		if (intArrDate[2] > intArrHighBorderDate[2]) {
			return false;
		} else {
			if (intArrDate[1] > intArrHighBorderDate[1]) {
				return false;
			} else {
				if (intArrDate[0] > intArrHighBorderDate[0]) {
					return false;
				}
			}
		}
		return true;
	}


	private static int[] getDateIdx(List<String> list, String fromDate, String toDate) {
		int[] res = new int[2];
		int fromIdx = 0, toIdx = 0;
		boolean findFirst = false;
		String tmp;

		Matcher matcherFrom, matcherTo;
		String fromDate1 = fromDate.replace("\"", "");
		String toDate1 = toDate.replace("\"", "");

		Pattern fromPattern = Pattern.compile("\\b" + toDate1 + "\\b");
		Pattern toPattern = Pattern.compile("\\b" + fromDate1 + "\\b");

		for (int i = 0; i < list.size(); i++) {
			// 02.01.2024,516849xxxxxx5165 ТЕГЛЕНЕ НА ATM   -> 01.01.2024 <-   12:52,BGR,,КАРТОВА ОПЕРАЦИЯ,,,,"50,00"
			// В даден ред започващ с дата: 02.01.2024 може да се съдържа дата: 01.01.2024, която да си задал като
			// граница. В този случай Матчера ще я мачне и ще върне дата 02.01.2024 като валидна (01.01.2024)
			// .split("-")[0] -> оправя точно този проблем.
			matcherFrom = fromPattern.matcher(list.get(i).trim().split("-")[0]);
			matcherTo = toPattern.matcher(list.get(i).trim().split("-")[0]);

			if (!findFirst && matcherFrom.find()) {
				fromIdx = i;
				findFirst = true;
				matcherFrom.reset();
			} else if (findFirst && matcherTo.find()) {
				toIdx = i;
			}
		}
		res[0] = fromIdx;
		res[1] = toIdx;
		return res;
	}


	// Проверки: Формат, +/- за Ден, месец, година. 30/31 ден. 13 месец.
	private static boolean areThereValidDates(List<String> list, String fromDate, String toDate) {

		if (fromDate.length() < 10 || toDate.length() < 10) return false;
		List<Integer> highMonthsDays = List.of(1, 3, 5, 7, 8, 10, 12);
		List<Integer> lowMonthsDays = List.of(4, 6, 9, 11);

        // Репери: Датиоте на първият и последният ред от листа
		String[] pivotDate = getFirstAndLastRowsFromList(list);
		String pivotFromDate = pivotDate[0];
		String pivotToDate = pivotDate[1];

		String pivotFromYearStr = pivotFromDate.split("\\.")[2];
		String pivotFromMonthStr = pivotFromDate.split("\\.")[1];
		String pivotFromDayStr = pivotFromDate.split("\\.")[0];

		String pivotToYearStr = pivotToDate.split("\\.")[2];
		String pivotToMonthStr = pivotToDate.split("\\.")[1];
		String pivotToDayStr = pivotToDate.split("\\.")[0];

        // Датите които търсиш
		String fromYearStr = fromDate.split("\\.")[2];
		String fromMonthStr = fromDate.split("\\.")[1];
		String fromDayStr = fromDate.split("\\.")[0];

		String toYearStr = toDate.split("\\.")[2];
		String toMonthStr = toDate.split("\\.")[1];
		String toDayStr = toDate.split("\\.")[0];


        //Масив: [ден, месец,година]
		int[] pivotIntArrFromDate = getIntValuesFromDateSTR(pivotFromDate);
		int[] intArrFromDate = getIntValuesFromDateSTR(fromDate);
		int[] pivotIntArrToDate = getIntValuesFromDateSTR(pivotToDate);
		int[] intArrToDate = getIntValuesFromDateSTR(toDate);

		// 12.01.1975 - length = 10
		if (((fromYearStr.length() > pivotFromYearStr.length()) || (toYearStr.length() > pivotToYearStr.length()))
				|| ((fromMonthStr.length() > pivotFromMonthStr.length())
				|| (toMonthStr.length() > pivotToMonthStr.length()))
				|| ((fromDayStr.length() > pivotFromDayStr.length()) || (toDayStr.length() > pivotToDayStr.length()))) {
			return false;
		}

		if ((intArrFromDate[2] > pivotIntArrFromDate[2]) || (intArrToDate[2] < pivotIntArrToDate[2])) {
			System.out.printf("Годината: %d е криминална%n", intArrFromDate[2]);
			System.out.printf("Диапазона е от %d до %d%n", pivotIntArrFromDate[2], pivotIntArrToDate[2]);
			return false;
		}

		if (intArrFromDate[0] < 0 || intArrToDate[0] < 0 || intArrFromDate[1] < 0 || intArrToDate[1] < 0) {
			System.out.println("404: Денят и месеца трябва да са положителни числа");
			return false;
		}

		if ((intArrFromDate[1] > 12 || intArrFromDate[1] <= 0) || ((intArrToDate[1] > 12) || intArrToDate[1] <= 0)) {
			System.out.println("404: Месеца не може да е по-голям от 12 или отрицателен");
			return false;
		}

		if ((highMonthsDays.contains(intArrFromDate[1]) && intArrFromDate[0] > 31)
				|| (lowMonthsDays.contains(intArrFromDate[1]) && intArrFromDate[0] > 30)) {
			System.out.printf("Датата %s е невалидна. (Денят е > 31 или < 0)%n", fromDate);
			return false;
		}
		if ((highMonthsDays.contains(intArrToDate[1]) && intArrToDate[0] > 31)
				|| (lowMonthsDays.contains(intArrToDate[1]) && intArrToDate[0] > 30)) {
			System.out.printf("Датата %s е невалидна. (Денят е > 31 или < 0)%n", toDate);
			return false;
		}

		if (intArrFromDate[2] % 4 != 0) {
			if ((intArrFromDate[0] > 28 && intArrFromDate[1] == 2) || (intArrToDate[0] > 28 && intArrToDate[1] == 2)) {
				System.out.println("Февруари май няма 29 дни");
				return false;
			}
		} else {
			if ((intArrFromDate[0] > 29 && intArrFromDate[1] == 2) || (intArrToDate[0] > 29 && intArrToDate[1] == 2)) {
				System.out.println("Февруари май няма 30 дни");
				return false;
			}
		}
		return true;
	}


	private static int[] getIntValuesFromDateSTR(String date) {
		int day = 0, month = 0, year = 0;
		int[] res = new int[3];

		try {
			day = Integer.parseInt(date.substring(0, 2));
			month = Integer.parseInt(date.substring(3, 5));
			year = Integer.parseInt(date.substring(6, 10));
		} catch (NumberFormatException ex) {
			System.out.printf("404: Формата на Дата \"%s\" не е ОК%n", date);
		}
		res[0] = day;
		res[1] = month;
		res[2] = year;
		return res;
	}

	private static String generateSTRDate(int[] intArrDate) {
		StringBuilder date = new StringBuilder();
		if (intArrDate[0] < 10) {
			if (intArrDate[1] >= 10) {
				date.append(0).append(intArrDate[0]).append('.')
						.append(intArrDate[1]).append('.').append(intArrDate[2]);
			} else date.append(0).append(intArrDate[0])
					.append('.').append(0).append(intArrDate[1]).append('.').append(intArrDate[2]);
		} else {
			if (intArrDate[1] >= 10) {
				date.append(intArrDate[0]).append('.').append(intArrDate[1]).append('.').append(intArrDate[2]);
			} else date.append(intArrDate[0]).append('.')
					.append(0).append(intArrDate[1]).append('.').append(intArrDate[2]);
		}
		return String.valueOf(date);
	}

	static String[] getFirstAndLastRowsFromList(List<String> list) {
		String[] res = new String[2];
		res[0] = list.getFirst().split("-")[0];
		res[1] = list.get(list.size() - 2).split("-")[0];
		return res;
	}

	private static String findNextValidDateUp(List<String> list, String date) {
		List<Integer> highMonthsDays = List.of(1, 3, 5, 7, 8, 10, 12);
		List<Integer> lowMonthsDays = List.of(2, 4, 6, 9, 11);
		int[] tmp = new int[3];
		int tmpDay, attempts = 0;

		// arrIntDate[] = [12,01,1975]; Генерирай дата и провери за съвпадения.
		int[] arrIntDate = getIntValuesFromDateSTR(date);
		while (searchingMatchesDates(list, date) == -1) {
			if (highMonthsDays.contains(arrIntDate[1])) {
				if (arrIntDate[0] < 31) {           // ако деня е < 31 увеличи деня с 1
					tmpDay = ++arrIntDate[0];
					tmp[0] = tmpDay;                // ден
					tmp[1] = arrIntDate[1];         // месец
					tmp[2] = arrIntDate[2];         // година
					date = generateSTRDate(tmp);
					arrIntDate = tmp;
				} else {
					if (arrIntDate[1] < 12) {      // ако деня == 31, но месеца < 12 увеличи месеца с 1
						tmp[0] = 1;
						tmp[1] = ++arrIntDate[1];
						tmp[2] = arrIntDate[2];
						date = generateSTRDate(tmp);
						arrIntDate = tmp;
					} else {                        // ако деня == 31 и месеца == 12 то увеличи годината с 1
						tmp[0] = 1;
						tmp[1] = 1;
						tmp[2] = ++arrIntDate[2];
						date = generateSTRDate(tmp);
						arrIntDate = tmp;
					}
				}
			} else if (lowMonthsDays.contains(arrIntDate[1])) {
				if (arrIntDate[0] < 30) {
					tmpDay = ++arrIntDate[0];
					tmp[0] = tmpDay;
					tmp[1] = arrIntDate[1];
					tmp[2] = arrIntDate[2];
					date = generateSTRDate(tmp);
					arrIntDate = tmp;
				} else {
					if (arrIntDate[1] <= 12) {
						tmp[0] = 1;
						tmp[1] = ++arrIntDate[1];
						tmp[2] = arrIntDate[2];
						date = generateSTRDate(tmp);
						arrIntDate = tmp;
					} else {
						tmp[0] = 1;
						tmp[1] = 1;
						tmp[2] = ++arrIntDate[2];
						date = generateSTRDate(tmp);
						arrIntDate = tmp;
					}
				}
			}
			attempts++;
			if (attempts > list.size()) return "404: Не съществува друга валидна дата";
		}
		return date;
	}

    // arrIntDate[] = [12,01,1975]; Генерирай дата и провери за съвпадения.
	private static String findNextValidDateDown(List<String> list, String date) {
		List<Integer> highMonthsDays = List.of(1, 3, 5, 7, 8, 10, 12);
		List<Integer> lowMonthsDays = List.of(2, 4, 6, 9, 11);
		int[] tmp = new int[3];
		int tmpDay, attempts = 0;

		// arrIntDate[] = [12,01,1975]
		int[] arrIntDate = getIntValuesFromDateSTR(date);
		while (searchingMatchesDates(list, date) == -1) {
			if (highMonthsDays.contains(arrIntDate[1])) {
				if (arrIntDate[0] > 1) {           // ако деня е > 1
					tmpDay = --arrIntDate[0];
					tmp[0] = tmpDay;
					tmp[1] = arrIntDate[1];
					tmp[2] = arrIntDate[2];
					date = generateSTRDate(tmp);
					arrIntDate = tmp;
				} else {
					if (arrIntDate[1] > 1) {       // ако деня == 1,  но месеца > 1
						tmp[0] = 31;
						tmp[1] = --arrIntDate[1];
						tmp[2] = arrIntDate[2];
						date = generateSTRDate(tmp);
						arrIntDate = tmp;
					} else {                        // ако деня и месеца == 1, то намали годината с 1
						tmp[0] = 1;
						tmp[1] = 1;
						tmp[2] = --arrIntDate[2];
						date = generateSTRDate(tmp);
						arrIntDate = tmp;
					}
				}
			} else if (lowMonthsDays.contains(arrIntDate[1])) {
				if (arrIntDate[0] > 1) {
					tmpDay = --arrIntDate[0];
					tmp[0] = tmpDay;
					tmp[1] = arrIntDate[1];
					tmp[2] = arrIntDate[2];
					date = generateSTRDate(tmp);
					arrIntDate = tmp;
				} else {
					if (arrIntDate[1] > 1) {
						tmp[0] = 30;
						tmp[1] = --arrIntDate[1];
						tmp[2] = arrIntDate[2];
						date = generateSTRDate(tmp);
						arrIntDate = tmp;
					} else {
						tmp[0] = 1;
						tmp[1] = 1;
						tmp[2] = --arrIntDate[2];
						date = generateSTRDate(tmp);
						arrIntDate = tmp;
					}
				}
			}
			attempts++;
			if (attempts > list.size()) return "404: Не съществува друга валидна дата";
		}
		return date;
	}

	// todo: Добави Максимална и Минимална сума със съответните дати. findByType -> return List<>.
	static void findByType(List<String> list, String thisWord) {
		Pattern pattern = Pattern.compile(thisWord.toUpperCase());
		Matcher matcher;
		int lastLineLength = 0, cnt = 0;
		double sum = 0.0, avg = 0.0;

		System.out.println();
		System.out.println("Търсенето е по: " + "\"" + thisWord.toUpperCase() + "\"");
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
		System.out.println(" - " + cnt + " транзакции");
		System.out.print("Общо: ");
		// rowDataPrint в този си вид когато е извикан печата допълнително printSpaces(data, spacesOnThisLine, -15); <-
		// За това когато изпълниш System.out.println(); и след това printSpaces(spaces); има разминавания
		int spaces = rowDataPrint(list, lastLineLength, false) + 2;
		//System.out.println();

		printSpaces(spaces - "Общо: ".length());
		System.out.printf("%.2f BGN%n", sum);

		System.out.print(thisWord.toUpperCase() +": "+ "Усреднена сума: ");
		int spacesA = rowDataPrint(list, lastLineLength, false) + 2;
		//System.out.println();
		printSpaces(spacesA - "Усреднена сума: ".length() - (thisWord.toUpperCase().length() + 2));
		System.out.printf("%.2f BGN%n", sum / cnt);
	}

	private static void getTransactions(File file) {
		List<String> dataList = readData(file);
		List<String> typeList = new ArrayList<>();

		String tmpRes, empty = " " + "\n";
		int cnt = 0;
		for (String line : dataList) {
			String[] tmp = line.trim().split("-");
			tmpRes = checkLine(tmp);
			if (!typeList.contains(tmpRes)) {
				typeList.add(tmpRes);
				cnt++;
			}
		}

		System.out.println();
		for (String el : typeList) System.out.printf("%s%n", el);
		System.out.println(empty.repeat(1));
	}

    //line[] = ["16.09.2024", "15.09.2024 ЕЛ Е...", "ЕЛЕКТPОХОЛДПPОД...", "BG15STSA9300000...", "КОМУНАЛНИ УСЛУГ...",
    // +4 more]
	static String checkLine(String[] line) {
		StringBuilder res = new StringBuilder();
		String[] tmpTxt;

		if (Character.isDigit(line[1].charAt(0))) {
			tmpTxt = line[1].split(" ");
			res.append(tmpTxt[1]).append(line[2]).append(line[3]);
		} else {
			if (line[1].charAt(0) == 'А') {
				res.append(line[1], 0, 5);
			} else if (line[1].charAt(0) == 'З') {
				res.append(line[1], 0, 7);
			} else res.append(line[1]);
		}
		return res.toString();
	}

	static void print(List<String> data) {
		for (int i = 0; i < data.size(); i++) {
			rowDataPrint(data, i, true);
		}
		System.out.println();
	}

	static void printSpaces(int spaces) {
		for (int i = 0; i < spaces; i++) {
			System.out.print(" ");
		}
	}
}
