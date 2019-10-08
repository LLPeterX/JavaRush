package com.javarush.task.task34.task3410.view;

import com.javarush.task.task34.task3410.controller.Controller;
import com.javarush.task.task34.task3410.controller.EventListener;
import com.javarush.task.task34.task3410.model.GameObjects;

import javax.swing.*;

public class View extends JFrame {
    private Controller controller;
    private Field field;

    public View(Controller controller) {
        this.controller = controller;
    }

    public void init() {
        field = new Field(this);
        add(field);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(500, 500);
        setLocationRelativeTo(null);
        setTitle("Сокобан");
        setVisible(true);
    }

    public void setEventListener(EventListener eventListener) {
        field.setEventListener(eventListener);
    }

    // должен вызывать у игрового поля field метод repaint().
    // Другими словами, метод update() будет обновлять представление (перерисовывать поле).
    public void update() {
        field.repaint();
    }

    public GameObjects getGameObjects() {
        return controller.getGameObjects();
    }

    public void completed(int level) {
        // Метод должен:
        //13.3.1. Обновлять представление.
        //13.3.2. Показывать диалоговое окно с информацией о том, что пользователь прошел
        //какой-то уровень.
        update();
        String message = String.format("Уровень %d завершен",level);
        JOptionPane.showMessageDialog(null,message);
        controller.startNextLevel();
    }

}
