package com.javarush.task.task18.task1825;

import java.io.*;
import java.util.*;

/* 
Собираем файл
*/

public class Solution {
    public static void main(String[] args) {
        List<String> files = new ArrayList<String>();
        // test here ---------------------------
//          String demo = "c:/temp/lion.avi.part2\nc:/temp/lion.avi.part1\nc:/temp/lion.avi.part3\nend\n";
//          byte[] byteDemo = demo.getBytes();
//          InputStream inDemo = new ByteArrayInputStream(byteDemo);
//          System.setIn(inDemo);
        //---------------------------------------
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            while (true) {
                String fname = reader.readLine();
                if (fname.contains("end")) break;
                files.add(fname);
            }
            String outName = files.get(0);
            outName = outName.substring(0, outName.lastIndexOf("."));
            //System.out.println("OutName = " + outName);
            //System.out.println("IN: "+files.toString());
            Collections.sort(files);
            //System.out.println("OUT: "+files.toString());
            BufferedOutputStream fout = new BufferedOutputStream(new FileOutputStream(outName));
            for (String srcName : files) {
                BufferedInputStream fin = new BufferedInputStream(new FileInputStream(srcName));
                while (fin.available() > 0)
                    fout.write(fin.read());
                fin.close();
            }
            fout.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
