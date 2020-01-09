package com.aisino.entry.list;

/**
 * @author: xiajun003
 * @Date: 2020/1/6 15:21
 * @Description:
 */
public  class  ListNode <T> {

    public T val;

    public ListNode next;

    public ListNode(T val){
        this.val=val;
    }

    public ListNode(T val,ListNode<T> next){
        this.val=val;
        this.next = next;
    }

    public  ListNode (T... vals){
        //[1,2,3,4]
        ListNode<T> tmp = null;
        for (int i = vals.length ; i > 0;i -- ){
            tmp = new ListNode(vals[i - 1],tmp);
        }
        if (tmp != null){
            this.val = tmp.val;
            this.next = tmp.next;
        }
    }

    public static void main(String[] args) {
        ListNode<Integer> integerListNode = new ListNode<Integer>(1,2,3,4,5,6);
        System.err.println(integerListNode);
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        ListNode node = this;
        while (node != null){
            sb.append(",").append(node.val);
            node = node.next;
        }
        if (sb.length()>0){
            sb.deleteCharAt(0);
        }
        return sb.toString();
    }
}
