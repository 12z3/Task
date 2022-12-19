package task;

public class WordSearch {

    public static void main(String[] args) {

        String line = "astasta la vista babyastasta";
        String word = "asta";
        char a = 'a';

        System.out.println(wordSearchInText(line, word));
        System.out.println(charSearchInText(line, a));
    }

    public static int wordSearchInText(String text, String word) {
        int count = 0, j = 0, fromIndex = 0;

        while (j < text.length()) {
            int index = text.indexOf(word, fromIndex);
            if (index != -1) {
                fromIndex = index + 1;
                count++;
            }
            j++;
        }
        return count;
    }

    public static int charSearchInText(String text, char x) {
        int count = 0, j = 0, fromIndex = 0;
        while (j < text.length()){
           int index = text.indexOf(x, fromIndex);
            if (index != -1){
                fromIndex = index + 1;
                count++;
            }
            j++;
        }
        return count;
    }
}
