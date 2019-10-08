package com.javarush.task.task34.task3410.model;

import java.awt.*;

public class Player extends CollisionObject implements Movable {
    public Player(int x, int y) {
        super(x, y);
    }

    @Override
    public void draw(Graphics graphics) {
        // рисуем игрока кругом красного цвета
        // круг вписан в квадрат (x0,y0) - (x0+w,y0+h)
        // центр круга (квадрата) - как раз по x/y
        //    Проследи, чтобы центр фигуры имел координаты x и y, а высота и ширина соответствовали
        //    значениям полей height и width.
        graphics.setColor(Color.RED);
        int x0 = this.getX() - getWidth() / 2;
        int y0 = this.getY() - getHeight() / 2;
        graphics.drawOval(x0, y0, getWidth(), getHeight());
        graphics.fillOval(x0, y0, getWidth(), getHeight());
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
