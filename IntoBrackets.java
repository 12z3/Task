package task;

import java.util.ArrayDeque;

public class IntoBrackets {
    public static void main(String[] args) {

        // 1+(2*(3+4))+6) ->
        // 2*(3+4)
        // 3+4


        String expression = "(1+(2*(3+4)/(2*8))+6)";
        String expression3 = "(1+(2*(3+4)/(2*8))+6)";

        String expression2 = "(1+(2*(3+4))+6)";
        String expression4 = "(1+(2*(3+4))+6)";
        String expression1 = "1+(2*(3+4))+6";

        // Тук -> "(1+(2*(3+4)/(2*8))+6)" се Чупи ;) не броиш никъде скобите.
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
        intoBrackets(expression3);
    }

    // Пазя индексите + 1 на "(" и push-ам. Когато стигна индекса "i" на ")" през "for (int j = tmp.pop(); j < i ; j++)"
    // печатам всичко между тях.
    private static void intoBrackets(String expression) {                             // (1+(2*(3+4))+6)
        ArrayDeque<Integer> tmp = new ArrayDeque<>();
        for (int i = 0; i < expression.length(); i++) {
            if (expression.charAt(i) == ' ') continue;
            if (expression.charAt(i) == '(') tmp.push(i + 1);                     // i = 1 -> взима и скобите
            if (expression.charAt(i) == ')') {
//                for (int j = tmp.pop(); j < i; j++) {
//                    System.out.print(expression.charAt(j));
//                }                                           // substring(fromIndex, toIndex) -> {fromIndex, toIndex];
                String result = expression.substring(tmp.pop(), i);                  // i + 1 -> взима и скобите
                System.out.println(result);
            }
        }
    }

    // Първи вариант. Това ми дойде първо като стратегия - но се чупи.
    // Подвърждава факта, за 100-н път, че ако едно решение е сложно -
    // - То Винаги Съществува друго по-просто такова!
    private static String getEquationsIntoBrackets(String expression) {
        StringBuilder result = new StringBuilder();
        ArrayDeque<Character> tmp = new ArrayDeque<>();

        char[] exprArr = expression.toCharArray();
        char symbolA = '(', symbolB = ')';

        int counter = 0, index = 0;
        boolean condition = true;
//                                                                                // (1+(2*(3+4)/(2*8))+6)
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
