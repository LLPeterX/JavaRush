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
        walls.add(new Wall(10,10));
        walls.add(new Wall(10+Model.FIELD_CELL_SIZE,10));
        walls.add(new Wall(10+Model.FIELD_CELL_SIZE*2,10));
        walls.add(new Wall(10+Model.FIELD_CELL_SIZE*3,10));
        walls.add(new Wall(10,10+Model.FIELD_CELL_SIZE));
        walls.add(new Wall(10,10+Model.FIELD_CELL_SIZE*2));
        walls.add(new Wall(10,10+Model.FIELD_CELL_SIZE*3));
        homes.add(new Home(40,60));
        boxes.add(new Box(80,100));
        Player player = new Player(120,80);

        // (Set<Wall> walls, Set<Box> boxes, Set<Home> homes, Player player)
        return new GameObjects(walls, boxes, homes, player);


    }
}
