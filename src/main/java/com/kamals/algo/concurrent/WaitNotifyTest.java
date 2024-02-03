package com.kamals.algo.concurrent;

public class WaitNotifyTest
{

    private volatile boolean go;

    public static void main(String[] args) throws InterruptedException
    {
        WaitNotifyTest waitNotifyTest = new WaitNotifyTest();

        Runnable r = () -> {
            try
            {
                waitNotifyTest.test();
            }
            catch (InterruptedException e)
            {
                throw new RuntimeException(e);
            }
        };

        Thread t1 = new Thread(r);
        t1.setName("T1");

        Thread t2 = new Thread(r);
        t2.setName("T2");

        Thread t3 = new Thread(r);
        t3.setName("T3");

        Thread t4 = new Thread(r);
        t4.setName("T4");

        t1.start();
        t2.start();
        t3.start();
        waitNotifyTest.go = true;
        t4.start();

        Thread.sleep(1000);
        waitNotifyTest.test2();
    }


    public synchronized void test() throws InterruptedException
    {
        System.out.println(Thread.currentThread().getName() + ": Start ");
        //if (!go)
        //{
            wait();
        //}
        System.out.println(Thread.currentThread().getName() + ": Here ");
        Thread.sleep(3000);
        //notifyAll();
    }

    public synchronized void test2() throws InterruptedException
    {
        notifyAll();
    }
}
