package com.javarush.games.spaceinvaders.gameobjects;

import com.javarush.games.spaceinvaders.ShapeMatrix;

public class EnemyShip extends Ship {


    public EnemyShip(double x, double y) {
        super(x, y);
        // 9. В конструкторе класса EnemyShip необходимо вызвать метод setStaticView(int[][]).
        // В качестве параметра передай ShapeMatrix.ENEMY.
        setStaticView(ShapeMatrix.ENEMY);
    }
}
