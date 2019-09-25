package com.javarush.task.task29.task2909.human;

import java.util.ArrayList;
import java.util.List;

public class University {
    private List<Student> students = new ArrayList<>();
    private String name;
    private int age;

    public University(String name, int age) {
        //super(name, age, 0);
        this.name = name;
        this.age = age;
    }

    public List<Student> getStudents() {
        return students;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public Student getStudentWithAverageGrade(double grade) {
        for(Student student : students) {
            if(student.getAverageGrade()==grade)
                return student;
        }
        return null;
    }

    public Student getStudentWithMaxAverageGrade() {
        double maxGrade=0;
        Student maxGradeStudent=null;
        for(int i=0; i<students.size(); i++) {
            double currentGrade = students.get(i).getAverageGrade();
            if(currentGrade > maxGrade) {
                maxGrade = currentGrade;
                maxGradeStudent = students.get(i);
            }
        }
        //TODO:
        return maxGradeStudent;
    }

    public Student getStudentWithMinAverageGrade() {
        //TODO:
        double minGrade = Double.MAX_VALUE;
        Student minStudent=null;
        for(Student s : students) {
            double currentGrade = s.getAverageGrade();
            if(currentGrade<minGrade) {
                minGrade = currentGrade;
                minStudent = s;
            }
        }
        return minStudent;
    }

    public void expel(Student student) {
        students.remove(student);
    }
}