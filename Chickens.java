package task;

import java.util.HashMap;
import java.util.Map;

public class Chickens {
    public static void main(String[] args) {
        System.out.println(chickens(1));
        System.out.println(chickens(2));
        System.out.println(chickens(3));
        System.out.println(chickens(4));
        System.out.println(chickens(7));
        System.out.println(chickens(8));
        System.out.println(chickens(9));
        System.out.println(chickens(10));
    }


    private static long chickens(int day) {
        long oldChickens = 1, newChicks = 0, allChicks = 0, deathChicks = 0;
        long oldTime = 1, newTime = 0, rec = 0;
        Map<Integer, Long> chicks = new HashMap<>();
        int days = 1;

        chicks.put(days, oldChickens);
        if (day == 1) allChicks = oldChickens;

        for (int i = 2; i <= day; i++) {
            newChicks = oldChickens * 2;
            allChicks = (newChicks + oldChickens);
            oldChickens += newChicks;

            oldTime++;
            chicks.put(i,allChicks);

            int key = i - 6;
            if (key >= 0){
                rec = chicks.get(key + 1);
                chicks.remove(key + 1);
            }
            // if (i == day) System.out.println(chicks.get(i));
        }
        return allChicks - rec;
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
