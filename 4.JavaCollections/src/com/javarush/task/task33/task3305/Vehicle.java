package com.javarush.task.task33.task3305;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "className")
//@JsonTypeInfo(use=JsonTypeInfo.Id.NAME, property = "className")
//@JsonSubTypes({
//        @JsonSubTypes.Type(value = Vehicle.class, name="com.javarush.task.task33.task3305.Vehicle")
//})
public abstract class Vehicle {
    protected String name;
    protected String owner;
    protected int age;
}