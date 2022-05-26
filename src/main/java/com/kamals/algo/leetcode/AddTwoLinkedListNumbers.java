package com.kamals.algo.leetcode;

/**
 * You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order, and each of their nodes contains a single digit. Add the two numbers and return the sum as a linked list.
 * <p>
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 * <p>
 * Example 1:
 * <p>
 * Input: l1 = [2,4,3], l2 = [5,6,4]
 * Output: [7,0,8]
 * Explanation: 342 + 465 = 807.
 * Example 2:
 * <p>
 * Input: l1 = [0], l2 = [0]
 * Output: [0]
 * Example 3:
 * <p>
 * Input: l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
 * Output: [8,9,9,9,0,0,0,1]
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in each linked list is in the range [1, 100].
 * 0 <= Node.val <= 9
 * It is guaranteed that the list represents a number that does not have leading zeros.
 */
public class AddTwoLinkedListNumbers
{
    public static void main(String[] args)
    {
        AddTwoLinkedListNumbers addTwoLinkedListNumbers = new AddTwoLinkedListNumbers();
        int a = 342;
        int b = 465;
        ListNode l1 = addTwoLinkedListNumbers.new ListNode(0).from(a);
        ListNode l2 = addTwoLinkedListNumbers.new ListNode(0).from(b);
        System.out.println(l1);
        System.out.println(l2);
        ListNode l3 = addTwoLinkedListNumbers.addTwoNumbers(l1, l2);
        System.out.println(l3);
    }

    public class ListNode
    {
        int val;
        ListNode next;

        ListNode()
        {
        }

        ListNode(int val)
        {
            this.val = val;
        }

        ListNode(int val, ListNode next)
        {
            this.val = val;
            this.next = next;
        }

        @Override
        public String toString()
        {
            StringBuilder sb = new StringBuilder();
            ListNode node = this;
            while (node != null)
            {
                sb.append(node.val).append(" ");
                node = node.next;
            }
            return sb.toString();
        }

        public ListNode from(int x)
        {
            ListNode head = null, tail = null;
            while (x > 0)
            {
                int val = x % 10;
                ListNode node = new ListNode(val);
                x = x / 10;
                if (head == null)
                {
                    head = tail = node;
                }
                else
                {
                    tail.next = node;
                    tail = tail.next;
                }
            }
            return head;
        }
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2)
    {
        ListNode l3 = null, tail = null;
        int carry = 0;
        while (l1 != null || l2 != null || carry > 0)
        {

            int val = carry;
            if (l1 != null)
            {
                val += l1.val;
                l1 = l1.next;
            }
            if (l2 != null)
            {
                val += l2.val;
                l2 = l2.next;
            }
            carry = val / 10;
            val = val % 10;
            if (l3 == null)
            {
                l3 = new ListNode(val);
                tail = l3;
            }
            else
            {
                tail.next = new ListNode(val);
                tail = tail.next;
            }
        }
        return l3;
    }
}
