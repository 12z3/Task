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
        String[] input = scanner.nextLine().split(" ");
        File file = new File("totoResult");
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));

           // writer.newLine(); -> "\n" +
            writer.write("\n" + Arrays.toString(input));

            writer.close();
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


