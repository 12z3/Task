package dynamicsStructure.dynamicsStructure;

import java.lang.reflect.Array;
import java.util.*;

public class Person {

	// Необходимо ли е да се пазят два параметъра в една векичина то -> Class
	/*
	az
	23
	to
	32
	we
	23
	 */

	private String name;
	private int age;

	Person(String name, int age) {
		this.name = name;
		this.age = age;
	}

	public String getName() {
		return this.name;
	}

	public int getAge() {
		return this.age;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Person[] people = new Person[150];
		String name = "";
		int age = 0;
		people[25] = new Person("Ivan", 25);
		people[35] = new Person("Tom", 35);
		people[45] = new Person("Jack", 45);

		ArrayList<Person> peopleS;
		Map<Integer, List<Person>> agePeople = new HashMap<>();
		int cnt = 0;

		while (cnt < 3) {
			name = scanner.next();
			age = scanner.nextInt();

			if (!agePeople.containsKey(age)) {
				peopleS = new ArrayList<>();
				peopleS.add(new Person(name, age));
				agePeople.put(age, peopleS);
			} else {
				agePeople.get(age).add(new Person(name, age));        //agePeople.get(age) == List<Person> -> peopleS
			}
			cnt++;
		}

		for (Map.Entry<Integer, List<Person>> el : agePeople.entrySet()) {
			System.out.print(el.getKey() + " : ");
			for (Person el1 : el.getValue()) {
				System.out.print(el1.getName() + "; ");
			}
			System.out.println();
		}

		int[][] matrix = {{1, 2, 3},
				{4, 5, 6},
				{7, 8, 9}};
		//spiralMatrix(matrix);
		spiralA(matrix);
	}

	static void spiralMatrix(int[][] a) {
		StringBuilder res = new StringBuilder();
		int[][] tmp = new int[3][3];
		int row = 3, col = 3, cnt = 0, len = 0, x = 0;

		for (int i = 0; i < row; i++) {
			for (int j = i; j < col; j++) {
				len++;

				if (len < 6) {
					if (i % 2 == 0) {
						res.append(a[i][j]);
					} else {
						res.append(a[j][col - 1]);
					}
				} else {
					x = (len + 1) - ((i + j * 2));
					res.append(a[i][x]);

				}

			}
		}
		System.out.println(res);
		System.out.println(len);
	}

	static void spiralA(int[][] a) {
		int row = 3, col = 3;
		for (int i = 0; i < row; i++) {
			if (i == 0) {
				printHCol(a, 0, 0, 3);
			} else if (i == row - 1) {
				printVCol(a, 2, 1, 3);
				printRHCol(a, 2, 1, 0);
				printRVCol(a, 0, 1, 1 );
			}


		}
	}

	static void printHCol(int[][] a, int rowIdx, int fromColIdx, int toColIdx) {
		StringBuilder stb = new StringBuilder();
		for (int i = fromColIdx; i < toColIdx; i++) {
			stb.append(a[rowIdx][i]);
		}
		System.out.print(stb);
	}

	static void printVCol(int[][] a, int colIdx, int fromColIdx, int toRowIdx) {
		StringBuilder stb = new StringBuilder();
		for (int i = fromColIdx; i < toRowIdx; i++) {
			stb.append(a[i][colIdx]);
		}
		System.out.print(stb);
	}

	static void printRHCol(int[][] a, int rowIdx, int fromColIdx, int toColIdx) {
		StringBuilder stb = new StringBuilder();
		for (int i = fromColIdx; i >= toColIdx; i--) {
			stb.append(a[rowIdx][i]);
		}
		System.out.print(stb);
	}

	static void printRVCol(int[][] a, int colIdx, int fromColIdx, int toColIdx) {
		StringBuilder stb = new StringBuilder();
		for (int i = fromColIdx; i >= toColIdx; i--) {
			stb.append(a[i][colIdx]);
		}
		System.out.print(stb);
	}

}
