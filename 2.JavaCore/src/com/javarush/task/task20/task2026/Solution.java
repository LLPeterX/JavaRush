package com.javarush.task.task20.task2026;

/* 
Алгоритмы-прямоугольники

Алгоритмы-прямоугольники
1. Дан двумерный массив N*N, который содержит несколько прямоугольников.
2. Различные прямоугольники не соприкасаются и не накладываются.
3. Внутри прямоугольник весь заполнен 1.
4. В массиве:
4.1) a[i, j] = 1, если элемент (i, j) принадлежит какому-либо прямоугольнику
4.2) a[i, j] = 0, в противном случае
5. getRectangleCount должен возвращать количество прямоугольников.
6. Метод main не участвует в тестировании


Требования:
1. В классе Solution должен существовать метод getRectangleCount с одним параметром типа byte[][].
2. Метод getRectangleCount должен быть публичным.
3. Метод getRectangleCount должен быть статическим.
4. Метод getRectangleCount должен возвращать количество прямоугольников (в соответствии с заданием)
   найденное в полученном массиве.
*/
public class Solution {
    public static void main(String[] args) {
        byte[][] a1 = new byte[][]{
                {1, 1, 0, 0},
                {1, 1, 0, 0},
                {1, 1, 0, 0},
                {1, 1, 0, 1}
        };
        byte[][] a2 = new byte[][]{
                {1, 0, 0, 1},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {1, 0, 0, 1}
        };

//        byte[][] a3 = new byte[][]{
//                {1, 0, 0, 1},
//                {0, 1, 1, 0},
//                {0, 1, 1, 0},
//                {1, 0, 0, 1}
//        };


        int count1 = getRectangleCount(a1);
        System.out.println("count = " + count1 + ". Должно быть 2");

        int count2 = getRectangleCount(a2);
        System.out.println("count = " + count2 + ". Должно быть 4");

//        int count3 = getRectangleCount(a3);
//        System.out.println("count = " + count3 + ". Должно быть 5");
    }

    public static int getRectangleCount(byte[][] a) {
        int N = a.length;
        int rectangles=0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (a[ i ][ j ] == 1) {
                        // закрашиваем все смежные клетки, которые = 1
                        rectangles++;
                        //clearRectangle(a,i,j);
                        clearRectangle(i,j,a);
                        //break;
                    }
                }
            }
        return rectangles;
    }
/*
    public static void clearRectangle(byte[][]a, int i0, int j0) {
        int imax=0, jmax=0;
        for(int i=i0; i<a.length;i++,imax++) {
            if (a[ i ][ j0 ] == 0) {
                imax = i;
                break;
            }
        }
        for(int i=j0; i<a.length; i++) {
            if(a[i0][i]==0) {
                jmax = i;
                break;
            }
        }
        imax = Math.min(imax,a.length);
        jmax = Math.min(jmax,a.length);
        for(int i=i0; i<imax; i++)
            for(int j=j0; j<jmax; j++)
                a[i][j]=0;

    }
*/
static void clearRectangle(int I, int J, byte[][] arr) {
    int n = arr.length;

    // find right bottom corner
    int jRight = 0;
    for (int k = J; k < n; k++) {
        if (arr[I][k] == 0) {
            jRight = k;
            break;
        }
        // if it is the last element
        if (k == n - 1) {
            jRight = k;
        }
    }

    // find right bottom corner
    int iBottom = 0;
    for (int k = I; k < n; k++) {
        if (arr[k][J] == 0) {
            iBottom = k;
            break;
        }
        // if it is last element
        if (k == n - 1) {
            iBottom = k;
        }
    }

    for (int ii = I; ii <= iBottom; ii++) {
        for (int jj = J; jj <= jRight; jj++) {
            arr[ii][jj] = 0;
        }
    }
}
}
