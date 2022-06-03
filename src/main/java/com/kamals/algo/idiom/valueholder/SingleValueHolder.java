package com.kamals.algo.idiom.valueholder;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class SingleValueHolder
{
    private volatile Value value;
    private final Lock lock = new ReentrantLock();
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public Value getValue()
    {
        if (value == null)
        {
            lock.lock();
            try
            {
                if (value == null)
                {
                    value = Value.generateValue();
                }
            }
            finally
            {
                lock.unlock();
            }
        }
        return value;
    }

    public Value getValue(int x)
    {
        if (value == null)
        {
            readWriteLock.readLock().lock();
            try
            {
                if (value != null)
                {
                    return value;
                }
            }
            finally
            {
                readWriteLock.readLock().unlock();
            }
            readWriteLock.writeLock().lock();
            try
            {
                if (value == null)
                {
                    value = Value.generateValue();
                }
            }
            finally
            {
                readWriteLock.writeLock().unlock();
            }
        }
        return value;
    }

    public void setValue(Value value)
    {
        this.value = value;
    }

    public void deleteValue()
    {
        value = null;
    }
}
