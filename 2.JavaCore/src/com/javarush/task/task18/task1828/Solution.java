package com.javarush.task.task18.task1828;

/* 
Прайсы 2
*/

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Solution {
    public static void main(String[] args) {
        if(args.length==0) {
            System.out.println("Must specify arguments");
            return;
        }
        String strid, productName=null, price=null, quantity=null;
        String fileName=null;
        int inputID;
        boolean deleteMode;
        if(args[0].equals("-d") && args.length==2)
            deleteMode = true;
        else if(args[0].equals("-u") && args.length==5)
            deleteMode = false;
        else {
            System.out.println("Invalid argument");
            return;
        }
        strid = args[1];
        if(args[0].equals("-u")) {
            productName = args[ 2 ];
            price = args[ 3 ];
            quantity = args[ 4 ];
        }
        inputID = Integer.parseInt(strid);
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            fileName = reader.readLine();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        List<String> content = new ArrayList<String>();
        try {
            BufferedReader input = new BufferedReader(new FileReader(fileName));
            String str;
            int id;
            while ((str = input.readLine()) != null) {
                content.add(str);
            }
            input.close();
            int lineNum=-1;
            for(int i=0; i<content.size(); i++) {
                String sid = content.get(i).substring(0,8).trim();
                id = Integer.parseInt(sid);
                if(inputID == id) {
                    if(deleteMode) {
                        content.remove(i);
                    } else {
                        // update: build new string
                        String strnew = content.get(i).substring(0,8)+String.format("%-30.30s%-8s%-4s",productName,price,quantity);
                        content.set(i,strnew);
                    }
                    break;
                }
            }
            // write back
            BufferedWriter output = new BufferedWriter(new FileWriter(fileName)); // без append
            for(int i=0; i<content.size(); i++) {
                output.write(content.get(i));
                output.newLine();
            }
            output.flush();
            output.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: "+fileName);
        }
        catch (IOException e) {
            e.printStackTrace();
        }


    }
}
