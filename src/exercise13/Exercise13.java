package exercise13;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

public class Exercise13 {
    public static void main(String[] args) throws IOException {
        String pathOrigin = "src/exercise13/files/";
        String name = "studentsdb";
        String extension = ".txt";
        //Tests
        generateData(pathOrigin+name+extension);

        readData(pathOrigin+name+extension);

        System.out.println("Student:  "+readStudent(pathOrigin+name+extension,0));

        editStudent(pathOrigin+name+extension,0, "Steven2", "Uriarte2", 90);

        readData(pathOrigin+name+extension);

        //Apparently not is possible deleted bytes, but simulated like if this was deleted
        //In this case -1 like id is same than empty row

        deleteStudent(pathOrigin+name+extension,0);
        readData(pathOrigin+name+extension);


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
                if(student.idStudent != -1) {
                    System.out.println(student);
                    students.add(student);
                }
            }
            System.out.println(students);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static Student readStudent(String path, int idStudent) throws IOException {
        try(RandomAccessFile raf = new RandomAccessFile(path, "r")) {
            raf.seek(0);
            while(raf.getFilePointer() < raf.length()) {
                Student student = Student.readStudent(raf);
                if(idStudent == student.getIdStudent() && idStudent!=-1) {
                    return student;
                } else {
                    System.out.println("Student not found");
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static void editStudent(String path, int idStudent, String setName, String setLastName, int setAge) throws IOException {
        try(RandomAccessFile raf = new RandomAccessFile(path, "rw")) {
            raf.seek(0);
            while (raf.getFilePointer() < raf.length()) {
                long pos = raf.getFilePointer();
                Student student = Student.readStudent(raf);
                if (idStudent == student.getIdStudent() && idStudent!=-1) {
                    //Modifying
                    if (!setName.isEmpty()) {
                        student.setName(setName);
                    }
                    if (!setLastName.isEmpty()) {
                        student.setLastName(setLastName);
                    }

                    student.setAge(setAge);

                    raf.seek(pos);
                    student.writeStudent(raf);
                    System.out.println("Student data updated");
                    return;
                } else {
                    System.out.println("Student not found");
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void deleteStudent(String path, int idStudent) throws IOException {
        try(RandomAccessFile raf = new RandomAccessFile(path, "rw")) {
            raf.seek(0);
            while (raf.getFilePointer() < raf.length()) {
                long pos = raf.getFilePointer();
                Student student = Student.readStudent(raf);
                if (idStudent == student.getIdStudent() && idStudent!=-1) {
                    student.setIdStudent(-1);
                    student.setName("");
                    student.setLastName("");
                    student.setAge(0);

                    raf.seek(pos);

                    student.writeStudent(raf);
                    System.out.println("Student data deleted");
                    return;
                } else {
                    System.out.println("Student not found");
                }
            }
        }
    }




}
