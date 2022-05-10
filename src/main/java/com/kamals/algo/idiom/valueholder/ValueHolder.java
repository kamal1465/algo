package com.kamals.algo.idiom.valueholder;

public interface ValueHolder
{
    Value getValue(String name);

    void setValue(String name, Value value);

    void deleteValue(String name);
}
