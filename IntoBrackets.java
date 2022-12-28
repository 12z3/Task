package task;

import java.util.ArrayDeque;

public class IntoBrackets {
    public static void main(String[] args) {

        // 1+(2*(3+4))+6) ->
        // 2*(3+4)
        // 3+4

        String expression = "(1+(2*(3+4))+6)";
        String expression2 = "(1+(2*(3+4))+6)";
        String expression1 = "1+(2*(3+4))+6";

        boolean condition = true;
        while (condition) {
            String tmp = getEquationsIntoBrackets(expression);
            System.out.println(tmp);
            expression = getEquationsIntoBrackets(tmp);
            if (expression.length() != 0) {
                System.out.println(expression);
            } else condition = false;
        }

        System.out.println("Хитро.... ;)");
        intoBrackets(expression2);
    }

    // Пазя индексите + 1 на "(" и push-ам. Когато стигна индекса "i" на ")" през for (int j = tmp.pop(); j < i ; j++)
    // печатам всичко между тях.
    private static void intoBrackets(String expression){                             // (1+(2*(3+4))+6)
        ArrayDeque<Integer> tmp = new ArrayDeque<>();
        for (int i = 0; i < expression.length(); i++) {
            if (expression.charAt(i) == ' ') continue;
            if (expression.charAt(i) == '(') tmp.push(i + 1);
            if (expression.charAt(i) == ')') {
                for (int j = tmp.pop(); j < i ; j++) {
                    System.out.print(expression.charAt(j));
                }
                System.out.println();
            }

        }
    }

    private static String getEquationsIntoBrackets(String expression) {
        StringBuilder result = new StringBuilder();
        ArrayDeque<Character> tmp = new ArrayDeque<>();

        char[] exprArr = expression.toCharArray();
        char symbolA = '(', symbolB = ')';

        int counter = 0, index = 0;
        boolean condition = true;

        for (int i = 0; i < exprArr.length; i++) {                                // (1+(2*(3+4))+6)
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
