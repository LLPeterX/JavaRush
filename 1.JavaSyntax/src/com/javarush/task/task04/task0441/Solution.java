package com.javarush.task.task04.task0441;

/* 
Как-то средненько
*/

import java.io.*;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws Exception {
        //напишите тут ваш код
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n1 = Integer.parseInt(reader.readLine());
        int n2 = Integer.parseInt(reader.readLine());
        int n3 = Integer.parseInt(reader.readLine());
        if(n1==n2 && n1==n3 && n2==n3) {
            System.out.println(n1);
            return;
        }
//        System.out.println("n1="+n1+", n2="+n2+", n3="+n3);
        int max = Math.max(Math.max(n1,n2),Math.max(n2,n3));
        int min = Math.min(Math.min(n1,n2),Math.min(n2,n3));
        if(n1==n2 || n1==n3)
            System.out.println(n1);
        else if (n2==n1 || n2==n3)
            System.out.println(n2);
        else if(n3==n1 || n3==n2)
            System.out.println(n3);
        else
            {
            //System.out.print("Среднее: ");
        //    System.out.println((n1 + n2 + n3) / 3);
             int middle = n1+n2+n3-(min+max);
             System.out.println(middle);
        }


    }
}
