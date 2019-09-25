package com.javarush.task.task26.task2601;

import java.util.*;

/*
Почитать в инете про медиану выборки

Реализуй логику метода sort, который должен сортировать данные в массиве по удаленности от его медианы.
Верни отсортированный массив от минимального расстояния до максимального.
Если удаленность одинаковая у нескольких чисел, то сортируй их в порядке возрастания.

Пример входящего массива:
13, 8, 15, 5, 17
медиана - 13

Отсортированный масив:
13, 15, 17, 8, 5


Требования:
1. Программа не должна выводить текст в консоль.
2. Программа не должна считывать данные с консоли.
3. Класс Solution должен содержать публичный статический метод Integer[] sort(Integer[] array).
4. Метод sort(Integer[] array) класса Solution должен сортировать данные в массиве по удаленности от его медианы.
*/
public class Solution {

    public static void main(String[] args) {
        Integer[] source = {13, 8, 15, 5, 17, 16};
        Arrays.sort(source);
        //System.out.println("Sorted: "+Arrays.toString(source));
        sort(source);
        //System.out.println(Arrays.toString(source));
    }

    public static Integer[] sort(Integer[] array) {
        //implement logic here
        Arrays.sort(array);
        int len = array.length;
        final double median;
        if (len % 2 != 0) median = array[len / 2];
        else median = (double) (array[len / 2 - 1] + array[len / 2]) / 2;

        Arrays.sort(array, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                double res = Math.abs(o1 - median) - Math.abs(o2 - median);
                if (res == 0) return o1 - o2;
                else return (int) res;
            }
        });

        return array;
    }
}
