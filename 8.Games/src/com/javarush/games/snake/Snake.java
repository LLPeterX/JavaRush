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
        this.direction = direction;
    }

    public void move() {

    }

}
