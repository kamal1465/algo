package com.kamals.algo.choodp.Ch8.umleditor;

import javax.swing.*;

/**
   A program for editing UML diagrams.
*/
public class UMLEditor
{
   public static void main(String[] args)
   {
      JFrame frame = new GraphFrame(new ClassDiagramGraph());
      frame.setVisible(true);
   }
}

