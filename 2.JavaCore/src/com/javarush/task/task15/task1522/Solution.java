package com.javarush.task.task15.task1522;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* 
Закрепляем паттерн Singleton
*/

public class Solution {
    public static void main(String[] args) {

    }

    public static Planet thePlanet;

    //add static block here - добавьте статический блок тут
    static {
        //Earth earth = Earth.getInstance();
        //Moon moon = Moon.getInstance();
        //Sun sun = Sun.getInstance();
        Planet thePlanet;
        readKeyFromConsoleAndInitPlanet();

    }

    public static void readKeyFromConsoleAndInitPlanet() {
        // implement step #5 here - реализуйте задание №5 тут
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String str = reader.readLine();
            if (str.equals(Planet.EARTH)) {
                thePlanet = Earth.getInstance();
            } else if (str.equals(Planet.MOON)) {
                thePlanet = Moon.getInstance();
            } else if (str.equals(Planet.SUN)) {
                thePlanet = Sun.getInstance();
            } else {
                thePlanet = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
