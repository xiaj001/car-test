package com.aisino.leetcode206;

import com.aisino.entry.list.ListNode;

/**
 * @author: xiajun003
 * @Date: 2019/12/18 20:36
 * @Description:
 */
public class ReverseList {

    public static ListNode reverseList(ListNode head) {
        ListNode pre = null,node = head,next = node.next;
        while (next != null){
            next = node.next;
            node.next = pre;
            pre = node;
            node = next;
        }
        return pre;
    }

    public static void main(String[] args) {
        // 1->2->3->4->5->NULL
        ListNode node1 = new ListNode(1,2,3,4,5);
        System.err.println(node1);
        System.err.println(reverseList(node1));

    }

}