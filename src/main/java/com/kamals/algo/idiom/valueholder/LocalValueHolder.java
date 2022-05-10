package com.kamals.algo.idiom.valueholder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Component("localValueHolder")
public class LocalValueHolder implements ValueHolder
{
    private static final Map<String, Value> valueMap = new ConcurrentHashMap<>();
    private static final Map<String, ReentrantReadWriteLock> lockMap = new ConcurrentHashMap<>();

    @Autowired
    private RemoteValueHolder remoteValueHolder;

    @Override
    public Value getValue(String name)
    {
        Value value = valueMap.get(name);
        if (value == null)
        {
            ReentrantReadWriteLock reentrantReadWriteLock = getLock(name);
            reentrantReadWriteLock.writeLock().lock();
            try
            {
                value = valueMap.get(name);
                if (value == null)
                {
                    value = remoteValueHolder.getValue(name);
                    if (value != null)
                    {
                        setValue(name, value);
                    }
                }
            }
            finally
            {
                reentrantReadWriteLock.writeLock().unlock();
            }
        }
        return value;
    }

    @Override
    public void setValue(String name, Value value)
    {
        valueMap.put(name, value);
    }

    @Override
    public void deleteValue(String name)
    {
        valueMap.remove(name);
    }

    private ReentrantReadWriteLock getLock(String name)
    {
        ReentrantReadWriteLock reentrantReadWriteLock = lockMap.get(name);
        if (reentrantReadWriteLock == null)
        {
            synchronized (this) //Is it needed? Can be improved?
            {
                reentrantReadWriteLock = lockMap.get(name);
                if (reentrantReadWriteLock == null)
                {
                    reentrantReadWriteLock = new ReentrantReadWriteLock();
                    lockMap.put(name, reentrantReadWriteLock);
                }
            }
        }
        return reentrantReadWriteLock;
    }
}
