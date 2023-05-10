package com.kamals.algo.design.elevatorsystem;

public interface Floor
{
    void summon(Direction direction);

    void reached(Lift lift, Direction direction);
}
