package hackerRank;

import training.Methods;
import java.util.Random;
import java.util.Scanner;

class MergeSort extends Methods {

    // https://www.youtube.com/watch?v=bOk35XmHPKs   <--

    int[] numbers = new int[10];

    private static void mergeSort(int[] inputArray) {          // Разделя основният масив на подмасиви.
        int inputLength = inputArray.length;

        if (inputLength < 2) {                                 // ДЪНОТО на РЕКУРСИЯТА !!!  <--
            return;
        }

        int midIndex = inputLength / 2;
        int[] leftHalf = new int[midIndex];
        int[] rightHalf = new int[inputLength - midIndex];     // За да работи и с масиви с НЕЧЕТНИ дължини.

        for (int i = 0; i < midIndex; i++) {                   // Запълва ЛЕВИЯТ масив.
            leftHalf[i] = inputArray[i];
        }                        // За да започне от 0-я "започвайки" от "midIndex" трябва: index [0] = [i - midIndex].
        for (int i = midIndex; i < inputLength; i++) {         // Запълва ДЕСНИЯТ масив.
            rightHalf[i - midIndex] = inputArray[i];           // ХИТРО Брат....Заа да започне от "0"-ия ИНДЕКС.
        }

        mergeSort(leftHalf);                           // РЕКУРСИВНО  mergeSort върху leftHalf и като ПРИКЛЮЧИ -
        mergeSort(rightHalf);                          // РЕКУРСИВНО  mergeSort върху rightHalf.
        merge(inputArray, leftHalf, rightHalf);        // Слива двата масива в един.
    }

    private static void merge(int[] inputArray, int[] leftHalf, int[] rightHalf) {
        int leftSize = leftHalf.length;
        int rightSize = rightHalf.length;

        int i = 0, j = 0, k = 0;                          // while == fori(i,j,k - индекси)
                                                          // Обединява вече сортираните подмасиви. time: 3:30.
        while (i < leftSize && j < rightSize) {           // Докато не свършат елементите от ляво И от дясно -
            if (leftHalf[i] <= rightHalf[j]) {            // Към inputArray добавя елементи на leftHalf или rightSize.
                inputArray[k] = leftHalf[i];
                i++;
            } else {
                inputArray[k] = rightHalf[j];
                j++;
            }
            k++;
        }

        while (i < leftSize) {                                 // Добавя ОСТАНАЛИТЕ елементи от ЛЕВИЯТ -
            inputArray[k] = leftHalf[i];                       // масив в inputArray, тези който са били по-големи.
            i++;
            k++;
        }

        while (j < rightSize) {                               // Добавя ОСТАНАЛИТЕ елементи от ДЕСНИЯТ -
            inputArray[k] = rightHalf[j];                     // масив в inputArray, тези който са били по-големи.
            j++;
            k++;
        }
    }

    private static void printArray(int[] numbers) {
        for (int i = 0; i < numbers.length; i++) {
            System.out.println(numbers[i]);
        }
    }


    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        timeAndData();

        Random rand = new Random();
        int[] numbers = new int[10];

        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = rand.nextInt(1000000);
        }

        System.out.println("Before:");
        printArray(numbers);

        mergeSort(numbers);

        System.out.println("\nAfter:");
        printArray(numbers);
    }


}
