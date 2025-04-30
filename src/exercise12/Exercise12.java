package exercise12;

import java.io.EOFException;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Exercise12 {
    public static void main(String[] args) throws IOException {
        String pathOrigin = "src/exercise12/files/";
        String name = "numbers";
        String extension = ".txt";
        int[] numbers = {12,3,4,4,2,3,4,1};

        orderFile(pathOrigin+name+extension,numbers);
    }

    public static void orderFile(String path, int[] numbers) throws IOException {
        List<Integer> numbersOrdered = new ArrayList<Integer>();

        // Write file with numbers
        try(RandomAccessFile raf = new RandomAccessFile(path,"rw")) {
            for (int number : numbers) {
                raf.writeInt(number);
            }
        } catch(IOException e) {
            System.out.println(" IO Exception: " + e + " write");
        }


        //Read file
        try(RandomAccessFile raf = new RandomAccessFile(path,"r")) {
           while (true){
               try {
                   int number = raf.readInt();
                   System.out.println("Read: " + number);
                   numbersOrdered.add(number);
               } catch(EOFException e) {
                   break;
               }
           }
        } catch(IOException e) {
            System.out.println(" IO Exception: " + e +" read");
        }

        //Order
        Collections.sort(numbersOrdered);
        try(RandomAccessFile raf = new RandomAccessFile(path,"rw")) {
            raf.setLength(0);
            for(int number : numbersOrdered){
                raf.writeInt(number);
            }
        } catch(IOException e) {
            System.out.println(" IO Exception: " + e +" write");
        }

        System.out.println("Ordered numbers: " + numbersOrdered);
    }
}
