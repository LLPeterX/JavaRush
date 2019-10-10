package com.javarush.task.task34.task3410.model;

import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;

// Загрузчик уровня из текстового файла
public class LevelLoader {
    private Path levels; // путь

    public LevelLoader(Path levels) {
        this.levels = levels;
    }

    public GameObjects getLevel(int level) {
        // вернем по одному объекту
        Set<Wall> walls = new HashSet<>();
        Set<Home> homes = new HashSet<>();
        Set<Box> boxes = new HashSet<>();
        for(int i=20; i<=400; i+=20) {
            walls.add(new Wall(i,20));
            walls.add(new Wall(i,400));
        }
        for(int i=20; i<=400-20; i++) {
            walls.add(new Wall(20,i));
            walls.add(new Wall(400,i));
        }
        walls.add(new Wall(200, 200));
        walls.add(new Wall(220, 200));
        walls.add(new Wall(240, 200));
        boxes.add(new Box(240, 240));
        boxes.add(new Box(280,240));
        homes.add(new Home(40, 40));
        homes.add(new Home(80, 40));
        Player player = new Player(320, 240);
        return new GameObjects(walls, boxes, homes, player);


    }
}
