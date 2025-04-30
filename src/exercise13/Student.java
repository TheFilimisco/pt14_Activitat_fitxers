package exercise13;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.Serializable;

public class Student{
    int idStudent;
    String name;
    String lastName;
    int age;

    public Student(int idStudent, String name, String lastName, int age) {
        this.idStudent = idStudent;
        this.name = name;
        this.lastName = lastName;
        this.age = age;
    }

    public int getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(int idStudent) {
        this.idStudent = idStudent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void writeStudent(RandomAccessFile raf) throws IOException{
        raf.writeInt(idStudent);
        raf.writeUTF(handleLength(name,50));
        raf.writeUTF(handleLength(name,50));
        raf.writeInt(age);
    }

    public static Student readStudent(RandomAccessFile raf) throws IOException{
        int idStudent = raf.readInt();
        String name = raf.readUTF();
        String lastName = raf.readUTF();
        int age = raf.readInt();
        return new Student(idStudent, name, lastName, age);
    }

    private String handleLength(String name, int length) {
        if (name.length()< length) {
            StringBuilder nameBuilder = new StringBuilder(name);
            while(nameBuilder.length() < length) {
                nameBuilder.append(" ");
            }
            name = nameBuilder.toString();
        } else {
            name = name.substring(0, length);
        }
        return name;
    }

    @Override
    public String toString() {
        return "Student{" +
                "idStudent=" + idStudent +
                ", name='" + name  +
                ", lastName='" + lastName +
                ", age=" + age +
                '}';
    }
}
