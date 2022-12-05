package task;

public class RotateMatrix {
    public static void main(String[] args) {

        //TODO: Rotate matrix on 90 degree:
        int[][] b = new int[3][3];

        for (int col = b.length - 1, p = 1; col >= 0; col--) {
            for (int row = 0; row < b[col].length; row++) {
                b[row][col] = p++;
            }
        }

        //int[][] bb = b;
        int[][] bb = new int[3][3];
        bb = b;
        for (int row = 0; row < bb.length; row++) {
            for (int col = 0; col < bb[row].length; col++) {
                System.out.print(bb[row][col] + " ");
            }
            System.out.println();
        }
    }
}
