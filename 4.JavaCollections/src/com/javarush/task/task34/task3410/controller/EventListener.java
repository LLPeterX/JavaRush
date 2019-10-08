package com.javarush.task.task34.task3410.controller;

import com.javarush.task.task34.task3410.model.Direction;

//В процессе работы игры, будут возникать различные события.
// Давай создадим интерфейс слушателя событий EventListener.
// Его должен реализовывать каждый класс, который хочет обрабатывать события.
// А классы, которые будут генерировать события, будут вызывать методы этого интерфейса.
public interface EventListener {
    public void move(Direction direction);
    public void restart();
    public void startNextLevel();
    public void levelCompleted(int level);

}
