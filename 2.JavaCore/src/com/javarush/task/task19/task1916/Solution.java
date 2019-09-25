package com.javarush.task.task19.task1916;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/* 
Отслеживаем изменения
*/

public class Solution {
    public static List<LineItem> lines = new ArrayList<LineItem>();

    public static void main(String[] args) {
        BufferedReader reader= new BufferedReader(new InputStreamReader(System.in));
        try {
            String fileName1 = reader.readLine();
            String fileName2 = reader.readLine();
            reader.close();
            List<String> file1 = new ArrayList<String>();
            List<String> file2 = new ArrayList<String>();
            // read files in lists
            String str;
            BufferedReader input1 = new BufferedReader(new FileReader(fileName1));
            while ((str = input1.readLine()) != null) {
                file1.add(str);
            }
            BufferedReader input2 = new BufferedReader(new FileReader(fileName2));
            while ((str = input2.readLine()) != null) {
                file2.add(str);
            }
            input1.close();
            input2.close();
            // compare two files
//            int j=0;
//            for(int i=0; i<file1.size();i++) {
//                // if both lines are same
//                //String tmp = null;
//                String str1 = file1.get(i);
//                String str2 = file2.get(j);
//                if(str1.equals(str2)) {
//                    lines.add(new LineItem(Type.SAME,str1));
//                    j++;
//                } else {
//                    // строка удалена, если str[j+2]=str[i]
//
//                    // строка добавлена, если иначе
//                }
//            }
            while (!file1.isEmpty() || !file2.isEmpty()) {
                if (file2.isEmpty()) {
                    // file2 закончился раньше, чем file1 - т.е. в file2 нет строки из file1
                    lines.add(new LineItem(Type.REMOVED, file1.get(0)));
                    file1.remove(0);
                }else if (file1.isEmpty()) {
                    // file1 заклнчился раньше, чем file2 - т.е. в file2 добавочная строка
                    lines.add(new LineItem(Type.ADDED, file2.get(0)));
                    file2.remove(0);
                }else if (file1.get(0).equals(file2.get(0))) {
                    // одинаковые строки
                    lines.add(new LineItem(Type.SAME, file1.get(0)));
                    file1.remove(0);
                    file2.remove(0);
                } else if (file1.get(0).equals(file2.get(1))) {
                    // след.строка = текущая из file1: тогда тек. из file2 есть добавляемая (след. = SAME)
                    lines.add(new LineItem(Type.ADDED, file2.get(0)));
                    file2.remove(0);
                } else {
                    lines.add(new LineItem(Type.REMOVED, file1.get(0)));
                    file1.remove(0);
                }
            }
            // -- output ---
            for(LineItem e : lines) {
                System.out.println(e.type+" "+e.line);
            }
            /// -------------
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public static enum Type {
        ADDED,        //добавлена новая строка
        REMOVED,      //удалена строка
        SAME          //без изменений
    }

    public static class LineItem {
        public Type type;
        public String line;

        public LineItem(Type type, String line) {
            this.type = type;
            this.line = line;
        }
    }
}
