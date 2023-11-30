package com.kamals.algo.choodp.Ch7.reflect1;

import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
   This program prints "Hello, World" the hard way,
   using reflection.
*/
public class HardHello
{
   public static void main(String[] args)
      throws NoSuchMethodException, IllegalAccessException,
             InvocationTargetException
   {
      Method m = PrintStream.class.getDeclaredMethod(
            "println", String.class);
      m.invoke(System.out, "Hello, World!");
   }
}

