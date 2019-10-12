package com.javarush.games.spaceinvaders.gameobjects;

import com.javarush.games.spaceinvaders.Direction;
import com.javarush.games.spaceinvaders.ShapeMatrix;

public class EnemyShip extends Ship {


    public EnemyShip(double x, double y) {
        super(x, y);
        // 9. В конструкторе класса EnemyShip необходимо вызвать метод setStaticView(int[][]).
        // В качестве параметра передай ShapeMatrix.ENEMY.
        setStaticView(ShapeMatrix.ENEMY);
    }

    public void move(Direction direction, double speed) {
        // 3. В методе move(Direction direction, double speed) необходимо увеличить значение поля x на speed,
        //    если direction равно Direction.RIGHT.
        // 4. В методе move(Direction direction, double speed) необходимо уменьшить значение поля x на speed,
        //    если direction равно Direction.LEFT.
        // 5. В методе move(Direction direction, double speed) необходимо увеличить значение поля y на 2,
        //    если direction равно Direction.DOWN.
        switch (direction) {
            case RIGHT:
                this.x+=speed;
                break;
            case LEFT:
                this.x-=speed;
                break;
            case DOWN:
                this.y+=2;
                break;
        }
    }

    @Override
    public Bullet fire() {
        // 5. Метод fire() класса EnemyShip должен возвращать новый объект типа Bullet.
        // В качестве параметров конструктора передай x + 1, y + height, Direction.DOWN.
        return new Bullet(x+1, y+height,Direction.DOWN);
    }

    @Override
    public void kill() {
        if(!isAlive) return;
        isAlive = false;
        setAnimatedView(ShapeMatrix.KILL_ENEMY_ANIMATION_FIRST,
                ShapeMatrix.KILL_ENEMY_ANIMATION_SECOND,
                ShapeMatrix.KILL_ENEMY_ANIMATION_THIRD);
    }

}
