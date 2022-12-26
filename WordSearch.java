package task;

import training.Methods;

import java.util.*;

public class WordSearch extends Methods {

    // Word 'aba' is matched 4 times in text: ababababa
    // Char 'a' is matched 5 times in text: ababababa

    // That words are: 'qikar' and 'picas.'
    // Different symbol is:
    // Index: 0, Char: q
    // Index: 2, Char: k
    // Index: 4, Char: r
    // Are that words same?: false

    public static void main(String[] args) {



        //String line = "astasta la vista babyastasta";
        //String word = "asta";

        String line = "ababababa";
        String word = "aba";

        String wordA = "qikar";
        String wordB = "picas";
        char a = 'a';

        wordSearchInText(line, word);
        charSearchInText(line, a);

        System.out.printf("Are that words same?: %s", compareTwoWords(wordA, wordB));

        //System.out.println(compareWords(wordA, wordB));
    }

    public static void wordSearchInText(String text, String word) {
        int count = 0, index = 0, fromIndex = 0, j = 0;

        while (j < text.length()) {
            index = text.indexOf(word, fromIndex);
            if (index != -1) {
                fromIndex = index + 1;
                count++;
            }
            j++;
        }
        System.out.printf
                ("Word '%s' is matched %d times in text: %s%n",
                        word, count, text);
    }

    public static void charSearchInText(String text, char x) {
        int count = 0, index = 0, fromIndex = 0, j = 0;
        while (j < text.length()) {
            index = text.indexOf(x, fromIndex);
            if (index != -1) {
                fromIndex = index + 1;
                count++;
            }
            j++;
        }
        System.out.printf("Char '%c' is matched %d times in text: %s%n",
                x, count, text);
    }

    public static boolean compareTwoWords(String wordA, String wordB) {
        boolean isNoMatch = true;

        Map<Integer, Character> different = new LinkedHashMap<>();
        ArrayDeque<Character> aWord = new ArrayDeque<>();                           // - pop, + push, = peek
        ArrayDeque<Character> bWord = new ArrayDeque<>();

        //Stack<Character> aWord = new Stack<>();
        //Stack<Character> bWord = new Stack<>();

        if (wordA.length() != wordB.length()) return false;

        for (int i = 0; i < wordA.length(); i++) {
            aWord.add(wordA.charAt(i));
            bWord.add(wordB.charAt(i));
        }

        for (int i = 0; i < wordA.length(); i++) {
            char el1 = aWord.pop();
            char el2 = bWord.pop();
            if (el1 != el2) {
                isNoMatch = false;
                different.put(i, el1);
            }
        }

        System.out.printf("%nThat words are: '%s' and '%s.'%n", wordA, wordB);
        if (!isNoMatch) {
            System.out.println("Different symbol is: ");
            for (Map.Entry<Integer, Character> el : different.entrySet()) {
                System.out.printf("Index: %d, Char: %c%n", el.getKey(), el.getValue());
            }
        }
        return isNoMatch;
    }
}
