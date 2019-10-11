package com.javarush.games.snake;

import com.javarush.engine.cell.*;

public class SnakeGame extends Game {
    public static final int WIDTH = 15;
    public static final int HEIGHT = 15;
    private Snake snake;
    private int turnDelay; // 300 ms за ход
    private Apple apple;

    @Override
    public void initialize() {
        setScreenSize(WIDTH,HEIGHT);
        createGame();
    }

    private void createGame() {
        snake = new Snake(WIDTH/2,HEIGHT/2);
        apple = new Apple(5,5);
        drawScene();
        turnDelay = 300; // 300 ms (изначально)
        setTurnTimer(turnDelay);
    }

    // 5. В классе SnakeGame в методе drawScene() необходимо заменить вызов метода setCellColor(int, int, Color)
    // на вызов метода setCellValueEx(int, int, Color, String) с параметрами:
    // x, y, цвет (например, Color.DARKSEAGREEN) и пустая строка.
    private void drawScene() {
        for(int i=0; i<HEIGHT; i++)
            for(int j=0; j<WIDTH; j++)
                setCellValueEx(i,j,Color.DARKSEAGREEN,"");
        snake.draw(this);
        apple.draw(this);
    }

    @Override
    public void onTurn(int value) {
        snake.move(apple);
        drawScene();
    }

    @Override
    public void onKeyPress(Key key) {
        switch (key) {
            case LEFT:
                snake.setDirection(Direction.LEFT);
                break;
            case RIGHT:
                snake.setDirection(Direction.RIGHT);
                break;
            case UP:
                snake.setDirection(Direction.UP);
                break;
            case DOWN:
                snake.setDirection(Direction.DOWN);
                break;
        }
    }
}
