package com.me.leetcode;

/**
 * @author zs
 * @date 2021/10/7.
 * 合并两个有序链表
 * 递归
 */

class ListNode {
    int val;
    ListNode next;

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}

public class lc_021 {

    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        if(l1.val < l2.val){
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        }else {
            l2.next = mergeTwoLists(l2.next, l1);
            return l2;
        }
    }

    public static void main(String[] args) {
        ListNode la1 = new ListNode(1);
        ListNode la2 = new ListNode(2, la1);
        ListNode la3 = new ListNode(4, la2);

        ListNode lb1 = new ListNode(1);
        ListNode lb2 = new ListNode(3, lb1);
        ListNode lb3 = new ListNode(4, lb2);
        ListNode listNode = mergeTwoLists(la3, lb3);

    }
}
