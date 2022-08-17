package hackerRank;

import training.Methods;

import java.util.Scanner;

public class Word extends Methods {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        timeAndData();

        // Има ли я думата "word" в низа "text1"...?

        String word = "and";
        String text = "alabala opa opa";
        String text1 = "Fallingwallandsand";
        String[] input = text.split(" ");
        String[] input1 = text.split(" ");
        char[] result = new char[text1.length()];

        boolean isMatch = false;
        int count = 0;
        StringBuilder stb = new StringBuilder();

        // -------------------------------------------------------------------------------------


//        for (int i = 0; i < input.length; i++) {
//            if (input[i].equals(word)) {
//                countMatches++;
//            }
//        }
//        if (countMatches > 0) {
//            System.out.print("''" + word + "''" + " matches is " + countMatches + " times.");
//        } else System.out.println("No Ok...");

        // -------------------------------------------------------------------------------------

//        while (i < text1.length()) {
//            stb.append(text1.charAt(k));
//            k++;
//
//            if ((k % word.length() == 0)) {
//                countChar = 0;
//                for (int j = 0; j < word.length(); j++) {
//                    if (stb.charAt(j) == word.charAt(j)) {
//                        countChar++;
//                    }
//                }
//                if (countChar == word.length()){
//                    System.out.print("Ok");
//                }
//                stb.delete(0, word.length());
//            }
//            i++;
//        }

        // -------------------------------------------------------------------------------------

        // Съставя дума със буквите на "j" като първа буква и "l".
        // Когато дължината на съставената дума стане равна на дължината на думата "word" -
        // сравнява двете думи по символи.
        // Изтрива създадената дума и генерира нова. Първа буква "j", останалите "l".
        // "k" - държи броя добавени или премахнат символи. Или к == броя текущи символи в "stb".

        System.out.println("Text: " + text1 + "; ->" + " Word: " + word);
        for (int j = 0; j < text1.length(); j++) {
            int k = 0;                                    // ... След като "l" = text1.length() нулирай "k" и "stb"
            stb.delete(0, word.length());
            stb.append(text1.charAt(j));                  // ... за новата стойност на "j" запълни "stb"
            k++;
            for (int l = j + 1; l < text1.length(); l++) {
                stb.append(text1.charAt(l));
                k++;                            //Ред 62: Дължината на създадения stb съвпада ли с дължината на думата?
                if ((k % word.length() == 0) && l < text1.length()) {
                    int countChar = 0;
                    for (int t = 0; t < word.length(); t++) {
                        if (stb.charAt(t) == word.charAt(t)) {
                           // result[l] = word.charAt(t);
                            countChar++;
                        }
                    }
                    if (countChar == word.length()) {
                        isMatch = true;
                        count++;
                    }
                    stb.delete(1, word.length());
                    k -= (word.length() - 1);              // "Маха" в случая двете последно добавени букви.
                }
            }
        }
        if (isMatch) {
            System.out.println("Occurs in " + count + " places.");
            System.out.print("There is it ;)");
        } else System.out.println(";(");

        for (char el: result) System.out.print(el + " ");
    }
}
