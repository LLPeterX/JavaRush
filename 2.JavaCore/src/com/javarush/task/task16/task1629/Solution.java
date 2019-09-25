package com.javarush.task.task16.task1629;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Solution {
    public static volatile BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws InterruptedException {
        Read3Strings t1 = new Read3Strings();
        Read3Strings t2 = new Read3Strings();

        //add your code here - добавьте код тут
        t1.start();
        t1.join();
        t2.start();
        t2.join();

        t1.printResult();
        t2.printResult();
    }

    //add your code here - добавьте код тут
    public static class Read3Strings extends Thread {
        private List<String> list = new ArrayList<String>() ;

        public Read3Strings() {
        }

        @Override
        public void run() {
            try {
                //synchronized (reader) {
                    for(int i=0; i<3; i++) {
                        //if (reader.ready()) {
                            list.add(reader.readLine());
                        //} else break;
                    }
                //}
            } catch (IOException e) {

            }
        }

        public void printResult() {
            for(int i=0; i<list.size(); i++)
                System.out.print(list.get(i)+" ");
            System.out.println();
        }
    }
}
