package com.javarush.games.snake;

import com.javarush.engine.cell.*;

public class SnakeGame extends Game {
    public static final int WIDTH = 15;
    public static final int HEIGHT = 15;

    @Override
    public void initialize() {
        setScreenSize(WIDTH,HEIGHT);
        createGame();
    }

    private void createGame() {
        drawScene();

    }

    private void drawScene() {
        for(int i=0; i<HEIGHT; i++)
            for(int j=0; j<WIDTH; j++)
                setCellColor(i,j,Color.DARKSEAGREEN);
    }
}
