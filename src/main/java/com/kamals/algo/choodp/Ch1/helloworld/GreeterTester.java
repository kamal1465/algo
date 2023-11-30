package com.kamals.algo.choodp.Ch1.helloworld;

public class GreeterTester
{
   public static void main(String[] args)
   {
      Greeter worldGreeter = new Greeter("World");
      String greeting = worldGreeter.sayHello();
      System.out.println(greeting);
   }
}
