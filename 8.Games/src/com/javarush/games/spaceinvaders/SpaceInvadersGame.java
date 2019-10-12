package com.javarush.games.spaceinvaders;

import com.javarush.engine.cell.*;
import com.javarush.games.spaceinvaders.gameobjects.Bullet;
import com.javarush.games.spaceinvaders.gameobjects.EnemyFleet;
import com.javarush.games.spaceinvaders.gameobjects.PlayerShip;
import com.javarush.games.spaceinvaders.gameobjects.Star;

import java.util.ArrayList;
import java.util.List;

public class SpaceInvadersGame extends Game  {
    public static final int WIDTH = 64;
    public static final int HEIGHT = 64;
    public static final int COMPLEXITY = 5; // сложность игры
    private static final int PLAYER_BULLETS_MAX = 1; // макс.кол-во пуль у нашей пушки
    private List<Star> stars;
    private EnemyFleet enemyFleet;
    private List<Bullet> enemyBullets;
    private List<Bullet> playerBullets;
    private PlayerShip playerShip;
    private boolean isGameStopped;
    private int animationsCount;
    private int score;

    @Override
    public void initialize() {
        setScreenSize(WIDTH, HEIGHT);
        createGame();
        //drawScene();
        //createGame();
    }

    private void createGame() {
        createStars();
        enemyFleet = new EnemyFleet();
        enemyBullets = new ArrayList<>();
        playerShip = new PlayerShip();
        isGameStopped = false;
        animationsCount = 0;
        playerBullets = new ArrayList<>();
        score = 0;
        drawScene();
        setTurnTimer(40);
    }

    private void drawScene() {
        drawField();
        enemyFleet.draw(this);
        playerShip.draw(this);
        for(Bullet b: enemyBullets) {
            b.draw(this);
        }
        for(Bullet b: playerBullets) {
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
        setScore(score);
        drawScene();
    }

    private void moveSpaceObjects() {
        enemyFleet.move();
        for (Bullet b : enemyBullets) {
            b.move();
        }
        playerShip.move();
        for (Bullet b : playerBullets) {
            b.move();
        }
    }

    private void removeDeadBullets() {
        enemyBullets.removeIf(b -> !b.isAlive || b.y>=HEIGHT-1);
        playerBullets.removeIf(b -> !b.isAlive || b.y+b.height<0);
    }

    private void check() {
        playerShip.verifyHit(enemyBullets);
        int scoreByShip = enemyFleet.verifyHit(playerBullets);
        score += scoreByShip;
        enemyFleet.deleteHiddenShips();
        removeDeadBullets();
        if(!playerShip.isAlive)
            stopGameWithDelay();
        // если враги дошли до пушки
        if(enemyFleet.getBottomBorder() >= playerShip.y)
            playerShip.kill();
        // если врагов не осталось
        if(enemyFleet.getShipsCount() == 0) {
            playerShip.win();
            stopGameWithDelay();
        }

    }

    private void stopGame(boolean isWin) {
        isGameStopped = true;
        stopTurnTimer();
        // 8. В методе stopGame(boolean isWin), если параметр isWin истина,
        // необходимо вызвать метод showMessageDialog(Color, String, Color, int). Цвет текста передай Color.GREEN.
        //9. В методе stopGame(boolean isWin), если параметр isWin ложь,
        // необходимо вызвать метод showMessageDialog(Color, String, Color, int). Цвет текста передай Color.RED.
        if(isWin) {
            showMessageDialog(Color.BLACK, "YOU WIN!",Color.GREEN,75);
        } else {
            showMessageDialog(Color.BLACK, "YOU LOSE!",Color.RED,75);
        }
    }

    private void stopGameWithDelay() {
        animationsCount++;
        if(animationsCount>=10)
            stopGame(playerShip.isAlive);
    }

    @Override
    public void onKeyPress(Key key) {
        if(key == Key.SPACE && isGameStopped) {
            createGame();
            return;
        }
        switch (key) {
            case LEFT:
                playerShip.setDirection(Direction.LEFT);
                break;
            case RIGHT:
                playerShip.setDirection(Direction.RIGHT);
                break;
            case SPACE:
                Bullet bullet = playerShip.fire();
                if(bullet!=null && playerBullets.size() < PLAYER_BULLETS_MAX)
                    playerBullets.add(bullet);
                break;
        }
    }

    @Override
    public void onKeyReleased(Key key) {
        if((key == Key.LEFT && playerShip.getDirection() == Direction.LEFT) ||
           (key == Key.RIGHT && playerShip.getDirection() == Direction.RIGHT))
                playerShip.setDirection(Direction.UP);
    }

    @Override
    public void setCellValueEx(int x, int y, Color color, String text) {
        if(x<0 || x>=WIDTH) return;
        if(y<0 || y>=HEIGHT) return;
        super.setCellValueEx(x,y,color,text);
    }
}
