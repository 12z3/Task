package dynamicsStructure.dynamicsStructure;

import java.util.*;

// https://www.youtube.com/watch?v=DjYZk8nrXVY
public class tmp {
	public static void main(String[] args) {
		int[] a = {1, 2, 3, 4, 5, 6, 7, 8, 9};
		int[] b = new int[a.length];
		int sum, fromIdx = 1, toIdx = 5;

		for (int i = 0; i < a.length; i++) {
			sum = 0;
			for (int j = 0; j <= i; j++) {
				sum += a[j];
			}
			b[i] = sum;
		}
		System.out.println("a[]: " + Arrays.toString(a));
		System.out.println("b[]: " + Arrays.toString(b));
		System.out.printf("Разликата между числата с индекси " +
						"%d(%d) и %d(%d) в масив b[] e %d = " +
						"Сумата на числата между числата в масив a[] с индекси %d(%d) и %d(%d)%n",
				fromIdx, b[fromIdx], toIdx, b[toIdx - 1],
				b[toIdx - 1] - b[fromIdx], fromIdx,
				a[fromIdx], toIdx, a[toIdx]);

		System.out.println(Arrays.toString(randomSwap(a)));
		System.out.println(Arrays.toString(randomListMemo(a)));
		pointers(a);

		int[][] tmp = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
		for (int[] row : tmp) {
			for (int el : row) {
				System.out.print(el + " ");
			}
			System.out.println();
		}

		System.out.println();
		for (int i = 0; i < tmp.length; i++) {
			for (int j = 0; j < tmp[i].length; j++) {
				System.out.print(tmp[i][j] + " ");
			}
			System.out.println();
		}
	}

	static int[] randomSwap(int[] a) {
		int idx;
		Random random = new Random();

		for (int i = 0; i < a.length; i++) {
			idx = random.nextInt(a.length);
			int tmp = a[i];
			a[i] = a[idx];
			a[idx] = tmp;
		}
		return a;
	}

	static int[] randomListMemo(int[] a) {
		Random random = new Random();
		List<Integer> memo = new ArrayList<>();
		int[] b = new int[a.length];
		int idx;

		for (int i = 0; i < a.length; i++) {
			idx = random.nextInt(a.length);
			if (!memo.contains(a[idx])) {
				b[i] = a[idx];
				memo.add(a[idx]);
			} else i--;
		}
		return b;
	}

	static void pointers(int[] a) {
		int left = 0, right = a.length - 1;
		while (left <= right) {
			System.out.println(a[left] + " <-> " + a[right]);
			if (left == right) System.out.println("Middle el: " + a[left]);
			left++;
			right--;
		}
	}
}
