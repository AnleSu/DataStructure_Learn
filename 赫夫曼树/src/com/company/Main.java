package com.company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    public static void main(String[] args) {
	// write your code here
//       WPL  树的带权路径长度  所有叶子节点的带权路径长度之和
//        WPL最小的就是赫夫曼树  权值越大的节点离根节点越近
//        构建赫夫曼树：
//        1.排序  2.取出根节点最小的两颗二叉树 3.组成新二叉树 新二叉树根节点权值就是前面两颗二叉树权值之和  4.新二叉树根节点 再排序  重复1 2 3 4 步骤
//
        /*
        * 1,3,6,7,8,13,29
        *    4
        *  /  \
        * 1    3
        *
        *
        *       10
        *      /  \
        *    4    6
        *  /  \
        * 1    3
        *
        *
        *    15
        *   /  \
        *  7    8
        *
        *
        *
        * */

        int arr[] = {13,7,8,3,29,6,1};


        preOrder(createHuffmanTree(arr));

    }
    //前序遍历
    public static void preOrder(Node root) {
        if (root != null){
            root.preOrder();
        }
    }

    public static Node createHuffmanTree(int[] arr) {
        //为了操作方便  遍历arr 将arr的每个元素构建成node
        List<Node> nodes = new ArrayList<Node>();
        for (int value : arr) {
            nodes.add(new Node(value));
        }
        //循环条件 最后所有节点都处理一遍 ArrayList中只剩最后一个parent
        while (nodes.size() > 1) {
            //1. 排序
            Collections.sort(nodes);

            System.out.println("nodes =" + nodes);

            //2. 取出根节点最小的两颗二叉树
            Node left = nodes.get(0);
            Node right = nodes.get(1);

            //3.组成新二叉树 新二叉树根节点权值就是前面两颗二叉树权值之和
            Node parent = new Node(left.value + right.value);
            parent.left = left;
            parent.right = right;

            //从ArrayList中删除处理过的node
            nodes.remove(left);
            nodes.remove(right);
            //讲parent加入ArrayList
            nodes.add(parent);
        }

        return nodes.get(0);


    }
}

//实现Comparable接口 为了支持Collections排序
class Node implements Comparable<Node>{
    int value; //权值
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }

    //前序遍历  测试结果是否是赫夫曼树
    public void preOrder() {
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    @Override
    public int compareTo(Node o) {
//        从小到大排序
        return this.value - o.value;
    }
}

