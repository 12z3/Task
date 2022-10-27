package task;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class MyFile {

    /**
     * @Source: <a href="https://www.geeksforgeeks.org/file-class-in-java/">...</a>
     */

    public static void main(String[] args) {
        write();
        read();
    }

    // Презаписвва файла защото всеки път го създава. Създай го веднъж и го преизползвай...
    // ... Същото се отнася и за List<List<Integers>> data;
    // Всеки път презаписва файла, а не продължава от там докъдето е стигнал....


    private static void write() {
        Scanner scanner = new Scanner(System.in);
        File file = new File("totoResult");
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            String totoResult = "totoResult";
            RandomAccessFile raf = new RandomAccessFile(totoResult, "rw");


            int count = 1;
            while (count <= 3) {
                String[] input = scanner.nextLine().split("");
                writer.write("\n" + Arrays.toString(input));
                count++;
            }

            printFilePath(file);
            writer.newLine();
            raf.seek(file.length());

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean isFileExist(File thisFile) {
        if (!thisFile.exists()) return true;
        return false;
    }

    private static void printFilePath(File file) {
        System.out.print("Path is: " + file.getAbsoluteFile());
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


