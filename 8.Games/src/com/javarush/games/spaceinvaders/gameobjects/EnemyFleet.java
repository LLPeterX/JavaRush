package com.javarush.games.spaceinvaders.gameobjects;

import com.javarush.engine.cell.Game;
import com.javarush.games.spaceinvaders.Direction;
import com.javarush.games.spaceinvaders.ShapeMatrix;
import com.javarush.games.spaceinvaders.SpaceInvadersGame;

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
        // добавляем босса
        Boss boss = new Boss((double)(STEP * COLUMNS_COUNT / 2 - ShapeMatrix.BOSS_ANIMATION_FIRST.length / 2 - 1), 5);
        ships.add(boss);
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
        // 1. Метод move() класса EnemyFleet ничего не делает, если в списке ships нет кораблей.
        if(ships.size()==0) return;
        // 2. Если значение поля direction равно Direction.LEFT и результат вызова метода getLeftBorder() отрицательный,
        // полю direction необходимо установить значение Direction.RIGHT.
        Direction oldDirection = direction;
        if(direction == Direction.LEFT && getLeftBorder() < 0)
            direction = Direction.RIGHT;
        else if(direction == Direction.RIGHT && getRightBorder() > SpaceInvadersGame.WIDTH)
            direction = Direction.LEFT;
        double speed = getSpeed();
        // 5. Если значение поля direction было изменено, у каждого объекта из списка ships
        //   необходимо вызвать метод move(Direction, double) с параметрами: Direction.DOWN, результат вызова метода getSpeed().
        //6. Если значение поля direction не было изменено, у каждого объекта из списка ships необходимо
        //   вызвать метод move(Direction, double) с параметрами: direction, результат вызова метода getSpeed().
        Direction shipDirection = (direction!=oldDirection) ? Direction.DOWN : direction;
        for(EnemyShip ship : ships)
            ship.move(shipDirection, speed);
    } // move()

    public Bullet fire(Game game) {
        if(ships.size()==0) return null;
        int random = game.getRandomNumber(100/SpaceInvadersGame.COMPLEXITY);
        // 4. В методе fire(Game) класса EnemyFleet необходимо вернуть null,
        // если результат вызова метода getRandomNumber(int) больше нуля. (???)
        if(random>0)  return null;
        // иначе пальнуть из случайного корабля
        int shipNumber = game.getRandomNumber(ships.size());
        return ships.get(shipNumber).fire();
    }

    // проверим попадания наших пуль по кораблям противника
    public int verifyHit(List<Bullet> bullets) {
        int totalScore=0;
        if(bullets.size()==0) return 0;
        for(EnemyShip ship : ships) {
            for(Bullet bullet : bullets) {
                if(ship.isCollision(bullet) && bullet.isAlive && ship.isAlive) {
                    totalScore+=ship.score;
                    bullet.kill();
                    ship.kill();
                }
            }
        }
        return totalScore;
    }

    public void deleteHiddenShips() {
        ships.removeIf(s -> !s.isVisible());
    }

    public double getBottomBorder() {
        // Метод getBottomBorder() должен возвращать максимальное из значений (y + height)
        // среди всех вражеских кораблей из списка ships.
        double bottom = 0;
        for(EnemyShip ship : ships) {
            bottom = Math.max(bottom,ship.y+ship.height);
        }
        return bottom;
    }

    public int getShipsCount() {
        return ships.size();
    }


}
