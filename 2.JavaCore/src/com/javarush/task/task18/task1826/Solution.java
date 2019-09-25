package com.javarush.task.task18.task1826;

/* 
Шифровка
*/

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Solution {
    public static void main(String[] args) {
        String cmd, srcFilename, dstFilename;
        //System.out.println("ENC");
        try {
            cmd = args[ 0 ];
            srcFilename = args[ 1 ];
            dstFilename = args[2];
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Invalid arguments");
            return;
        }
        //System.out.println("INPUT: "+cmd+" "+srcFilename+" "+dstFilename);
//        switch (cmd) {
//            case "-e" : // encrypt
        if(cmd.equals("-e")) {
            try {
                FileInputStream fin = new FileInputStream(srcFilename);
                FileOutputStream fout = new FileOutputStream(dstFilename);
                byte[] b = new byte[ fin.available() ];
                fin.read(b);
                for (int i = 0; i < b.length; i++)
                    b[ i ] = (byte) (b[ i ] +1);
                fout.write(b);
                fout.close();
                fin.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if(cmd.equals("-d")) {
            //case "-d":
                try {
                    FileInputStream fin = new FileInputStream(srcFilename);
                    FileOutputStream fout = new FileOutputStream(dstFilename);
                    byte[] b = new byte[ fin.available() ];
                    fin.read(b);
                    for (int i = 0; i < b.length; i++)
                        b[ i ] = (byte) (b[ i ] - 1);
                    fout.write(b);
                    fout.close();
                    fin.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }

    }

}
