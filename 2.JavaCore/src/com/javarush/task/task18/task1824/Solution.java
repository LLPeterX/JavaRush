package com.javarush.task.task18.task1824;

/* 
Файлы и исключения
*/

//import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.io.*;

public class Solution {
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        //FileInputStream fin1=null, fin2=null;
        String fname=null;
        try {
            while(true) {
                fname = reader.readLine();
                FileInputStream fin = new FileInputStream(fname);
                fin.close();
            }
        } catch (FileNotFoundException e) {
            System.out.println(fname);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

