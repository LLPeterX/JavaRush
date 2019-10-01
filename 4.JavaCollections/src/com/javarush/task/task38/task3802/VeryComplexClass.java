package com.javarush.task.task38.task3802;

/* 
Проверяемые исключения (checked exception)
*/

import java.io.File;
import java.io.FileInputStream;

public class VeryComplexClass {
    public void veryComplexMethod() throws Exception {
        //напишите тут ваш код
        File file = new File("dummy");
        FileInputStream inputStream = new FileInputStream(file);
    }

    public static void main(String[] args) throws Exception {
        new VeryComplexClass().veryComplexMethod();
    }
}
