package com.javarush.task.task25.task2508;

public class TaskManipulator implements Runnable, CustomThreadManipulator {
    private Thread spyThread;

    @Override
    public void run() {
        try {
            while(!spyThread.isInterrupted()) {
                System.out.println(Thread.currentThread().getName());
                Thread.sleep(100);
            }
        } catch (InterruptedException e) {
            //e.printStackTrace();
        }

    }

    @Override
    public void start(String threadName) {
        spyThread = new Thread(this,threadName); // запускаем ЭТОТ объект - TaskManipulator() c именеи threadName
        spyThread.start();
    }

    @Override
    public void stop() {
        spyThread.interrupt();

    }
}
