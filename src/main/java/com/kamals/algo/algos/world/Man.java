package com.kamals.algo.algos.world;

import java.util.ArrayList;
import java.util.List;

public class Man extends Person
{
    private Woman woman;
    private List<Woman> desireList;

    public Man(String name)
    {
        super(name);
    }

    public Woman getWoman()
    {
        return woman;
    }

    public void setWoman(Woman woman)
    {
        this.woman = woman;
    }

    public void addWoman(Woman w)
    {
        if (desireList == null)
        {
            desireList = new ArrayList<>();
        }
        desireList.add(w);
    }

    public List<Woman> getDesireList()
    {
        return desireList;
    }

    public void setDesireList(List<Woman> desireList)
    {
        this.desireList = desireList;
    }
}
