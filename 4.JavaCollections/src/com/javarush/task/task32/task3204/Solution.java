package com.javarush.task.task32.task3204;

import java.io.ByteArrayOutputStream;
import java.io.StringWriter;

/* 
Генератор паролей
*/
public class Solution {
    public static void main(String[] args) {
        ByteArrayOutputStream password = getPassword();
        System.out.println(password.toString());
    }

    public static ByteArrayOutputStream getPassword() {
        //return null;
        // 1) 8 символов.
        // 2) только цифры и латинские буквы разного регистра.
        // 3) обязательно должны присутствовать цифры, и буквы разного регистра.
        // Все сгенерированные пароли должны быть уникальные.
        //StringWriter sw = new StringWriter();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        char[] digits = "0123456789".toCharArray();
        char[] uppers = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
        char[] lowers = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        int pos;
        char c='z';
        for(int i=0; i<8; i++) {
            int what = (int)(Math.random()*4);
            if(i==0) what=3;
            if(i==3) what=1;
            if(i==5) what=0;
            switch (what) {
                case 0: // digits
                    c= digits[ (int) (Math.random() * digits.length)];
                    break;
                case 1:
                    c = uppers[ (int) (Math.random() * uppers.length) ];
                    break;
                case 3:
                    c = lowers[ (int) (Math.random() * lowers.length)];
                    break;
            }
            baos.write(c);
        }
        return baos;
    }
}