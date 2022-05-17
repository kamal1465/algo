package com.kamals.algo.idiom.proration;

public class DiscountProrationTest
{
    public static void perform()
    {
        OrderBuilder orderBuilder = new OrderBuilder("101");

        orderBuilder.addEntry(100d, "p1", 10d);
        orderBuilder.addEntry(100d, "p2", 10d);
        orderBuilder.addEntry(100d, "p3", 10d);

        orderBuilder.addPromo("p4", 10d);
        orderBuilder.addPromo("p5", 10d);

        Order order = orderBuilder.buildOrder();

        System.out.println(order);
    }

    public static void main(String[] args)
    {
        perform();
    }
}
