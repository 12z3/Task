package task;

import java.util.*;

public class rndIssues {
    public static void main(String[] args) {
        int[] x = {1, 2, 3, 4, -5, 5, -7};  // 7 - 2 = 5
        int[] x1 = {-1, 0, 1, 2, -1, 4};  // 7 - 2 = 5
        int[] a = {1, 1, 1, 3, 3, 3, 3, 7, 7};  // 7 - 2 = 5
        int[] y = {1, 0, 3, 2, 0};
        int[] t = {13, 46, 24, 52, 20, 9};
        int[] t1 = {6, 1, 3, 2, 4, 3, 3, 3};
        int[] t2 = {1, 1, 4, 3};
        int[] t3 = {7, 1, 5, 2, 4, 3};

        System.out.println(Arrays.toString(x));
        System.out.println(Arrays.toString(rotatedArr(x, 3)));
        //System.out.println(Arrays.toString(rotatedArr1(x, 2)));

        System.out.println();

        System.out.println(Arrays.toString(a));
        for (Map.Entry<Integer, Integer> el : countDigitsMatch(a).entrySet()) System.out.print(el + "; ");
        System.out.println();
        for (Map.Entry<Integer, Integer> el : countDigitsMatch1(a).entrySet()) System.out.print(el + "; ");
        System.out.println();
        for (Map.Entry<Integer, Integer> el : countDigitsMatch2(a).entrySet()) System.out.print(el + "; ");
        System.out.println();
        System.out.println();
        System.out.println(Arrays.toString(swap(x, 1, 6)));

        System.out.println();

        int[] arr = {2, 2, 1, 1, 1, 2, 2};
        int ans = majorityElement(arr);
        System.out.println("The majority element is: " + ans);
        System.out.println();

        System.out.print("sumProblem: ");
        sumProblem(y, 3);

        System.out.println();
        System.out.println();
        selection_sort(t);

        System.out.println(Arrays.toString(x));
        System.out.println(Arrays.toString(skipIdx(x, 1)));
        System.out.println(Arrays.toString(skipIdx1(x, 1)));
        System.out.println();

        System.out.println("Three sum: ");
        threeSums(x1);

        System.out.println();
        numbers(t2);
        System.out.println();
        numbers1(t2);
        System.out.println();
        numbers2(t2);

    }

    static int[] rotatedArr(int[] a, int pivot) {
        int[] res = new int[a.length];

        for (int start = 0, end = a.length - 1 - start, k = 0; start < res.length; start++, end--) {
            if (end >= pivot) res[start] = a[end];
            else res[start] = a[k++];
        }
        return res;
    }

    static int[] rotatedArr1(int[] a, int pivot) {
        int[] tmp = new int[pivot];
        int count = 0;

        for (int i = 0, c = 0; i < a.length; i++) {
            if (i < pivot) tmp[i] = a[i];
            if (i < a.length - tmp.length) {
                a[i] = a[a.length - i - 1];
                count++;
            } else a[count++] = tmp[c++];
        }
        return a;
    }

    static int[] swap(int[] a, int pos1, int pos2) {
        int tmp = a[pos1];
        a[pos1] = a[pos2];
        a[pos2] = tmp;
        return a;
    }

    static Map<Integer, Integer> countDigitsMatch(int[] a) {
        Map<Integer, Integer> digitsCount = new LinkedHashMap<>();

        for (int j : a) digitsCount.put(j, 0);
        for (int j : a) {
            if (digitsCount.containsKey(j)) {
                digitsCount.put(j, digitsCount.get(j) + 1);
            }
        }
        return digitsCount;
    }

    static Map<Integer, Integer> countDigitsMatch1(int[] a) {
        Map<Integer, Integer> countDigits = new HashMap<>();
        //Ако мапа не съдържа ключа  a[i] то добави го със стойност 1;
        // В противен случай презапиши стойността на ключ a[i] с еденица от горе.
        for (int i = 0; i < a.length; i++) {
            if ((!countDigits.containsKey(a[i]))) {
                countDigits.put(a[i], 1);
            } else {
                countDigits.put(a[i], countDigits.get(a[i]) + 1);
            }
        }
        return countDigits;
    }

