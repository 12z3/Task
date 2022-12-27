package task;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public class Brackets {
    public static void main(String[] args) {
        int idx1 = 0, idx2 = 0;
        char bracket1 = '(';
        char bracket2 = ')';
        String input = "1 *2+(3-5* 2)+ 3* (4+5*6) +..... ((3-2)*5)";
        ArrayDeque<String> a1 = new ArrayDeque<>();

        String equation = createdEquation(input);

        idx1 = getBracketsIndexes(equation).get(0);
        idx2 = getBracketsIndexes(equation).get(1);
        String equ1 = getEquationIntoBracketsIndexes(equation);
        System.out.println(equ1);

        int result = 0;
        for (int i = idx1; i < idx2 ; i++) {

        }

    }

    private static int getMultiplicationIndex(String input){
        int multiPidx = 0;
        multiPidx = findIndexOfOperation(input, "*");
        return multiPidx;
    }

    private static int findIndexOfOperation(String input, String operation) {
        int multiPidx = 0;
        for (int i = 0; i < input.length(); i++) {
            multiPidx = input.indexOf(operation, i);
        }
        return multiPidx;
    }

    private static List<Integer> getBracketsIndexes(String equation) {
        List<Integer> bracketsPositions = new ArrayList<>();
        int idx1 = 0, idx2 = 0;
        for (int i = 0; i < equation.length(); i++) {
            if (equation.indexOf("(", i) != -1) idx1 = equation.indexOf("(");
            if (equation.indexOf(")", i) != -1) idx2 = equation.indexOf(")");
        }
        bracketsPositions.add(idx1);
        bracketsPositions.add(idx2);
        return bracketsPositions;
    }
    private static String getEquationIntoBracketsIndexes(String equation) {
        String equationIntoBracketsIndexes = "";
        int index1 = getBracketsIndexes(equation).get(0) + 1;
        int index2 = getBracketsIndexes(equation).get(1);

        for (int i = index1, j = 0; i < index2 ; i++, j++) {
            equationIntoBracketsIndexes += equation.charAt(i);
        }
        return equationIntoBracketsIndexes ;
    }

    private static String createdEquation(String input) {
        StringBuilder equation = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) != ' ' && input.charAt(i) != '.') {
                equation.append(input.charAt(i));

            }
        }
        return equation.toString();
    }
}
