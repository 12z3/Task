package task;

public class BinarySearch {

    /**
     * @Problem: https://practice.geeksforgeeks.org/problems/
     * binary-search-1587115620/1?page=1&curated[]=1&sortBy=submissions
     * Given a sorted array of size N and an integer K,
     * find the position at which K is present in the array using binary search.
     */
    public static void main(String[] args) {

        int[] a1 = {1, 2, 7, 9, 55, 4, 77, 13, 24, 54, 67, 18};   // 11; 5 - 77
        int[] a = {1, 2, 3, 4, 5};   // 11; 5 - 77
        System.out.println("index: " + binarySearch(a, 4) +
                " -> element: " + a[binarySearch(a, 4)]);

        System.out.println(binarySearch(a, 4));
    }

    public static int binarySearch(int[] array, int elementToSearch) {
        int startIndex = 0, endIndex, middleIndex = 0, elementIndex = 0;
        endIndex = array.length - 1;

        while (startIndex <= endIndex) {
            // middleIndex = (endIndex + startIndex) / 2;
            middleIndex = startIndex + (endIndex - startIndex) / 2;

            if (elementToSearch == array[middleIndex]) {
                return middleIndex;
            }else if (elementToSearch > array[middleIndex]) {
                startIndex = middleIndex + 1;
            } else if (elementToSearch < array[middleIndex]) {
                endIndex = middleIndex - 1;
            }
        }
        return -1;
    }
}
