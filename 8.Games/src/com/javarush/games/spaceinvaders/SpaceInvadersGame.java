package com.javarush.games.spaceinvaders;

import com.javarush.engine.cell.Color;
import com.javarush.engine.cell.Game;
import com.javarush.games.spaceinvaders.gameobjects.Bullet;
import com.javarush.games.spaceinvaders.gameobjects.EnemyFleet;
import com.javarush.games.spaceinvaders.gameobjects.Star;

import java.util.ArrayList;
import java.util.List;

public class SpaceInvadersGame extends Game  {
    public static final int WIDTH = 64;
    public static final int HEIGHT = 64;
    public static final int COMPLEXITY = 5; // сложность игры
    private List<Star> stars;
    private EnemyFleet enemyFleet;
    private List<Bullet> enemyBullets;

    @Override
    public void initialize() {
        setScreenSize(WIDTH, HEIGHT);
        drawScene();
        createGame();
    }

    private void createGame() {
        createStars();
        enemyFleet = new EnemyFleet();
        enemyBullets = new ArrayList<>();
        drawScene();
        setTurnTimer(40);
    }

    private void drawScene() {
        drawField();
        enemyFleet.draw(this);
        for(Bullet b: enemyBullets) {
            b.draw(this);
        }
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
        moveSpaceObjects();
        check();
        Bullet bullet = enemyFleet.fire(this);
        if(bullet!=null) {
            enemyBullets.add(bullet);
        }
        drawScene();
    }

    private void moveSpaceObjects() {
        enemyFleet.move();
        for(Bullet b: enemyBullets) {
            b.move();
        }
    }

    private void removeDeadBullets() {
        enemyBullets.removeIf(b -> !b.isAlive || b.y>=HEIGHT-1);
    }

    private void check() {
        removeDeadBullets();
    }
}
