package task;

import java.util.LinkedHashMap;
import java.util.Map;

public class WordCounting {

    /**
     * Намаира колко пъти всяка една дума се съдържа в текста.
     * Идеята: Търси индекса на " ". Съставя дума и търси колко пъти тази дума се среща в текста.
     */

    public static void main(String[] args) {

        String text = "You You have numbers seen the use of output the print and format() seen have methods use" +
                " to print output the print with have to You the formatted numbers print output seen you ";

        countingWordsInTextA(text);
        System.out.println();
        countingWordsInTextB(text);
        System.out.println();
        countingWordsInTextC(text);

    }

    private static void countingWordsInTextC(String text) {
        Map<String, Integer> wordsCounts = new LinkedHashMap<>();
        StringBuilder stb = new StringBuilder();
        int countThisWord = 0, j = 0;

        String[] word = text.trim().split(" ");
        while (j < word.length) {
            stb.append(word[j]);
            if (wordsCounts.containsKey(stb.toString())) {
                countThisWord = wordsCounts.get(stb.toString());
                countThisWord++;
            } else {
                countThisWord = 1;
                //System.out.println(stb);
            }
            wordsCounts.put(stb.toString(), countThisWord);
            stb.delete(0, stb.length());
            j++;
        }
        for (Map.Entry<String, Integer> el : wordsCounts.entrySet()) {
            System.out.printf("%s -> %d%n", el.getKey(), el.getValue());

        }
    }

    private static void countingWordsInTextA(String text) {
        Map<String, Integer> wordsCounts = new LinkedHashMap<>();
        StringBuilder stb = new StringBuilder();
        int indexFrom = 0, j = 0, index = 0;
        Integer countThisWord = null;


        while (j < text.length()) {
            if (text.indexOf(32, indexFrom) != -1) {
                indexFrom = text.indexOf(32, indexFrom) + 1;
                stb.append(text.substring(index, indexFrom - 1));
                index = indexFrom;

                if (wordsCounts.containsKey(stb.toString())) {                          //  if (countThisWord != null)....
                    countThisWord = wordsCounts.get(stb.toString());
                    countThisWord++;
                    wordsCounts.put(stb.toString(), countThisWord);
                    stb.delete(0, stb.length());
                } else {
                    countThisWord = 1;
                    wordsCounts.put(stb.toString(), countThisWord);
                    //System.out.println(stb);
                    stb.delete(0, stb.length());
                }
            }
            j++;
        }
        for (Map.Entry<String, Integer> el : wordsCounts.entrySet()) {
            System.out.printf("%s -> %d%n", el.getKey(), el.getValue());
        }
    }

    private static void countingWordsInTextB(String text) {
        Map<String, Integer> wordsCounts = new LinkedHashMap<>();
        StringBuilder stb = new StringBuilder();
        int indexFrom = 0, j = 0, countThisWord = 0,
                index = 0, p = 0, indexA = 0;

        while (j < text.length()) {                                                // index = indexFrom = 0;
            p = indexA = 0;                                                        // text.indexOf(32, indexFrom) = 3 ->
            if (text.indexOf(32, indexFrom) != -1) {                           // -> индекса на " " = 32
                indexFrom = text.indexOf(32, indexFrom) + 1;                   // indexFrom = 3 + 1 = 4
                stb.append(text.substring(index, indexFrom - 1));                  // stb = You
                index = indexFrom;                                                 // index = 4
                while (p < text.length()) {                                        // TODO: използвай containsKey()...
                    if (text.indexOf(stb.toString(), indexA) != -1) {
                        countThisWord++;
                        indexA = text.indexOf(stb.toString(), indexA) + 1;
                    }
                    p++;
                }
                wordsCounts.put(stb.toString(), countThisWord);
                //System.out.println(stb);
                stb.delete(0, stb.length());
                countThisWord = 0;
            }
            j++;
        }
        for (Map.Entry<String, Integer> el : wordsCounts.entrySet()) {
            System.out.printf("%s -> %d%n", el.getKey(), el.getValue());
        }
    }
}