    static Map<Integer, Integer> countDigitsMatch2(int[] a) {
        Map<Integer, Integer> countDigits = new HashMap<>();
        //Ако мапа не съдържа ключа  a[i] то добави го със стойност 1;
        // В противен случай презапиши стойността на ключ a[i] с еденица от горе.
        for (int i = 0; i < a.length; i++) {
            // Дай ми стойността на ключа a[i] в противен случай(ако няма такава) върни 0,1...или каквото там си задал
            // в defaultValue в случая 0.
            int count = countDigits.getOrDefault(a[i], 0);
            countDigits.put(a[i], count + 1);
        }
        return countDigits;
    }



    // https://takeuforward.org/data-structure/two-sum-check-if-a-pair-with-given-sum-exists-in-array/
    static void sumProblem(int[] a, int target) {              // 0, 1, 2, 3    t = 4    - Key
        Map<Integer, Integer> tmp = new HashMap<>();           // 2, 3, 1, 4    t = 4    - Value
        Map<Integer, Integer> res = new HashMap<>();


        for (int i = 0; i < a.length; i++) tmp.put(i, a[i]);
        for (int i = 0; i < a.length; i++) {
            int selected = tmp.get(i);
            int searchingEl = (target - selected);
            tmp.remove(i);
            if (tmp.containsValue(searchingEl)) {
                res.put(a[i], searchingEl);
            }
        }

        for (Map.Entry<Integer, Integer> el : res.entrySet())
            System.out.print(el.getKey() + " -> " + el.getValue() + "; ");

    }

    // https://takeuforward.org/data-structure/find-the-majority-element-that-occurs-more-than-n-2-times/
    public static int majorityElement(int[] v) {            // 2, 2, 1, 1, 1, 2, 2
        //size of the given array:
        int n = v.length;
        int cnt = 0; // count
        int el = 0;  // Element

        //applying the algorithm:
        // Предполага се, че първият елемент се среща най-много. И започвам да броя:
        // Ако следващият е == на този ел то cnt++, aко ли не то cnt--. При положение, че cnt == 0,
        // то този елемент ще бъде нарочен за максимално срещащ се. И цялата галимация с броенето се повтаря.
        for (int i = 0; i < n; i++) {
            if (cnt == 0) {
                cnt = 1;
                el = v[i];
            } else if (el == v[i]) cnt++;
            else cnt--;
        }

        // checking if the stored element
        // is the majority element:
        int cnt1 = 0;
        for (int i = 0; i < n; i++) {
            if (v[i] == el) cnt1++;
        }

        if (cnt1 > (n / 2)) return el;
        return -1;
    }

    public static int majorityElement1(int []v) {
        //size of the given array:
        int n = v.length;

        //declaring a map:
        HashMap<Integer, Integer> mpp = new HashMap<>();           // mpp -> {0,0} {0,0} {0,0}

        //storing the elements with its occurrence:
        for (int i = 0; i < n; i++) {
            int value = mpp.getOrDefault(v[i], 0);       // v[i] - Key, count - Value
            mpp.put(v[i], value + 1);
        }

        //searching for the majority element:
        for (Map.Entry<Integer, Integer> it : mpp.entrySet()) {
            if (it.getValue() > (n / 2)) {
                return it.getKey();
            }
        }

        return -1;
    }

