package exercise7;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import java.util.Scanner;

public class Exercise7 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the path of exercise1: ");
        var inputFile = input.nextLine();
        var extension = ".txt";
        var path = "src/exercise7/files/";
        copyFile(path+inputFile+extension);
    }

    public static void copyFile(String inputFile) {

        try {
            Path path = Paths.get(inputFile);

            File file = path.toFile();
            if (!file.exists()) {
                throw new FileNotFoundException("File not found: " + inputFile);
            }
            if (!file.canRead()) {
                throw new SecurityException("Cannot read file: " + inputFile);
            }

            List<String> lines = Files.readAllLines(path);
            System.out.println("Inside readFile:");

            var inputFileFormatted = inputFile.replace(".txt", "2.txt");

            try(FileWriter writer = new FileWriter((inputFileFormatted))) {
                for (String line : lines) {
                    System.out.println(line);
                    writer.write(line+ "\r\n");
                }
            }catch (IOException e){
                System.out.println("Error creating file: " + path);
            }

        } catch (InvalidPathException e) {
            System.out.println("Invalid route: " + e.getMessage());
        } catch (NoSuchFileException | FileNotFoundException e) {
            System.out.println("File not work: " + e.getMessage());
        } catch (SecurityException e) {
            System.out.println("Permissions error: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error I/O: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
        }
    }
}
