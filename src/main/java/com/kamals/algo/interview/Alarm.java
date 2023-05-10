package com.kamals.algo.interview;

import java.util.Date;
import java.util.PriorityQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;

public class Alarm
{

    PriorityQueue<Date> pq = new PriorityQueue<>();

    ExecutorService executorService = new ScheduledThreadPoolExecutor(1);

    Thread thead;

    public void setAlarm(Date date)
    {
        if (pq.isEmpty())
        {
            initPolling();
        }
        pq.offer(date);

        if (pq.peek() == date)
        {
            //thread.interrupt();
        }
    }

    private void initPolling()
    {
//        thread = new Thread();

//        thread.start();
    }

    private void poll()
    {
//        case begin:
        Date date = pq.peek();

        Date now = new Date();


        long timeToNext = now.getTime() - date.getTime();

        try
        {
            Thread.sleep(timeToNext);

//            AlarmService.fire();
            pq.poll(); //pop first time
        }
        //Go to sleep for timeToNext
        catch (InterruptedException e)
        {
//            goto begin;
        }

    }

    public static void main(String[] args)
    {

    }
}
