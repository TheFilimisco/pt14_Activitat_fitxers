package exercise8;

import java.io.*;

public class Exercise8 {
    public static void main(String[] args) throws IOException {
        long start;
        long end;

        //BufferWriter/Reader
        start = System.nanoTime();
        writerWithBuffer();
        readWriterWithBuffer();
        end = System.nanoTime();
        System.out.println("Time lapse Text: "+ (end-start)/1_000_000 + ".ms");


        //- Binario BufferOutput/InputStream
        start = System.nanoTime();
        writerWithBufferStream();
        readWriterWithBufferStream();
        end = System.nanoTime();
        System.out.println("Time lapse Byte: "+ (end-start)/1_000_000  + ".ms");
    }

    public static void writerWithBufferStream() throws IOException {
        try (OutputStream out = new FileOutputStream("src/exercise8/files/text.txt");
        BufferedOutputStream bw = new BufferedOutputStream(out)) {
            for (int i = 0; i < 2000; i++) {
                String line = "Word:" + i + "\n";
                bw.write(line.getBytes());
            }
            bw.flush();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void readWriterWithBufferStream() throws IOException {
        try (InputStream in = new FileInputStream("src/exercise8/files/text.txt");
            BufferedInputStream br = new BufferedInputStream(in) ) {
           int byteRead;
           while ((byteRead = br.read()) != -1) {
              // System.out.print((char) byteRead);
           }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void writerWithBuffer() throws  IOException{
        try (BufferedWriter bw = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream("src/exercise8/files/text2.txt")))) {
            for (int i = 0; i < 2000; i++) {
                bw.write("Hello World " + i + "\n");
            }
            bw.flush();
        } catch (IOException e) {
            System.out.println("Error al escribir con BufferedWriter: " + e.getMessage());
        }
    }

    public static void readWriterWithBuffer() throws IOException {
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(new FileInputStream("src/exercise8/files/text2.txt")))) {
            while (br.readLine() != null) {
                // br.readLine(); // si no quieres imprimir
            }
        } catch (IOException e) {
            System.out.println("Error al leer con BufferedReader: " + e.getMessage());
        }
    }
}
