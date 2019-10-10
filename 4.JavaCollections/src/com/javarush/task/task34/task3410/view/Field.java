package com.javarush.task.task34.task3410.view;

import com.javarush.task.task34.task3410.controller.EventListener;
import com.javarush.task.task34.task3410.model.Direction;
import com.javarush.task.task34.task3410.model.GameObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Field extends JPanel {
    private View view;
    private EventListener eventListener;

    public Field(View view) {
        this.view = view;
        // для Sokoban(14):
        // 14.3. В конструкторе класса Field:
        //14.3.1. Создай объект класса KeyHandler.
        //14.3.2. Добавь его слушателем с помощью метода addKeyListener().
        //14.3.3. Установи focusable в true (метод setFocusable()).
        KeyHandler keyHandler = new KeyHandler();
        addKeyListener(keyHandler);
        setFocusable(true);
    }

    // пока заглушка
    public void paint(Graphics g) {
        // залить всё поле темно-коричневым цветом
        Color backgroundColor = new Color(66,46,3); // темно-коричневый
        g.setColor(backgroundColor);
        g.fillRect(0,0,500,500); // размер игрового поля 500 x 500
        for(GameObject o : view.getGameObjects().getAll()) {
            o.draw(g);
        }
    }

    public void setEventListener(EventListener eventListener) {
        this.eventListener = eventListener;
    }
    // ------------------- обработчик клавиш ---------------------------------------
    // вложенный класс (д.б. public, хотя в условии этого нет!)
    public class KeyHandler extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    eventListener.move(Direction.LEFT);
                    break;
                case KeyEvent.VK_RIGHT:
                    eventListener.move(Direction.RIGHT);
                    break;
                case KeyEvent.VK_UP:
                    eventListener.move(Direction.UP);
                    break;
                case KeyEvent.VK_DOWN:
                    eventListener.move(Direction.DOWN);
                    break;
                case KeyEvent.VK_R:
                    eventListener.restart();
                    break;
            } // switch
        } // keyPressed()
    }
}
