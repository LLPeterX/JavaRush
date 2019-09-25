package com.javarush.task.task19.task1923;

/* 
Слова с цифрами
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
        String str;
        //List<String> outList = new ArrayList<String>();
        while((str=input.readLine())!=null) {
            String[] words = str.split(" ");
            //boolean isWritten = false;
            for(int k=0; k<words.length; k++) {
                char[] b = words[k].toCharArray();
//                boolean isWritten=false;
                for (int i = 0; i < b.length; i++) {
                    if (b[ i ] >= '0' && b[ i ] <= '9') {
                        output.write(words[k]);
                        output.write(32);
                        //isWritten = true;
                        break;
                    }
                }
//                if(isWritten) break;
            }
        }
        input.close();
        output.close();
    }
}
