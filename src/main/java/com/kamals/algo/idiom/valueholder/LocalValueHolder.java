package com.kamals.algo.idiom.valueholder;

import java.util.HashMap;
import java.util.Map;

public class LocalValueHolder implements ValueHolder
{
    private static final Map<String, Value> valueMap = new HashMap<>();

    @Override
    public Value getValue(String name)
    {
        Value value = valueMap.get(name);
        if (value == null)
        {
            synchronized (this)
            {
                value = valueMap.get(name);
                if (value == null)
                {
                    value = Value.generateValue();
                    valueMap.put(name, value);
                }
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
}
