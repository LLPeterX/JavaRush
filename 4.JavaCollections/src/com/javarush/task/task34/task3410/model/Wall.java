package com.javarush.task.task34.task3410.model;

import java.awt.*;

public class Wall extends CollisionObject {

    public Wall(int x, int y) {
        super(x, y);
    }

    @Override
    public void draw(Graphics graphics) {
        // квадрат коричневого цвета
        Color brown = new Color(173, 129, 9);
        graphics.setColor(brown);
        int x0 = this.getX() - getWidth() / 2;
        int y0 = this.getY() - getHeight() / 2;
        graphics.drawRect(x0, y0, getWidth(), getHeight());
        graphics.fillRect(x0, y0, getWidth(), getHeight());
    }
}
