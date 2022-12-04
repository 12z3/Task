package task;

import java.util.Objects;

public class CountWord {
    public static void main(String[] args) {

        /*
        Колко пъти думата "word" се среща в текста "text"?
        */

        String text = "the aldks the dkjtlkee the";
        String word = "the";

        System.out.println(searchThisWord(text, word));
        System.out.println(searchThisWord1(text, word));

    }

    private static int searchThisWord(String text, String word) {
        int count = 0, nextIndex = 0;
        for (int i = 0; i < text.length(); i++) {
            int currentIndex = text.indexOf(word, nextIndex);    // намира индекса на Първото срещане. Нещо си или -1.
            if (currentIndex != -1) {
                count++;
                nextIndex = currentIndex + word.length();   // Следващото търсене ще е от този индекс на татък в думата.
            }
        }
        return count;
    }

    private static int searchThisWord1(String text, String word) {
        int count = 0;
        String[] tmp = text.split(" ");
        for (int i = 0; i < tmp.length; i++) {
            if (Objects.equals(tmp[i], word)) {
                count++;
            }
        }
        return count;
    }
}
