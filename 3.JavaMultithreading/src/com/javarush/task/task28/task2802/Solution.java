package com.javarush.task.task28.task2802;


import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/* 
Пишем свою ThreadFactory
*/
public class Solution {
    //private static AtomicInteger fabricNumber = new AtomicInteger(0);

    public static void main(String[] args) {
        class EmulatorThreadFactoryTask implements Runnable {
            @Override
            public void run() {
                emulateThreadFactory();
            }
        }

        ThreadGroup group = new ThreadGroup("firstGroup");
        Thread thread = new Thread(group, new EmulatorThreadFactoryTask());

        ThreadGroup group2 = new ThreadGroup("secondGroup");
        Thread thread2 = new Thread(group2, new EmulatorThreadFactoryTask());

        thread.start();
        thread2.start();
    }

    private static void emulateThreadFactory() {
        AmigoThreadFactory factory = new AmigoThreadFactory();
        Runnable r = new Runnable() {
            @Override
            public void run() { // не срабатывает!!!
                System.out.println(Thread.currentThread().getName());
            }
        };
        //System.out.println(Thread.currentThread().getThreadGroup().getName());
        factory.newThread(r).start();
        factory.newThread(r).start();
        factory.newThread(r).start();
        //fabricNumber.incrementAndGet();
    }

    // Фабрика создает потоки
    public static class AmigoThreadFactory implements ThreadFactory{
        AtomicInteger integer=new AtomicInteger(0); // protected
        AtomicInteger factoryNum=new AtomicInteger(0);
        static AtomicInteger factoryCount=new AtomicInteger(0);
        public AmigoThreadFactory() {
            factoryNum.set(factoryCount.incrementAndGet());
        }
        @Override
        public Thread newThread(Runnable r)
        {
            Thread thread = new Thread(r);
            thread.setDaemon(false);
            thread.setPriority(Thread.NORM_PRIORITY);
            thread.setName(thread.getThreadGroup().getName()+"-pool-"+factoryNum+"-thread-"+integer.incrementAndGet());
            return thread;
        }
    }
}
