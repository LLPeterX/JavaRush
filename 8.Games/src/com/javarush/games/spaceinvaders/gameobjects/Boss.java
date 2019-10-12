package com.javarush.games.spaceinvaders.gameobjects;

import com.javarush.games.spaceinvaders.Direction;
import com.javarush.games.spaceinvaders.ShapeMatrix;

public class Boss extends EnemyShip {
    private int frameCount = 0;

    public Boss(double x, double y) {
        super(x, y);
        setAnimatedView(true, ShapeMatrix.BOSS_ANIMATION_FIRST, ShapeMatrix.BOSS_ANIMATION_SECOND);
    }

    @Override
    public void nextFrame() {
        frameCount++;
        if(frameCount%10 == 0 || !isAlive)
            super.nextFrame();

    }

    @Override
    public Bullet fire() {
        if(!isAlive) return null;
        // 4. В методе fire() нужно вызвать конструктор класса Bullet с параметрами:
        // x + 6, y + height, Direction.DOWN, если matrix == ShapeMatrix.BOSS_ANIMATION_FIRST.
        // 5. В методе fire() нужно вызвать конструктор класса Bullet с параметрами:
        // x, y + height, Direction.DOWN, если matrix != ShapeMatrix.BOSS_ANIMATION_FIRST.
        Bullet bullet;
        if(matrix == ShapeMatrix.BOSS_ANIMATION_FIRST)
            bullet = new Bullet(x+6, y+height, Direction.DOWN);
        else
            bullet = new Bullet(x,y+height,Direction.DOWN);
        return bullet;
    }

    @Override
    public void kill() {
        if(!isAlive) return;
        isAlive = false;
        setAnimatedView(false,ShapeMatrix.KILL_BOSS_ANIMATION_FIRST,
                ShapeMatrix.KILL_BOSS_ANIMATION_SECOND,
                ShapeMatrix.KILL_BOSS_ANIMATION_THIRD);
    }
}
