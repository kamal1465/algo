package com.kamals.algo.choodp.Ch5.mailgui;

/**
   A mailbox contains messages that can be listed, kept or discarded.
*/
public class Mailbox
{
   /**
      Create Mailbox object.
      @param aPasscode passcode number
      @param aGreeting greeting string
   */
   public Mailbox(String aPasscode, String aGreeting)
   {
      passcode = aPasscode;
      greeting = aGreeting;
      newMessages = new MessageQueue();
      keptMessages = new MessageQueue();
   }

   /**
      Check if the entered supplied is correct.
      @param aPasscode a passcode to check
      @return true if the supplied passcode matches the mailbox passcode
   */
   public boolean checkPasscode(String aPasscode)
   {
      return aPasscode.equals(passcode);
   }

   /**
      Add a message to the mailbox.
      @param aMessage the message to be added
   */
   public void addMessage(Message aMessage)
   {
      newMessages.add(aMessage);
   }

   /**
      Get the current message.
      @return the current message
   */
   Message getCurrentMessage()
   {
      if (newMessages.getLength() > 0)
         return newMessages.getHead();
      else if (keptMessages.getLength() > 0)
         return keptMessages.getHead();
      else
         return null;
   }

   /**
      Remove the current message from the mailbox.
      @return the message that has just been removed
   */
   public Message removeCurrentMessage()
   {
      if (newMessages.getLength() > 0)
         return newMessages.remove();
      else if (keptMessages.getLength() > 0)
         return keptMessages.remove();
      else
         return null;
   }

   /**
      Save the current message
   */
   public void saveCurrentMessage()
   {
      Message m = removeCurrentMessage();
      if (m != null)
         keptMessages.add(m);
   }

   /**
      Change mailbox's greeting.
      @param newGreeting the new greeting string
   */
   public void setGreeting(String newGreeting)
   {
      greeting = newGreeting;
   }

   /**
      Change mailbox's passcode.
      @param newPasscode the new passcode
   */
   public void setPasscode(String newPasscode)
   {
      passcode = newPasscode;
   }

   /**
      Get the mailbox's greeting.
      @return the greeting
   */
   String getGreeting()
   {
      return greeting;
   }

   private MessageQueue newMessages;
   private MessageQueue keptMessages;
   private String greeting;
   private String passcode;
}
