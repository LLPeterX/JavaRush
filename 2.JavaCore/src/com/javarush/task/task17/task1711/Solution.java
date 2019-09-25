package com.javarush.task.task17.task1711;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/* 
CRUD 2
*/

@SuppressWarnings("ALL")
public class Solution {
    public static volatile List<Person> allPeople = new ArrayList<Person>();

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
        //System.out.println("Start! "+args[0]+", len="+args.length);
        //for(int i=0; i<args.length; i++) {
            //String cmd = args[0].;
        //if(args.length<3 || !args[0].startsWith("-")) return;
        //char cmd=args[0].toCharArray()[1];
        switch (args[0]) {
            //if(cmd.equals("-c")) {
            case "-c":
                // add peoples to list
                synchronized (allPeople) {
                    for (int i = 1; i < args.length; i += 3) {
                        strName = args[ i ];
                        strSex = args[ i + 1 ];
                        strBD = args[ i + 2 ];
                        Sex sex = (strSex.equals("м")) ? Sex.MALE : Sex.FEMALE;
                        try {
                            bd = dfIn.parse(strBD);
                        } catch (ParseException e) {
                            bd = new Date();
                        }
                        String strDate = dfOut.format(bd);
                        Person p;
                        if (sex == Sex.MALE)
                            p = Person.createMale(strName, bd);
                        else
                            p = Person.createFemale(strName, bd);
                        allPeople.add(p);
                        System.out.println(allPeople.size() - 1);
                    }
                }
                //prAll();
                break;
            case "-u":
                //update person
                synchronized (allPeople) {
                    for (int i = 1; i < args.length; i += 4) {
                        strId = args[ i ];
                        strName = args[ i + 1 ];
                        strSex = args[ i + 2 ];
                        strBD = args[ i + 3 ];
                        int id;
                        try {
                            id = Integer.parseInt(strId);
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid id");
                            return;
                        }
                        if (id < 0 || id >= allPeople.size()) {
                            System.out.println("Invalid id");
                            return;
                        }

                        try {
                            bd = dfIn.parse(strBD);
                        } catch (ParseException e) {
                            bd = new Date();
                        }
                        Sex sex = (strSex.equals("м")) ? Sex.MALE : Sex.FEMALE;
                        Person p = allPeople.get(id);
                        p.setName(strName);
                        p.setSex(sex);
                        p.setBirthDate(bd);
                        allPeople.set(id, p);
                    }
                }
                //prAll();
                break;
            case "-d":
                //System.out.println("Delete: len="+args.length);
                synchronized (allPeople) {
                    for (int i = 1; i < args.length; i++) {
                        int id = Integer.parseInt(args[i]);
                        System.out.println("Delete "+id); // рисует правильные id's
//                        Person p = allPeople.get(id);
//                        p.setName(null);
//                        p.setSex(null);
//                        p.setBirthDate(null);
//                        allPeople.set(id,p);
                        allPeople.get(id).setName(null);
                        allPeople.get(id).setSex(null);
                        allPeople.get(id).setBirthDate(null);
                    }
                    //prAll();
                }

                break;
            case "-i":
                synchronized (allPeople) {
                    for (int i = 1; i < args.length; i++) {
                        int id = Integer.parseInt(args[i]);
                        Person p = allPeople.get(id);
                        String sex;
                        if (p.getSex() == Sex.MALE) sex = "м";
                        else sex = "ж";
                        System.out.println(p.getName() + " " + sex + " " + dfOut.format(p.getBirthDate()));
                    }
                    //prAll();
                }
                break;
        }
        //}
    }

    static void prAll() {
        for(Person p : allPeople) {
            System.out.println("Person: "+p.getName()+" "+p.getSex()+" "+p.getBirthDate());
        }
    }
}
