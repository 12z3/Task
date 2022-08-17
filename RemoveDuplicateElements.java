package hackerRank;

import org.jetbrains.annotations.NotNull;
import training.Methods;

import java.util.Arrays;
import java.util.Scanner;

public class RemoveDuplicateElements extends Methods {
    private static int @NotNull [] removeDuplicateElements(int[] arr) {
        int countDuplicate = 0, index = 0;
        boolean isChecked = false;
        int[] checkedElements = new int[arr.length];

        for (int i = 0; i < arr.length; i++) {
            isChecked = false;

            if (i != 0) {
                for (int j = 0; j < checkedElements.length; j++) {
                    if (arr[i] == checkedElements[j]) {                            // Търси съвпадения
                        isChecked = true;
                        break;
                    }
                }
            }
            if (isChecked) continue;                    // arr = {1, 2, 1, 1, 2, 2, 3, 4}
            // checkedElements[i] = arr[i];             // checkedElements = {1, 2, 0, 0, 0, 0, 3, 4} = non-duplicated

            for (int j = i + 1; j < arr.length; j++) {             // Не стига до тук ако е проверяван елемента -
                if (arr[i] == arr[j]) {                    // и има съвпадение -> if (arr[i] == checkedElements[j])
                    countDuplicate++;
                }
            }
            checkedElements[i] = arr[i];                    // checkedElements = {1, 2, 0, 0, 0, 0, 3, 4}
        }
        int mach = 0;
        int[] tmp = new int[arr.length - countDuplicate];

        for (int i = 0; i < checkedElements.length; i++) {
            if (checkedElements[i] != 0) {
                tmp[index++] = checkedElements[i];

            }
//        for (int i = 0; i < arr.length; i++) {
//            mach = 1;
//            for (int j = i + 1; j < arr.length; j++) {
//                if (arr[i] == arr[j]) {
//                    mach++;
//                }
//            }
//            if (mach == 1) {
//                tmp[index++] = arr[i];                 // Записва елементи с Индекси -> 1, 4, 5
//            }                                          // Първо добавя елемента в tmp[l], след това увеличава л с 1.
        }
        arr = tmp;
        return arr;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        timeAndData();

        int[] aaa = {1, 2, 1, 1, 2, 2, 3, 4};
        System.out.println(Arrays.toString(removeDuplicateElements(aaa)));
    }
}
