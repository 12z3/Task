package task;

import training.Methods;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

public class MyMap extends Methods {

    // mapH.entrySet() + "option == Alt" + "Enter" -> Iterate

    public static void main(String[] args) {

        long startTime = getStartTime();

        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i <= 10; i++) {
            map.put(i,i);
        }

        for (Integer el: map.keySet()) System.out.print(el + " ");
        System.out.println();
        for (int i = 0; i < map.size(); i++) System.out.print(map.get(i) + " ");
        System.out.println();
        for (Map.Entry<Integer,Integer> el: map.entrySet()) System.out.print(el + " ");


        Map<String, Integer> mapH = new HashMap<>(); // Не гарантира, че ще се разпечатат във реда в който са въведени.
        mapH.put("one", 1);                          // ... просто ги съхранява.
        mapH.put("two", 2);
        mapH.put("three", 3);

        for (Map.Entry<String, Integer> el: mapH.entrySet()) {
            System.out.printf("%s -> %d%n", el.getKey(), el.getValue());
        }

        Map<Integer, Integer> mapT = new TreeMap<>();                   // Елементите са сортирани в него по Key.
        mapT.put(1, 3);                                                 // Key = String -> по азбучен ред.
        mapT.put(2, 2);                                                 // Key = Integer -> по възходящ ред.
        mapT.put(3, 1);
        System.out.println();

        for(Map.Entry<Integer, Integer> el: mapT.entrySet()){
            System.out.printf("%s -> %d%n", el.getKey(), el.getValue());
        }

        Map<Integer, Integer> mapL = new LinkedHashMap<>();             // Както са въведени така и ще се разпечатат.
        mapL.put(3, 3);
        mapL.put(2, 2);
        mapL.put(1, 1);
        System.out.println();

        for (Map.Entry<Integer, Integer> el: mapL.entrySet()){
            System.out.printf("%s -> %d%n", el.getKey(), el.getValue());
        }
        getEndTime();
    }
}
