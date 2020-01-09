package com.aisino.entry.tree;

import java.util.*;

/**
 * @author: xiajun003
 * @Date: 2020/1/6 16:15
 * @Description:
 */
public class TreeNode<T> {

    public T val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(T val) {
        this.val = val;
    }

    public TreeNode(T val,TreeNode left,TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }


    /**
     * 二叉搜索树的查找
     * @param val
     * @param root
     * @return
     */
    public TreeNode find(Integer val,TreeNode<Integer> root) {
        TreeNode<Integer> current = root;
        while (!Objects.equals(current.val,val)){
            if (val < current.val){
                current = current.left;
            }else {
                current = current.right;
            }
            if (current == null){
                return null;
            }
        }
        return current;
    }

    public void insert(Integer val,TreeNode<Integer> root) {
        TreeNode<Integer> node = new TreeNode(val);
        TreeNode<Integer> pNode = null;
        TreeNode<Integer> current = root;
        while (current != null){
            pNode = current;
            if (current.val > val){
                current = current.left;
            }else {
                current = current.right;
            }
        }

        if (pNode == null){
            root = node;
        }else if (pNode.val > val){
            pNode.left = node;
        }else {
            pNode.right =  node;
        }
    }

    public List<T> preOrderTraversal() {
        List<T> list = new ArrayList();
        preOrderReverse(list,this);
        return list;
    }

    public List<T> inOrderTraversal() {
       List<T> list = new ArrayList();
        inOrderReverse(list,this);
       return list;
    }


    public List<T> postOrderTraversal() {
        List<T> list = new ArrayList();
        postOrderReverse(list,this);
        return list;
    }

    public List<T> levelOrderTraversal() {
        List<T> list = new ArrayList();
        levelOrderReverse(list,this);
        return list;
    }

    public void inOrderReverse(List<T> list , TreeNode<T> root){
        if (root == null)
            return ;
        if (root.left != null)
            inOrderReverse(list,root.left);
        list.add(root.val);
        if (root.right != null)
            inOrderReverse(list,root.right);
    }

    public void preOrderReverse(List<T> list , TreeNode<T> root){
        if (root == null)
            return ;
        list.add(root.val);
        if (root.left != null)
            preOrderReverse(list,root.left);
        if (root.right != null)
            preOrderReverse(list,root.right);
    }

    public void postOrderReverse(List<T> list , TreeNode<T> root){
        if (root == null)
            return ;
        if (root.left != null)
            postOrderReverse(list,root.left);
        if (root.right != null)
            postOrderReverse(list,root.right);
        list.add(root.val);
    }

    public void levelOrderReverse(List<T> list , TreeNode<T> root){
        Queue<TreeNode<T>> queue = new LinkedList();
        queue.offer(root);
        while (!queue.isEmpty()){
            TreeNode<T> node = queue.poll();
            list.add(node.val);
            if (node.left != null)
                queue.offer(node.left);
            if (node.right != null)
                queue.offer(node.right);
        }
    }


    public static void main(String[] args) {
        TreeNode<Integer> treeNode1 = new TreeNode<>(4);
        TreeNode<Integer> treeNode2 = new TreeNode<>(2);
        TreeNode<Integer> treeNode3 = new TreeNode<>(6);
        TreeNode<Integer> treeNode4 = new TreeNode<>(1);
        TreeNode<Integer> treeNode5 = new TreeNode<>(3);
        TreeNode<Integer> treeNode6 = new TreeNode<>(5);
        TreeNode<Integer> treeNode7 = new TreeNode<>(7);


        treeNode1.left = treeNode2;
        treeNode1.right = treeNode3;
        treeNode2.left = treeNode4;
        treeNode2.right = treeNode5;
        treeNode3.left = treeNode6;
        treeNode3.right = treeNode7;

        TreeNode<Integer> tree = treeNode1;

        System.err.println(tree.preOrderTraversal());
        System.err.println(tree.inOrderTraversal());
        System.err.println(tree.postOrderTraversal());
        System.err.println(tree.levelOrderTraversal());

        System.err.println(tree.find(7,treeNode1));

        treeNode1.insert(8,treeNode1);
        System.err.println(treeNode1.levelOrderTraversal());

    }
}
