package exercise9;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class Exercise9 {
    public static void main(String[] args) {
        String origin = "src/exercise9/files/img.png";
        String dest = "src/exercise9/files/img_copy.png";
        copyFile(origin, dest);
    }

    public static void copyFile(String pathOrigin, String pathDest) {
        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(pathOrigin));
             BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(pathDest))){

            byte[] buffer = new byte[2024];

            int bytesRead;

            while ((bytesRead = bis.read(buffer)) != -1){
                    bos.write(buffer, 0, bytesRead);
            }
            bos.flush();
            System.out.println("File copied");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
