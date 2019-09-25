package com.javarush.task.task20.task2003;

import java.io.*;
import java.util.*;

/* 
Знакомство с properties
*/
public class Solution {
    public static Map<String, String> properties = new HashMap<>();

    public void fillInPropertiesMap() throws IOException {
        //implement this method - реализуйте этот метод
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = reader.readLine();
        reader.close();
        FileInputStream input = new FileInputStream(fileName);
        try {
            load(input);
        } catch (Exception e) {
            e.printStackTrace();
        }
        input.close();
    }

    public void save(OutputStream outputStream) throws Exception {
        //implement this method - реализуйте этот метод
        Properties p = new Properties();
        for(Map.Entry<String,String> entry : properties.entrySet()) {
            p.setProperty(entry.getKey(), entry.getValue());
        }
        p.store(outputStream,null);
    }

    public void load(InputStream inputStream) throws Exception {
        //implement this method - реализуйте этот метод
        Properties p = new Properties();
        p.load(inputStream);
        for(String s : p.stringPropertyNames()) {
            properties.put(s, p.getProperty(s));
        }

    }

    public static void main(String[] args) {
/*
        Solution sol = new Solution();
        try {
            // Начальное создание файла

//            Properties p = new Properties();
//            p.setProperty("URL","http://www.mail.ru");
//            p.setProperty("User","papapap");
//            p.setProperty("Password","dddkdk");
//            FileOutputStream fout = new FileOutputStream("c:/temp/test2.prop");
//            p.store(fout,null);
//            fout.close();
            sol.fillInPropertiesMap();
            // читаем пропертиес
            for(Map.Entry<String,String> entry : properties.entrySet()) {
                System.out.println(entry.getKey()+" = "+entry.getValue());
            }



        } catch (Exception e) {
            e.printStackTrace();
        }
*/
    }
}
