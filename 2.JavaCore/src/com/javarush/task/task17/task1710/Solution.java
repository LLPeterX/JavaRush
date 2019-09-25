package com.javarush.task.task17.task1710;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/* 
CRUD
*/

public class Solution {
    public static List<Person> allPeople = new ArrayList<Person>();

    static {
        allPeople.add(Person.createMale("Иванов Иван", new Date()));  //сегодня родился    id=0
        allPeople.add(Person.createMale("Петров Петр", new Date()));  //сегодня родился    id=1
    }

    public static void main(String[] args) {
        //start here - начни тут
        String strId, strName, strSex, strBD;
        Date bd;
        SimpleDateFormat dfIn, dfOut;
        dfIn = new SimpleDateFormat("dd/MM/yyyy");
        dfOut = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
        for(int i=0; i<args.length; i++) {
            String cmd = args[i];
            if(cmd.equals("-c")) {
                // add new people to list
                strName = args[++i];
                strSex = args[++i];
                strBD = args[++i];
                Sex sex = (strSex.equals("м")) ? Sex.MALE : Sex.FEMALE;
                try {
                    bd = dfIn.parse(strBD);
                } catch (ParseException e) {
                    bd=new Date();
                }
                String strDate = dfOut.format(bd);
                Person p;
                if(sex == Sex.MALE)
                    p=Person.createMale(strName, bd);
                else
                    p=Person.createFemale(strName,bd);
                allPeople.add(p);
                System.out.println(allPeople.size()-1);
            } else if (cmd.equals("-u")) {
                strId = args[++i];
                strName = args[++i];
                strSex = args[++i];
                strBD = args[++i];
                int id;
                try {
                    id=Integer.parseInt(strId);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid id");
                    return;
                }
                if(id<0 || id>=allPeople.size()) {
                    System.out.println("Invalid id");
                    return;
                }
                Sex sex = (strSex.equals("м")) ? Sex.MALE : Sex.FEMALE;
                try {
                    bd = dfIn.parse(strBD);
                } catch (ParseException e) {
                    bd=new Date();
                }
                Person p = allPeople.get(id);
                p.setName(strName);
                p.setSex(sex);
                p.setBirthDate(bd);
                allPeople.set(id,p);
            } else if(cmd.equals("-d")) {
                strId = args[++i];
                int id;
                try {
                    id=Integer.parseInt(strId);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid id");
                    return;
                }
                if(id<0 || id>=allPeople.size()) {
                    System.out.println("Invalid id");
                    return;
                }
                Person p = allPeople.get(id);
                p.setName(null);
                p.setSex(null);
                p.setBirthDate(null);
                allPeople.set(id,p);
            } else if(cmd.equals("-i")) {
                //Миронов м 15-Apr-1990
                strId = args[++i];
                int id;
                try {
                    id=Integer.parseInt(strId);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid id");
                    return;
                }
                if(id<0 || id>=allPeople.size()) {
                    System.out.println("Invalid id");
                    return;
                }
                Person p = allPeople.get(id);
                System.out.println(p.getName()+" "+(p.getSex()==Sex.MALE?"м":"ж")+" "+dfOut.format(p.getBirthDate()));
            }
        }
    }
}
