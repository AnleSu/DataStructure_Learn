package com.company;

public class Main {
//双向链表 可以向前或者向后查找
//    可以自我删除  找到要删除的节点temp  temp.pre.next = temp.next;  temp.next.pre = temp.pre;

    public static void main(String[] args) {
	// write your code here
        HeroNode node1 = new HeroNode(1,"宋江","及时雨");
        HeroNode node2 = new HeroNode(2,"卢俊义","玉麒麟");
        HeroNode node3 = new HeroNode(3,"吴用","智多星");
        HeroNode node4 = new HeroNode(4,"林冲","豹子头");
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        doubleLinkedList.add(node1);
        doubleLinkedList.add(node2);
        doubleLinkedList.add(node3);
        doubleLinkedList.add(node4);

        doubleLinkedList.list();
        HeroNode node5 = new HeroNode(4,"公孙胜","入云龙 ");
        doubleLinkedList.update(node5);
        doubleLinkedList.list();

        doubleLinkedList.delete(3);
    }
}


class DoubleLinkedList {
    private HeroNode head = new HeroNode(0,"","");

    public HeroNode getHead() {
        return head;
    }

//    遍历双向链表   和单链表list相同
    public void list() {
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
    //        因为head节点不能动 所以需要一个辅助节点
        HeroNode temp = head.next;
        while (temp.next != null) {

            System.out.println(temp.toString());
            temp = temp.next;
        }
        System.out.println(temp.toString());
    }

    public void add(HeroNode node) {
//        因为head节点不能动 所以需要一个辅助节点
        HeroNode temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = node;
        node.pre = temp;

    }

//    和单链表update相同
    public void update(HeroNode node) {
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        //        因为head节点不能动 所以需要一个辅助节点
        HeroNode temp = head.next;
        boolean flag = false; //表示是否找到该节点
        while (true) {
            if (temp == null) {
                break;
            }

            if (temp.no == node.no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }

        if (flag) {
            temp.name = node.name;
            temp.nickName = node.nickName;
        } else {
            System.out.println("没有找到编号为%d的节点 不能修改" + node.no);
            return;
        }
    }

//    注意和单链表的区别  找到要删除的节点 而不是要删除节点的前一个节点

    public void delete(int no) {
        if (head.next == null) {
            System.out.println("list empty");
            return;
        }
//        直接指向head.next
        HeroNode temp = head.next;
        boolean flag = false;
        while (true) {
            if (temp == null) {
                break;
            }
            if (temp.no == no) {//找到待删除节点
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            temp.pre.next = temp.next;

//            如果要删除的是最后一个节点 则不需要执行下面这一句代码 否则会出现空指针异常
            if (temp.next != null) {
                temp.next.pre = temp.pre;
            }

        } else {
            System.out.println("待删除的节点%d 不存在" + no);
        }

    }

}


class HeroNode {
    public int no;
    public String name;
    public String nickName;
    public HeroNode next;
    public HeroNode pre;

    public HeroNode(int hNo, String hName, String hNickName) {
        this.no = hNo;
        this.name = hName;
        this.nickName = hNickName;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +

                '}';
    }
}