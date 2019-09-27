package com.javarush.task.task33.task3310;

import java.math.BigInteger;
import java.security.SecureRandom;

public class Helper {

    public static String generateRandomString() {
        SecureRandom rnd = new SecureRandom();
        return new BigInteger(130, rnd).toString(36);
    }

    public static void printMessage(String message) {
        System.out.println(message);
    }

//    public static void main(String[] args) {
//        System.out.println(generateRandomString());
//        System.out.println(generateRandomString());
//    }
}
