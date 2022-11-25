package com.kamals.algo.design.elevatorsystem;

public interface Lift
{
    void move(Floor floor);

    void move(Floor floor, Direction direction);
}
