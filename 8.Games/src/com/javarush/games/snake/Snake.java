package com.javarush.games.snake;

import com.javarush.engine.cell.*;

import java.util.ArrayList;
import java.util.List;

public class Snake {
    private List<GameObject> snakeParts = new ArrayList<>();
    private static final String HEAD_SIGN = "\uD83D\uDC7E";
    private static final String BODY_SIGN = "\u26AB";
    public boolean isAlive = true;
    private Direction direction = Direction.LEFT;

    // конструктор создает сразу 3 сегмента змеи
    public Snake(int x, int y) {
        snakeParts.add(new GameObject(x,y));
        snakeParts.add(new GameObject(x+1,y));
        snakeParts.add(new GameObject(x+2,y));
    }

    public void draw(Game game) {
        for(int i=0; i<snakeParts.size();i++) {
            GameObject part = snakeParts.get(i);
            if(i==0) {
                //game.setCellValue(part.x, part.y, HEAD_SIGN);
                game.setCellValueEx(part.x, part.y, Color.NONE, HEAD_SIGN, (isAlive)?Color.BLACK:Color.RED,75);
            } else {
                //game.setCellValue(part.x, part.y, BODY_SIGN);
                game.setCellValueEx(part.x, part.y, Color.NONE, BODY_SIGN, (isAlive)?Color.BLACK:Color.RED,75);
            }
        }
    }

    public void setDirection(Direction direction) {
        // проверка, что нельзя развернуть на 180 градусов
        if(this.direction == Direction.LEFT && direction==Direction.RIGHT) return;
        if(this.direction == Direction.RIGHT && direction==Direction.LEFT) return;
        if(this.direction == Direction.UP && direction==Direction.DOWN) return;
        if(this.direction == Direction.DOWN && direction==Direction.UP) return;
        // условия из Snake(18)
        GameObject obj0 = snakeParts.get(0);
        GameObject obj1 = snakeParts.get(1);
        // 1. Метод setDirection(Direction) класса Snake не должен ничего делать,
        // если значение поля direction равно LEFT, и сегменты змейки из snakeParts с индексами 0 и 1
        // имеют одинаковое значение координаты x.
        if(this.direction == Direction.LEFT && (obj0.x==obj1.x)) return;
        // 2. Метод setDirection(Direction) класса Snake не должен ничего делать,
        // если значение поля direction равно RIGHT, и сегменты змейки из snakeParts с индексами 0 и 1
        // имеют одинаковое значение координаты x.
        if(this.direction == Direction.RIGHT && (obj0.x==obj1.x)) return;
        // 3. Метод setDirection(Direction) класса Snake не должен ничего делать, если значение поля direction равно UP,
        // и сегменты змейки из snakeParts с индексами 0 и 1 имеют одинаковое значение координаты y.
        if(this.direction == Direction.UP && (obj0.y == obj1.y)) return;
        // ну и для DOWN
        if(this.direction == Direction.DOWN && (obj0.y == obj1.y)) return;
        this.direction = direction;
    }

    public Direction getDirection() {
        return direction;
    }

    // Перепиши метод move() класса Snake.
    // Теперь в качестве аргумента он должен принимать яблоко, и если окажется, что змейка "съела" яблоко,
    // состояние яблока должно устанавливаться в "неживое", а размер змейки — увеличиваться на 1 элемент.
    public void move(Apple apple) {
        GameObject head = createNewHead();
        if(head.x<0 || head.y<0 || head.x>=SnakeGame.WIDTH || head.y>=SnakeGame.HEIGHT) {
            isAlive = false;
            return;
        }
        // проверяем, что столкнулись сами с собой
        if(checkCollision(head)) {
            isAlive = false;
            return;
        }
        snakeParts.add(0,head);
        if(apple.x==head.x && apple.y==head.y) {
            apple.isAlive = false;
            return; // не удалять хвост змеи - т.о. размер змеи увеличится на 1
        }
        removeTail();
    }

    public GameObject createNewHead() {
        GameObject currentHead = snakeParts.get(0);
        int newX = currentHead.x;
        int newY = currentHead.y;
        switch (direction) {
            case LEFT:
                newX--;
                break;
            case RIGHT:
                newX++;
                break;
            case UP:
                newY--;
                break;
            case DOWN:
                newY++;
                break;
        }
        return new GameObject(newX,newY);
    }

    public void removeTail() {
        GameObject tail = snakeParts.get(snakeParts.size()-1);
        snakeParts.remove(tail);
    }

    // проверка, что змея не проходит через свое тело
    public boolean checkCollision(GameObject o) {
        for(GameObject snakePart : snakeParts) {
            if(snakePart.x == o.x && snakePart.y == o.y)
                return true;
        }
        return false;
    }

    public int getLength() {
        return snakeParts.size();
    }

}
