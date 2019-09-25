package com.javarush.task.task18.task1827;

/*
Прайсы
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws Exception {
//        if(args.length == 0)
//            return;
        if(args.length==4 && args[0].equals("-c")) {
            String productName = args[1];
            String price = args[2];
            String quantity=args[3];
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String filename = reader.readLine();
            reader.close();
            BufferedReader input = new BufferedReader(new FileReader(filename));
            String str;
            // find max id
            int max_id = 0;
            while ((str = input.readLine()) != null) {
                String strId = str.substring(0, 8).trim();
                int id = Integer.parseInt(strId);
                if (id > max_id) max_id = id;
            }
            input.close();
            int newid = max_id + 1;
            String strNew = String.format("\n%-8d%-30.30s%-8s%-4s", newid, productName, price, quantity);
            BufferedWriter output = new BufferedWriter(new FileWriter(filename, true));
            output.write(strNew);
            //output.write(10);
            output.flush();
            output.close();
        }
    }
}
