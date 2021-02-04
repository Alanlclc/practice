package leetcode.offer;

import base.ListNode;
import base.TreeNode;

import java.util.*;

/**
 * 给定一棵二叉树，设计一个算法，创建含有某一深度上所有节点的链表（比如，若一棵树的深度为 D，则会创建出 D 个链表）。返回一个包含所有深度的链表的数组。
 */
public class Test0403 {

    public ListNode[] listOfDepth(TreeNode tree) {
        List<ListNode> list = new ArrayList<>();
        LinkedList<TreeNode> queue = new LinkedList<>();
        ListNode pre = new ListNode(0);
        queue.offer(tree);
        while (!queue.isEmpty()){
            int size = queue.size();
            ListNode temp = pre;
            for (int i = 0; i < size ; i++){
                TreeNode node = queue.poll();
                temp.next = new ListNode(node.val);
                if (node.left != null){
                    queue.offer(node.left);
                }
                if (node.right != null){
                    queue.offer(node.right);
                }
                temp = temp.next;
            }
            list.add(pre.next);
            pre.next = null;
        }
        return list.toArray(new ListNode[]{});
    }
}
