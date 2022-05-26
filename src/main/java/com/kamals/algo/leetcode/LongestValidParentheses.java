package com.kamals.algo.leetcode;

import java.util.*;

/**
 * Given a string containing just the characters '(' and ')',
 * find the length of the longest valid (well-formed) parentheses substring.
 * Example 1:
 * Input: s = "(()"
 * Output: 2
 * Explanation: The longest valid parentheses substring is "()".
 * Example 2:
 * Input: s = ")()())"
 * Output: 4
 * Explanation: The longest valid parentheses substring is "()()".
 * Example 3:
 * Input: s = ""
 * Output: 0
 * Constraints:
 * 0 <= s.length <= 3 * 10^4
 * s[i] is '(', or ')'.
 */
public class LongestValidParentheses
{
    public static void main(String[] args)
    {
        int length = 10;
        LongestValidParentheses longestValidParentheses = new LongestValidParentheses();
        String s = longestValidParentheses.generateRandomParenthesesString(length);
        //        s = "(";
        System.out.println(s);
        int l = longestValidParentheses.longestValidParentheses3(s);
        System.out.println(l);
    }

    public int longestValidParentheses(String s)
    {
        int length = s.length();
        int[] graph = new int[length + 1];

        int i = 1;
        for (char c : s.toCharArray())
        {
            if (c == '(')
            {
                graph[i] = graph[i - 1] + 1;
            }
            else if (c == ')')
            {
                graph[i] = graph[i - 1] - 1;
            }
            i++;
        }

        int validLength = 0;
        for (int j = 0; j < graph.length - 1; j++)
        {
            int z = 0;
            int x = graph[j];
            int min = -1;
            for (int k = j + 1; k < graph.length; k++)
            {
                int y = graph[k];
                if (x == y)
                {
                    z = k - j;
                }
                else if (y < x)
                {
                    break;
                }
                min = Math.min(min, y);
            }
            validLength = Math.max(validLength, z);
            System.out.println(j + " = " + z);
            j += z;
        }
        return validLength;
    }

    public int longestValidParentheses3(String s)
    {
        int maxLength = 0;

        Stack<Integer> stack = new Stack<>();
        Stack<Integer> indices = new Stack<>();
        Stack<Integer> lengths = new Stack<>();

        for (char c : s.toCharArray())
        {
            if (c == '(')
            {
                stack.push(1);
            }
            else if (c == ')')
            {
                if (stack.isEmpty())
                {
                    if (!indices.isEmpty())
                    {
                        indices.clear();
                        lengths.clear();
                    }
                }
                else
                {
                    stack.pop();
                    int l = stack.size();
                    int k = indices.isEmpty() ? -1 : indices.peek();
                    if (l == k)
                    {
                        lengths.push(lengths.pop() + 2);
                    }
                    else if (l < k)
                    {
                        indices.pop();
                        int len = lengths.pop() + 2;
                        k = indices.isEmpty() ? -1 : indices.peek();
                        if (k == l)
                        {
                            lengths.push(lengths.pop() + len);
                        }
                        else
                        {
                            indices.push(l);
                            lengths.push(len);
                        }
                    }
                    else
                    {
                        indices.push(l);
                        lengths.push(2);
                    }
                    maxLength = Math.max(lengths.peek(), maxLength);
                }
            }
        }

        return maxLength;
    }

    private String generateRandomParenthesesString(int length)
    {
        char[] p = new char[]{'(', ')'};

        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < length; i++)
        {
            Random random = new Random();
            int k = random.nextInt(2);
            stringBuilder.append(p[k]);
        }
        return stringBuilder.toString();
    }

    public int longestValidParentheses2(String s)
    {
        Node node = null;

        for (char c : s.toCharArray())
        {
            Node n = new Node(c == '(' ? 1 : -1);
            n.setPrev(node);
            node = n;
        }

        while (node != null && node.getPrev() != null)
        {
            node.getPrev().setNext(node);
            node = node.getPrev();
        }

        Node head = node;
//        System.out.println(head);

        while (node != null)
        {
//            System.out.println();
//            System.out.println("Head=>" + head);
//            System.out.println("Node=>" + node);
            Node prev = node.getPrev();
            Node next = node.getNext();
//            System.out.println("Prev=>" + prev);
//            System.out.println("Next=>" + next);
            if (next != null && node.getC() == 1 && next.getC() == -1)
            {
                node.setC(2);
                node.setNext(next.getNext());
                if (next.getNext() != null)
                {
                    next.getNext().setPrev(node);
                }
            }
            else if (prev != null && next != null && prev.getC() == 1 && next.getC() == -1 && node.getC() > 1)
            {
                prev.setC((node.getC() +  2));
                prev.setNext(next.getNext());
                if (next.getNext() != null)
                {
                    next.getNext().setPrev(prev);
                }
                node = prev.getPrev() != null ? prev.getPrev() : prev;
            }
            else if (next != null && node.getC() > 1 && next.getC() > 1)
            {
                node.setC((node.getC() + next.getC()));
                node.setNext(next.getNext());
                if (next.getNext() != null)
                {
                    next.getNext().setPrev(node);
                }
            }
            else if (prev != null && node.getC() > 1 && prev.getC() > 1)
            {
                prev.setC(node.getC() + prev.getC());
                prev.setNext(next);
                if (next != null)
                {
                    next.setPrev(prev);
                }
                node = prev;
            }
            else
            {
                node = node.getNext();
            }
        }

        node = head;
//        System.out.println(head);
        int validLength = 0;

        while (node != null)
        {
            if (node.getC() > 1)
            {
                validLength = Math.max(validLength, node.getC());
            }
            node = node.getNext();
        }

        return validLength;
    }

    class Node
    {
        private int c;
        private Node prev;
        private Node next;

        public Node(int c)
        {
            this.c = c;
        }

        public int getC()
        {
            return c;
        }

        public void setC(int c)
        {
            this.c = c;
        }

        public Node getPrev()
        {
            return prev;
        }

        public void setPrev(Node prev)
        {
            this.prev = prev;
        }

        public Node getNext()
        {
            return next;
        }

        public void setNext(Node next)
        {
            this.next = next;
        }

        @Override
        public String toString()
        {
            StringBuilder sb = new StringBuilder();
            Node node = this;
            while (node != null)
            {
                sb.append(node.getC()).append(" ");
                node = node.getNext();
            }
            return sb.toString();
        }
    }

}
