package com.kamals.algo.choodp.Ch4.icon1;

import javax.swing.*;

public class IconTester
{
   public static void main(String[] args)
   {
      JOptionPane.showMessageDialog(
            null, 
            "Hello, World!",
            "Message",
            JOptionPane.INFORMATION_MESSAGE,
            new ImageIcon("globe.gif"));
      System.exit(0);
   }
}
