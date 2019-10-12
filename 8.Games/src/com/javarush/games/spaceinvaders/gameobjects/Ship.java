package com.javarush.games.spaceinvaders.gameobjects;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Ship extends GameObject {
    public boolean isAlive = true;
    private List<int[][]> frames;
    private int frameIndex;

    public Ship(double x, double y) {
        super(x, y);
    }

    public void setStaticView(int[][] viewFrame) {
        // 5. В методе setStaticView(int[][] viewFrame) необходимо вызвать метод setMatrix(int[][]) базового класса.
        // В качестве параметра передай в метод viewFrame.
        super.setMatrix(viewFrame);
        frames = new ArrayList<int[][]>();
        frames.add(viewFrame);
        frameIndex = 0;
    }

    public Bullet fire() {
        return null; // заглушка - переопределим этот метод в других классах-наследниках
    }

    public void kill() {
        isAlive = false;
    }

    public void setAnimatedView(int[][]... viewFrames) {
        setMatrix(viewFrames[0]);
        frames =  Arrays.asList(viewFrames);
        frameIndex = 0;

    }
}
