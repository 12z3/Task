import java.util.Arrays;

public class test {
    public static void main(String[] args) {

//        int[] arr = {12, 11, 13, 5, 6};
//        insertionSort(arr);
//        System.out.println("Сортираният масив е: ");
//        for (int i : arr) {
//            System.out.print(i + " ");
//        }

        //String input = "1 1 1 2 2 3 3 3 4 4 5 6 7 8";
        String input = "1 3 1 2 1 3 4 3 4 3 5 6 2 8 3";
        frequentNumbers(input);
    }


    protected static void frequentNumbers(String input) {
        int cnt;
        String[] numbers = input.split(" ");
        int[] countMatches = new int[numbers.length];
        System.out.println(Arrays.toString(numbers));

        String[] digits = new String[numbers.length];
        Arrays.fill(digits, "-1");

        for (int i = 0; i < numbers.length; i++) {
            cnt = 1;
            if (isChecked(digits, numbers[i])) continue;
            for (int j = i + 1; j < numbers.length; j++) {
                if (numbers[i].equalsIgnoreCase(numbers[j])) cnt++;
            }
            digits[i] = numbers[i];
            countMatches[i] = cnt;
        }
        for (int i = 0; i < countMatches.length; i++) {
            if (countMatches[i] != 0) System.out.printf("%s: %s%n", numbers[i], countMatches[i]);
        }

        System.out.println(Arrays.toString(digits));
        System.out.println(Arrays.toString(countMatches));
    }

    protected static boolean isChecked(String[] arr, String el) {
        for (String string : arr) {
            if (string.equalsIgnoreCase(el)) return true;
        }
        return false;
    }

    public static void insertionSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int current = arr[i];
            int j = i - 1;

            // Преместване на елементите на arr[0..i-1], които са
            // по-големи от current, с една позиция напред
            // от тяхната текуща позиция
            while (j >= 0 && arr[j] > current) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            // Вмъкване на current на правилното място
            arr[j + 1] = current;
        }
    }
}
