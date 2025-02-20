package com.kamals.algo.chcj.v2ch12.printf3;

import java.io.*;

/**
 * @version 1.11 2018-05-01
 * @author Cay Horstmann
 */
class Printf3Test
{
   public static void main(String[] args)
   {
      double price = 44.95;
      double tax = 7.75;
      double amountDue = price * (1 + tax / 100);
      var out = new PrintWriter(System.out);
      Printf3.fprint(out, "Amount due = %8.2f\n", amountDue);
      out.flush();
   }
}
