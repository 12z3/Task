package task;

public class GenericS<T> {

    void print(T[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (!arr[i].equals(0)) System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
    
    public static void main(String[] args) {

        Integer[] inT = {1, 2, 0, 4, 5};
        Double[] douB = {1.1, 2.2, 0.0, 4.1, 5.3};
        String[] stR = {"a", "b", " " };
        Boolean[] booL = {false, true,};

        GenericS<Integer> printI = new GenericS<>();
        GenericS<Double> printD = new GenericS<>();
        GenericS<String> printS = new GenericS<>();
        GenericS<Boolean> printB = new GenericS<>();

        printI.print(inT);
        printD.print(douB);
        printS.print(stR);
        printB.print(booL);

    }
}
