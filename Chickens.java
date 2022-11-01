package task;

import java.util.HashMap;
import java.util.Map;

public class Chickens {
    public static void main(String[] args) {
        for (int i = 1; i <= 10; i++) {
            System.out.println(chickens(i));
        }

        //System.out.println(chickens(10));
    }


    private static long chickens(int day) {
        long oldChickens = 1, newChicks = 0, allChicks = 0, deathChicks = 0;
        long oldTime = 1, newTime = 0, rec = 0;
        Map<Integer, Long> chicks = new HashMap<>();
        int days = 1;

        chicks.put(days, oldChickens);
        if (day == 1) allChicks = oldChickens;

        for (int i = 2; i <= day; i++) {
            oldChickens -= rec;
            newChicks = oldChickens * 2;
            allChicks = (newChicks + oldChickens);
            oldChickens += newChicks;

            oldTime++;
            chicks.put(i,newChicks);

            int key = i - 5;
            if (key >= 1){
                rec = chicks.get(key);
                //chicks.remove(key + 1);
            }
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
