package com.javarush.task.task09.task0921;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/* 
Метод в try..catch
*/

public class Solution {
    public static void main(String[] args) {
        readData();
    }

    public static void readData() {
        //напишите тут ваш код
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<Integer> numbers = new ArrayList<Integer>();
        while(true) {
            try {
                Integer num = Integer.parseInt(reader.readLine());
                //System.out.println(" >> "+num);
                numbers.add(num);
//            } catch (NumberFormatException e) {
//                for (Integer i : numbers)
//                    System.out.println(i);
//                break;
//            } catch(IOException e) {
//                e.printStackTrace();
//                break;
            } catch (Exception e) {
                //e.printStackTrace();
                for(Integer i : numbers)
                    System.out.println(i);
                break;
            }
        }
    }
}
