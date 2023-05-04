package task;

public class ChristmasTree {
    public static void main(String[] args) {
        int n = 9;
        for (int row = 1; row < n; row++) {
            if (row == 1) {
                printTree("*", n, row);
            } else printTree("# ", n, row);
        }

        tree();
    }

    private static void printTree(String symbol, int n, int row) {
        System.out.print(space(n - row));
        System.out.print(symbol(symbol, row));
        printText(row);
    }

    private static void printText(int row) {
        if (row == 5) {
            System.out.print(space(row));
            System.out.print("Наздраве Младежи!");
        }
        System.out.println();
    }

    protected static StringBuilder symbol(String text, int count) {
        StringBuilder stb = new StringBuilder();
        stb.append(text.repeat(Math.max(0, count)));
//        for (int i = 0; i < count; i++) {
//            stb.append(text);
//        }
        return stb;
    }

    protected static String space(int count) {
        String space = " ";
        return (space.repeat(Math.max(0, count)));
    }

    private static void tree() {
        int i = 0, n = 5, k = 1;
        char star = '*', space = ' ';

        while (i < n) {
            StringBuilder line = new StringBuilder();

            line.append(space);
            String spaceCount = line.toString().repeat((n - i));
            System.out.print(spaceCount);
            line.delete(0, line.length());

            line.append(star);
            String starsCount = line.toString().repeat(Math.max(0, i++ + k++));
            System.out.println(starsCount);

        }
    }
}
