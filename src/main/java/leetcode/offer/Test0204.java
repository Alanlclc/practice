package leetcode.offer;

import base.ListNode;

public class Test0204 {

    //迭代  当index >= x 当前节点删除并添加到新的链表  结束后尾结点指向新链表
    public ListNode partition(ListNode head, int x) {
        ListNode tt = null;
        ListNode pre = new ListNode(0);
        ListNode temp = new ListNode(0);
        pre.next = head;
        temp.next = pre;
        while(pre.next != null){
            if (pre.next.val >= x) {
                ListNode node = pre.next;
                pre.next = pre.next.next;
                node.next = tt;
                tt = node;
            }
            pre = pre.next;
        }
        temp.next.next = tt;
        return temp.next.next;
    }

    public static void main(String[] args) {
        Test0204 test0204 = new Test0204();
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(4);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(5);
        ListNode node5 = new ListNode(2);
        node4.next = node5;
        node3.next = node4;
        node2.next = node3;
        node1.next = node2;
        ListNode node = test0204.partition(node1,3);
        System.out.println(1);
    }

}
