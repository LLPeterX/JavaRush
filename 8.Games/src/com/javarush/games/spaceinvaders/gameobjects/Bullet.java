package com.javarush.games.spaceinvaders.gameobjects;

import com.javarush.games.spaceinvaders.Direction;
import com.javarush.games.spaceinvaders.ShapeMatrix;

// пуля, вылетающая из нашей пушки
public class Bullet extends GameObject {
    private int dy; // измнение координаты y
    public boolean isAlive = true;

    public Bullet(double x, double y, Direction direction) {
        super(x, y);
        setMatrix(ShapeMatrix.BULLET);
        if(direction == Direction.UP)
            this.dy = -1;
        else
            this.dy = 1;
    }

    public void move() {
        this.y += dy; // т.к. dy=-1, то вверх
    }
}
