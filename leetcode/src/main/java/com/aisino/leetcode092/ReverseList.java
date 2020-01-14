package com.aisino.leetcode092;

import com.aisino.entry.list.ListNode;

/**
 * @author: xiajun003
 * @Date: 2019/12/24 20:44
 * @Description:
 *
 * 反转从位置 m 到 n 的链表。请使用一趟扫描完成反转。
 *
 * 说明:
 * 1 ≤ m ≤ n ≤ 链表长度。
 *
 * 示例:
 *
 * 输入: 1->2->3->4->5->NULL, m = 2, n = 4
 * 输出: 1->4->3->2->5->NULL
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reverse-linked-list-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class ReverseList {

    public static void main(String[] args) {
        ListNode root = new ListNode(1,2,3,4,5);
        System.err.println(root);
        ListNode rever = rever(2, 4, root);
        System.err.println(rever);
    }

    private static ListNode rever(int m,int n,ListNode head){

        ListNode cur = head,pre = null;
        while (m > 1){
            pre = cur;
            cur = cur.next;
            m --;
            n --;
        }

        ListNode con = cur, next = cur.next;

        while (n >1){

        }

        return null;

    }


}

