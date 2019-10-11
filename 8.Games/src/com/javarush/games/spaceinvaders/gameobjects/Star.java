package com.javarush.games.spaceinvaders.gameobjects;

import com.javarush.engine.cell.*;

public class Star extends GameObject {
    private static final String STAR_SIGN = "\u2605";

    public Star(double x, double y) {
        super(x, y);
    }

    // 3. В методе draw(Game) у объекта типа Game должен быть вызван метод setCellValueEx(int, int, Color, String, Color, int)
    // с параметрами: x, y, Color.NONE, STAR_SIGN, <цвет звезды>, 100.
    // (<цвет звезды> используй какой тебе нравиться, например, Color.WHITE).
    public void draw (Game game) {
       game.setCellValueEx((int)x, (int)y, Color.NONE, STAR_SIGN, Color.YELLOW,100);
    }
}
