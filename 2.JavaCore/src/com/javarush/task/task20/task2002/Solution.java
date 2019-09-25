package com.javarush.task.task20.task2002;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/* 
Читаем и пишем в файл: JavaRush
*/
public class Solution {
    public static void main(String[] args) {
        //you can find your_file_name.tmp in your TMP directory or adjust outputStream/inputStream according to your file's actual location
        //вы можете найти your_file_name.tmp в папке TMP или исправьте outputStream/inputStream в соответствии с путем к вашему реальному файлу
        try {
            File yourFile = File.createTempFile("your_file_name", null);
            OutputStream outputStream = new FileOutputStream(yourFile);
            InputStream inputStream = new FileInputStream(yourFile);

            JavaRush javaRush = new JavaRush();
            //initialize users field for the javaRush object here - инициализируйте поле users для объекта javaRush тут
            SimpleDateFormat sf = new SimpleDateFormat("dd.MM.yyyy");
            User u = new User();
            u.setFirstName("Peter");
            u.setLastName("Lebedev");
            u.setBirthDate(sf.parse("15.02.1965"));
            u.setCountry(User.Country.RUSSIA);
            u.setMale(true);
            javaRush.users.add(u);

            User u1 = new User();
            u1.setFirstName("Lena");
            u1.setLastName("Plotnikova");
            u1.setBirthDate(sf.parse("15.02.1965"));
            u1.setCountry(User.Country.RUSSIA);
            u1.setMale(true);
            javaRush.users.add(u1);

            javaRush.save(outputStream);
            outputStream.flush();

            JavaRush loadedObject = new JavaRush();
            loadedObject.load(inputStream);
            //here check that the javaRush object is equal to the loadedObject object - проверьте тут, что javaRush и loadedObject равны
            if(javaRush.equals(loadedObject)) {
                System.out.println("Objects are equal");
            } else {
                System.out.println("Objects NOT equal");
            }

            outputStream.close();
            inputStream.close();

        } catch (IOException e) {
            //e.printStackTrace();
            System.out.println("Oops, something is wrong with my file");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Oops, something is wrong with the save/load method");
        }
    }

    public static class JavaRush {
        public List<User> users = new ArrayList<>();

        public void save(OutputStream outputStream) throws Exception {
            //implement this method - реализуйте этот метод
            SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
            for(int i=0; i<users.size(); i++) {
                StringBuilder sb = new StringBuilder();
                sb.append(users.get(i).getFirstName());
                sb.append(',');
                sb.append(users.get(i).getLastName());
                sb.append(',');
                long ms = users.get(i).getBirthDate().getTime();
                sb.append(ms);
                sb.append(',');
                sb.append(users.get(i).getCountry());
                sb.append(',');
                sb.append(users.get(i).isMale());
                sb.append('\n');
                outputStream.write(sb.toString().getBytes());
            }
        }

        public void load(InputStream inputStream) throws Exception {
            //implement this method - реализуйте этот метод
            if(inputStream.available()==0) return;
            SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
            StringBuilder sb = new StringBuilder();
            while(inputStream.available()>0)
                sb.append((char)inputStream.read());
            String[] rows = sb.toString().split("\n");
            for(int i=0; i<rows.length; i++) {
                String[] str = rows[i].split(",");
                User u = new User();
                u.setFirstName(str[0]);
                u.setLastName(str[1]);
                //u.setBirthDate(df.parse(str[2]));
                long ms = Long.parseLong(str[2]);
                Date d = new Date(ms);
                u.setBirthDate(d);;
                switch (str[3]) {
                    case "RUSSIA":
                        u.setCountry(User.Country.RUSSIA);
                        break;
                    case "UKRAINE":
                        u.setCountry(User.Country.UKRAINE);
                        break;
                    case "OTHER":
                        u.setCountry(User.Country.OTHER);
                        break;
                }
                u.setMale(str[4].equals("true"));
                this.users.add(u);
            }
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            JavaRush javaRush = (JavaRush) o;

            return users != null ? users.equals(javaRush.users) : javaRush.users == null;

        }

        @Override
        public int hashCode() {
            return users != null ? users.hashCode() : 0;
        }
    }
}
