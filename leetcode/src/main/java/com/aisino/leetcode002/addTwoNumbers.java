package com.aisino.leetcode002;

import org.junit.Test;


/**
 *
 * 给定两个非空链表来表示两个非负整数。位数按照逆序方式存储，
 * 它们的每个节点只存储单个数字。将两数相加返回一个新的链表。
 *
 * 你可以假设除了数字 0 之外，这两个数字都不会以零开头。
 *
 * 示例：
 *
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 0 -> 8
 * 原因：342 + 465 = 807
 *
 *
 */
public class addTwoNumbers {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(0);
        ListNode p = l1, q = l2, curr = dummyHead;
        int carry = 0;
        while (p != null || q != null) {
            int x = (p != null) ? p.val : 0;
            int y = (q != null) ? q.val : 0;
            int sum = carry + x + y;
            carry = sum / 10;
            curr.next = new ListNode(sum % 10);
            curr = curr.next;
            if (p != null) p = p.next;
            if (q != null) q = q.next;
        }
        if (carry > 0) {
            curr.next = new ListNode(carry);
        }
        return dummyHead.next;
    }


    @Test
    public void testAddTwoNumbers(){
        ListNode node1 = new ListNode(2);
        node1.next=new ListNode(4);
        node1.next.next=new ListNode(3);
        ListNode node2 = new ListNode(5);
        node2.next=new ListNode(6);
        node2.next.next=new ListNode(4);
        ListNode listNode = addTwoNumbers(node1, node2);
        System.err.println(listNode);

    }
}


class ListNode{
    public Integer val;
    public ListNode next;
    public ListNode(Integer value){
        this.val=value;
    }
}

