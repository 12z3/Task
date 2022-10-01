package hackerRank;

public class Random {
    public static void main(String[] args) {

        /**
         * @Problem: Генерира число в диапазона от 5 до 10
         * Math.random() - връща число между 0.0 и 0.9.
         * Следователно за да започне от min = 5 то == Math.random() + 5.
         * За да достигне горната граница max = 10 то==  Math.random() * (10 + 1).
         * Или: т 5 до 10 -> [5,11) == (Math.random() * max) + (min + 1)

         * random() method returns a random number between 0.0 and 0.9...,
         * you multiply it by 50, so upper limit becomes 0.0 to 49.999...
         * when you add 1, it becomes 1.0 to 50.999..., now when you truncate to int, you get 1 to 50.

        This will give you value from 1 to 50
        in case of int or 1.0 (inclusive) to 50.0 (exclusive) in case of double:
         * double random = Math.random() * 49 + 1;
         * or
         * int random = (int )(Math.random() * 50 + 1);
         *
         * int random = (int)(Math.random() * ((max - min) + 1)) + min; <- ПРАВИЛНИЯТ НАЧИН.
         */

        System.out.print("First: ");
        randomGen(10, 5);
        System.out.println();
        System.out.print("Second: ");
        //randomGeN(10,5);
    }


    public static void randomGeN(int max, int min) {
        Random ranGen = new Random();
        for (int i = 0; i < 20; i++) {
            int random = (int) ((Math.random() * max) + min);
            System.out.print(random + " ");
        }
    }


    public static void randomGen(int max, int min) {
        for (int i = 0; i < 20; i++) {
            int random = (int) ((Math.random() * max) + min);
            System.out.print(random + " ");
        }
    }
}
