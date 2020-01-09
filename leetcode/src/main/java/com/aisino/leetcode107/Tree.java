package com.aisino.leetcode107;

import java.util.*;

/**
 * @author: xiajun003
 * @Date: 2019/12/18 19:54
 * @Description:
 *
 *给定一个二叉树，返回其节点值自底向上的层次遍历。 （即按从叶子节点所在层到根节点所在的层，逐层从左向右遍历）
 *
 * 例如：
 * 给定二叉树 [3,9,20,null,null,15,7],
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回其自底向上的层次遍历为：
 *
 * [
 *   [15,7],
 *   [9,20],
 *   [3]
 * ]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-tree-level-order-traversal-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 *
 */
public class Tree {


    /*public List<List<Integer>> levelOrderBottom(TreeNode root) {

    }*/

    private static TreeNode before() {
        TreeNode root = new TreeNode(3);
        TreeNode node1 =  new TreeNode(9);
        TreeNode node2 =  new TreeNode(20);
        TreeNode node3 =  new TreeNode(15);
        TreeNode node4 =  new TreeNode(7);
        root.left = node1;
        root.right = node2;
        node2.left = node3;
        node2.right = node4;
        return root;
    }

    public static void main(String[] args) {
        TreeNode root = before();
        Tree tree = new Tree();
        tree.preOrder(root);
    }

    // 前序遍历
    public void preOrder(TreeNode root){

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        LinkedList<List<Integer>> result = new LinkedList<>();
        while (!queue.isEmpty()){

            List<Integer> oneLevel = new ArrayList<>();
            int count = queue.size();
            for (int i = 0 ; i < count; i ++){
                TreeNode node = queue.poll();
                oneLevel.add(node.val);
                if (node.left != null){
                    queue.add(node.left);
                }
                if (node.right != null){
                    queue.add(node.right);
                }
            }

            System.err.println(oneLevel);


        }
    }



}

 class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) {
        val = x;
    }
}
