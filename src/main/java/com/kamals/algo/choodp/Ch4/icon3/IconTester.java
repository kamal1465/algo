package com.kamals.algo.choodp.Ch4.icon3;

import javax.swing.*;

public class IconTester
{
   public static void main(String[] args)
   {
      JOptionPane.showMessageDialog(
            null, 
            "Hello, Car!",
            "Message",
            JOptionPane.INFORMATION_MESSAGE,
            new CarIcon(100));
      System.exit(0);
   }
}

