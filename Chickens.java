package task;

import java.util.HashMap;
import java.util.Map;

public class Chickens {

    /**
     * @Source: <a href="https://practice.geeksforgeeks.org/problems/chicks-in-a-zoo1159/1">...</a>
     * @Problem: Initially, the zoo have a single chick.
     * A chick gives birth to 2 chicks every day and the life expectancy of a chick is 6 days.
     * Zoo officials want to buy food for chicks so they want to know the number of chicks on an Nth day.
     * Help the officials with this task.
     * K: V
     * 1: 1
     * 2: 3
     * 3: 9
     * 4: 27
     * 5: 81
     * 6: 243
     * 7: 726
     * 8: 2172
     * 9: 6498
     * 10: 19440
     */
    public static void main(String[] args) {
        for (int i = 1; i <= 10; i++) {
            System.out.println(i + ": " + chickens(i));
        }

        //System.out.println(chickens(10));
    }


    private static long chickens(int day) {
        long oldChickens = 1, newChicks = 0, allChicks = 0, death = 0;
        Map<Integer, Long> chicks = new HashMap<>();
        int days = 1;

        chicks.put(days, oldChickens);
        if (day == 1) allChicks = oldChickens;

        for (int i = 2; i <= day; i++) {
            oldChickens -= death;
            newChicks = oldChickens * 2;
            allChicks = (newChicks + oldChickens);
            oldChickens += newChicks;

            chicks.put(i, newChicks);                                      // Key: деня, Value: броя нови.

            int key = i - 5;                                               // На 6-я ден key = 0;
            if (key >= 1) death = chicks.get(key);                         // death = броя преди 6 дни.

            // if (i == day) System.out.println(chicks.get(i));
        }
        return allChicks;
    }

//    public long oldChicks(int N) {
//        // Code here
//        long oldChickens = 1, newChicks = 0, allChicks = 0;
//        long oldTime = 1, newTime = 0;
//
//        for (int i = 1; i <= N; i++) {
//            if (i == 1) {
//                allChicks = 1;
//                continue;
//            } else {
//                oldTime++;
//                newChicks = oldChickens * 2;
//                allChicks = newChicks + oldChickens;
//                if (oldTime % 6 == 0) {
//                    oldChickens -= 1;
//                    oldTime = newTime;
//                }
//
//                newTime++;
//                oldChickens += newChicks;
//            }
//        }
//        return allChicks;
//    }
}
