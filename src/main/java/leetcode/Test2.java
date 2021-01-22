package leetcode;

import base.ListNode;

import java.util.List;

/**
 * 给你两个非空 的链表，表示两个非负的整数。它们每位数字都是按照逆序的方式存储的，并且每个节点只能存储一位数字。
 *
 * 请你将两个数相加，并以相同形式返回一个表示和的链表。
 *
 * 你可以假设除了数字 0 之外，这两个数都不会以 0开头。
 */
public class Test2 {



    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode pre = new ListNode();
        ListNode first = new ListNode(0,pre);
        ListNode left = l1;
        ListNode right = l2;
        int temp = 0;
        do {
            int a;
            if (left == null){
                a = right.val + temp;
                right = right.next;
            }else if (right == null){
                a = left.val + temp;
                left = left.next;
            }else{
                a = left.val + right.val + temp;
                left = left.next;
                right = right.next;
            }
            pre.next = new ListNode( a % 10);
            temp = a / 10 > 0 ? 1 : 0;
            pre = pre.next;
        } while (left != null || right != null);
        if (temp > 0) {
            pre.next = new ListNode(temp);
        }
        return first.next.next;
    }


}
