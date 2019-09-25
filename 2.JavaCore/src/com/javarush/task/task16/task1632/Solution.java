package com.javarush.task.task16.task1632;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Solution {
    public static List<Thread> threads = new ArrayList<>(5);
    static {
        threads.add(new Thread1());
        threads.add(new Thread2());
        threads.add(new Thread3());
        threads.add(new Thread4());
        threads.add(new Thread5());
    }

    public static void main(String[] args) {
    }

    // бескончно выполняться
    public static class Thread1 extends Thread {

        @Override
        public void run() {
            while(!interrupted()) {

            }
        }
    }

    // при возникновении InterruptedException писать соответствующее
    public static class Thread2 extends Thread {
        @Override
        public void run() {
            while(!interrupted()) {

            }
            System.out.println("InterruptedException");
        }
    }

    // выводить "Ура" каждые полсекунды
    public static class Thread3 extends Thread {

        @Override
        public void run() {
//            while(!interrupted()) {
//                try {
//                    Thread.sleep(500);
//                    System.out.println("Ура");
//                } catch (InterruptedException e) {
//                    return;
//                }
//            }
            try {
                while (true) {
                    System.out.println("Ура");
                    Thread.sleep(500);
                }

            } catch (InterruptedException e) {

            }
        }
    }

    // интерфейс Message и вызове showWarning тек.нить должна прерываться
    public static class Thread4 extends Thread implements Message {

        @Override
        public void run() {
            while(!interrupted()) {

            }

        }

        @Override
        public void showWarning() {
            if(isAlive())
               this.interrupt();
        }
    }

    // читать числа
    public static class Thread5 extends Thread {

        private int sum=0;

        public Thread5() {

        }

        @Override
        public void run() {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String str;
            int num;
            while(true) {
                try {
                    str = reader.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                    break;
                }
                if(str.equals("N")) break;
                try {
                    num = Integer.parseInt(str);
                    sum = sum + num;
                } catch (NumberFormatException e) {
                    break;
                }

            }
            System.out.println(sum);
        }
    }
}