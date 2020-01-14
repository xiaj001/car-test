package com.aisino.leetcode019;

import com.aisino.entry.list.ListNode;

/**
 * @author: xiajun003
 * @Date: 2020/1/14 19:44
 * @Description:
 * 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
 *
 * 示例：
 *
 * 给定一个链表: 1->2->3->4->5, 和 n = 2.
 *
 * 当删除了倒数第二个节点后，链表变为 1->2->3->5.
 * 说明：
 *
 * 给定的 n 保证是有效的。
 *
 * 进阶：
 *
 * 你能尝试使用一趟扫描实现吗？
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class RemoveFromList {

    /**
     * 此题两个关键点，1.建立哑节点 2.使用双指针
     * @param head
     * @param n
     * @return
     */
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode temp = new ListNode(0);
        temp.next = head;
        ListNode first = temp;
        ListNode second = temp;
        for (int i = 0 ; i < n+1 ; i ++){
            first = first.next;
        }
        while (first != null){
            first = first.next;
            second = second.next;
        }
        second.next = second.next.next;
        return temp.next;
    }

    public static void main(String[] args) {
        ListNode<Integer> listNode = new ListNode<>(1, 2, 3, 4, 5);
        System.err.println(listNode);
        System.err.println(removeNthFromEnd(listNode,5));
    }

}
