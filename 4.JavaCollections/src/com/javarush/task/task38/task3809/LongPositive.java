package com.javarush.task.task38.task3809;

import java.lang.annotation.*;

@Target(value = ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface LongPositive {
}
