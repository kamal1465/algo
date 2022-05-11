package com.kamals.algo.idiom.valueholder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Component("remoteValueHolder")
public class RemoteValueHolder implements ValueHolder
{
    private static final Map<String, Value> remoteValueMap = new ConcurrentHashMap<>();
    private static final Map<String, ReentrantReadWriteLock> lockMap = new HashMap<>();

    @Override
    public Value getValue(String name)
    {
        Value value = remoteValueMap.get(name);
        if (value == null)
        {
            synchronized (this) //Need Global Locking here..(Redis, Hz etc.)
            {
                value = remoteValueMap.get(name);
                if (value == null)
                {
                    value = Value.generateValue();
                    setValue(name, value);
                }
            }
        }
        return value;
    }

    @Override
    public void setValue(String name, Value value)
    {
        remoteValueMap.put(name, value);
    }

    @Override
    public void deleteValue(String name)
    {
        remoteValueMap.remove(name);
    }
}
