package com.javarush.task.task25.task2506;

import javax.swing.plaf.nimbus.State;

public class LoggingStateThread extends Thread {
    Thread spyThread;

    public LoggingStateThread(Thread target) {
        super(target);
        this.spyThread = target;
        System.out.println(getState().toString());
    }

    @Override
    public void run() {
        Thread.State prev_state;
        prev_state = spyThread.getState();
        while(prev_state != State.TERMINATED) {
            if(prev_state!=spyThread.getState()) {
                System.out.println(spyThread.getState());
                prev_state = spyThread.getState();
            }
        }
    }
}
