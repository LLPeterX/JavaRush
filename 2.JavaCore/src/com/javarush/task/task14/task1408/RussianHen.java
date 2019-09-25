package com.javarush.task.task14.task1408;

public class RussianHen extends Hen {
    @Override
    public int getCountOfEggsPerMonth() {
        return 13;
    }

    @Override
    public String getDescription() {
        return super.getDescription()+" Моя страна - Russia. Я несу "+this.getCountOfEggsPerMonth()+" яиц в месяц.";
    }
}
