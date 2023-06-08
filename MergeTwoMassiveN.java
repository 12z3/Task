package dynamicsStructhure;

import training.Methods;

import java.util.Arrays;
import java.util.Scanner;

public class MergeTwoMassiveN extends Methods {

    /**
     * TODO: Направи ги и за двумерни масиви. Виж и "removeZeroElementsOfArray"
     * Слива два масива като изключва повтарящите се елементи.
     */
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        timeAndData();

        int[] a = {1, 2, 3, 5, 45, 66};
        int[] b = {11, 2, 3, 1, 34, 1, 121, 3};

        System.out.println("a = " + Arrays.toString(a));
        System.out.println("b = " + Arrays.toString(b));
        mergeTwoMassive(a, b);
    }

    public  static int findZeros(int[] a, int[] b){
        int countA = 0, countB = 0;
        boolean isMatch;

        for (int i = 0; i < a.length; i++) {
            isMatch = false;
            for (int j = 0; j < b.length; j++) {
                if (a[i] == b[j]) {
                    isMatch = true;
                    b[j] = 0;
                    countB++;
                }
            }
            if (isMatch) {
                a[i] = 0;
                countA++;
            }
        }
        return countA + countB;
    }

    public static void mergeTwoMassive(int[] a, int[] b) {
        int  idxA = 0, idxB = 0;

        int[] tmp = new int[(a.length + b.length) - (findZeros(a,b))];
        for (int i = 0; i < tmp.length; i++) {
            if (idxA < a.length) {
                if (a[idxA] == 0) {
                    idxA++;
                    i--;
                } else tmp[i] = a[idxA++];
            } else {
                if (idxB < b.length) {
                    if (b[idxB] == 0) {
                        idxB++;
                        i--;
                    } else tmp[i] = b[idxB++];
                }
            }
        }

        System.out.println("a = " + Arrays.toString(a));
        System.out.println("b = " + Arrays.toString(b));
        System.out.println("Result = " + Arrays.toString(tmp));
    }

    /**
     * Печата Не нулевите елементи в масива.
     *
     * @param arr "Масива който ще се печата"
     */

    // Проверява нулев ли е елемента. Ако е Да, брой и премини на следващия Индекс.
    // Ако ли не изпринтирай го. Като провериш И условието:
    // "if (index > countZeroElement)" -> условието за печатане на запетайте.
    // Използвам стойността на "countZeroElement" като индекс.

    // ВАЖНО:
    // Печата ", " ако е изпълнено условието -> (index > countZeroElement).
    // И едва След това печата елемента ако е различен от нула независимо от условието за запетаята.
    public static void printNoZeroIntDArray(int[] arr) {
        int count, countZeroElement = 0;

        System.out.print("[");
        for (int index = 0; index < arr.length; index++) {
            if ((arr[index] == 0)) {              // 33: index = 4, countZeroElement = 3;
                countZeroElement++;               // Брои нулевите елементи. {0, 0, 3, 0, 33, 0}
            } else {
                if (index > countZeroElement) {   // Ако индекса на текущия > Броя на нулевите елементи то... Печатай:
                    System.out.print(", ");       // ... Първо печата ", "... при изпълнено условие...
                }
                System.out.print(arr[index]);     // ... след това числото независимо от ", ". Важно е.
            }
        }
        System.out.print("]");
        System.out.println();
    }
}
