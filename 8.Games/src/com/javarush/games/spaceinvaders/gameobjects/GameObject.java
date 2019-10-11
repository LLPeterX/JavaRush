package com.javarush.games.spaceinvaders.gameobjects;

import com.javarush.engine.cell.*;

public class GameObject {
    public double x;
    public double y;
    public int[][] matrix;
    public int width, height; // размеры матрицы объекта


    public GameObject(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void setMatrix(int[][] matrix) {
        this.matrix = matrix;
        this.width = matrix[0].length;
        this.height = matrix.length;
    }

    public void draw(Game game) {
        // В методе draw(Game) для каждой ячейки матрицы matrix должен быть вызван метод
        // setCellValueEx(int, int, Color, String) у объекта типа Game.
        // В качестве параметров необходимо передать:
        // x объекта на игровом поле + x в матрице, y объекта на игровом поле + y в матрице, цвет и пустую строку.
        for(int i=0; i<height; i++) {
            for(int j=0; j<width; j++) {
                game.setCellValueEx((int)(this.x)+j,(int)(this.y)+i, Color.values()[matrix[i][j]],"");
            }
        }

    }
}
