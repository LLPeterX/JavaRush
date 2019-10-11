package com.javarush.games.spaceinvaders.gameobjects;

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

    private void createShips() {
        this.ships = new ArrayList<EnemyShip>();
    }

}
