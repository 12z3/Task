package task;

public class SearchDuplicates {

    /**
     * <p>
     * Виж: GitHiB -> inTime -> RemoveDuplicateElements
     * {1, 1, 3, 3, 4, 5, 2, 1, 3, 4, 1};
     * {4, 0, 3, 0, 2, 1, 1, 0, 0, 0, 0}; - > counter of matches
     * </p>
     * Взима първият "1" и брои колко пъти се среща в следващите.
     * Взима втория. Проверява дали е проверяван. Ако Е прескача на следващия.
     * В противен случай извършва проверката и за него.
     * <p>
     * Element 1 = finds 4 times
     * Element 3 = finds 3 times
     * Element 4 = finds 2 times
     * Element 5 = finds 1 times
     * Element 2 = finds 1 times
     * </p>
     */

    public static void main(String[] args) {
        int[] a = {1, 1, 3, 3, 4, 5, 2, 1, 3, 4, 1};
             // = {4, 0, 3, 0, 2, 1, 1, 0, 0, 0, 0};
        searchDuplicates(a);
    }

    private static void searchDuplicates(int[] arr) {
        int[] matches = new int[arr.length];

        for (int i = 0; i < arr.length; i++) {
            int matchesCounter = 1, el1 = arr[i], index = i + 1;

            if (i == 0) {
                matchesCounter = findDuplicated(arr, el1, index);
            } else {
                if (!isChecked(arr, arr[i], i)) {
                    matchesCounter = findDuplicated(arr, el1, index);
                } else continue;
            }
            matches[i] = matchesCounter;
            System.out.printf("Element %d = finds %d times %n", el1, matchesCounter);
        }

        for (int el : matches) System.out.print(el + " ");
    }

    private static int findDuplicated(int[] arr, int el1, int index) {
        int matches = 1;
        while (index < arr.length) {
            int el2 = arr[index];
            if (el1 == el2) {
                matches++;
            }
            index++;
        }
        return matches;
    }

    private static boolean isChecked(int[] arr, int el, int index) {
        for (int j = index - 1; j >= 0; j--) {
            if (el == arr[j]) return true;
        }
        return false;
    }
}
