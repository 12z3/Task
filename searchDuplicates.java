package task;

public class searchDuplicates {

    /**
     * {1, 1, 3, 3, 4, 5, 2, 1, 3, 4, 1};
     * Element 1 = finds 4 times
     * Element 3 = finds 3 times
     * Element 4 = finds 2 times
     * Element 5 = finds 1 times
     * Element 2 = finds 1 times
     * 4 0 3 0 2 1 1 0 0 0 0
     */

    public static void main(String[] args) {
        int[] a = {1, 1, 3, 3, 4, 5, 2, 1, 3, 4, 1};

        searchDuplicatesM(a);
    }

    private static void searchDuplicatesM(int[] a) {
        int[] matches = new int[a.length];

        for (int i = 0; i < a.length; i++) {
            int elCount = 1, el1 = a[i], index = i + 1;

            if (i == 0) {
                elCount = findDuplicated(a, elCount, el1, index);
            } else {
                if (!isOccur(a, a[i], i)) {
                    elCount = findDuplicated(a, elCount, el1, index);
                } else continue;
            }
            matches[i] = elCount;
            System.out.printf("Element %d = finds %d times %n", el1, elCount);
        }

        for (int el : matches) System.out.print(el + " ");
    }

    private static int findDuplicated(int[] a, int elCount, int el1, int index) {
        while (index < a.length) {
            int el2 = a[index];
            if (el1 == el2) {
                elCount++;
            }
            index++;
        }
        return elCount;
    }

    private static boolean isOccur(int[] a, int el, int elIndex) {
        for (int j = elIndex - 1; j >= 0; j--) {
            if (el == a[j]) return true;
        }
        return false;
    }
}
