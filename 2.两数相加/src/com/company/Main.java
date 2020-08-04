package com.company;

public class Main {
    /*
    *  给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
    *  如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
    *  您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
    *  示例：
    * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
    * 输出：7 -> 0 -> 8
    * 原因：342 + 465 = 807
    * */

    /*
    * 本题主要考察 相加进位的处理 以及领界条件的判断
    * */
    public static void main(String[] args) {
	// write your code here
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode pre = new ListNode(0);
        ListNode cur = pre;
        int carry = 0;//记录进位的值
        while (l1 != null || l2 != null) {
            //两个链表可能长度不一致 较短的链表补0  比如 33 + 1 = 33 + 01
            int x1 = l1 == null ? 0 : l1.val;
            int x2 = l2 == null ? 0 : l2.val;
            int sum = x1 + x2 + carry;

            carry = sum / 10; //更新进位值
            sum = sum % 10;//取余  是当前这一位要保留的结果

            cur.next = new ListNode(sum);

            cur = cur.next;
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }
        //检查最后一位 相加 是否还需要再进位
        if (carry == 1) {
            cur.next = new ListNode(carry);
        }
        return pre.next;
    }
}

class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }

    void print() {
        System.out.print(this.val + "-->");
        if (this.next != null) {
            this.next.print();
        }
    }
}
