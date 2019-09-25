package com.javarush.task.task14.task1420;

/* 
НОД
*/

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//        try {
            int num1 = Integer.parseInt(reader.readLine());
            int num2 = Integer.parseInt(reader.readLine());
            if(num1<=0 || num2<=0) {
                throw new NumberFormatException();
            }
            if(num1>=0 && num2>=0)
              System.out.println(nod(num1,num2));

//        } catch (NumberFormatException e) {
//            System.out.println("Неверные числа");
//        } catch (ArithmeticException e) {
//            System.out.println("Деление на 0!");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    public static int nod(int a, int b)
    {
        if (b == 0)
            return a;
        return nod(b, a % b);
    }
}
