package com.javarush.task.task34.task3410.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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
        Set<Wall> walls = new HashSet<>();
        Set<Home> homes = new HashSet<>();
        Set<Box> boxes = new HashSet<>();
        Player player = null;

        int loopLevel = (level>60) ? level % 60 : level;

        try (BufferedReader reader = new BufferedReader(new FileReader(levels.toFile())))
        {
            int readLevel = 0;
            int x;
            int y = Model.FIELD_CELL_SIZE / 2;
            boolean isLevelMap = false;
            String line;
            while ((line = reader.readLine()) != null)
            {
                if (line.contains("Maze:"))
                {
                    readLevel = Integer.valueOf(line.split(" ")[1]);
                    continue;
                }
                if (readLevel == loopLevel)
                {
                    if (line.length() == 0)
                    {
                        boolean isEnd = isLevelMap;

                        isLevelMap = !isLevelMap;

                        if (isEnd && !isLevelMap)
                        {
                            break;
                        } else
                        {
                            continue;
                        }
                    }
                    if (isLevelMap)
                    {
                        x = Model.FIELD_CELL_SIZE / 2;

                        char[] chars = line.toCharArray();
                        for (char c : chars)
                        {
                            switch (c)
                            {
                                case 'X':
                                    walls.add(new Wall(x, y));
                                    break;
                                case '*':
                                    boxes.add(new Box(x, y));
                                    break;
                                case '.':
                                    homes.add(new Home(x, y));
                                    break;
                                case '&':
                                    boxes.add(new Box(x, y));
                                    homes.add(new Home(x, y));
                                    break;
                                case '@':
                                    player = new Player(x, y);
                            }
                            x += Model.FIELD_CELL_SIZE;
                        }
                        y += Model.FIELD_CELL_SIZE;
                    }
                }
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return new GameObjects(walls, boxes, homes, player);
    }
/*
    // получить целочисленное значение из строки типа "Maze: 23"
    private int getIntParam(String s) {
        String[] tmp = s.split(" ");
        return  Integer.parseInt(tmp[1]);
    }
*/
}
