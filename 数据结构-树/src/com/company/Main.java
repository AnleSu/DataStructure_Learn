package com.company;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
	// write your code here
//        数组 使用小标检索 速度快
//        数组添加元素 -》 数组扩容 -》 创建新的数组 -》 将原来数据拷贝到新数组并插入新的元素
//        扩容策略  但是整体移动 效率还是较低的
        ArrayList arrayList = new ArrayList<>();

//        链表存储 出入删除效率还可以 但是检索效率低

//        树结构


//        二叉树  前序  后序  中序遍历 看父节点的输出顺序

        BinaryTree binaryTree = new BinaryTree();
        HeroNode root = new HeroNode(1,"宋江");
        HeroNode node2 = new HeroNode(2,"无用");
        HeroNode node3 = new HeroNode(3,"卢俊义");
        HeroNode node4 = new HeroNode(4,"林冲");
        HeroNode node5 = new HeroNode(5,"关胜");
        root.setLeft(node2);
        root.setRight(node3);
        node3.setRight(node4);
        node3.setLeft(node5);

        binaryTree.setRoot(root);
        System.out.println("前序遍历：");
        binaryTree.preOrder();

//        System.out.println("中序遍历：");
//        binaryTree.infixOrder();
//
//        System.out.println("后序遍历：");
//        binaryTree.postOrder();
//
//        System.out.println("前序遍历查找");
//        HeroNode resNode = binaryTree.preOrderSearch(15);
//        if (resNode != null) {
//            System.out.printf("找到 no=%d name=%s",resNode.getNo(),resNode.getName());
//        } else {
//            System.out.println("没找到");
//        }
        System.out.println("删除后前序遍历：");
        binaryTree.delNode(2);

        binaryTree.preOrder();


    }
}

class BinaryTree {
    private HeroNode root;
    public void setRoot(HeroNode root) {
        this.root = root;
    }

    //前序遍历
    public void preOrder() {
        if (this.root != null) {
            this.root.preOrder();
        } else {
            System.out.println("empty tree");
        }
    }

    public void infixOrder() {
        if (this.root != null) {
            this.root.infixOrder();
        } else {
            System.out.println("empty tree");
        }
    }

    public void postOrder() {
        if (this.root != null) {
            this.root.postOrder();
        } else {
            System.out.println("empty tree");
        }
    }

    public HeroNode preOrderSearch(int no) {
        if (this.root != null) {
            return this.root.preOrderSearch(no);
        } else return null;
    }

    public HeroNode infiexOrderSearch(int no) {
        if (this.root != null) {
            return this.root.infixOrderSearch(no);
        } else return null;
    }

    public HeroNode postOrderSearch(int no) {
        if (this.root != null) {
            return this.root.postOrderSearch(no);
        } else return null;
    }

    public void delNode(int no) {
        if (this.root != null) {
//      判断root 不然后面没机会判断root
            if (this.root.getNo() == no) {
                this.root = null;
            } else {
                this.root.delNode(no);
            }

        } else {

        }
    }
}


class HeroNode {
    private int no;
    private String name;
    private HeroNode left;
    private HeroNode right;

    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }

    //前序遍历
    public void preOrder() {
        System.out.println(this);//输出父节点
        if (this.left != null) {//左子树前序遍历
            this.left.preOrder();
        }
        if (this.right != null) {//右子树前序遍历
            this.right.preOrder();
        }

    }

    public void infixOrder() {
        if (this.left != null) {
            this.left.infixOrder();
        }
        System.out.println(this);//输出父节点
        if (this.right != null) {
            this.right.infixOrder();
        }
    }

    public void postOrder() {
        if (this.left != null) {
            this.left.postOrder();
        }
        if (this.right != null) {
            this.right.postOrder();
        }
        System.out.println(this);//输出父节点
    }

    public HeroNode preOrderSearch(int no) {
        if (this.no == no) {
            return this;
        }

        HeroNode resNode = null;
        if (this.left != null) {
            resNode = this.left.preOrderSearch(no);
        }
        if (resNode != null) {
            return resNode;
        }

        if (this.right != null) {
            resNode = this.right.preOrderSearch(no);
        }
        return resNode;

    }

    public HeroNode infixOrderSearch(int no) {
        HeroNode resNode = null;
        if (this.left != null) {
            resNode = this.left.preOrderSearch(no);
        }
        if (resNode != null) {
            return resNode;
        }
        if (this.no == no) {
            return this;
        }
        if (this.right != null) {
            resNode = this.right.preOrderSearch(no);
        }
        return resNode;
    }

    public HeroNode postOrderSearch(int no) {
        HeroNode resNode = null;
        if (this.left != null) {
            resNode = this.left.preOrderSearch(no);
        }
        if (resNode != null) {
            return resNode;
        }
        if (this.right != null) {
            resNode = this.right.preOrderSearch(no);
        }
        if (resNode != null) {
            return resNode;
        }

        if (this.no == no) {
            return this;
        }
        return resNode;
    }

//    叶子节点 删除该节点
//    非叶子节点 删除该子树
    public void  delNode(int no) {
        if (this.left != null && this.left.no == no) {
            this.left = null;
            return;
        }

        if (this.right != null && this.right.no == no) {
            this.right = null;
            return;
        }

        if (this.left != null) {
            this.left.delNode(no);
        }

        if (this.right != null) {
            this.right.delNode(no);
        }
    }
}
