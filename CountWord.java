package task;

import java.util.Objects;

public class CountWord {
    public static void main(String[] args) {

        /*
        Колко пъти думата "word" се среща в текста "text"?
        */

        String text = "A regular expression (shortened as regex or regexp;[1] " +
                "sometimes referred to as rational expression[2][3]) is a sequence " +
                "of characters that specifies a search pattern in text. " +
                "Usually such patterns are used by string-searching algorithms " +
                "for \"find\" or \"find and replace\" operations on strings, " +
                "or for input validation. Regular expression techniques are developed " +
                "in theoretical computer science and formal language theory.\n" +
                "\n" +
                "The concept of regular expressions began in the 1950s, " +
                "when the American mathematician " +
                "Stephen Cole Kleene formalized the concept of a regular language. " +
                "They came into common use with Unix text-processing utilities. " +
                "Different syntaxes for writing regular expressions have existed since the 1980s, " +
                "one being the POSIX standard and another, widely used, being the Perl syntax.";

        String word = "the";
        System.out.println(searchThisWord(text, word));
        System.out.println(searchThisWord1(text, word));
        System.out.println(searchThisWord2(text, word));

    }

    private static int searchThisWord2(String text, String word) {
        int count = 0, nextIndex = -1;
        while ((nextIndex = text.indexOf(word, nextIndex + 1)) != -1) {
            count++;
        }
        return count;
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
            if (tmp[i].equalsIgnoreCase(word)) count++;
        }
        return count;
    }
}
