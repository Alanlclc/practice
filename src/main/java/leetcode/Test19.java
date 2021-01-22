package leetcode;

import base.ListNode;

/**
 * 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
 */
public class Test19 {

    //双指针法
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode pre = new ListNode(0,head);
        int temp = 0;
        ListNode node = head;
        ListNode preNode = null;
        while (head != null) {
            temp++;
            if (n < temp){
                //第二个指针开始移动
                preNode = node;
                node = node.next;
            }
            if (head.next == null){
                if (preNode != null){
                    preNode.next = node.next;
                }else{
                    //未移动过  删除第一个或者不删除
                    if (n == temp){
                        pre.next = pre.next.next;
                    }
                }
            }
            head = head.next;
        }
        return pre.next;
    }


    public static void main(String[] args) {
        Test19 test19 = new Test19();
//        ListNode listNode5 = new ListNode(5);
//        ListNode listNode4 = new ListNode(4,listNode5);
//        ListNode listNode3 = new ListNode(3,listNode4);
//        ListNode listNode2 = new ListNode(2,listNode3);
        ListNode listNode1 = new ListNode(1);
        test19.removeNthFromEnd(listNode1,1);
    }
}
