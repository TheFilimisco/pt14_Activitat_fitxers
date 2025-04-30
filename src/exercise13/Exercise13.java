package exercise13;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

public class Exercise13 {
    public static void main(String[] args) throws IOException {
        String pathOrigin = "src/exercise13/files/";
        String name = "studentsdb";
        String extension = ".txt";
        generateData(pathOrigin+name+extension);
        readData(pathOrigin+name+extension);
        readStudent(pathOrigin+name+extension,0);
    }

    public static void generateData(String path) throws IOException {
        try(RandomAccessFile raf = new RandomAccessFile(path, "rw")) {
            // Write 5 students
            Student[] students = {
                    new Student(0,"Steven", "Uriarte", 23),
                    new Student(1,"Jack", "Bob", 22),
                    new Student(2,"Miguel", "Alejandro", 23),
                    new Student(3,"Nilo", "Marquez", 21),
                    new Student(4,"Nicolas", "Cage", 21),
            };

            for(Student student : students) {
                student.writeStudent(raf);
            }

            System.out.println("Student data generated");
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    public static void readData(String path) throws IOException {
        try(RandomAccessFile raf = new RandomAccessFile(path, "r")) {
            ArrayList<Student> students = new ArrayList<>();
            raf.seek(0);
            while(raf.getFilePointer() < raf.length()) {
                Student student = Student.readStudent(raf);
                System.out.println(student);
                students.add(student);
            }
            System.out.println(students);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void readStudent(String path, int idStudent) throws IOException {
        try(RandomAccessFile raf = new RandomAccessFile(path, "r")) {
            raf.seek(0);
            while(raf.getFilePointer() < raf.length()) {
                Student student = Student.readStudent(raf);
                if(idStudent == student.getIdStudent()) {
                    System.out.println(Student.readStudent(raf));
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }




}
