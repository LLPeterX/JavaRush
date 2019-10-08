package com.javarush.task.task34.task3410.model;

import java.awt.*;

// клетки, куда надо поместить ящики. Он не Movable или CollisionObject
public class Home extends GameObject {
    public Home(int x, int y) {
        super(x, y, 2, 2);
    }

    @Override
    public void draw(Graphics graphics) {
        // красный круг без заливки
        graphics.setColor(Color.RED);
        int x0 = this.getX() - getWidth() / 2;
        int y0 = this.getY() - getHeight() / 2;
        graphics.drawOval(x0, y0, getWidth(), getHeight());
    }
}
