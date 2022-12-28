package task;

public class Binary {
    public static void main(String[] args) {

        int number = 11;
        digitToBinary(number);
        binaryToDigit("1011");
    }
    // 11: 11/2 = 5(1) -> 5/2 = 2(1) -> 2/2 = 1(0) - 1/2 = 0(1) -> 1011 Взима остатъка в обратен ред.
    // Делия на 2, взимам цялото число и помня остатъка.....и така докато цялото число > 0
    private static void digitToBinary(int number) {
        StringBuilder bNumber = new StringBuilder();
        int result = number / 2;
        bNumber.append(number % 2);
        while (result > 0) {
            number = result;
            result = number / 2;
            bNumber.append(number % 2);
        }
        bNumber.reverse();
        System.out.println(bNumber);
    }

    // 1011 = 1*2^0 + 1*2^1 + 9*2^2 + 1*2^3 -> += x * 2^(индекса на x)
    private static void binaryToDigit(String binary) {
        StringBuilder tmp = new StringBuilder(binary);
        int digit = 0;
        tmp.reverse();
        for (int i = 0; i < binary.length(); i++) {
            int a = Integer.parseInt(String.valueOf(tmp.charAt(i)));
            int b = (int) Math.pow(2, i);
            digit += a * b;
        }
        System.out.println(digit);
    }
}
