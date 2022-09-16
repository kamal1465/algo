package com.kamals.algo.leetcode;

public class LinkedList
{
    /**
     * Reverse a linked list
     * @param head
     * @return
     */
    public ListNode reverseLinkedList(ListNode head)
    {
        ListNode node = head;
        ListNode tmp;
        head = null;
        while (node != null)
        {
            tmp = node.next;
            node.next = head;
            head = node;
            node = tmp;
        }
        return head;
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
    }
}
