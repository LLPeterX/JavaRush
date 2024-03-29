package com.javarush.task.task39.task3913;

import java.nio.file.Paths;
import java.util.Date;

public class Solution {
    public static void main(String[] args) {
        //LogParser logParser = new LogParser(Paths.get("c:/logs/"));
        LogParser logParser = new LogParser(Paths.get("c:/temp/logs/"));
        //System.out.println(logParser.getNumberOfUniqueIPs(null, new Date())); // вывети всё
        System.out.println("IP:");
        System.out.println(logParser.getUniqueIPs(null, null)); // вывети всё
        System.out.println(logParser.getIPsForUser("Vasya Pupkin",null, new Date())); // вывети всё
        //System.out.println(logParser.getIPsForEvent(Event.DONE_TASK,null, new Date())); // вывети всё
        //System.out.println(logParser.getIPsForStatus(Status.FAILED,null, new Date())); // вывети всё
        System.out.println("users:");
        System.out.println(logParser.getSolvedTaskUsers(null, null, 18));
        System.out.println(logParser.getDoneTaskUsers(null, null, 15));
        System.out.println("dates:");
        //System.out.println(logParser.getDatesForUserAndEvent("Amigo", Event.SOLVE_TASK, null, null));
        //System.out.println(logParser.getDatesWhenSomethingFailed(null, null));
        System.out.println(logParser.getDateWhenUserDoneTask("Vasya Pupkin",15, null, null));
        System.out.println("events:");
        System.out.println("NumAll: "+logParser.getNumberOfAllEvents(null,null));
        System.out.println("forIP: "+logParser.getEventsForIP("127.0.0.1",null,null));
        System.out.println("Att.Solve 18: "+logParser.getNumberOfAttemptToSolveTask(18,null,null)); // 3
        System.out.println("Att.Solve 18: "+logParser.getNumberOfAttemptToSolveTask(18,null,new Date())); // 1
        System.out.println("Solv&Num: "+logParser.getAllDoneTasksAndTheirNumber(null,new Date())); // 1=1, 18=1
        System.out.println("Solv&Num_all: "+logParser.getAllDoneTasksAndTheirNumber(null,null)); // 1=1, 18=3
        System.out.println("QL:");
        //logParser.execute("get ip for user = \"Vasya Pupkin\"");
        //System.out.println();
//        Set<Object> set = logParser.execute("get ip");
//        System.out.println("Got IP: "+ set);
//        System.out.println("Got users: "+logParser.execute("get user"));
//        System.out.println("Got dates:"+logParser.execute("get date"));
//        System.out.println("QL1:");
        //System.out.println("ALL:"+logParser.execute("get ip"));
        //System.out.println("Pupkin: "+logParser.execute("get ip for user = \"Vasya Pupkin\""));
        //System.out.println("MZ2: "+logParser.execute("get ip for user = \"Eduard Petrovich Morozko\" and date between \"11.12.2013 0:00:00\" and \"03.01.2014 23:59:59\""));
        //System.out.println("IpEv: "+logParser.execute("get ip for event = \"WRITE_MESSAGE\" and date between \"13.09.2013 5:04:50\" and \"03.01.2014 03:45:23\""));
        //System.out.println("StDate: "+logParser.execute("get status for date = \"13.09.2013 5:04:50\" and date between \"13.09.2013 5:04:50\" and \"03.01.2014 03:45:23\""));
        System.out.println("DtEv: "+ logParser.execute("get date for event = \"LOGIN\" and date between \"13.09.2013 5:04:50\" and \"03.01.2014 03:45:23\""));
        System.out.println("IPv: "+ logParser.execute("get ip for event = \"LOGIN\" and date between \"13.09.2013 5:04:50\" and \"03.01.2014 03:45:23\""));
    }
}