package hackerRank;

import training.Methods;

import java.util.Arrays;
import java.util.Scanner;

public class MergeTwoMassive extends Methods {


    /**
     * TODO: Направи ги и за двумерни масиви.
     *       Виж и "removeZeroElementsOfArray"
     * Слива два масива като изключва повтарящите се елементи.
     * Ако съвпадат -> "Нищо не прави". Ако ли не запиши елемента в нов масив и го разпечатай.
     */
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        timeAndData();

        int[] a = {1, 2, 3, 4, 5, 56, 47};
        int[] b = {11, 2, 33, 4, 5, 12, 34};

        System.out.println("a = " + Arrays.toString(a));
        System.out.println("b = " + Arrays.toString(b));
        mergeTwoMassive(a, b);
    }

    public static void mergeTwoMassive(int[] a, int[] b) {
        int count = 0;
        boolean isMatch = false, isTrue = false;
        int[] mergeMassive = new int[a.length + b.length];
        int[] tmp = new int[a.length + b.length];

        try {
            for (int i = 0; i < a.length; i++) {     // Сравнява всеки елементи от единият масив с всеки ел. от другия.
                isMatch = false;
                for (int j = 0; j < a.length; j++) {
                    if (a[i] == b[j]) {
                        tmp[j] = b[j];              // На място "j" на което е b[j] записва стойността a[i] в tmp[j].
                        count++;
                        isMatch = true;
                    }
                }
                if (isMatch) {                 // При съвпадение мини на следващият Индекс "i".
                    continue;
                } else {                       // Ако няма съвпадение на елемента на i-я индекс го копира в новия масив.
                    mergeMassive[i] = a[i];
                    mergeMassive[a.length + i] = b[i];
                }
            }
        } catch (Exception e) {
            System.out.print("There's something wrong in Denmark....");
        }

        System.out.println("MergeMassive = " + Arrays.toString(mergeMassive));
        System.out.print("Printing massive: ");
        printNoZeroIntDArray(mergeMassive);

        System.out.printf("Match's %d -> ", count);
        printNoZeroIntDArray(tmp);
    }

    /**
     * Печата Не нулевите елементи в масива.
     *
     * @param arr "Масива който ще се печата"
     */

    // Проверява нулев ли е елемента. Ако е Да, брой и премини на следващия Индекс.
    // Ако ли не изпринтирай го. Като провериш И условието:
    // "if (index > countZeroElement)" -> условието за печатане на запетайте.

    // ВАЖНО:
    // Печата ", " ако е изпълнено условието -> (index > countZeroElement).
    // И едва След това печата елемента ако е различен от нула независимо от условието за запетаята.
    public static void printNoZeroIntDArray(int[] arr) {
        boolean isMatch = false;
        int count = 0, countZeroElement = 0;
        System.out.print("[");

        for (int index = 0; index < arr.length; index++) {
            if ((arr[index] == 0)) {
                countZeroElement++;               // Брои нулевите елементи. {0,0,3,0,33,0}
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
