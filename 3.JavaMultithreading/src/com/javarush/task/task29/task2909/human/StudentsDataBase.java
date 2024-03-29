package com.javarush.task.task29.task2909.human;

import java.util.ArrayList;
import java.util.List;

public class StudentsDataBase {
    public static List<Student> students = new ArrayList<>(); // сама БД

//    public static void addInfoAboutStudent(String name, int age, double averageGrade) {
//        Student student = new Student(name, age, averageGrade);
//        students.add(student);
//        printInfoAboutStudent(student);
//
//    }
public static void addInfoAboutStudent(Student student) {
    students.add(student);
    printInfoAboutStudent(student);
}

//    public static void (String name, Student student) {
//        System.out.println("Имя: " + name + " Возраст: " + student.getAge());
//    }
    public static void printInfoAboutStudent(Student student) {
        System.out.println("Имя: "+student.getName()+" Возраст: " + student.getAge());
    }

    public static void removeStudent(int index) {
        if(index>=0 && index<students.size())
            students.remove(index);
    }

    public static void findDimaOrSasha() {
        for (int i = 0; i < students.size(); i++) {
                String studentName = students.get(i).getName();
                if (studentName.equals("Dima") || studentName.equals("Sasha")) {
                    System.out.println("Студент "+studentName+" есть в базе.");
                    break;
                }
        }
    }
}