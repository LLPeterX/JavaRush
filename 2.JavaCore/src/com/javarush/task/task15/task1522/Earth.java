package com.javarush.task.task15.task1522;

public class Earth implements Planet {
    private static Earth instance;

    // вместо конструктора - инициализатор.
    // может не работать в многопоточной среде
    private Earth() {};

    public static Earth getInstance() {
        if(instance == null)
            instance = new Earth();
        return instance;
    }
}
