package com.kamals.algo.idiom.valueholder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValueHolderTest
{
    @Autowired
    private LocalValueHolder localValueHolder;

    public Value perform()
    {
        Value val = localValueHolder.getValue("abc");
        System.out.println(val);
        return val;
    }

    public static void main(String[] args)
    {
        ValueHolderTest valueHolderTest = new ValueHolderTest();
        Value val = valueHolderTest.perform();
    }
}
