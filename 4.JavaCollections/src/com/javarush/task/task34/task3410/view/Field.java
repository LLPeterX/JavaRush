package com.javarush.task.task34.task3410.view;

import com.javarush.task.task34.task3410.controller.EventListener;
import com.javarush.task.task34.task3410.model.GameObject;

import javax.swing.*;
import java.awt.*;

public class Field extends JPanel {
    private View view;
    private EventListener eventListener;

    public Field(View view) {
        this.view = view;
        //this.view.init(); // не сюда!
    }

    // пока заглушка
    public void paint(Graphics g) {
        /*
        //  --- тест
        Player p = new Player(30,30);
        Box b = new Box(30,60);
        Home h = new Home(60,30);
        Wall w = new Wall(60,60);
        p.draw(g);
        b.draw(g);
        h.draw(g);
        w.draw(g);
        // ---- тест -----
         */

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
}
