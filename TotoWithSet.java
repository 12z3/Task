package Jackpot;

import java.util.*;

public class TotoWithSet {
	public static void main(String[] args) {
		List<List<Integer>> suppose = digitsGenerator(3);
		printRes(suppose);

		for (List<Integer> row : suppose) {
			System.out.print(checkMatchesByRow(row, suppose.get(0)) + "   ");
		}

		//System.out.println(stdOfRow(suppose.get(0)));

		System.out.println();
		printRes(generatorByMatches(suppose, 8));

	}

	private static void printRes(List<List<Integer>> suppose) {
		for (List<Integer> row : suppose) {
			for (int el : row) {
				System.out.print(el + " ");
			}
			System.out.println();
		}
	}

	private static List<List<Integer>> digitsGenerator(int cntOfRows) {
		List<List<Integer>> suppose = new ArrayList<>();
		Random rnd = new Random();
		Set<Integer> digits;
		int cnt = 0;

		while (cnt < cntOfRows) {
			digits = new HashSet<>();
			while (digits.size() < 6) {
				digits.add(rnd.nextInt(1, 50));
			}
			List<Integer> res = new ArrayList<>(digits);
			Collections.sort(res);
			suppose.add(res);
			cnt++;
		}
		return suppose;
	}

	private static List<Integer> sampleGenerator(int numOfDigits) {
		Random random = new Random();
		Set<Integer> digits = new HashSet<>();

		while (digits.size() < numOfDigits) {
			digits.add(random.nextInt(1, 50));
		}
		List<Integer> res = new ArrayList<>(digits);
		Collections.sort(res);
		return res;

	}


	private static List<List<Integer>> generatorByStd(List<Integer> lastDraw, double std) {
		int cnt = 0;
		double tmpStd = Integer.MIN_VALUE;
		List<Integer> tmpRow;
		List<List<Integer>> res = new ArrayList<>();

		while (cnt < 3) {
			tmpRow = new ArrayList<>();
			while (std != tmpStd) {
				tmpRow = sampleGenerator(6);
				tmpStd = stdOfRow(tmpRow);
			}
			Collections.sort(tmpRow);
			res.add(tmpRow);
			tmpStd = Integer.MIN_VALUE;
			cnt++;
		}
		return res;
	}


	private static List<List<Integer>> generatorByMatches(List<List<Integer>> input, int matchesSum) {
		int tmpSum = 0, cnt = 0;

		while (tmpSum <= matchesSum) {
			tmpSum = 0;
			input = generatorByStd(new ArrayList<>(List.of(6, 14, 17, 21, 30, 32)), 11);
			for (List<Integer> row : input) {
				tmpSum += checkMatchesByRow(row, new ArrayList<>(List.of(6, 14, 17, 21, 30, 32)));
			}
			cnt++;
		}
		System.out.println(cnt + " <- ");
		return input;
	}

	// Всяко едно число от row дали се съдържа в officialResult?
	private static int checkMatchesByRow(List<Integer> row, List<Integer> officialResult) {
		int matches = 0;
		for (Integer elOfRow : row) {
			for (Integer elOfOfficialResult : officialResult) {
				if (elOfRow.equals(elOfOfficialResult)) {
					matches++;
					break;
				}
			}
		}
		return matches;
	}

	private static double stdOfRow(List<Integer> row) {
		double avrByRow, sum = 0, avrSums = 0;

		for (int el : row) sum += el;
		avrByRow = sum / row.size();
		for (int el : row) {
			double x = (Math.pow((el - avrByRow), 2));
			avrSums += x;
		}
		return Math.sqrt(avrSums / row.size());
	}
}
