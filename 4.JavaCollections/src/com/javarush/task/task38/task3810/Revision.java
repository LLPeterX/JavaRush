package com.javarush.task.task38.task3810;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Revision {
    //напиши свой код
    // revision, year = 2011, month = 5, day = 30, hour = 18, minute = 35, second = 18
    int revision();
    Date date();
    String comment() default "";
    // authors необязательный
    Author[] authors() default {};
}
