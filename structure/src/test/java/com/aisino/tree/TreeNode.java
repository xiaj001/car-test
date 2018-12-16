package com.aisino.tree;

public class TreeNode {
    public TreeNode(){}
    public TreeNode(Integer value){
        this.value = value;
    }
    public TreeNode(TreeNode left,TreeNode right,Integer value){
        this.left = left;
        this.right = right;
        this.value = value;
    }
    public TreeNode left;
    public TreeNode right;
    public Integer value;
}
