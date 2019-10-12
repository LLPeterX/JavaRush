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

}
