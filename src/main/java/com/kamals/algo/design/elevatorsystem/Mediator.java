package com.kamals.algo.design.elevatorsystem;

public interface Mediator
{
    void summon(Floor floor, Direction direction);

    void reached(Lift lift, Floor floor, Direction direction);
}
