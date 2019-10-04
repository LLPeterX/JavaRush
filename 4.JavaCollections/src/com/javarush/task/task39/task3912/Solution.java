package com.javarush.task.task39.task3912;

/* 
Максимальная площадь
*/

public class Solution {
    public static void main(String[] args) {
        int[][] square = { // 4x4=16
                {1,0,1,1,1,1,1,0},
                {0,0,1,1,1,1,1,0},
                {0,0,0,1,1,1,1,0},
                {1,1,1,1,1,1,1,0},
                {0,0,1,1,1,1,1,0}
        };
        System.out.println(maxSquare(square));
    }

    // matrix содержит только 0 и 1
    // вернуть площадь макс.квадрата
    public static int maxSquare(int[][] matrix) {
        int squareSide = 0; // размер ребра квадрата
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (i * j == 0)
                    continue;
                if (matrix[i][j] == 1)
                    matrix[i][j] = Math.min(matrix[i][j - 1], Math.min(matrix[i - 1][j], matrix[i - 1][j - 1])) + 1;

                if (matrix[i][j] > squareSide)
                    squareSide = matrix[i][j];
            }
        }
        return squareSide * squareSide;
    }

}
