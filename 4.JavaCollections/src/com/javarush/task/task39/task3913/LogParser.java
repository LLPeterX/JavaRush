package com.javarush.task.task39.task3913;

import com.javarush.task.task39.task3913.query.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LogParser implements IPQuery, UserQuery, DateQuery, EventQuery, QLQuery {
    private Path logDir; // каталог с логами (м.б. несколько)
    //private List<String> logStrData; // потом удалим
    private List<Log> logData; // основной массив данных(классов Log)

    public LogParser(Path logDir) {
        this.logDir = logDir;
        logData = new ArrayList<>();
        // записать содержимое всех логов в logData
        for (File fname : logDir.toFile().listFiles()) {
            if (fname.getName().endsWith(".log")) {
                try (BufferedReader input = new BufferedReader(new FileReader(fname))) {
                    String str;
                    while ((str = input.readLine()) != null) {
                        //logStrData.add(str); // надо удалить
                        logData.add(new Log(str));
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    // ------------------------------- реализация методов интерфейса IPQuery
    @Override
    public int getNumberOfUniqueIPs(Date after, Date before) {
        return getUniqueIPs(after, before).size();
    }

    @Override
    public Set<String> getUniqueIPs(Date after, Date before) {
        //return null;
        Set<String> set = new TreeSet<String>(); // IP - число
        // after <= date <=before
        long time0 = (after == null) ? 0 : after.getTime();
        long time1 = (before == null) ? Long.MAX_VALUE : before.getTime();

        for (Log log : logData) {
            long timeLog = log.date.getTime();
            if (timeLog >= time0 && timeLog <= time1) {
                set.add(log.ip);
            }
        }
        return set;
    }

    // вернуть set из IP, с которыми работал данный юзер
    @Override
    public Set<String> getIPsForUser(String user, Date after, Date before) {
        //return null;
        Set<String> set = new TreeSet<String>(); // IP - число
        long time0 = (after == null) ? 0 : after.getTime();
        long time1 = (before == null) ? Long.MAX_VALUE : before.getTime();

        for (Log log : logData) {
            long timeLog = log.date.getTime();
            if (timeLog >= time0 && timeLog <= time1 && log.user.equals(user)) {
                set.add(log.ip);
            }
        }
        return set;
    }

    @Override
    public Set<String> getIPsForEvent(Event event, Date after, Date before) {
        //return null;
        Set<String> set = new TreeSet<String>();
        long time0 = (after == null) ? 0 : after.getTime();
        long time1 = (before == null) ? Long.MAX_VALUE : before.getTime();

        for (Log log : logData) {
            long timeLog = log.date.getTime();
            if (timeLog >= time0 && timeLog <= time1 && log.event == event) {
                set.add(log.ip);
            }
        }
        return set;
    }

    @Override
    public Set<String> getIPsForStatus(Status status, Date after, Date before) {
        //   return null;
        Set<String> set = new TreeSet<String>();
        long time0 = (after == null) ? 0 : after.getTime();
        long time1 = (before == null) ? Long.MAX_VALUE : before.getTime();

        for (Log log : logData) {
            long timeLog = log.date.getTime();
            if (timeLog >= time0 && timeLog <= time1 && log.status == status) {
                set.add(log.ip);
            }
        }
        return set;
    }

    @Override
    public Set<String> getAllUsers() {
        Set<String> set = new TreeSet<>();
        for (Log log : logData)
            set.add(log.user);
        return set;
    }

    @Override
    public int getNumberOfUsers(Date after, Date before) {
        Set<String> set = new TreeSet<>();
        long time0 = (after == null) ? 0 : after.getTime();
        long time1 = (before == null) ? Long.MAX_VALUE : before.getTime();
        for (Log log : logData) {
            long timeLog = log.date.getTime();
            if (timeLog >= time0 && timeLog <= time1) {
                set.add(log.user);
            }
        }
        return set.size();
    }

    public Set<String> getIPforEvent(Event e) {
        Set<String> set = new HashSet<>();
        for (Log log : logData) {
            if (log.event == e)
                set.add(log.ip);
        }
        return set;
    }

    public Set<String> getIPforStatus(Status st) {
        Set<String> set = new HashSet<>();
        for (Log log : logData) {
            if (log.status == st)
                set.add(log.ip);
        }
        return set;
    }


    public Set<String> getIPforDate(Date date) {
        Set<String> set = new HashSet<>();
        long time = date.getTime();
        for (Log log : logData) {
            if (log.date.getTime() == time)
                set.add(log.ip);
        }
        return set;
    }

    @Override
    public int getNumberOfUserEvents(String user, Date after, Date before) {
        Set<Event> set = new TreeSet<>();
        long time0 = (after == null) ? 0 : after.getTime();
        long time1 = (before == null) ? Long.MAX_VALUE : before.getTime();
        for (Log log : logData) {
            long timeLog = log.date.getTime();
            if (timeLog >= time0 && timeLog <= time1 && log.user.equals(user)) {
                set.add(log.event);
            }
        }
        return set.size();
    }

    @Override
    public Set<String> getUsersForIP(String ip, Date after, Date before) {
//        return null;
        Set<String> set = new TreeSet<>();
        long time0 = (after == null) ? 0 : after.getTime();
        long time1 = (before == null) ? Long.MAX_VALUE : before.getTime();
        for (Log log : logData) {
            long timeLog = log.date.getTime();
            if (timeLog >= time0 && timeLog <= time1 && log.ip.equals(ip)) {
                set.add(log.user);
            }
        }
        return set;
    }

    public Set<String> getUsersForDate(Date d) {
        Set<String> set = new TreeSet<>();
        for (Log log : logData) {
            long timeLog = log.date.getTime();
            if (timeLog == d.getTime()) {
                set.add(log.user);
            }
        }
        return set;
    }

    public Set<String> getUsersForEvent(Event e) {
        Set<String> users = new HashSet<>();
        for (Log log : logData)
            if (log.event == e)
                users.add(log.user);
        return users;
    }

    public Set<String> getUsersForStatus(Status e) {
        Set<String> users = new HashSet<>();
        for (Log log : logData)
            if (log.status == e)
                users.add(log.user);
        return users;
    }

    @Override
    public Set<String> getLoggedUsers(Date after, Date before) {
        //return null;
        Set<String> set = new TreeSet<>();
        long time0 = (after == null) ? 0 : after.getTime();
        long time1 = (before == null) ? Long.MAX_VALUE : before.getTime();
        for (Log log : logData) {
            long timeLog = log.date.getTime();
            if (timeLog >= time0 && timeLog <= time1 && log.event == Event.LOGIN) {
                set.add(log.user);
            }
        }
        return set;
    }

    @Override
    public Set<String> getDownloadedPluginUsers(Date after, Date before) {
        Set<String> set = new TreeSet<>();
        long time0 = (after == null) ? 0 : after.getTime();
        long time1 = (before == null) ? Long.MAX_VALUE : before.getTime();
        for (Log log : logData) {
            long timeLog = log.date.getTime();
            if (timeLog >= time0 && timeLog <= time1 && log.event == Event.DOWNLOAD_PLUGIN) {
                set.add(log.user);
            }
        }
        return set;
    }

    @Override
    public Set<String> getWroteMessageUsers(Date after, Date before) {
        Set<String> set = new TreeSet<>();
        long time0 = (after == null) ? 0 : after.getTime();
        long time1 = (before == null) ? Long.MAX_VALUE : before.getTime();
        for (Log log : logData) {
            long timeLog = log.date.getTime();
            if (timeLog >= time0 && timeLog <= time1 && log.event == Event.WRITE_MESSAGE) {
                set.add(log.user);
            }
        }
        return set;
    }

    @Override
    public Set<String> getSolvedTaskUsers(Date after, Date before) {
        Set<String> set = new TreeSet<>();
        long time0 = (after == null) ? 0 : after.getTime();
        long time1 = (before == null) ? Long.MAX_VALUE : before.getTime();
        for (Log log : logData) {
            long timeLog = log.date.getTime();
            if (timeLog >= time0 && timeLog <= time1 && log.event == Event.SOLVE_TASK) {
                set.add(log.user);
            }
        }
        return set;
    }

    @Override
    public Set<String> getSolvedTaskUsers(Date after, Date before, int task) {
        Set<String> set = new TreeSet<>();
        long time0 = (after == null) ? 0 : after.getTime();
        long time1 = (before == null) ? Long.MAX_VALUE : before.getTime();
        for (Log log : logData) {
            long timeLog = log.date.getTime();
            if (timeLog >= time0 && timeLog <= time1 && log.event == Event.SOLVE_TASK && log.taskNum == task) {
                set.add(log.user);
            }
        }
        return set;
    }

    @Override
    public Set<String> getDoneTaskUsers(Date after, Date before) {
        Set<String> set = new TreeSet<>();
        long time0 = (after == null) ? 0 : after.getTime();
        long time1 = (before == null) ? Long.MAX_VALUE : before.getTime();
        for (Log log : logData) {
            long timeLog = log.date.getTime();
            if (timeLog >= time0 && timeLog <= time1 && log.event == Event.DONE_TASK) {
                set.add(log.user);
            }
        }
        return set;
    }

    @Override
    public Set<String> getDoneTaskUsers(Date after, Date before, int task) {
        Set<String> set = new TreeSet<>();
        long time0 = (after == null) ? 0 : after.getTime();
        long time1 = (before == null) ? Long.MAX_VALUE : before.getTime();
        for (Log log : logData) {
            long timeLog = log.date.getTime();
            if (timeLog >= time0 && timeLog <= time1 && log.event == Event.DONE_TASK && log.taskNum == task) {
                set.add(log.user);
            }
        }
        return set;
    }
    // -------------------- интерфейс DateQuery

    // в условии не определен, но нужен на этапе выборки всех уникальных дат
    public Set<Date> getAllDates() {
        Set<Date> set = new HashSet<>();
        for (Log log : logData) {
            set.add(log.date);
        }
        return set;
    }

    public Set<Date> getDatesForIP(String ip) {
        Set<Date> set = new TreeSet<>();
        for (Log log : logData) {
            if (log.ip.equals(ip))
                set.add(log.date);
        }
        return set;
    }

    public Set<Date> getDatesForUser(String user) {
        Set<Date> set = new TreeSet<>();
        for (Log log : logData) {
            if (log.user.equals(user))
                set.add(log.date);
        }
        return set;
    }
    public Set<Date> getDatesForEvent(Event e) {
        Set<Date> set = new TreeSet<>();
        for (Log log : logData) {
            if (log.event == e)
                set.add(log.date);
        }
        return set;
    }

    public Set<Date> getDatesForStatus(Status e) {
        Set<Date> set = new TreeSet<>();
        for (Log log : logData) {
            if (log.status == e)
                set.add(log.date);
        }
        return set;
    }


    @Override
    public Set<Date> getDatesForUserAndEvent(String user, Event event, Date after, Date before) {
        Set<Date> set = new TreeSet<>();
        long time0 = (after == null) ? 0 : after.getTime();
        long time1 = (before == null) ? Long.MAX_VALUE : before.getTime();
        for (Log log : logData) {
            long timeLog = log.date.getTime();
            if (timeLog >= time0 && timeLog <= time1 && log.user.equals(user) && log.event == event) {
                set.add(log.date);
            }
        }
        return set;
    }

    @Override
    public Set<Date> getDatesWhenSomethingFailed(Date after, Date before) {
        Set<Date> set = new TreeSet<>();
        long time0 = (after == null) ? 0 : after.getTime();
        long time1 = (before == null) ? Long.MAX_VALUE : before.getTime();
        for (Log log : logData) {
            long timeLog = log.date.getTime();
            if (timeLog >= time0 && timeLog <= time1 && log.status == Status.FAILED) {
                set.add(log.date);
            }
        }
        return set;
    }

    @Override
    public Set<Date> getDatesWhenErrorHappened(Date after, Date before) {
        Set<Date> set = new TreeSet<>();
        long time0 = (after == null) ? 0 : after.getTime();
        long time1 = (before == null) ? Long.MAX_VALUE : before.getTime();
        for (Log log : logData) {
            long timeLog = log.date.getTime();
            if (timeLog >= time0 && timeLog <= time1 && log.status == Status.ERROR) {
                set.add(log.date);
            }
        }
        return set;
    }

    @Override
    public Date getDateWhenUserLoggedFirstTime(String user, Date after, Date before) {
        TreeSet<Date> set = new TreeSet<>();
        long time0 = (after == null) ? 0 : after.getTime();
        long time1 = (before == null) ? Long.MAX_VALUE : before.getTime();
        for (Log log : logData) {
            long timeLog = log.date.getTime();
            if (timeLog >= time0 && timeLog <= time1 && log.event == Event.LOGIN && log.user.equals(user)) {
                set.add(log.date);
            }
        }
        return set.isEmpty() ? null : set.first();
    }

    @Override
    public Date getDateWhenUserSolvedTask(String user, int task, Date after, Date before) {
        TreeSet<Date> set = new TreeSet<>();
        long time0 = (after == null) ? 0 : after.getTime();
        long time1 = (before == null) ? Long.MAX_VALUE : before.getTime();
        for (Log log : logData) {
            long timeLog = log.date.getTime();
            if (timeLog >= time0 && timeLog <= time1 && log.event == Event.SOLVE_TASK && log.user.equals(user) && log.taskNum == task) {
                set.add(log.date);
            }
        }
        return set.isEmpty() ? null : set.first();
    }

    @Override
    public Date getDateWhenUserDoneTask(String user, int task, Date after, Date before) {
        TreeSet<Date> set = new TreeSet<>();
        long time0 = (after == null) ? 0 : after.getTime();
        long time1 = (before == null) ? Long.MAX_VALUE : before.getTime();
        for (Log log : logData) {
            long timeLog = log.date.getTime();
            if (timeLog >= time0 && timeLog <= time1 && log.event == Event.DONE_TASK && log.user.equals(user) && log.taskNum == task) {
                set.add(log.date);
            }
        }
        return set.isEmpty() ? null : set.first();
    }

    @Override
    public Set<Date> getDatesWhenUserWroteMessage(String user, Date after, Date before) {
        Set<Date> set = new HashSet<>();
        long time0 = (after == null) ? 0 : after.getTime();
        long time1 = (before == null) ? Long.MAX_VALUE : before.getTime();
        for (Log log : logData) {
            long timeLog = log.date.getTime();
            if (timeLog >= time0 && timeLog <= time1 && log.event == Event.WRITE_MESSAGE && log.user.equals(user)) {
                set.add(log.date);
            }
        }
        return set;
    }

    @Override
    public Set<Date> getDatesWhenUserDownloadedPlugin(String user, Date after, Date before) {
        Set<Date> set = new HashSet<>();
        long time0 = (after == null) ? 0 : after.getTime();
        long time1 = (before == null) ? Long.MAX_VALUE : before.getTime();
        for (Log log : logData) {
            long timeLog = log.date.getTime();
            if (timeLog >= time0 && timeLog <= time1 && log.event == Event.DOWNLOAD_PLUGIN && log.user.equals(user)) {
                set.add(log.date);
            }
        }
        return set;
    }

    // --------------------- импелементация EventQuery
    @Override
    public int getNumberOfAllEvents(Date after, Date before) {
        return getAllEvents(after, before).size();
    }

    @Override
    public Set<Event> getAllEvents(Date after, Date before) {
        Set<Event> set = new HashSet<>();
        long time0 = (after == null) ? 0 : after.getTime();
        long time1 = (before == null) ? Long.MAX_VALUE : before.getTime();
        for (Log log : logData) {
            long timeLog = log.date.getTime();
            if (timeLog >= time0 && timeLog <= time1) {
                set.add(log.event);
            }
        }
        return set;
    }

    @Override
    public Set<Event> getEventsForIP(String ip, Date after, Date before) {
        Set<Event> set = new HashSet<>();
        long time0 = (after == null) ? 0 : after.getTime();
        long time1 = (before == null) ? Long.MAX_VALUE : before.getTime();
        for (Log log : logData) {
            long timeLog = log.date.getTime();
            if (timeLog >= time0 && timeLog <= time1 && log.ip.equals(ip)) {
                set.add(log.event);
            }
        }
        return set;
    }

    @Override
    public Set<Event> getEventsForUser(String user, Date after, Date before) {
        Set<Event> set = new HashSet<>();
        long time0 = (after == null) ? 0 : after.getTime();
        long time1 = (before == null) ? Long.MAX_VALUE : before.getTime();
        for (Log log : logData) {
            long timeLog = log.date.getTime();
            if (timeLog >= time0 && timeLog <= time1 && log.user.equals(user)) {
                set.add(log.event);
            }
        }
        return set;
    }

    // для сокращения кода - временный метод получения сета по статусту в диапазоне дат
    private Set<Event> getEventsByStatus(Date after, Date before, Status status) {
        Set<Event> set = new HashSet<>();
        long time0 = (after == null) ? 0 : after.getTime();
        long time1 = (before == null) ? Long.MAX_VALUE : before.getTime();
        for (Log log : logData) {
            long timeLog = log.date.getTime();
            if (timeLog >= time0 && timeLog <= time1 && log.status == status) {
                set.add(log.event);
            }
        }
        return set;
    }

    @Override
    public Set<Event> getFailedEvents(Date after, Date before) {
        return getEventsByStatus(after, before, Status.FAILED);
    }

    @Override
    public Set<Event> getErrorEvents(Date after, Date before) {
        return getEventsByStatus(after, before, Status.ERROR);
    }

    public Set<Event> getEventsForDate(Date d) {
        Set<Event> ev = new HashSet<>();
        for(Log log : logData)
            if(log.date.equals(d))
                ev.add(log.event);
        return ev;
    }

    @Override
    public int getNumberOfAttemptToSolveTask(int task, Date after, Date before) {
        int num = 0;
        long time0 = (after == null) ? 0 : after.getTime();
        long time1 = (before == null) ? Long.MAX_VALUE : before.getTime();
        for (Log log : logData) {
            long timeLog = log.date.getTime();
            if (timeLog >= time0 && timeLog <= time1 && log.event == Event.SOLVE_TASK && log.taskNum == task) {
                num++;
            }
        }
        return num;
    }

    @Override
    public int getNumberOfSuccessfulAttemptToSolveTask(int task, Date after, Date before) {
        int num = 0;
        long time0 = (after == null) ? 0 : after.getTime();
        long time1 = (before == null) ? Long.MAX_VALUE : before.getTime();
        for (Log log : logData) {
            long timeLog = log.date.getTime();
            if (timeLog >= time0 && timeLog <= time1 && log.event == Event.DONE_TASK && log.taskNum == task) {
                num++;
            }
        }
        return num;
    }

    @Override
    public Map<Integer, Integer> getAllSolvedTasksAndTheirNumber(Date after, Date before) {
        Map<Integer, Integer> map = new HashMap<>();
        long time0 = (after == null) ? 0 : after.getTime();
        long time1 = (before == null) ? Long.MAX_VALUE : before.getTime();
        for (Log log : logData) {
            long timeLog = log.date.getTime();
            if (timeLog >= time0 && timeLog <= time1 && log.event == Event.SOLVE_TASK) {
                if (map.containsKey(log.taskNum)) {
                    map.put(log.taskNum, map.get(log.taskNum) + 1);
                } else {
                    map.put(log.taskNum, 1);
                }
            }
        }
        return map;
    }

    @Override
    public Map<Integer, Integer> getAllDoneTasksAndTheirNumber(Date after, Date before) {
        Map<Integer, Integer> map = new HashMap<>();
        long time0 = (after == null) ? 0 : after.getTime();
        long time1 = (before == null) ? Long.MAX_VALUE : before.getTime();
        for (Log log : logData) {
            long timeLog = log.date.getTime();
            if (timeLog >= time0 && timeLog <= time1 && log.event == Event.DONE_TASK) {
                if (map.containsKey(log.taskNum)) {
                    map.put(log.taskNum, map.get(log.taskNum) + 1);
                } else {
                    map.put(log.taskNum, 1);
                }
            }
        }
        return map;
    }

    public Set<Status> getAllStatuses() {
        Set<Status> set = new HashSet<>();
        for (Log log : logData)
            set.add(log.status);
        return set;
    }

    private Date strToDate(String s) {
        SimpleDateFormat sf = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        try {
            return sf.parse(s);
        } catch (ParseException e) {
            return null;
        }

    }

    public Set<Status> getStatusesFromIP(String ip) {
        Set<Status> set = new HashSet<>();
        for(Log log: logData)
            if(log.ip.equals(ip))
                set.add(log.status);
        return set;
    }

    public Set<Status> getStatusesFromUser(String user) {
        Set<Status> set = new HashSet<>();
        for(Log log: logData)
            if(log.user.equals(user))
                set.add(log.status);
        return set;
    }
    public Set<Status> getStatusesFromDate(Date d) {
        Set<Status> set = new HashSet<>();
        for(Log log: logData)
            if(log.date.equals(d))
                set.add(log.status);
        return set;
    }

    public Set<Status> getStatusesFromEvent(Event e) {
        Set<Status> set = new HashSet<>();
        for(Log log: logData)
            if(log.event == e)
                set.add(log.status);
        return set;
    }

    // ------------------ имплементация QLQuery
    @Override
    public Set<Object> execute(String query) {
        // 1 - откуда,
        // 3- имя параметра
        // 4- условие
        Pattern pattern = Pattern.compile("^get\\s+(\\S+)(\\sfor\\s(\\S+)\\s+=\\s+(.+))*");
        Matcher matcher = pattern.matcher(query);
        String what = null, field = null, arg = null;
        if (matcher.find()) {
//            System.out.println("  G1="+matcher.group(1)); // ip
//            System.out.println("  G2="+matcher.group(2));
//            System.out.println("  G3="+matcher.group(3));
//            System.out.println("  G4="+matcher.group(4));
            what = matcher.group(1);
            if (matcher.groupCount() > 2) {
                field = matcher.group(3);
                arg = matcher.group(4); // мржет быть null
                if (arg != null) // удаляем кавычки в начале и в конце
                    arg = arg.replaceAll("^\"|\"$", "");
            }
        } else {
//            System.out.println("FAIL");
            return null;
        }
        //System.out.format("What:%s, field:%s, arg:%s%n",what,field,arg);
        switch (what) {
            case "ip":
                //return new HashSet<>(getUniqueIPs(null,null));
                if (field != null) {
                    switch (field) {
                        case "user":
                            return new HashSet<>(getIPsForUser(arg, null, null));
                        case "date":
                            return new HashSet<>(getIPforDate(strToDate(arg)));
                        case "event":
                            Event e = Event.valueOf(arg);
                            return new HashSet<>(getIPforEvent(e));
                        case "status":
                            Status s = Status.valueOf(arg);
                            return new HashSet<>(getIPforStatus(s));
                    }
                } else {
                    return new HashSet<>(getUniqueIPs(null, null));
                }
            case "user":
                //result = Collections.singleton(getAllUsers());
                if (field == null) {
                    return new HashSet<>(getAllUsers());
                } else {
                    switch (field) {
                        case "ip":
                            return new HashSet<>(getUsersForIP(arg, null, null));
                        case "date":
                            return new HashSet<>(getUsersForDate(strToDate(arg)));
                        case "event":
                            Event e = Event.valueOf(arg);
                            return new HashSet<>(getUsersForEvent(e));
                        case "status":
                            Status s = Status.valueOf(arg);
                            return new HashSet<>(getUsersForStatus(s));
                    }
                }
                //break;
            case "date":
                if (field == null) {
                    return new HashSet<>(getAllDates());
                } else {
                    switch (field) {
                        case "ip":
                            return new HashSet<>(getDatesForIP(arg));
                        case "user":
                            return new HashSet<>(getDatesForUser(arg));
                        case "event":
                            Event e = Event.valueOf(arg);
                            return new HashSet<>(getDatesForEvent(e));
                        case "status":
                            Status s = Status.valueOf(arg);
                            return new HashSet<>(getDatesForStatus(s));
                    }
                }
                //break;
            case "event":
                if(field == null) {
                    return new HashSet<>(getAllEvents(null, null));
                } else {
                    switch (field) {
                        case "ip":
                            //return new HashSet<>(getAllEvents(null,null));
                            return new HashSet<>(getEventsForIP(arg,null,null));
                        case "user":
                            return new HashSet<>(getEventsForUser(arg,null,null));
                        case "date":
                            Date d = strToDate(arg);
                            return new HashSet<>(getEventsForDate(d));
                        case "status":
                            Status s = Status.valueOf(arg);
                            return new HashSet<>(getEventsByStatus(null, null, s));
                    }
                }
            case "status":
                if(field == null) {
                    return new HashSet<>(getAllStatuses());
                } else {
                    switch (field) {
                        case "ip":
                            return new HashSet<>(getStatusesFromIP(arg));
                        case "user":
                            return new HashSet<>(getStatusesFromUser(arg));
                        case "date":
                            Date d = strToDate(arg);
                            return new HashSet<>(getStatusesFromDate(d));
                        case "event":
                            Event e = Event.valueOf(arg);
                            return new HashSet<>(getStatusesFromEvent(e));
                    }
                }
        }
        return null;
    }


    // --- внутренний служебный класс logData для хранения компонентов лога
    private class Log {
        String ip;
        String user;
        Date date;
        Event event;
        int taskNum;
        Status status;
        String originalString;

        // конструктор
        Log(String logStr) {
            String[] comps = logStr.split("\t");
            ip = comps[ 0 ];
            user = comps[ 1 ];
            originalString = logStr;
            // берем дату
            SimpleDateFormat sf = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
            try {
                date = sf.parse(comps[ 2 ]);
            } catch (Exception e) {
                e.printStackTrace();
            }
            String[] eventStr = comps[ 3 ].split(" ");
            // Для событий SOLVE_TASK и DONE_TASK существует доп.параметр ч/з пробел
            if (eventStr[ 0 ].equals("SOLVE_TASK") || eventStr[ 0 ].equals("DONE_TASK")) {
                event = Event.valueOf(eventStr[ 0 ]);
                taskNum = Integer.parseInt(eventStr[ 1 ]);
            } else {
                event = Event.valueOf(comps[ 3 ]);
                taskNum = 0;
            }
            // статус
            status = Status.valueOf(comps[ 4 ]);
        }

        public String toString() {
            return originalString;
        }
    }
}