package dynamicsStructhure;

import training.Methods;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
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
        int[] b = {11, 2, 3, 1, 34, 1, 121, 3, 89, 1, 99};
        int[] c = {0, 0, 3, 0, 45, 66};

        System.out.println("a = " + Arrays.toString(a));
        System.out.println("b = " + Arrays.toString(b));

        long s = System.currentTimeMillis();

        mergeTwoMassiveB(a, b);
        mergeTwoMassiveA(a, b);
        System.out.println();
        printNoZeroIntArray(c);

        long e = System.currentTimeMillis();
        System.out.println((double) (e - s) + " ms");
    }

    // ->  else mapB.replace(a[i], 1):  При условие, че mapB съдържа ключ a[i]
    // (масива b[] съдържа текущият елемент от масива а[]), то промени стойността на Value със ключ Key на 1;
    // -> if (mapB.get(b[i - a.length]) != 1): Ако mapB не съдържа ключ чието Value = 1,
    // то добави го към result. (Ако елемнта от масива b[] не се съдържа в масива a[] -> mapB<b[i], 0>)
    private static void mergeTwoMassiveA(int[] a, int[] b) {
        Map<Integer, Integer> result = new LinkedHashMap<>();
        Map<Integer, Integer> bMap = new LinkedHashMap<>();
        int size = a.length + b.length;

        for (int el : b) bMap.put(el, 0);
        for (int i = 0; i < size; i++) {
            if (i < a.length) {
                if (!bMap.containsKey(a[i])) {
                    result.put(a[i], 0);
                } else bMap.replace(a[i], 1);
            } else {
                if (bMap.get(b[i - a.length]) != 1) {
                    result.put(b[i - a.length], 0);
                }
            }
        }
        System.out.print("Result = ");
        for (Map.Entry<Integer, Integer> el : result.entrySet())
            System.out.print(el.getKey() + " ");
    }

    public static int findZeros(int[] a, int[] b) {
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

    public static void mergeTwoMassiveB(int[] a, int[] b) {
        int idxA = 0, idxB = 0;

        int[] result = new int[(a.length + b.length) - (findZeros(a, b))];
        for (int i = 0; i < result.length; i++) {
            if (idxA < a.length) {
                if (a[idxA] == 0) {
                    idxA++;
                    i--;
                } else result[i] = a[idxA++];
            } else {
                if (idxB < b.length) {
                    if (b[idxB] == 0) {
                        idxB++;
                        i--;
                    } else result[i] = b[idxB++];
                }
            }
        }

        System.out.println("a = " + Arrays.toString(a));
        System.out.println("b = " + Arrays.toString(b));
        System.out.println("Result = " + Arrays.toString(result));
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
    public static void printNoZeroIntArray(int[] arr) {
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
