package com.javarush.task.task24.task2410;

import java.util.LinkedList;
import java.util.List;

/* 
Рефакторинг, анонимные классы

Сделайте так, чтобы метод getIterator возвращал анонимный класс.
Перенесите логику LocalIterator в анонимный класс.
Меняйте только тело метода getIterator.


Требования:
1. Метод getIterator должен возвращать объект типа Iterator.
2. Объект возвращаемый методом getIterator должен быть экземпляром анонимного класса.
3. Программа должна выводить данные на экран.
4. Логика поведения программы должна остаться прежней.
*/
public class Solution {
    public static List<Iterator> iterators = new LinkedList<>();

    private int countItems;

    public Iterator getIterator(final String name) {
//        class LocalIterator implements Iterator {
//            public LocalIterator() {
//                countItems++;
//                System.out.println(name + " item " + countItems);
//            }
//
//            public Iterator next() {
//                return new LocalIterator();
//            }
//        }
//        return new LocalIterator();
        return new Iterator() {
            @Override
            public Iterator next() {
                return getIterator(name);
            }

            String iteratorName;
//            @Override
//            public Iterator next() {
//                //return null;
//                countItems++;
//                System.out.println(name + " item " + countItems);
//                return this;
//            }
            {
                iteratorName = name;
                countItems++;
                System.out.println(name + " item " + countItems);
            }

        };
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        Iterator iterator = solution.getIterator("iterator");
        for (int i = 1; i < 5; i++) {
            iterators.add(iterator.next());
        }
    }
}
