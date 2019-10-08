package com.javarush.task.task34.task3410.model;

import com.javarush.task.task34.task3410.controller.EventListener;

import java.nio.file.Paths;

public class Model {
    public static final int FIELD_CELL_SIZE = 20; // размер ячейки игрового поля
    private EventListener eventListener;
    private GameObjects gameObjects; // все игровые объекты
    private int currentLevel = 1; // тек.уровень
    // проинициализировать
    private LevelLoader levelLoader = new LevelLoader(Paths.get("../res/levels.txt"));


    public void setEventListener(EventListener eventListener) {
        this.eventListener = eventListener;
    }

    public GameObjects getGameObjects() {
        return this.gameObjects;
    }

    // 1.5. Метод restartLevel(int level), он должен получать новые игровые объекты
    // для указанного уровня у загрузчика уровня levelLoader и сохранять их в поле gameObjects.
    public void restartLevel(int level) {
        gameObjects = levelLoader.getLevel(level);
    }

    public void restart() {
        restartLevel(currentLevel);
    }

    public void startNextLevel() {
        this.currentLevel++;
        restartLevel(this.currentLevel);
    }
}
