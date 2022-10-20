package task.task;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class MyFile {


    public static void main(String[] args) {
        write();
        read();
    }

    private static void write() {
        Scanner scanner = new Scanner(System.in);
        String[] input = scanner.nextLine().split(",");
        File file = new File("totoResult");
        try {
            BufferedWriter writher = new BufferedWriter(new FileWriter(file));

            writher.write("\n" + Arrays.toString(input));

            writher.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void read() {
        try {
            File file = new File("totoResult");
            BufferedReader reader = new BufferedReader(new FileReader(file));

            String line = "";
            while (line != null) {
                line = reader.readLine();
                System.out.println(line);
            }
            reader.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}


