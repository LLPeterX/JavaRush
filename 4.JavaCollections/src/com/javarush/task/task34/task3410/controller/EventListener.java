package com.javarush.task.task34.task3410.controller;

import com.javarush.task.task34.task3410.model.Direction;

//В процессе работы игры, будут возникать различные события.
// Давай создадим интерфейс слушателя событий EventListener.
// Его должен реализовывать каждый класс, который хочет обрабатывать события.
// А классы, которые будут генерировать события, будут вызывать методы этого интерфейса.
public interface EventListener {
    void move(Direction direction);
    void restart();
    void startNextLevel();
    void levelCompleted(int level);

}
