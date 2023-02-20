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

//        String text = "Build a strong foundation of knowledge in Java programming! " +
//                "In this lesson we take a look at lists and their application!";



        countingWordsInTextCA(text);
        System.out.println();
        countingWordsInTextB(text);
        System.out.println();
        countingWordsInTextC(text);

    }

    private static void countingWordsInTextC(String text) {
        Map<String, Integer> wordsCounts = new LinkedHashMap<>();          // Map<Дума, Повторения>
        StringBuilder thisWord = new StringBuilder();
        int countThisWord = 0, j = 0;

        String[] words = text.trim().split(" ");
        while (j < words.length) {
            thisWord.append(words[j]);
            if (wordsCounts.containsKey(thisWord.toString())) {             // Има ли ключ съответстващ на тази дума?
                countThisWord = wordsCounts.get(thisWord.toString());       // ... дай ми на тази дума повторенията
                countThisWord++;
            } else {
                countThisWord = 1;
                //System.out.println(thisWord);
            }
            wordsCounts.put(thisWord.toString(), countThisWord);          //  Обново Map-a
            thisWord.delete(0, thisWord.length());
            j++;
        }
        for (Map.Entry<String, Integer> el : wordsCounts.entrySet()) {
            System.out.printf("%s -> %d%n", el.getKey(), el.getValue());

        }
    }
    private static void countingWordsInTextCA(String text) {
        Map<String, Integer> wordsCounts = new LinkedHashMap<>();          // Map<Дума, Повторения>
        int countThisWord = 0, j = 0;

        String[] words = text.trim().split(" ");
        while (j < words.length) {
            String currentWord = words[j];
            if (wordsCounts.containsKey(currentWord)) {             // Има ли ключ съответстващ на тази дума?
                countThisWord = wordsCounts.get(currentWord);       // ... дай ми на тази дума повторенията
                countThisWord++;
            } else {
                countThisWord = 1;
            }
            wordsCounts.put(currentWord, countThisWord);          //  Обново Map-a
            j++;
        }
        for (Map.Entry<String, Integer> el : wordsCounts.entrySet()) {
            System.out.printf("%s -> %d%n", el.getKey(), el.getValue());

        }
    }

    private static void countingWordsInTextA(String text) {
        Map<String, Integer> wordsCounts = new LinkedHashMap<>();
        StringBuilder thisWord = new StringBuilder();
        int indexFrom = 0, j = 0, index = 0;
        Integer countThisWord = null;


        while (j < text.length()) {
            if (text.indexOf(32, indexFrom) != -1) {
                indexFrom = text.indexOf(32, indexFrom) + 1;
                thisWord.append(text.substring(index, indexFrom - 1));
                index = indexFrom;

                if (wordsCounts.containsKey(thisWord.toString())) {                  //  if (countThisWord != null)....
                    countThisWord = wordsCounts.get(thisWord.toString());
                    countThisWord++;
                    wordsCounts.put(thisWord.toString(), countThisWord);
                    thisWord.delete(0, thisWord.length());
                } else {
                    countThisWord = 1;
                    wordsCounts.put(thisWord.toString(), countThisWord);
                    //System.out.println(thisWord);
                    thisWord.delete(0, thisWord.length());
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
        StringBuilder thisWord = new StringBuilder();
        int indexFrom = 0, j = 0, countThisWord = 0,
                index = 0, p = 0, indexA = 0;

        while (j < text.length()) {                                                // index = indexFrom = 0;
            p = indexA = 0;                                                        // text.indexOf(32, indexFrom) = 3 ->
            if (text.indexOf(32, indexFrom) != -1) {                           // -> индекса на " " = 32
                indexFrom = text.indexOf(32, indexFrom) + 1;                   // indexFrom = 3 + 1 = 4
                thisWord.append(text.substring(index, indexFrom - 1));                  // stb = You
                index = indexFrom;                                                 // index = 4
                while (p < text.length()) {                                        // TODO: използвай containsKey()...
                    if (text.indexOf(thisWord.toString(), indexA) != -1) {
                        countThisWord++;
                        indexA = text.indexOf(thisWord.toString(), indexA) + 1;
                    }
                    p++;
                }
                wordsCounts.put(thisWord.toString(), countThisWord);
                //System.out.println(thisWord);
                thisWord.delete(0, thisWord.length());
                countThisWord = 0;
            }
            j++;
        }
        for (Map.Entry<String, Integer> el : wordsCounts.entrySet()) {
            System.out.printf("%s -> %d%n", el.getKey(), el.getValue());
        }
    }
}
