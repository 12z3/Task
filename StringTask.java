package task;

import hackerRank.Collections;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class StringTask {
    public static void main(String[] args) {
        String word = "ice", word1 = "key";
        String text = "icekicealkiicecelice", text1 = "kkeyeytextkey";
        List<String> list = new ArrayList<>(List.of("Windows", "Linux"));
        String text2 = "Linux offers great speed and security, " +
                "on the other hand, Windows offers great ease of use, " +
                "so that even non-tech-savvy people can work easily on personal computers. " +
                "Linux is employed by many corporate organizations as servers and " +
                "OS for security purpose while Windows is mostly employed by business users and gamers.";
        String wordA = "Linux", wordB = "Windows";

        //textA(word, text);
        System.out.println();
        textB(word1, text1);
        System.out.println();
        textC(word, text);
        System.out.println(wordReplacement(wordReplacement(text2, wordA), wordB));
        System.out.println(wordReplacementA(text2, list));
    }

    private static void printStreamReplacementWord(String text) {
        String[] input = text.split(" ");


    }

    private static String wordReplacement(String text, String word) {
        StringBuilder replacementWord = new StringBuilder();
        for (int i = 0; i < word.length(); i++) replacementWord.append("*");
        while (text.contains(word)) text = text.replace(word, replacementWord);
        return text;
    }

    private static String wordReplacementA(String text, List<String> bannedWords) {
        StringBuilder replacementWord = new StringBuilder();
        for (String banned : bannedWords) {
            replacementWord.append("*".repeat(banned.length()));
            text = text.replace(banned, replacementWord);
            replacementWord.delete(0, replacementWord.length());
        }
        return text;
    }

    private static void textC(String word, String text) {
        while (text.contains(word)) {
            text = text.replace(word, "");
            System.out.println(text);
        }
    }

    private static void textB(String word, String text) {
        StringBuilder tmp = new StringBuilder(text);
        int count = 1;

        while (tmp.toString().contains(word)) {
            tmp.delete(tmp.indexOf(word), tmp.indexOf(word) + word.length());
            System.out.printf("%d: %s%n", count++, tmp);
        }
    }

    private static void textA(String word, String text) {
        StringBuilder tmp = new StringBuilder(text);
        char[] chars = text.toCharArray();
        int count = 1;

        System.out.printf("%d: %s%n", count++, tmp);
        for (int i = 0; i < chars.length; i++) {
            if (tmp.indexOf(word) != -1) {
                tmp.delete(tmp.indexOf(word), tmp.indexOf(word) + word.length());
                System.out.printf("%d: %s%n", count++, tmp);
            }
        }
    }
}
