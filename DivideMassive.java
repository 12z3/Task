package hackerRank;

import org.jetbrains.annotations.NotNull;
import training.Methods;
import java.util.Random;
import java.util.Scanner;

public class DivideMassive extends Methods {

    public static void fillMassive(int @NotNull [] massive) {
        Random random = new Random();
        for (int i = 0; i < massive.length; i++) {
            massive[i] = random.nextInt(11);
        }
    }

    public static void swapIndexes(int index1, int index2){
        int temp = 0;
        if (index1 > index2){
            temp = index1;
            index2 = index1;
            index1 = temp;
        }
    }

    public static void dividesMassive(int[] massive) {
        int massiveSize = massive.length;
        int leftSize = massive.length / 2;
        int rightSize = massiveSize - leftSize;

        int[] leftPart = new int[leftSize];
        int[] rightPart = new int[rightSize];

        if (massiveSize < 2){
            return;
        }

        for (int i = 0; i < leftSize; i++) {
            leftPart[i] = massive[i];
        }
        for (int j = leftSize; j < massiveSize; j++) {
            rightPart[j - leftSize] = massive[j];
        }
        //printInt1DArray(leftPart);
        //printInt1DArray(rightPart);
        System.out.println();

        dividesMassive(leftPart);
        dividesMassive(rightPart);

        //printInt1DArray(leftPart);
        //printInt1DArray(rightPart);
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        timeAndData();

        // {1, 23, 4, 5, 7, 9, 45};
        int[] massive = new int[11];

        fillMassive(massive);
        //printInt1DArray(massive);
        dividesMassive(massive);

    }
}
