package com.javarush.task.task27.task2709;

public class ConsumerTask implements Runnable {
    private TransferObject transferObject;
    protected volatile boolean stopped;

    //1. В методе run класса ConsumerTask должен содержаться synchronized блок, монитор - transferObject.
    public ConsumerTask(TransferObject transferObject) {
            this.transferObject = transferObject;
            new Thread(this, "ConsumerTask").start();
    }

    public void run() {
        while (!stopped) {
            synchronized (transferObject) {
                try {
                    transferObject.get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void stop() {
        stopped = true;
    }
}