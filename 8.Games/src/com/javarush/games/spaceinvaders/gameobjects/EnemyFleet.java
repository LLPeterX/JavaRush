package com.javarush.games.spaceinvaders.gameobjects;

import com.javarush.engine.cell.Game;
import com.javarush.games.spaceinvaders.Direction;
import com.javarush.games.spaceinvaders.ShapeMatrix;

import java.util.ArrayList;
import java.util.List;

// В этой части мы подготовим основу для вражеского флота.
// Для этого создадим класс EnemyFleet.
// Он будет хранить список кораблей, количество рядов кораблей(ROWS_COUNT),
// количество кораблей в ряду(COLUMNS_COUNT) и расстояние между левыми верхними углами соседних кораблей(STEP).
// Создавать корабли и заполнять ими список(ships) мы будем в методе createShips, но к его реализации приступим позже.
public class EnemyFleet {
    private static final int ROWS_COUNT = 3; // числов рядов
    private static final int COLUMNS_COUNT = 10; // число вражин в ряду
    private static final int STEP = ShapeMatrix.ENEMY.length+1; // расстояние в ряду между вражинами
    private List<EnemyShip> ships;
    private Direction direction = Direction.RIGHT;

    // конструктор без параметров
    public EnemyFleet() {
        createShips();
    }

    private void createShips() {
        this.ships = new ArrayList<EnemyShip>();
        for(int y=0; y<ROWS_COUNT; y++) {
            for(int x=0; x<COLUMNS_COUNT; x++) {
                ships.add(new EnemyShip(x*STEP, y*STEP+12));
            }
        }
    }

    public void draw(Game game) {
        for(EnemyShip ship : ships) {
            ship.draw(game);
        }
    }

    private double getLeftBorder() {
        //  Метод getLeftBorder() должен возвращать минимальную координату x среди всех вражеских кораблей из списка ships.
        double minX=ships.get(0).x;
        for(int i=1; i<ships.size(); i++) {
            minX = Math.min(minX,ships.get(i).x);
        }
        return minX;
    }

    private double getRightBorder() {
        // 9. Метод getRightBorder() должен возвращать максимальное из значений (x + width) среди всех вражеских кораблей из списка ships.
        double maxX=ships.get(0).x+ships.get(0).width;
        for(int i=1; i<ships.size(); i++) {
            maxX = Math.max(maxX,ships.get(i).x+ships.get(i).width);
        }
        return maxX;
    }

    private double getSpeed() {
        return Math.min(2.0, 3.0/(double)ships.size());
    }

    public void move() {

    }

}
