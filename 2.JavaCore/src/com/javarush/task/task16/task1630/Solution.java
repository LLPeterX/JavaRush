package com.javarush.task.task16.task1630;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Solution {
    public static String firstFileName;
    public static String secondFileName;

    //add your code here - добавьте код тут
    static {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            firstFileName = reader.readLine();
            secondFileName = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) throws InterruptedException {
        //System.out.println("first name:"+firstFileName);
        //System.out.println("second name:"+secondFileName);
        systemOutPrintln(firstFileName);
        systemOutPrintln(secondFileName);
    }

    public static void systemOutPrintln(String fileName) throws InterruptedException {
        ReadFileInterface f = new ReadFileThread();
        f.setFileName(fileName);
        f.start();
        //add your code here - добавьте код тут
        f.join();
        System.out.println(f.getFileContent());
    }

    public interface ReadFileInterface {

        void setFileName(String fullFileName);

        String getFileContent();

        void join() throws InterruptedException;

        void start();
    }

    //add your code here - добавьте код тут

    public static class ReadFileThread extends Thread implements ReadFileInterface {

        private String filename;
        private String content="";

        @Override
        public void setFileName(String fullFileName) {
            this.filename = fullFileName;
        }

        @Override
        public String getFileContent() {
            return this.content;
        }

        @Override
        public void run() {
            FileReader fr=null;
            BufferedReader reader=null;
            try {
                fr = new FileReader(this.filename);
                reader = new BufferedReader(fr);
                String str;
                while((str = reader.readLine())!=null) {
                    if(content.isEmpty())
                        content = str;
                    else
                        content = content+" "+str;
                }
                fr.close();
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
