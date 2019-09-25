package com.javarush.task.task19.task1925;

/* 
Длинные слова
*/

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Solution {
    public static void main(String[] args) throws IOException  {
        if(args.length!=2) return;
        String fileName1 = args[0];
        String fileName2 = args[1];
        BufferedReader input = new BufferedReader(new FileReader(fileName1));
        BufferedWriter output = new BufferedWriter(new FileWriter(fileName2));
        List<String> list6 = new ArrayList<String>();
        String str="";
        while ((str=input.readLine())!=null) {
            String words[] = str.split(" ");
            for(int i=0; i<words.length; i++)
                if(words[i].length()>6)
                    list6.add(words[i]);

        }
        input.close();
        for(int i=0; i<list6.size(); i++) {
            if (i > 0)
                output.write(",");
            output.write(list6.get(i));
        }
        output.close();
    }
}
