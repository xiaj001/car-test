package com.aisino.leetcode061;

/**
 * @author jun.xia
 *
 * 给定一个链表，旋转链表，将链表每个节点向右移动 k 个位置，其中 k 是非负数。
 *
 * 示例 1:
 * 输入: 1->2->3->4->5->NULL, k = 2
 * 输出: 4->5->1->2->3->NULL
 * 解释:
 * 向右旋转 1 步: 5->1->2->3->4->NULL
 * 向右旋转 2 步: 4->5->1->2->3->NULL
 *
 * 示例 2:
 * 输入: 0->1->2->NULL, k = 4
 * 输出: 2->0->1->NULL
 * 解释:
 * 向右旋转 1 步: 2->0->1->NULL
 * 向右旋转 2 步: 1->2->0->NULL
 * 向右旋转 3 步: 0->1->2->NULL
 * 向右旋转 4 步: 2->0->1->NULL
 */
public class RotateRight {

    public ListNode rotateRight(ListNode head, int k) {
        if (head==null){
            return null;
        }
        ListNode pFirst = head;
        ListNode pSecond =head;

        //先移动第一个指针,移动K次
        for (int i = 0 ;i < k  ; i++){
            pFirst = pFirst.next==null?head:pFirst.next;
        }

        //同时移动
        while (pFirst.next!=null){
            pFirst = pFirst.next;
            pSecond=pSecond.next;
        }
        ListNode next = pSecond.next;
        pSecond.next=null;
        pSecond = next;
        while (pSecond.next!=null){
            pSecond=pSecond.next;
        }
        pSecond.next=head;
        head=next;
        return head;
    }

    public void reverse(ListNode head,int k){

    }


}

class ListNode{
    public ListNode next;
    public int val;
}
