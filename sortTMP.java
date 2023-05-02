package task;

public class sortTMP {
    public static void main(String[] args) {

        int[] arr = {1, 5, 4, 2, 3, 9, 7, 4};

        sortA(arr);
        System.out.println();
        sortB(arr);
    }

    private static void sortB(int[] arr) {                        // 1, 5, 4, 2, 3, 9, 7, 4}
        int minIndex;
        for (int i = 0; i < arr.length; i++) {
            minIndex = i;
            for (int j = i; j < arr.length; j++) {
                if (arr[minIndex] > arr[j]) minIndex = j;
            }
            int tmp = arr[i];
            arr[i] = arr[minIndex];
            arr[i] = tmp;
        }
        for (int el : arr) System.out.print(el + " ");
    }

    private static void sortA(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int elA = arr[i];
            for (int j = 0; j < arr.length; j++) {
                int elB = arr[j];
                if (elA < elB) {
                    int tmp = arr[j];
                    arr[j] = arr[i];
                    arr[i] = tmp;
                }
            }
        }

        for (int el : arr) System.out.print(el + " ");
    }
}
