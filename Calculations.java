package task;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class Calculations {
    public static final int TEST_COUNT = 55;

    public static void main(String[] args) throws InterruptedException {
        runTestA(TEST_COUNT);
        runTestB(TEST_COUNT);

//        Thread testA = new Thread(() -> {
//            try {
//                runTestA(TEST_COUNT);
//                Thread.sleep(3000);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//        });
//
//        Thread testB = new Thread(() -> {
//            try {
//                runTestB(TEST_COUNT);
//                Thread.sleep(3000);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//        });
//
//        testA.start();
//        System.out.println();
//        testB.start();

    }

    static void runTestA(int numOfTests) throws InterruptedException {
        while (numOfTests > 0) {
            test();
            TimeUnit.SECONDS.sleep((long) 1.5);
            numOfTests--;
        }
    }

    // Направи го с две Нишки!
    static void test() throws InterruptedException {
        Random random = new Random();
        List<String> operations = new ArrayList<>(List.of("+", "*"));
        int currentOperation = random.nextInt(0, operations.size());
        String operand = operations.get(currentOperation);

        List<Integer> digit = fillDigits(new ArrayList<>());
        int el1 = digit.get(random.nextInt(0, digit.size()));
        int el2 = digit.get(random.nextInt(0, digit.size()));

        System.out.printf("%d %s %d = ", el1, operand, el2);
        TimeUnit.SECONDS.sleep((long) 1.3);
        //Thread.sleep(1300);
        switch (operand) {
            case ("+") -> System.out.printf("%d%n", el1 + el2);
            case ("*") -> System.out.printf("%d%n", el1 * el2);
        }
        digit.remove(digit.size() - 1);
    }

    static List<Integer> fillDigits(List<Integer> list) {
        Random random = new Random();
        for (int i = 0; i < 15; i++) list.add(random.nextInt(3, 10));
        return list;
    }

    static void runTestB(int numOfTest) throws InterruptedException {
        ArrayDeque<Integer> stack = new ArrayDeque<>(List.of(3, 4, 5, 6, 7, 8, 9));
        Random random = new Random();
        String operand = "";

        while (numOfTest > 0) {
            operand = (random.nextInt(0, 2) == 1) ? "+" : "*";
            int el1 = getElementFrom(stack);
            int el2 = getElementFrom(stack);
            if (el1 == -1 || el2 == -1) {
                System.out.println("Stack is Empty!");
                return;
            }
            System.out.printf("%d %s %d = ", el1, operand, el2);

            TimeUnit.SECONDS.sleep((long) 1.9);
            switch (operand) {
                case ("+") -> System.out.printf("%d%n", el1 + el2);
                case ("*") -> System.out.printf("%d%n", el1 * el2);
            }
            numOfTest--;
            TimeUnit.SECONDS.sleep((long) 1.3);
        }
    }

    public static int getElementFrom(ArrayDeque<Integer> stack) {
        if (stack.size() > 1) {
            int el = stack.poll();
            stack.addLast(el);
            return el;
        }
        return -1;
    }
}
