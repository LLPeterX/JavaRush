package com.javarush.task.task37.task3701;

import java.util.ArrayList;
import java.util.Iterator;

/* 
Круговой итератор
*/
public class Solution<T> extends ArrayList<T> {
    public static void main(String[] args) {
        Solution<Integer> list = new Solution<>();
        list.add(1);
        list.add(2);
        list.add(3);

        int count = 0;
        for (Integer i : list) {
            //1 2 3 1 2 3 1 2 3 1
            System.out.print(i + " ");
            count++;
            if (count == 10) {
                break;
            }
        }
    }

    public class RoundIterator<T> implements Iterator<T>{
        private int currentIndex = 0;
        private Iterator<T> it = (Iterator<T>) Solution.super.iterator();

        @Override
        public boolean hasNext() {
            if(size()==0) return false;
// просто return true не работает (?)
            if (currentIndex >= size()) {
                it = (Iterator<T>) Solution.super.iterator();
                currentIndex = 0;
            }
            return true; // т.к. итератор цикличный, всегда true
        }

        @Override
        public T next() {
            // вернуть текущее значение из ArrayList и стать на следующее
// ниже не работает:
//            T value = (T) get(currentIndex);
//            currentIndex++;
//            if(currentIndex>=size())
//                currentIndex=0;
//            return value;
            currentIndex++;
            return it.next();
        }

        @Override
        public synchronized void remove() {
            it.remove();

        }
    } // RoundedIterator

    @Override
    public Iterator<T> iterator() {
        return new RoundIterator();
    }
}
