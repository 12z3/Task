package task;

import java.util.*;

public class Primes {
    public static void main(String[] args) {

        Boolean[] isPrime = new Boolean[40 + 1];
        Arrays.fill(isPrime, false);


        for (int i = 2; i <= 40; i++) {
            if (!isPrime[i]) {
                for (int j = i * 2; j <= 40; j += i) {
                    isPrime[j] = true;
                }
            }
        }

        for (int i = 2; i <= 40 ; i++) {
            if (!isPrime[i]) System.out.print(i + " ");
        }
    }
}
