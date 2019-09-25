package com.javarush.task.task28.task2805;

import java.util.concurrent.atomic.AtomicInteger;

public class MyThread extends Thread {
    private static int threadPriority=Thread.MIN_PRIORITY;
//    static {
//        threadPriority++;
//    }

    public MyThread() {
        //threadPriority.incrementAndGet();
        //System.out.println("  new pr="+threadPriority);
        setMyPriority();
    }



    public MyThread(Runnable target) {
        super(target);
        setMyPriority();

    }

    public MyThread(ThreadGroup group, Runnable target) {
        super(group, target);
        setMyPriority();
    }

    public MyThread(String name) {
        super(name);
        setMyPriority();
    }

    public MyThread(ThreadGroup group, String name) {
        super(group, name);
        setMyPriority();
    }

    public MyThread(Runnable target, String name) {
        super(target, name);
        setMyPriority();
    }

    public MyThread(ThreadGroup group, Runnable target, String name) {
        super(group, target, name);
        setMyPriority();
    }

    public MyThread(ThreadGroup group, Runnable target, String name, long stackSize) {
        super(group, target, name, stackSize);
        setMyPriority();
    }


    private void setMyPriority() {
        ThreadGroup group = this.getThreadGroup();
        int max_priority=group.getMaxPriority();
        // если p < GR.MAX - вренуть p
        // если p>=GR_MAX && p<T.MAX - врнеть GR.MAX
        int pr = threadPriority;
        if(pr >= max_priority && pr<=Thread.MAX_PRIORITY)
            pr=max_priority;
        else if(threadPriority > Thread.MAX_PRIORITY) {
            threadPriority=Thread.MIN_PRIORITY;
        }
        //System.out.println("Setting priority to "+threadPriority+" max="+max_priority+" cur="+threadPriority);
        setPriority(threadPriority);
        threadPriority++;
    }


}
