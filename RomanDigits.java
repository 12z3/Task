package task;

public class RomanDigits {
    public static void main(String[] args) {

        // TODO: Направи го и със ArrayDequeue.

        // Ако една цифра е по-голяма или равна на следващата тя се прибавя кьм общата стойност.
        // В обратния случай се изважда.

        String romanNumber3 = "DCLXVI";
        String romanNumber1 = "XVI";
        String romanNumber4 = "MCMXCIV";                                // DCLXVI = 500 ÷ 100 ÷ 50 ÷ 10 ÷ 5 ÷ 1 = 666
        String romanNumber2 = "DIX";                                    // • DIX = 500 - 1 + 10
        String romanNumber = "IV";
        String romanNumber5 = "III";


        //stb.reverse();

        System.out.println(romanNumber(romanNumber4));
    }

    private static int romanNumber(String s) {
        int firstValue = 0, result = 0;

        for (int i = 0; i < s.length(); i++) {
            char currentIndex = s.charAt(i);
            int currentValue = getValue(currentIndex);

            int nextIndexC = i + 1;
            if (nextIndexC < s.length()) {
                 char nextIndex = s.charAt(i + 1);
                int nextValue = getValue(nextIndex);

                if (currentValue >= nextValue){
                    result += currentValue;

                } else {
                    result -= currentValue;
                }
            } else result += currentValue;
        }
        return result;
    }


    private static int getValue(char digit) {
        int value = 0;

        switch (digit) {
            case 'I' -> {
                value = 1;
            }
            case 'V' -> {
                value = 5;
            }
            case 'X' -> {
                value = 10;
            }
            case 'L' -> {
                value = 50;
            }
            case 'C' -> {
                value = 100;
            }
            case 'D' -> {
                value = 500;
            }
            case 'M' -> {
                value = 1000;
            }
        }
        return value;
    }
}
