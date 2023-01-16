package task;

import java.util.LinkedHashMap;
import java.util.Map;

public class WordCounting {

    /**
     Намаира колко пъти всяка една дума се съдържа в текста.
     Идеята: Търси индекса на " ". Съставя дума и търси колко пъти тази дума се среща в текста.
     */

    public static void main(String[] args) {

        String text = "You have seen the use of the print and format() seen have methods use" +
                " to print output the print with have to You formatted numbers print output you ";

        Map<String, Integer> words = new LinkedHashMap<>();
        System.out.println(text.length());
        int indexFrom = 0, j = 0, countThisWord = 0, index = 0, p = 0, indexA = 0;
        StringBuilder stb = new StringBuilder();

        while (j < text.length()) {                                                // index = indexFrom = 0;
            p = indexA = 0;                                                        // text.indexOf(32, indexFrom) = 3 ->
            if (text.indexOf(32, indexFrom) != -1) {                           // -> индекса на " " = 32
                indexFrom = text.indexOf(32, indexFrom) + 1;                   // indexFrom = 3 + 1 = 4
                stb.append(text.substring(index, indexFrom - 1));                  // stb = You
                index = indexFrom;                                                 // index = 4
                while (p < text.length()) {
                    if (text.indexOf(stb.toString(), indexA) != -1) {
                        countThisWord++;
                        indexA = text.indexOf(stb.toString(), indexA) + 1;
                    }
                    p++;
                }
                words.put(stb.toString(), countThisWord);
                //System.out.println(stb);
                stb.delete(0, stb.length());
                countThisWord = 0;
            }
            j++;
        }
        for (Map.Entry<String, Integer> el : words.entrySet()) {
            System.out.printf("%s -> %d%n", el.getKey(), el.getValue());
        }


    }
}
