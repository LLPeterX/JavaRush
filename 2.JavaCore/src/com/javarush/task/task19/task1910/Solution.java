package com.javarush.task.task19.task1910;

/* 
Пунктуация
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            String fname1 = reader.readLine();
            String fname2 = reader.readLine();
            reader.close();
            BufferedReader inp = new BufferedReader(new FileReader(fname1));
            BufferedWriter outp = new BufferedWriter(new FileWriter(fname2));
//            while(inp.ready()) {
//                int b = inp.read();
//                if(b!='.' && b!='\n' && b!=',' && b!=';' && b!=':')
//                    outp.write(b);
//            }
            String str;
            while ((str=inp.readLine())!=null) {
                str = str.replaceAll("\\p{Punct}+","");
                str = str.replaceAll("\n","");
                outp.write(str);
            }
            outp.close();
            inp.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

