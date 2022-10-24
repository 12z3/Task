package task.hackerRank;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SomeOperations {                                // Collections.singleton(String.valueOf(a)) = int от a -> в String a
    public static void main(String[] args) {
        List<Integer> aa = new ArrayList<>(List.of(1, 2, 3));
        List<Integer> bb = new ArrayList<>(List.of(11, 12, 13));

        List<String> result = new ArrayList<>();

        result.addAll(Collections.singleton(String.valueOf(aa)));
        result.addAll(Collections.singleton(String.valueOf(bb)));


        System.out.println(String.join("-", result));
        System.out.println(result);

        int count = 0;
        String some = "ala bala up down up";
        System.out.println(some.indexOf("up"));

        System.out.println(some.substring(0, 5));


    }
}
