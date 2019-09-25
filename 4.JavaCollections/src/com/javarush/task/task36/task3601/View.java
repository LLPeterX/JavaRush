package com.javarush.task.task36.task3601;

public class View {

    public void fireShowDataEvent() {
        Controller c = new Controller();
        System.out.println(c.onShowDataList());
    }
}
