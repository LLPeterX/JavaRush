package com.javarush.task.task34.task3410.view;

import com.javarush.task.task34.task3410.model.Box;
import com.javarush.task.task34.task3410.model.Home;
import com.javarush.task.task34.task3410.model.Player;
import com.javarush.task.task34.task3410.model.Wall;

import javax.swing.*;
import java.awt.*;

public class Field extends JPanel {
    private View view;

    public Field(View view) {
        this.view = view;
        //this.view.init(); // не сюда!
    }

    // пока заглушка
    public void paint(Graphics g) {
        // тест
        Player p = new Player(30,30);
        Box b = new Box(30,60);
        Home h = new Home(60,30);
        Wall w = new Wall(60,60);
        p.draw(g);
        b.draw(g);
        h.draw(g);
        w.draw(g);
    }
}
