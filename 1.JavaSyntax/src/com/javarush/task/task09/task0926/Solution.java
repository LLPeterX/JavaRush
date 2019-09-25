package com.javarush.task.task09.task0926;

import java.util.ArrayList;

/* 
Список из массивов чисел
*/

public class Solution {
    public static void main(String[] args) {
        ArrayList<int[]> list = createList();
        printList(list);
    }

    public static ArrayList<int[]> createList() {
        //напишите тут ваш код
        //Добавить в список пять объектов-массивов длиной 5, 2, 4, 7, 0 соответственно.
        ArrayList<int[]> list = new ArrayList<int[]>();
        list.add(new int[5]);
        list.add(new int[2]);
        list.add(new int[4]);
        list.add(new int[7]);
        list.add(new int[0]);
        // fill list 1
        int[] data1 = new int[5];
        for(int i=0; i<5; i++)
            data1[i]=i;
        list.set(0,data1);
        // fill list 2
        int data2[] = new int[2];
        for(int i=0; i<2; i++)
            data2[i]=i;
        list.set(1,data2);
        // fill list 3
        int data3[] = new int[4];
        for(int i=0; i<4; i++)
            data3[i]=i;
        list.set(2,data3);
        // fill list 4
        int data4[] = new int[7];
        for(int i=0; i<7; i++)
            data4[i]=i;
        list.set(3,data4);

        return list;
    }

    public static void printList(ArrayList<int[]> list) {
        for (int[] array : list) {
            for (int x : array) {
                System.out.println(x);
            }
        }
    }
}
