package com.javarush.task.task34.task3410.controller;

import com.javarush.task.task34.task3410.model.Direction;
import com.javarush.task.task34.task3410.model.GameObjects;
import com.javarush.task.task34.task3410.model.Model;
import com.javarush.task.task34.task3410.view.View;

public class Controller implements EventListener {
    private View view;
    private Model model;

    public Controller() {
        
        this.view = new View(this);
        this.model = new Model();
        this.model.restart();
        this.view.init();
        this.model.setEventListener(this);
        this.view.setEventListener(this);

    }
    // основные методы

    // основной метод -----------------------------------
    public static void main(String[] args) {
        Controller controller = new Controller();
    }
    // --------------------------------------------------

    @Override
    public void move(Direction direction) {
        // должен вызывать move(Direction direction) у модели
        model.move(direction);
        view.update();
    }

    @Override
    public void restart() {
        // перезапустить модель и обновить view
        model.restart();
        view.update();
    }

    @Override
    public void startNextLevel() {
        // запускать у модели новый уровень и обновлять представление.
        model.startNextLevel();
        view.update();
    }

    @Override
    public void levelCompleted(int level) {
        view.completed(level);
    }

    public GameObjects getGameObjects() {
        return model.getGameObjects();
    }
}
