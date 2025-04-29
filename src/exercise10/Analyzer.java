package exercise10;

import java.io.*;
import java.util.Scanner;

public class Analyzer {

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the file name: ");
        String fileName= sc.nextLine();

        String pathOrigin = "src/exercise10/files/";
        String extension = ".txt";

        //Test name Files
        String cv = "curriculum";
        String abecedari = "abecedari";

        //Testing
        readFile(pathOrigin+fileName+extension);
        readFile(pathOrigin+cv+extension);
        readFile(pathOrigin+abecedari+extension);

    }


    public static void readFile(String pathFile) throws IOException {
        try(FileReader file = new FileReader(pathFile)){
            int acum = 0;
            while (file.read() != -1){
                acum++;
            }
            System.out.println(pathFile+": " + acum);
            saveFileResult(acum);
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    public static void saveFileResult(int result) throws IOException {
        try(FileWriter file = new FileWriter("src/exercise10/files/words.txt", true)){
            file.write(result+"\n");
            System.out.println("saved words.txt");
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

}
