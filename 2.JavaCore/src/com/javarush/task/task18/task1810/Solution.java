package com.javarush.task.task18.task1810;

/* 
DownloadException
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws DownloadException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            while (true) {
                String fname = reader.readLine();
                FileInputStream in = new FileInputStream(fname);
                if (in.available() < 1000) {
                    in.close();
                    throw new DownloadException();
                }
                in.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static class DownloadException extends Exception {

    }
}
