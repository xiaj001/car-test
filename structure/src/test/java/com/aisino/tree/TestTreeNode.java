package com.aisino.tree;

import java.util.LinkedList;

public class TestTreeNode {

    public void arrayToTreeNode(Integer[] arr){

    }

    public TreeNode  createTree(){
        TreeNode root = new TreeNode(0);
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);
        root.left = node1;
        root.right=node2;
        node1.left=node3;
        node1.right=node4;
        node2.left=node5;
        node2.right=node6;
        return root;
    }


    public void preTreeNode(TreeNode root){
        if (null==root)return;
        preTreeNodeReceive(root);
    }

    public void preTreeNodeReceive(TreeNode node){
        if (null==node)return;
        System.err.print(node.value+",");
        if (node.left!=null)preTreeNodeReceive(node.left);
        if (node.right!=null)preTreeNodeReceive(node.right);
    }


    public void postTreeNode(TreeNode root){
        if(null==root)return;
        postTreeNodeReceive(root);
    }

    public void postTreeNodeReceive(TreeNode node){
        if (null==node)return;
        if (node.left!=null)postTreeNodeReceive(node.left);
        if (node.right!=null)postTreeNodeReceive(node.right);
        System.err.print(node.value +",");
    }

    public void interTreeNode(TreeNode root){
        if (null==root) return;
        interTreeNodeReceive(root);
    }

    public void interTreeNodeReceive(TreeNode node){
        if (null==node)return;
        if (node.left!=null) interTreeNodeReceive(node.left);
        System.err.print(node.value+",");
        if (node.right!=null) interTreeNodeReceive(node.right);
    }

    public void levelTreeNode(TreeNode root){
        LinkedList<TreeNode> queue = new LinkedList();
        if (root!=null)queue.offer(root);
        while (queue.size()>0){
            TreeNode node = queue.poll();
            System.err.print(node.value+",");
            if (node.left!=null)queue.offer(node.left);
            if (node.right!=null)queue.offer(node.right);
        }
    }

    public static void main(String[] args) {
        TestTreeNode treeNode = new TestTreeNode();
        TreeNode tree = treeNode.createTree();
        treeNode.levelTreeNode(tree);
    }
}
