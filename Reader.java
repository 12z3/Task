package task;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class Reader {
    public static void main(String[] args) {

        readerBuffer();
        readerScanner();
        // readerBufferBad();
    }

    private static void readerBuffer() {
        try {
            FileReader file = new FileReader("totoNew.txt");
            File myFile = new File("totoNew.txt");

            BufferedReader reader = new BufferedReader(file);
            String line = "";

            String answer = myFile.canRead() ? "YES" : "NO";

            System.out.println("Is " + myFile.getName() + " is readable? -> " + answer);
            System.out.println("It is on " + myFile.getAbsoluteFile() +
                    " and file sizes is " + myFile.length() + " bytes.");

            while (line != null) {
                line = reader.readLine();
                System.out.println(line);
            }

        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void readerScanner() {
        try {
            File file = new File("toto.txt");
            BufferedReader reader = new BufferedReader(new FileReader(file));
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line != null) {
                    System.out.println(line);
                }
            }
            reader.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void readerBufferBad() {
        try {
            FileReader file = new FileReader("totoNew.txt");
            BufferedReader br = new BufferedReader(file);
            String line = "";

            while (br.read() != -1) {
                System.out.print((char) br.read());
            }

        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
