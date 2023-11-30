package com.kamals.algo.concurrent;

import java.util.ArrayList;
import java.util.List;

public class ThreadTest
{
    public static void main(String[] args)
    {
        List<Thread> threadList = new ArrayList<>(Thread.getAllStackTraces().keySet());

        for (Thread t : threadList)
        {
            System.out.println(t.getId() + " " + t.getName() + " " + t.getState() + " " + t.getContextClassLoader() + " " + t.getThreadGroup());
        }
    }
}
