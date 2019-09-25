package com.javarush.task.task24.task2401;

public class SelfInterfaceMarkerImpl implements SelfInterfaceMarker {

    public void showSome() {
        System.out.println("Helo, itsme!");
    }

    public void showOther() {
        System.out.println("Hi this is enemy!");
    }
}
