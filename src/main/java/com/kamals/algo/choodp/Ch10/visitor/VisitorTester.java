package com.kamals.algo.choodp.Ch10.visitor;

import java.io.File;

public class VisitorTester
{
   public static void main(String[] args)
   {
      DirectoryNode node = new DirectoryNode(new File(".."));
      node.accept(new PrintVisitor());
   }   
}
