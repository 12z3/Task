package task;

import java.util.*;

public class Encryption {
    public static void main(String[] args) {

        /*
         1N73LL1G3NC3
         15 7H3
         4B1L17Y
         70 4D4P7 70
         CH4NG3
         -573PH3N H4WKING

         Intelligence is the ability to adapt to change.
         A -> 4; B -> 8; C -> (; D -> |); E -> 3; F -> |=; G -> 6; H -> #; I -> 1; J -> |; K -> |<; L -> 1; M -> //;
         N -> //; O -> 0; P -> |D; Q -> (,); R -> |2; S -> 5; T -> 7; U -> ||; V -> /; W -> //; X -> ><; Y -> `/;
         Z -> 2;
         */

        String message = getMessage();
        ArrayList<Character> key = getKey();
        String encryptedMessage = encrypt(key, message);
        System.out.println("\n\nEncrypt message is: " + encryptedMessage);
        String decryptedMessage = decrypt(key, encryptedMessage);
        System.out.println("Decrypt message is: " + decryptedMessage);
    }

    private static String getMessage() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Input message: ");
        return scanner.nextLine();
    }

    private static ArrayList<Character> getASCII() {
        char chars = 32;
        ArrayList<Character> list = new ArrayList<>();
        for (int i = 32; i < 127; i++) list.add(chars++);
        return list;
    }

    private static ArrayList<Character> getKey() {
        ArrayList<Character> list = getASCII();
        ArrayList<Character> shuffledList = new ArrayList<>(list);
        Collections.shuffle(shuffledList);

        System.out.print("Key: ");
        for (Character el : shuffledList) System.out.print(el);
        return shuffledList;
    }

    private static String decrypt(ArrayList<Character> shuffledList, String message) {
        ArrayList<Character> list = getASCII();
        char[] newMessage = new char[message.length()];
        ArrayList<Character> key = new ArrayList<>(shuffledList);
        char[] resultA = new char[newMessage.length];

        int idxA = -1;
        for (int i = 0; i < message.length(); i++) {
            for (int j = 0; j < key.size(); j++) {
                if (message.charAt(i) == key.get(j)) {
                    idxA = j;
                    break;
                }
            }
            resultA[i] = list.get(idxA);
        }

        StringBuilder encryptedMessage = new StringBuilder();
        for (Character el : resultA) encryptedMessage.append(el);
        return encryptedMessage.toString();
    }

    private static String encrypt(ArrayList<Character> key, String message) {
        System.out.println();
        ArrayList<Character> list = getASCII();
        char[] decryptMessage = new char[message.length()];

        int idx = -1;
        for (int i = 0; i < message.length(); i++) {
            System.out.print(message.charAt(i) + ": ");
            for (int j = 0; j < list.size(); j++) {
                System.out.print(list.get(j));
                if (message.charAt(i) == list.get(j)) {
                    idx = j;
                    break;
                }
            }
            decryptMessage[i] = key.get(idx);
            System.out.print(" -> " + decryptMessage[i]);
            System.out.println();
        }

        StringBuilder decryptedMessage = new StringBuilder();
        for (Character el : decryptMessage) decryptedMessage.append(el);
        return decryptedMessage.toString();
    }
}
