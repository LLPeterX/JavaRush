package com.javarush.task.task14.task1408;

public class BelarusianHen extends Hen {
    @Override
    public int getCountOfEggsPerMonth() {
        return 35;
    }

    @Override
    public String getDescription() {
        return super.getDescription()+" Моя страна - Belarus. Я несу "+this.getCountOfEggsPerMonth()+" яиц в месяц.";
    }
}
