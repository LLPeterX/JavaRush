package com.javarush.task.task20.task2014;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/* 
Serializable Solution
*/
public class Solution implements Serializable {
    public static void main(String[] args) {
        Solution savedObject = new Solution(4);
        System.out.println(savedObject);

        try {
            ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("c:/temp/test3.bin"));
            output.writeObject(savedObject);
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // читаем объект

        Solution loadedObject = null;
        try {
            ObjectInputStream input = new ObjectInputStream(new FileInputStream("c:/temp/test3.bin"));
            loadedObject = (Solution)input.readObject();
            input.close();
            System.out.println(loadedObject);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    // transient потому, что всё хранится в одной строке string и лишнее сохранять не нужно
    transient private final String pattern = "dd MMMM yyyy, EEEE";
    transient private Date currentDate;
    transient private int temperature;
    String string;

    public Solution(int temperature) {
        this.currentDate = new Date();
        this.temperature = temperature;

        string = "Today is %s, and the current temperature is %s C";
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        this.string = String.format(string, format.format(currentDate), temperature);
    }




    @Override
    public String toString() {
        return this.string;
    }
}
