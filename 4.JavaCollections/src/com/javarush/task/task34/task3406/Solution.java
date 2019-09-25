package com.javarush.task.task34.task3406;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/* 
Слабые ссылки
*/
public class Solution {
    public static Helper helper = new Helper();

    public static class Monkey {
        private String name;

        public Monkey(String name) {
            this.name = name;
        }

        // по сути, override метод Object finalize()
        protected void finalize() {
            Helper.isFinalized = true;
            System.out.format("Bye-Bye, %s!\n", name);
        }
    }

    public static void main(String args[]) throws InterruptedException {
        helper.startTime();

        Monkey monkey = new Monkey("Simka");

        //Add reference here
        WeakReference<Monkey> reference = new WeakReference<Monkey>(monkey);

        helper.callGC(); // вызываем сборщик мусора. Но monkey не должен удалиться.
        //System.out.println("Monkey is null: "+monkey==null ? "null" : "not null"); // not null
        monkey = null;

        helper.callGC();
        helper.consumeHeap(); // вызываем переполнение памяти. Памяти не хватает, так что GC должен удалить monkey

        if (reference.get() == null)
            System.out.println("Finalized");

        helper.finish();
    }

    public static class Helper {
        public static boolean isFinalized;

        private long startTime;

        void startTime() {
            this.startTime = System.currentTimeMillis();
        }

        int getTime() {
            return (int) (System.currentTimeMillis() - startTime) / 1000;
        }

        void callGC() throws InterruptedException {
            System.gc();
            Thread.sleep(1000);
        }

        void consumeHeap() {
            try {
                List<Solution> heap = new ArrayList<Solution>(100000);
                while (!isFinalized) {
                    heap.add(new Solution());
                }
            } catch (OutOfMemoryError e) {
                System.out.println("An out-of-memory exception has occurred");
            }
        }

        public void finish() {
            System.out.println("Done");
            System.out.println("It took " + getTime() + " s");
        }
    }
}
