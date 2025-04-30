package exercise11;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;

public class Exercise11 {
    public static void main(String[] args) throws IOException {
        //test
        String pathOrigin = "src/exercise11/files/";
        String name = "numbers";
        String extension = ".txt";

        getAvgFile(pathOrigin+name+extension);
    }

    public static void getAvgFile(String path) throws IOException {
        try(FileReader fr = new FileReader(path)) {
            var sum = 0;
            var count = 0;
            int ch;

            while ((ch = fr.read()) !=-1) {
                if (Character.isDigit(ch)) {
                    int digit = Character.getNumericValue(ch);
                    System.out.println("Read: " + digit);
                    sum += digit;
                    count++;
                }
            }

            if (count != 0) {
                System.out.println("Average: " + sum / count);
            } else {
                System.out.println("No numbers inside");
            }

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("I/O Error");
        }
    }
}

