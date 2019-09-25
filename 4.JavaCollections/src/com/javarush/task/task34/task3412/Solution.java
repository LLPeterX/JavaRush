package com.javarush.task.task34.task3412;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/* 
Добавление логирования в класс
*/

public class Solution {
    private static final Logger logger = LoggerFactory.getLogger(Solution.class);

    private int value1;
    private String value2;
    private Date value3;

    public Solution(int value1, String value2, Date value3) {
        this.value1 = value1;
        this.value2 = value2;
        this.value3 = value3;
        logger.debug("Constructor {} {} {}",value1, value2, value3); // d1
    }

    public static void main(String[] args) {

    }

    public void calculateAndSetValue3(long value) {
        value -= 133;
        if (value > Integer.MAX_VALUE) {
            logger.trace("Warning: value greather than Integer.MAX_VALUE");
            value1 = (int) (value / Integer.MAX_VALUE);
            logger.debug("value1={}",value1); // d2
        } else {
            value1 = (int) value;
            logger.debug("value1={}",value1); // d2
        }

    }

    public void printString() {
        if (value2 != null) {
            System.out.println(value2.length());
        } else {
            logger.trace("Warning: value=null"); // w1
        }


    }

    public void printDateAsLong() {
        if (value3 != null) {
            System.out.println(value3.getTime());
        } else {
            logger.trace("Warning: value2=null");
        }

    }

    public void divide(int number1, int number2) {
        try {
            if(number2==0) {
                logger.trace("Warning: number2 is 0"); // w3
            }
            System.out.println(number1 / number2);
        } catch (ArithmeticException e) {
            logger.error(e.getMessage(),e);
        }
    }

    public void setValue1(int value1) {
        this.value1 = value1;
        logger.debug("value1={}",value1); // d5
    }

    public void setValue2(String value2) {
        this.value2 = value2;
        logger.debug("value2={}",value2); // d6
    }

    public void setValue3(Date value3) {
        this.value3 = value3;
        logger.debug("value3={}",value3); // d7
    }
}
