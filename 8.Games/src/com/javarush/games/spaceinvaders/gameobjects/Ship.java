package com.javarush.games.spaceinvaders.gameobjects;

public class Ship extends GameObject {

    public Ship(double x, double y) {
        super(x, y);
    }

    public void setStaticView(int[][] viewFrame) {
        // 5. В методе setStaticView(int[][] viewFrame) необходимо вызвать метод setMatrix(int[][]) базового класса.
        // В качестве параметра передай в метод viewFrame.
        super.setMatrix(viewFrame);
    }
}