    // https://takeuforward.org/sorting/selection-sort-algorithm/
    // Взима 1-ят ел и го Обявява за мин. Сравнява го със всички останали. Ако е най-малкият си остава на мястото.
    // Ако ли Не то разменя местата на този ел с най-малкият от всички останали елементи.
    static void selection_sort(int arr[]) {
        int n = arr.length;                           // 13, 46, 24, 52, 20, 9
        for (int i = 0; i < n - 1; i++) {
            int mini = i;                             // <-  !!!
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[mini]) {
                    mini = j;
                }
            }
            //swap
            int temp = arr[mini];
            arr[mini] = arr[i];
            arr[i] = temp;
        }

        System.out.println("After selection sort:");
        for (int i = 0; i < n; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    // https://www.youtube.com/watch?v=RBSGKlAvoiM&list=PLWKjhJtqVAbn5emQ3RRG8gEBqkhf_5vxD

    static int[] skipIdx(int[] a, int idx) {
        int[] tmp = new int[a.length - 1];
        for (int i = 0, j = 0; i < a.length; i++, j++) {
            if (i == idx) {
                j--;
            } else tmp[j] = a[i];
        }
        a = tmp;
        return a;
    }

    static int[] skipIdx1(int[] a, int idx) {
        int[] tmp = new int[a.length - 1];
        int j = 0;
        for (int i = 0; i < a.length; i++) {
            if (i != idx) tmp[j++] = a[i];
        }
        a = tmp;
        return a;
    }

    // https://takeuforward.org/data-structure/3-sum-find-triplets-that-add-up-to-a-zero/
    static void threeSums(int[] a) {
        //List<List<Long>> res = new ArrayList<>();
        List<Integer> res = new ArrayList<>();
        List<Integer> tmp;
        List<Integer> tmpA = new ArrayList<>();
        int[] x = null;
        int tmpRes = 0;

        for (int i = 0; i < a.length; i++) {
            tmp = new ArrayList<>();
            for (int j = i + 1; j < a.length; j++) {
                tmpRes = -1 * (a[i] + a[j]);
                if (tmp.contains(tmpRes)) {
//                    tmpA.add(a[i]);
//                    tmpA.add(a[j]);
//                    tmpA.add(tmpRes);

                    x = new int[]{a[i], a[j], tmpRes};
                    Arrays.sort(x);
                    System.out.println(Arrays.toString(x));
                } else tmp.add(a[j]);
                //tmpA.clear();
                x = null;
            }
        }
    }

    // https://www.youtube.com/watch?v=2D0D8HE6uak     <-  Find the Missing and Repeating Number

    static void numbers(int[] a) {                               // 1,2,2,3,4, -> 6,4,1,2,3,2  sum - sum1 = xNum
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        int count = 1, missing, maxCount = 0, maxIdx = -1, Idx = -1;
        int sum = 0, sum1 = 0, repeatingEl;

        for (int i = 0; i < a.length; i++) {
            if (a[i] < min) min = a[i];
            if (a[i] > max) max = a[i];
        }

        for (int j = min; j <= max; j++) {
            count = 0;
            for (int i = 0; i < a.length; i++) {
                if (a[i] == j) {
                    count++;
                    if (count == 1) maxIdx = i;
                    // first Idx = if (count == 1) maxIdx = i;
                    // Last Idx = maxIdx = i;
                }
            }
            if (count > maxCount) {
                maxCount = count;
                Idx = maxIdx;
            }
        }

        repeatingEl = (count >= 1) ? a[Idx] : -1;
        if (repeatingEl == -1) maxCount = -1;

        for (int i = 0; i < a.length; i++) sum += a[i];
        for (int i = 1; i <= max; i++) sum1 += i;
        missing = Math.abs((sum - (maxCount - 1) * a[Idx]) - sum1);

        System.out.println(Arrays.toString(a));
        System.out.printf("missing el = %d, repeating el = %d -> %d times", missing, repeatingEl, maxCount);
    }

    static void numbers1(int[] a) {
        int max = Integer.MIN_VALUE;
        int count, maxCount = 0, missingEl = 0, repeatingEl = 0;

        for (int i = 0; i < a.length; i++) {
            if (a[i] > max) max = a[i];
        }
        for (int i = 1; i <= max; i++) {
            count = 0;
            for (int j = 0; j < a.length; j++) {
                if (a[j] == i) count++;
            }
            if (count > 1) {
                repeatingEl = i;
                maxCount = count;
            } else if (count == 0) missingEl = i;
        }
        System.out.printf("missing el = %d, repeating el = %d -> %d times", missingEl, repeatingEl, maxCount);
    }

    static void numbers2(int[] a) {
        int value, max = Integer.MIN_VALUE, missingEl = 0, repeatingEl = 0;
        int[] tmp = new int[a.length + 1];

        for (int i = 0; i < a.length; i++) {
            value = tmp[a[i]];
            tmp[a[i]] = ++value;
        }
        for (int i = 1; i < tmp.length; i++) {
            if (tmp[i] > max) {
                max = tmp[i];
                repeatingEl = i;
            }
            if (tmp[i] == 0 && i > 1) missingEl = i;
        }

        System.out.println(Arrays.toString(a));
        System.out.println(Arrays.toString(tmp));
        System.out.printf("missing el = %d, repeating el = %d -> %d times", missingEl, repeatingEl, max);
    }
}
