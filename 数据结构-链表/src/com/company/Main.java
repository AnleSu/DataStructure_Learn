package com.company;

import java.util.Stack;

public class Main {

    public static void main(String[] args) {
	// write your code here
//        链表是以节点的方式来存储 链式存储
//        每个节点包含data next：指向下一个节点
//        链表各个节点不一定是连续存储
//        链表分带头节点和不带头节点的链表 根据实际需求确定
//        head节点 1.不存放具体数据  2.表示单链表的头

        HeroNode node1 = new HeroNode(1,"宋江","及时雨");
        HeroNode node2 = new HeroNode(2,"卢俊义","玉麒麟");
        HeroNode node3 = new HeroNode(3,"吴用","智多星");
        HeroNode node4 = new HeroNode(4,"林冲","豹子头");
        SingleLinkedList singleLinkedList = new SingleLinkedList();
//        singleLinkedList.add(node1);
//        singleLinkedList.add(node2);
//        singleLinkedList.add(node3);
//        singleLinkedList.add(node4);

        singleLinkedList.addByOrder(node2);
        singleLinkedList.addByOrder(node4);
        singleLinkedList.addByOrder(node1);
        singleLinkedList.addByOrder(node3);
        singleLinkedList.addByOrder(node2);
        singleLinkedList.list();

//        singleLinkedList.delete(1);
//        singleLinkedList.delete(2);
//        singleLinkedList.delete(3);
//        singleLinkedList.delete(4);
//        singleLinkedList.delete(4);
//        singleLinkedList.list();
        System.out.println(getLength(singleLinkedList.getHead()));

        HeroNode res = findLastIndexNode(singleLinkedList.getHead(), 4);
        System.out.println("res = " + res);


        reversePrint(singleLinkedList.getHead());
    }


//    获取单链表的节点个数  如果是带头节点的链表 需要不统计头节点
//    head 头节点
//    返回有效节点个数
    public static int getLength(HeroNode head) {
        if (head.next == null) {
            return 0;
        }
        int length = 0;
        HeroNode cur = head.next;//不统计头节点
        while (cur != null) {
            length++;
            cur = cur.next;
        }
        return length;
    }

//    查找单链表中的倒数第K个节点
    public static HeroNode findLastIndexNode(HeroNode head, int index) {
        if (head.next == null) {
            return null;
        }
//        获取链表长度
        int size = getLength(head);
//        遍历到 size - index  就是倒数第index个节点
        if (index <= 0 || index > size) {
            return null;
        }

        HeroNode cur = head.next;
        for (int i = 0; i < size - index; i++) {
            cur = cur.next;
        }
        return cur;
    }

//    单链表反转
    public static void reverseList(HeroNode head) {
        if (head.next == null || head.next.next == null) {
            return;
        }
        HeroNode cur = head.next;
        HeroNode next = null; //只想当前节点的下一个节点
        HeroNode reverseHead = new HeroNode(0,"","");
//        遍历链表 每遍历一个节点 放在reverseHead的最前面
        while (cur != null) {
            next = cur.next; //暂时保存当前节点的下一个节点 因为后面需要使用
            cur.next = reverseHead.next;
            reverseHead.next = cur;
            cur = next;
        }
        head.next = reverseHead.next;
    }

//    从尾到头 打印单链表  1.先反转再打印 会破坏原来单链表的结构  2.利用栈 将各个节点压栈然后利用栈的先进后出的特点 实现逆序打印
    public static void reversePrint(HeroNode head) {
        if (head.next == null) {
            return;
        }
        Stack<HeroNode> stack = new Stack<HeroNode>();
        HeroNode cur = head.next;
//        所有节点入栈
        while (cur != null) {
            stack.add(cur);
            cur = cur.next;
        }
//        遍历打印 pop出栈
        while (stack.size() > 0) {
            System.out.println(stack.pop());
        }
    }

//    合并两个有序的单链表
   
}

//管理HeroNode
class SingleLinkedList{
    private HeroNode head = new HeroNode(0,"","");

    public HeroNode getHead() {
        return head;
    }

    public void add(HeroNode node) {
//        因为head节点不能动 所以需要一个辅助节点
        HeroNode temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = node;

    }

    public void addByOrder(HeroNode node) {
        //        因为head节点不能动 所以需要一个辅助节点来找到添加的位置
        HeroNode temp = head;
        boolean flag = false; //添加的编号是否存在
        while (true) {
            if (temp.next == null) {
                break;
            }
//            位置找到 就在temp之后  新节点要加载temp和temp.next之间
            if (temp.next.no > node.no) {
                break;
            } else if (temp.next.no == node.no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }

        if (flag == true) {
            System.out.printf("待出入的编号%d 已经存在 不能添加\n",node.no);
        } else {
            node.next = temp.next;
            temp.next = node;
        }

    }

//    根据编号修改 no不能改
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


//    删除节点
    public void delete(int no) {
        HeroNode temp = head;
        boolean flag = false;
        while (true) {
            if (temp.next == null) {
                break;
            }
            if (temp.next.no == no) {//找到待删除节点的前一个节点
                    flag = true;
                    break;
            }
            temp = temp.next;
        }
        if (flag) {
            temp.next = temp.next.next;
        } else {
            System.out.println("待删除的节点%d 不存在" + no);
        }

    }

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
}

class HeroNode {
    public int no;
    public String name;
    public String nickName;
    public HeroNode next;

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
