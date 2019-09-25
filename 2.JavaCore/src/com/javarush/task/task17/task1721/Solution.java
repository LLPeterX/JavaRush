package com.javarush.task.task17.task1721;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/* 
Транзакционность
*/

public class Solution {
    public static List<String> allLines = new ArrayList<String>();
    public static List<String> forRemoveLines = new ArrayList<String>();

    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName1=null, fileName2=null;
        FileReader fin1=null, fin2=null;
        BufferedReader reader1, reader2;
        Solution sol = new Solution();
        try {
            fileName1 = reader.readLine();
            fileName2 = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fin1 = new FileReader(fileName1);
            reader1 = new BufferedReader(fin1);
            fin2 = new FileReader(fileName2);
            reader2 = new BufferedReader(fin2);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            return;
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        // читаем первый файл в allLines
        String str;
        try {
            while ((str = reader1.readLine()) != null)
                allLines.add(str);
            // читаем второй файл
            while ((str = reader2.readLine()) != null)
                forRemoveLines.add(str);
            reader1.close();
            reader2.close();
        } catch (IOException e) {
            System.out.println("Cannot read file(s)");
        }
        // Проверяем
        try {
            sol.joinData();
        } catch (CorruptedDataException e) {
            e.printStackTrace();
        }


    }

    public void joinData() throws CorruptedDataException {

        //if(allLines.size() != forRemoveLines.size()) {
//            throw new CorruptedDataException();
//            return;
//        }
        //System.out.println("L1:"+allLines.size()+", L2:"+forRemoveLines.size());
        if(forRemoveLines.isEmpty())
            throw new CorruptedDataException();
        for(int i=0; i<forRemoveLines.size(); i++) {
                int j = allLines.indexOf(forRemoveLines.get(i));
                if(j>=0)
                    allLines.remove(j);
                else {
                    allLines.clear();
                    throw new CorruptedDataException();
                }
            }
            //if(allLines.isEmpty()) {



    }
}
