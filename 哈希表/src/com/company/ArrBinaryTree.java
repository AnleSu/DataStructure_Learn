package com.company;

//二叉树的顺序存储
//以数组的形式存储二叉树
public class ArrBinaryTree {
    private int[] arr;

    public ArrBinaryTree(int[] arr) {
        this.arr = arr;
    }

    public void preOrder(int index) {
        if (arr == null || arr.length == 0) {
            return;
        }
        System.out.println(arr[index]);//先序遍历先输出当前节点
        if (index * 2 + 1 < arr.length) {
            preOrder(index * 2 + 1);//第index元素的左子节点
        }

        if (index*2 + 2 < arr.length) {
            preOrder(index*2 + 2);//第index元素的右子节点
        }
    }


}
