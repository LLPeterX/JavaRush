package com.javarush.task.task19.task1904;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/* 
И еще один адаптер
*/

public class Solution {

    public static void main(String[] args) {

    }

    public static class PersonScannerAdapter implements PersonScanner {
        private final Scanner fileScanner;

        public PersonScannerAdapter(Scanner fileScanner) {
            this.fileScanner = fileScanner;
        }



        @Override
        public Person read() throws IOException {
            //return null;
            String str = fileScanner.nextLine();
            if(str == null) return null;
            String[] parts = str.split(" ");
            // [0-2] - ФИО
            // [3-5] - dd mm yyyy
            String strDate = parts[3]+"/"+parts[4]+"/"+parts[5];
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            try {
                Date d = dateFormat.parse(strDate);
                Person p = new Person(parts[1], parts[2], parts[0], d);
                return p;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        public void close() throws IOException {
            fileScanner.close();
        }
    }
}
