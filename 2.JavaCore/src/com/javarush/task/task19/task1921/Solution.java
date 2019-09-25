package com.javarush.task.task19.task1921;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/* 
Хуан Хуанович
*/

public class Solution {
    public static final List<Person> PEOPLE = new ArrayList<Person>();

    public static void main(String[] args) {
        if(args.length == 0) return;
        String fileName = args[0];
        try {
            BufferedReader input = new BufferedReader(new FileReader(fileName));
            String str;
            SimpleDateFormat sf = new SimpleDateFormat("dd.MM.yyyy");
            while ((str = input.readLine()) != null) {
                String[] comp = str.split(" ");
                int argsLen = comp.length;
                String strYear = comp[ --argsLen ];
                String strMonth = comp[ --argsLen ];
                String strDay = comp[ --argsLen ];
                String name = "";
                String strDate = strDay+"."+strMonth+"."+strYear;
                for (int i = 0; i < argsLen; i++)
                    name = name + comp[ i ] + " ";
                name = name.trim();
                Date d = sf.parse(strDate);
                Person p = new Person(name, d);
                PEOPLE.add(p);
                //System.out.println("name: " + name + " " + strDay + "." + strMonth + "." + strYear);
                //System.out.println(">> "+d.toString());
            }
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
