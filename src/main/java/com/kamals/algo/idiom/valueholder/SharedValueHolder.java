package com.kamals.algo.idiom.valueholder;

public class SharedValueHolder implements ValueHolder
{
    private ValueHolder valueHolder;

    @Override
    public Value getValue(String name)
    {
        return null;
    }

    @Override
    public void setValue(String name, Value value)
    {

    }

    @Override
    public void deleteValue(String name)
    {

    }
}
