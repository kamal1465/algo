package com.kamals.algo.choodp.Ch5.mailgui;

public class MailSystemTester
{
   public static void main(String[] args)
   {
      MailSystem system = new MailSystem(MAILBOX_COUNT);

      Telephone p = new Telephone();
      Connection c = new Connection(system, p);
      p.run(c);
   }

   private static int MAILBOX_COUNT = 20;
}
