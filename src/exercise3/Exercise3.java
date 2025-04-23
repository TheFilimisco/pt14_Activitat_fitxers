package exercise3;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import java.util.Scanner;

public class Exercise3 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the path of exercise3: ");
        var inputFile = input.nextLine();
        var path = "src/exercise3/files/";
        var textInsideFile = getTextInsideFile(path + inputFile);
        var textFormated = textInsideFile.replace(" ","").toLowerCase();
        System.out.println("Enter your sentence that will search:");
        var sentence = input.nextLine().replace(" ", "").toLowerCase();
        System.out.println(compareToTexts(textFormated, sentence));

    }

    public static String getTextInsideFile(String inputFile) {
        StringBuilder text = new StringBuilder();
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
            for (String line : lines) {
                text.append(line);
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
        return text.toString();
    }


    public static boolean compareToTexts(String textInsideFile, String inputSentence) {
        return textInsideFile.contains(inputSentence);
    }
}
