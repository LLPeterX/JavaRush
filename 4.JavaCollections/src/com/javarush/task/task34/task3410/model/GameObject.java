package com.javarush.task.task34.task3410.model;

import java.awt.*;

// это абстрактный класс для всех объектов игры (Home, Box и т.д.)
public abstract class GameObject {
    private int x,y; // позиция объекта для отрисовки
    private int width, height; //  размер объекта для отрисовки

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
    // конструкторы

    public GameObject(int x, int y) {
        this.x = x;
        this.y = y;
        this.width = Model.FIELD_CELL_SIZE;
        this.height = Model.FIELD_CELL_SIZE;
    }

    public GameObject(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    // draw() будем реализовывать для каждого игрового объекта (Home, Box и т.п.)
    public abstract void draw(Graphics graphics);
}
