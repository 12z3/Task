package task;

import java.util.ArrayDeque;
import java.util.Scanner;

public class tmpBrackets {
    public static void main(String[] args) {

        // 1+(2*(3+4))+6) ->
        // 2*(3+4)
        // 3+4

        String expression = "(1+(2*(3+4))+6)";

        int x = expression.length();
        while (x > 0) {
            String tmp = getEquationsIntoBrackets(expression);
            System.out.println(tmp);
            expression = getEquationsIntoBrackets(tmp);
            if (expression.length() != 0) {
                System.out.println(expression);
            } else break;
            x -= tmp.length();
        }
    }

    private static String getEquationsIntoBrackets(String expression) {
        StringBuilder result = new StringBuilder();
        Scanner scanner = new Scanner(expression);
        ArrayDeque<Character> tmp = new ArrayDeque<>();

        char[] exprArr = expression.toCharArray();
        char symbolA = '(', symbolB = ')';

        int counter = 0, index = 0, allCount = 0;
        boolean condition = true;

        for (int i = 0; i < exprArr.length; i++) {
            if (exprArr[i] == symbolA) {
                //tmp.push(exprArr[i]);                                           // Най-външните скоби
                counter++;
                index = i + 1;
                while (condition) {
                    tmp.push(exprArr[index]);
                    index++;
                    if (exprArr[index] == symbolA) counter++;
                    if (exprArr[index] == symbolB) counter--;
                    if (counter == 0) {
                        //tmp.push(exprArr[index]);                               // Най-външните скоби
                        condition = false;
                    }
                    i = index;
                }
            }

            int size = tmp.size();
            if (!tmp.isEmpty()) {
                while (size > 0) {
                    char el = tmp.pop();
                    result.append(el);
                    size--;
                }
                result.reverse();
                //System.out.println(result);
            }
        }
        return result.toString();
    }
}
