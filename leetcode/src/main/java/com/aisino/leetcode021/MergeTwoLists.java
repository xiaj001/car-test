package com.aisino.leetcode021;

import com.aisino.entry.list.ListNode;

/**
 * @author: xiajun003
 * @Date: 2020/1/14 20:22
 * @Description:
 *
 * 将两个有序链表合并为一个新的有序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。 
 *
 * 示例：
 *
 * 输入：1->2->4, 1->3->4
 * 输出：1->1->2->3->4->4
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/merge-two-sorted-lists
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class MergeTwoLists {

    public static ListNode solution(ListNode<Integer> l1, ListNode<Integer> l2) {

        ListNode<Integer> dummyHead = new ListNode(0);
        ListNode<Integer> current = dummyHead;

        while (l1 != null && l2 != null){
            if (l1.val < l2.val){
                current.next = l1;
                current = current.next;
                l1 = l1.next;
            }else {
                current.next = l2;
                current = current.next;
                l2 = l2.next;
            }
        }
        if (l1 == null){
            current.next = l2;
        }else {
            current.next = l1;
        }
        return dummyHead.next;
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(1,2,4);
        ListNode l2 = new ListNode(1,3,4);
        System.err.println(solution(l1,l2));
    }

}
