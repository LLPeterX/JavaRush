package com.javarush.task.task34.task3410.model;

import java.awt.*;

public class Box extends CollisionObject implements Movable {

    public Box(int x, int y) {
        super(x, y);
    }

    @Override
    public void draw(Graphics graphics) {
        // коробку рисуем квадратом желтого цвета с диагоналями
        graphics.setColor(Color.PINK);
        int x0 = this.getX() - getWidth() / 2;
        int y0 = this.getY() - getHeight() / 2;

        graphics.drawRect(x0, y0, getWidth(), getHeight());
        graphics.drawRect(x0+1, y0+1, getWidth()-2, getHeight()-2);
        graphics.drawLine(x0,y0,x0+getWidth(), y0+getHeight());
        graphics.drawLine(x0,y0+getHeight(),x0+getWidth(), y0);
        //graphics.fillRect(x0, y0, getWidth(), getHeight());
    }

    @Override
    public void move(int x, int y) {
// перемещение игрока - смещение тек.координат
        int newX = this.getX()+x;
        int newY = this.getY()+y;
        this.setX(newX);
        this.setY(newY);
    }
}
