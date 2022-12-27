package task;

import java.util.*;

public class Brackets {
    public static void main(String[] args) {
        int idx1 = 0, idx2 = 0;
        char bracket1 = '(';
        char bracket2 = ')';
        String input = "1 *2+ (3 - 5 *2 + 4/2 + 2*6 - 6/3)+ 3* (4+5*6) +..... ((3-2)*5)";
        ArrayDeque<String> a1 = new ArrayDeque<>();

        String equation = createdEquation(input);
        System.out.println(equation);

        idx1 = getBracketsIndexes(equation).get(0);
        idx2 = getBracketsIndexes(equation).get(1);
        String equ1 = getEquationIntoBracketsIndexes(equation);
        System.out.println(equ1 + " = " + calculateEquation(equ1));
        System.out.println(equ1);

        int result = 0;
        for (int i = idx1; i < idx2; i++) {

        }

    }

//    private static int getMultiplicationIndex(String input) {
//        int multiPidx = 0;
//        multiPidx = findIndexOfOperation(input, "*");
//        return multiPidx;
//    }

    private static int findIndexOfOperation(String input, String operation, int index) {
        int multiPidx = 0;
        multiPidx = input.indexOf(operation, index);
        if (multiPidx != -1) {
            return multiPidx;
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
        StringBuilder equationIntoBracketsIndexes = new StringBuilder();
        int index1 = getBracketsIndexes(equation).get(0) + 1; // Това е вярно само за случая когато има само
        int index2 = getBracketsIndexes(equation).get(1);     // * и / -> 3 - 5*2 + 4/2
        // 3 - 5*2 + 4/2 + 2*6 - 6/3 се чупи.
        for (int i = index1, j = 0; i < index2; i++, j++) {
            equationIntoBracketsIndexes.append(equation.charAt(i));
        }
        return equationIntoBracketsIndexes.toString();
    }

    private static int calculateEquation(String equation) {
        Map<Integer, Integer> multiArr = new TreeMap<>();
        Map<Integer, Integer> dervArr = new TreeMap<>();
        int counterM = 0, counterD = 0, indexMM = 0, indexDD = 0;

        ArrayDeque<Integer> ard1 = new ArrayDeque();
        ArrayDeque<Integer> ardResult = new ArrayDeque();              // 0 1 234 5 6 7
        List<Integer> indexes = new ArrayList<>();                     // 3 - 5*2 + 3 - 1 + 4/2 + 1 + 2 + 2*6 + 6 - 6/3
        int mediumResultM = 0, mediumResultD = 0, result = 0;          // 3 - 5*2 + 4/2
        char charX;

        for (int i = 0; i < equation.length(); i++) {
            int indexM = findIndexOfOperation(equation, "*", indexMM);
            if (indexM != -1) {
                counterM++;
                int x1 = Integer.parseInt(String.valueOf(equation.charAt(indexM - 1)));
                int x2 = Integer.parseInt(String.valueOf(equation.charAt(indexM + 1)));
                mediumResultM = x1 * x2;
                multiArr.put(indexM -1, mediumResultM);
                indexMM = ++indexM;
            }
            int indexD = findIndexOfOperation(equation, "/", indexDD);
            if (indexD != -1) {
                counterD++;
                int x1 = Integer.parseInt(String.valueOf(equation.charAt(indexD - 1)));
                int x2 = Integer.parseInt(String.valueOf(equation.charAt(indexD + 1)));
                mediumResultD = x1 / x2;
                dervArr.put(indexD -1, mediumResultD);
                indexDD = ++ indexD;
            }
        }

//        idxM = findIndexOfOperation(equation, "*");
//        idxD = findIndexOfOperation(equation, "/");
//
//        if (idxM != -1) {
//            int x1 = Integer.parseInt(String.valueOf(equation.charAt(idxM - 1)));
//            int x2 = Integer.parseInt(String.valueOf(equation.charAt(idxM + 1)));
//            mediumResultM = x1 * x2;
//        }
//        if (idxD != -1) {
//            int x1 = Integer.parseInt(String.valueOf(equation.charAt(idxM - 1)));
//            int x2 = Integer.parseInt(String.valueOf(equation.charAt(idxM + 1)));
//            mediumResultD = x1 / x2;
//        }
//        if (idxM < idxD) {
//            ardResult.push(mediumResultD);
//            ardResult.push(mediumResultM);
//        } else {
//            ardResult.push(mediumResultM);
//            ardResult.push(mediumResultD);
//        }
        for (int i = 0; i < equation.length(); i++) {                  // 3 - 5*2 + 3 - 1 + 4/2 + 1 + 2 + 2*6 + 6 - 6/3
            charX = equation.charAt(i);
            if (Character.isDigit(charX)) {
                ard1.push(Integer.parseInt(String.valueOf(charX)));
            } else {
                switch (charX) {
                    case '+' -> {
                        //result = ard1.pop() +
                        i += 3;
                        ard1.push(result);
                    }
                    case '-' -> {
                        //result = ard1.pop() -
                        i += 3;
                        ard1.push(result);
                    }
                }
            }
        }


        return ard1.pop();
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
