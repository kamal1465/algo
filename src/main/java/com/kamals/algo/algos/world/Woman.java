package com.kamals.algo.algos.world;

import java.util.ArrayList;
import java.util.List;

public class Woman extends Person
{
    private Man man;
    private List<Man> desireList;

    public Woman(String name)
    {
        super(name);
        this.man = man;
    }

    public Man getMan()
    {
        return man;
    }

    public void setMan(Man man)
    {
        this.man = man;
    }

    public void addMan(Man m)
    {
        if (desireList == null)
        {
            desireList = new ArrayList<>();
        }
        desireList.add(m);
    }

    public List<Man> getDesireList()
    {
        return desireList;
    }

    public void setDesireList(List<Man> desireList)
    {
        this.desireList = desireList;
    }
}
