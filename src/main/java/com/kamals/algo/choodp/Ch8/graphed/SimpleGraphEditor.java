package com.kamals.algo.choodp.Ch8.graphed;

import javax.swing.*;

/**
   A program for editing UML diagrams.
*/
public class SimpleGraphEditor
{
   public static void main(String[] args)
   {
      JFrame frame = new GraphFrame(new SimpleGraph());
      frame.setVisible(true);
   }
}

