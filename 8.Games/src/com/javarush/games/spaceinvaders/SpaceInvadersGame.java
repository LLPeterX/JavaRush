package com.javarush.games.spaceinvaders;

import com.javarush.engine.cell.Color;
import com.javarush.engine.cell.Game;
import com.javarush.games.spaceinvaders.gameobjects.EnemyFleet;
import com.javarush.games.spaceinvaders.gameobjects.Star;

import java.util.ArrayList;
import java.util.List;

public class SpaceInvadersGame extends Game  {
    public static final int WIDTH = 64;
    public static final int HEIGHT = 64;
    private List<Star> stars;
    private EnemyFleet enemyFleet;

    @Override
    public void initialize() {
        setScreenSize(WIDTH, HEIGHT);
        drawScene();
        createGame();
    }

    private void createGame() {
        createStars();
        enemyFleet = new EnemyFleet();
        drawScene();
        setTurnTimer(40);
    }

    private void drawScene() {
        drawField();
        enemyFleet.draw(this);
        //9. В методе drawScene() после вызова метода drawField()
        // у объекта enemyFleet должен быть вызван метод draw(Game). В качестве параметра передай в метод "this".
    }

    private void drawField() {
        // закрасим поле
        for(int i=0; i<HEIGHT; i++)
            for(int j=0; j<WIDTH; j++)
                setCellValueEx(i,j, Color.BLACK, "");
        for(Star star : stars)
            star.draw(this);
    }

    private void createStars() {
        stars = new ArrayList<>();
        // создать 8 звезд
        for(int i=0; i<8; i++) {
            int x = getRandomNumber(WIDTH);
            int y = getRandomNumber(HEIGHT);
            stars.add(new Star(x,y)); // ну, не будем проверять совпадение координат
        }
    }

    @Override
    public void onTurn(int delay) {
        drawScene();
    }

}
