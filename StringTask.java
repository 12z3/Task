package task;

public class StringTask {
    public static void main(String[] args) {
        String word = "ice", word1 = "key";
        String text = "icekicealkiicecelice", text1 = "kkeyeytextkey";

        //textA(word, text);
        System.out.println();
        textB(word1, text1);
        System.out.println();
        textC(word,text);
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
