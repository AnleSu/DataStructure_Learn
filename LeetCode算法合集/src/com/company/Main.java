package com.company;


import java.lang.reflect.Array;
import java.util.*;

public class Main {

    public static void main(String[] args) {
	// write your code here
//        int result = integerBreak(8);
//        System.out.println(result);


        Solution solution = new  Solution();
//         int result = solution.findRepeatNumber2(new int[]{2, 3, 5, 1, 0, 5});

//        int result = solution.add(99,23);

//        int[][] arr=new int[][]{{1,4,7,11,15},{2,5,8,12,19},{3,6,9,16,22},{10,13,14,17, 24},{18,21,23,26,30}};
//        int[][] arr=new int[][]{{}};
//        boolean result = solution.findNumberIn2DArray(arr,0);

//        String result = solution.replaceSpace(new StringBuilder("we are happy"));

//        ListNode node = new ListNode(2);
//        node.add(3);
//        node.add(1);
//        int[] result = solution.reversePrint2(node);

//        int result = solution.hammingWeight(
//                10);
//        System.out.println("结果"+result);


//        solution.printNumbers(2);

        solution.buildTree(new int[]{3,9,20,15,7}, new int[]{9,3,15,20,7});
    }

    static int integerBreak(int n) {
        if (n <= 3) return n - 1;
        //x是整数部分 y是余数部分  3是最优因子
        int x = n / 3, y = n % 3;
        if (y == 0) return (int) Math.pow(3, x);
        //如果余数是1 对于1的拆分没有意义，将最后一次的拆分用2和2 代替
        if (y == 1) return (int) (Math.pow(3, x-1)*4);
        //如果余数是2 直接乘
        return (int) (Math.pow(3, x) * 2);
    }
    //二叉搜索树 key唯一 左子树小于右子树
    TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) return null;
        //key比根节点小 去左子树找
        if (root.val > key) {
            root.left = deleteNode(root.left, key);
        } else if (root.val < key) { //key比根节点大 去右子树找
            root.right = deleteNode(root.right, key);
        } else { //找到要删除的节点
            if (root.right == null) {//只有左子树 无右子树 左子树移到被删除节点位置
                return root.left;
            }

            if (root.left == null) {//只有右子树 无左子树 右子树移到被删除节点位置
                return root.right;
            }


            /*
            * 左右子树都有 要保持搜索二叉树的结构
            * 1.后继节点代替被删除节点  右子树中找到最小节点  将左子树全部移到这个节点下
            * 2.前驱节点代替被删除节点  左子树中找到最大节点 将右子树全部移到这个节点下
            * */
//            TreeNode rightMin = root.right;//右子树的最小左子树
//            while (rightMin.left != null) {
//                rightMin = rightMin.left;
//            }
//            rightMin.left = root.left;//右子树的最小左子树的左子树
//            // 返回右子树，放到当前删除节点的位置
//            return root.right;

            TreeNode leftMax = root.left;
            while (leftMax.right != null) {
                leftMax = leftMax.right;
            }
            leftMax.right = root.right;
            return  root.left;

        }


        return  root;
    }

    boolean wordPattern(String pattern, String str) {
        HashMap<Character, String> map = new HashMap<>();
        HashSet<String> set = new HashSet<>();
        //        str 用空格分隔成数组
        String[] array = str.split(" ");
        if (pattern.length() != array.length) return  false;
        for (int i = 0; i < pattern.length(); i++) {
            char key = pattern.charAt(i);
            if (!map.containsKey(key)) {
                if (set.contains(array[i])) return false;
                map.put(key, array[i]);
                set.add(array[i]);
            } else  {
                if (!map.get(key).equals(array[i])) return false;
            }
        }
        return true;
    }




}


class Solution {
    //面试题03. 数组中重复的数字
//时间复杂度O(n)  空间复杂度O(n)
    public int findRepeatNumber(int[] nums) {

        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (set.contains(nums[i])) {
                return nums[i];
            } else  {
                set.add(nums[i]);
            }
        }
        return -1;
    }
//    时间复杂度O(nlogn)  空间复杂度O(1)
    public  int findRepeatNumber1(int[] nums) {
//        先排序
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == nums[i+1]) {
                return nums[i];
            }
        }

        return -1;
    }
// 鸽巢原理 时间复杂度O(n)  空间复杂度O(1)
    public  int findRepeatNumber2(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            while (nums[i] != i) {
                if (nums[i] == nums[nums[i]]) return  nums[i];
                int tmp = nums[i];
                nums[i] = nums[tmp];
                nums[tmp] = tmp;
            }
        }
        return -1;
    }


//    面试题 17.01. 不用加号的加法
    public int add(int a, int b) {
//        int sum = 0, carry = 0;
//        while (b != 0) {
//            sum = a ^ b;// 异或计算未进位的部分
//            carry = (a & b) << 1;// 进位部分
//            a = sum;// 保存未进位部分，再次计算
//            b = carry;// 保存进位部分，再次计算
//        }
//        return a;// 最后无进位，异或的结果即加法结果

        return b == 0 ? a : add(a ^ b, (a & b) << 1);
    }

//    面试题 17.04. 消失的数字
//    O(n)时间复杂度O(1)空间复杂度实现
    public int missingNumber(int[] nums) {
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            res ^= i;
            res ^= nums[i];
        }
        res ^= nums.length;
        return res;
    }

//    面试题04. 二维数组中的查找
//    O(n+m)时间复杂度   O(1)空间复杂度实现
    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0) return false;
        int row = 0;
        int col = matrix[0].length - 1;

        while (row < matrix.length && col >= 0) {
            if (matrix[row][col] > target) {
                col--;
            } else if (matrix[row][col] < target) {
                row++;
            } else {
                return true;
            }
        }
        return false;
        
    }

//试题05. 替换空格
    public String replaceSpace(String s) {
//        return s.replace(" ","%20");
        //    O(n*n)时间复杂度   O(n)空间复杂度实现
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == ' ') {
                sb.append("%20");//没替换一个空格 都要把后面所有字符都向后移动 所以时间复杂度是n*n
            } else {
                sb.append(c);
            }
        }
        TreeNode [] A = new TreeNode[]{};
        return sb.toString();
    }

    //    O(n)时间复杂度   O(1)空间复杂度实现
    public String replaceSpace(StringBuilder s) {
        int blackCount = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' ') blackCount++;
        }
        int oldLength = s.length() - 1;
        int newLength = s.length() + blackCount * 2 ;
        s.setLength(newLength);//手动扩容 防止越界
        int newIndex = newLength -1;
        //从后往前复制替换 不会破坏原始字符串 也不开辟新空间  而从前往后 会覆盖？
        for (int i = oldLength; i >= 0 && i < newLength -1 ; --i) {
            if (s.charAt(i) == ' ') {
                s.setCharAt(newIndex--,'0');
                s.setCharAt(newIndex--,'2');
                s.setCharAt(newIndex--,'%');
            } else  {
                s.setCharAt(newIndex--,s.charAt(i));
            }
        }
        return s.toString();

    }

//面试题06. 从尾到头打印链表
    ArrayList<Integer> tmp = new ArrayList<>();
//O(n)时间复杂度   O(n)空间复杂度实现
    int[] reversePrint(ListNode head) {
        recur(head);
        int[] res = new int[tmp.size()];
        for (int i = 0; i < tmp.size(); i++) {
            res[i] = tmp.get(i);

        }
        return res;

    }
//方案一 回溯   系统递归需要使用 O(n)的栈空间

    void recur(ListNode head) {
        if (head == null) return;
        recur(head.next);//先执行回溯
        System.out.println(head.val);
        tmp.add(head.val);//倒序添加入数组

    }

//方案二 堆栈  O(n)时间复杂度   O(n)空间复杂度实现
    int[] reversePrint2(ListNode head) {

        LinkedList<Integer> stack = new LinkedList<>();
        while (head != null) {
            stack.addLast(head.val);
            head = head.next;
        }

        int[] res = new  int[stack.size()];
        for (int i = 0; i < res.length; i++) {
            res[i] = stack.removeLast();
        }
        return res;
    }

//    面试题 17.16. 按摩师
//    int massage(int[] nums) {
//
//    }

    ListNode deleteNode(ListNode head, int val) {
        if (head == null) return null;
        if (head.val == val) return head.next;
        ListNode cur = head;
        while (cur.next != null && cur.next.val != val) {
            cur = cur.next;
        }
        if (cur.next != null)
            cur.next = cur.next.next;

        return head;
    }

//    面试题17. 打印从1到最大的n位数
    void printNumbers(int n) {
        StringBuilder str = new StringBuilder();
        // 将str初始化为n个'0'字符组成的字符串
        for (int i = 0; i < n; i++) {
            str.append('0');
        }
        while(!increment(str)){
            // 去掉左侧的0
            int index = 0;
            while (index < str.length() && str.charAt(index) == '0'){
                index++;
            }
            System.out.println(str.toString().substring(index));
        }
    }
//考虑 超过long的大数  所以用char数组
    public boolean increment(StringBuilder str) {
        boolean isOverflow = false;
        for (int i = str.length() - 1; i >= 0; i--) {
            char s = (char)(str.charAt(i) + 1);
            // 如果s大于'9'则发生进位
            if (s > '9') {
                str.replace(i, i + 1, "0");
                if (i == 0) {
                    isOverflow = true;
                }
            }
            // 没发生进位则跳出for循环
            else {
                str.replace(i, i + 1, String.valueOf(s));
                break;
            }
        }
        return isOverflow;
    }


    int[] printNumbers01(int n) {
//      Math.pow算出n位最大的整数 然后减1  并且从1开始
        int max = (int)Math.pow(10, n);
        int[] ans = new int[max - 1];
        for(int i = 1 ; i <= max - 1 ; i++){
            ans[i - 1] = i;
        }
        return ans;
    }


//    面试题15. 二进制中 1 的个数
//    时间复杂度O(log2n) O(1)空间复杂度实现
    int hammingWeight(int n) {
//    与运算 定义 n&0 = 0 则二进制最右位为0  n&1 = 1 则二进制最右位为1
        int res = 0;
        while (n != 0) {
            res += n&1;
//  将二进制数字n 无符号右移一位
            n >>>= 1;
        }
        return res;
    }
//巧用 n&n-1 每次消除最右边的一个1
//    时间复杂度O(m) O(1)空间复杂度实现
    int hammingWeight01(int n) {
        int res = 0;
        while (n != 0) {
            res++;
            n &= n-1;
        }
        return res;
    }


//面试题11. 旋转数组的最小数字
//旋转数组 找到旋转点就是最小数字
//    左 右两个数组都是有序数组
//    左数组中任一数字都大于等于右数组中的数字
//    二分查找 i指向数组第一个元素  j指向数组最后一个元素
//    时间复杂度O(log2n) O(1)空间复杂度实现
int minArray(int[] numbers) {
    int i = 0, j = numbers.length - 1;
    while (i < j) {
        int m = (i + j) / 2;
        if (numbers[m] > numbers[j]) i = m + 1;//m一定在左数组中 所以旋转点在【m+1,j】这个范围内
        else if (numbers[m] < numbers[j]) j = m;//m j 都在右数组中 旋转点在【i，m】这个范围内
        else j--;//无法判断m在哪个数组中 所以缩小比较范围 j--
    }
    return numbers[i];
}

//面试题10- I. 斐波那契数列
//斐波那契数列定义：    F(0) = 0,   F(1) = 1，    F(N) = F(N - 1) + F(N - 2), 其中 N > 1.
//    时间复杂度O(n)
//    空间复杂度O(1)
    int fib(int n) {
        int a = 0, b = 1, sum;
        for (int i = 0; i < n; i++) {
//            防止大数越界
            sum = (a + b) % 1000000007;
            a = b;
            b = sum;
        }
        return a;
    }


//面试题07. 重建二叉树
//  输入某二叉树的前序遍历和中序遍历的结果，请重建该二叉树。假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
    HashMap<Integer, Integer> dic = new HashMap<>();//为了提升搜索效率 用哈希表预先存储中序遍历中值和索引的对应关系
    int[] po;
    TreeNode buildTree(int[] preorder, int[] inorder) {
        po = preorder;
        for (int i = 0; i < inorder.length; i++)
            dic.put(inorder[i], i);
        return recur(0,0,inorder.length-1);
    }
//递归
//  pre_root 根节点索引
//  in_left 中序遍历左边界
//  in_right  中序遍历右边界
    TreeNode recur(int pre_root, int in_left, int in_right) {
        if (in_left > in_right) return null;
        TreeNode root = new TreeNode(po[pre_root]);//根节点 是先序遍历的第一个节点
        int i = dic.get(po[pre_root]); //取出根节点在中序遍历中的下标 则i左侧为左子树的中序遍历 右侧为右子树的中序遍历
        root.left = recur(pre_root + 1, in_left,i - 1);
        root.right = recur(pre_root + i - in_left + 1, i + 1, in_right);//根节点索引为：根节点索引+左子树长度+1
        return root;
    }


//面试题10- II. 青蛙跳台阶问题
//    等价于菲波那切数列  起始值不同
    int numWays(int n) {
        if (n == 0 || n == 1) {
            return 1;
        }
        int a = 1, b = 2, sum;
        for (int i = 1; i < n; i++) {
//            防止大数越界
            sum = (a + b) % 1000000007;
            a = b;
            b = sum;
        }
        return a;
    }



//   面试题12. 矩阵中的路径
//    深度优先搜索DFS
    boolean exist(char[][] board, String word) {
        char[] words = word.toCharArray();

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (dfs(board,words,i,j,0)) return true;
            }
        }
        return false;
    }

    boolean dfs(char[][] board, char[] word, int i, int j, int k) {
        if (i >= board.length || i < 0 || j >= board[0].length || j < 0 || board[i][j] != word[k]) return false;
        if (k == word.length - 1) return true;
        char tmp = board[i][j];
        board[i][j] = '/';
        boolean res = dfs(board, word, i + 1, j, k+1) || dfs(board, word, i - 1, j , k+1) || dfs(board, word, i , j + 1, k + 1) ||dfs(board, word, i , j - 1, k + 1);
        board[i][j] = tmp;
        return res;

    }



//面试题13. 机器人的运动范围  此类问题类似于矩阵中的路径问题 可用深度优先搜索（DFS) 或者 广度优先搜索（BFS）解决
//  时间复杂度 O(mn)  空间复杂度   O(mn)  搜索的时候需要一个大小为 O(mn)的结构标记每一个格子是否被走过
//    求数位之和   然后找到进位的规律
    int sums(int x) {// eg. 16
        int s = 0;
        while (x != 0) {
            s += x % 10; //得到各位数  6
            x = x / 10; //删除个位数 即向右移动一位  1
        }
        return s;
    }
//    进位规律
//    1. sums(19) = 10  sums(20) = 2 = sums(19) - 8     n % 10 = 0
//    2. sums(5) = 5 sums(6) = 6 = sums(5) + 1          n % 10 != 0

    int m, n, k;
    boolean[][] visited;
    int movingCount(int m, int n, int k) {
        this.m = m; this.n = n; this.k = k;
        this.visited = new boolean[m][n];
        return dfs(0, 0, 0, 0);
    }

    int dfs(int i, int j, int si, int sj) {
        if (i >= m || j >= n || k < si + sj || visited[i][j]) return 0;
        visited[i][j] = true;
        return 1 + dfs(i + 1, j ,(i + 1) % 10 !=0 ? si + 1 : si - 8, sj) + dfs(i , j + 1 ,si , (j + 1) % 10 != 0 ? sj + 1 : sj - 8);
    }

// 广度优先搜索BFS实现方法  使用队列 先进先出的规则 直到队列为空 所有满足条件的节点遍历完成
    int movingCountBFS(int m, int n, int k) {
        boolean[][] visited = new boolean[m][n];
        int res = 0;
        Queue<int[]> queue = new LinkedList<int[]>();
        queue.add(new int[] {0, 0, 0, 0});
        while (queue.size() > 0) {
            int[] x = queue.poll();
            int i = x[0], j = x[1], si = x[2], sj = x[3];
            if (i >= m || j >= n || visited[i][j] || si + sj > k) continue;
            visited[i][j] = true;
            res++;
            queue.add(new int[]{i + 1, j , (i + 1) %10 !=0 ? si + 1 : si - 8, sj});
            queue.add(new int[]{i, j + 1, si, (j + 1) % 10 != 0 ? sj + 1 : sj - 8});
        }
        return res;
    }


//面试题14- I. 剪绳子
//    数学推导  将绳子尽可能以长度3等分为多段时 乘积最大
//    对3取余  三种情况 ： 0：是最优  1： 则把最后一份3 + 1 替换为 2 + 2 因为3 * 1 < 2 * 2  2： 则保留不再拆分成1 + 1
    int cuttingRope(int n) {
        if (n <= 3) return n - 1;
        int a = n / 3, b = n % 3;
        if (b == 0) return (int)Math.pow(3, a);
        if (b == 1) return (int)Math.pow(3, a - 1) * 4;
        return (int)Math.pow(3, a) * 2;
    }

//    贪心思路：



//    124. 二叉树中的最大路径和
    int max_sum = Integer.MIN_VALUE;
    int oneSideMax(TreeNode root) {
        if (root == null) return 0;
        int leftSideMax = Math.max(oneSideMax(root.left), 0);
        int rightSideMax = Math.max(oneSideMax(root.right), 0);

        int new_path = root.val + leftSideMax + rightSideMax;

        max_sum = Math.max(max_sum, new_path);

        return root.val + Math.max(leftSideMax, rightSideMax);
    }
    int maxPathSum(TreeNode root) {
        oneSideMax(root);
        return max_sum;
    }

//99. 恢复二叉搜索树
    void recoverTree(TreeNode root) {

    }


//    匹配字符串
//    KMP算法 《部分匹配表》


//    汉诺塔  分治算法

//    八皇后问题

//    马踏棋盘算法  图的DFS + 贪心算法优化


//   面试题24. 反转链表
    ListNode reverseList(ListNode head) {
        if (head.next == null || head.next.next == null) {
            return head;
        }


        ListNode cur = head;
        ListNode next = null;
        ListNode pre = null;
        while (cur != null) {
            next = cur.next; //记录下一个节点
            cur.next = pre;
            pre = cur;
            cur = next;
        }

        return pre;
    }

//    合并两个有序链表
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode mergeNode = new ListNode(0);
        ListNode cur = mergeNode;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                cur.next = l1;
                l1 = l1.next;
            } else {
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;
        }
        cur.next = l1 != null ? l1 : l2;
        return mergeNode.next;
    }


//    倒数第k个节点
    public ListNode getKthFromEnd(ListNode head, int k) {
        ListNode pre = head, latter = head;
        for (int i = 0; i < k; i++) {
            if (pre == null) return null;
            pre = pre.next;
        }
        while (pre != null) {
            pre = pre.next;
            latter = latter.next;
        }
        return latter;

    }

//    147. 对链表进行插入排序
    public ListNode insertionSortList(ListNode head) {
        ListNode sortNode = new ListNode(0);
        sortNode.next = head;
        ListNode prev = head;
        if(head == null || head.next == null) {
            return head;
        }
        ListNode node = head.next;
        while (node != null) {
            if (node.val < prev.val) {
                ListNode temp = sortNode;
                while (temp.next.val < node.val) {
                    temp = temp.next;
                }
                prev.next = node.next;
                node.next = temp.next;
                temp.next = node;
                node = prev.next;
            } else  {
                prev = prev.next;
                node = node.next;
            }
        }
        return sortNode.next;
    }

    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        ListNode slow = head;
        ListNode fast = head;
        while (fast != slow) {
            if (fast == null || fast.next == null) {
                return false;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        return true;
    }

}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
    TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
    }
}

class ListNode {
     int val;
     ListNode next;
     ListNode(int x) { val = x; }

     void add(int data) {
         ListNode newNode = new ListNode(data);
         if (this.next == null) {
             this.next = newNode;
         } else  {
             this.next.add(data);
         }
     }

     void print() {
         System.out.print(this.val + "--->");
         if (this.next != null) {
             this.next.print();
         }
     }
}

class CQueue {
    LinkedList<Integer> stack1, stack2;
    public CQueue() {
        stack1 = new LinkedList<>();
        stack2 = new LinkedList<>();
    }

    public void appendTail(int value) {
        stack1.addLast(value);
    }

    public int deleteHead() {
        if (!stack2.isEmpty()) return stack2.removeLast();
        if (stack1.isEmpty()) return -1;
        while (!stack1.isEmpty()) {
            stack2.addLast(stack1.removeLast());
        }
        return stack2.removeLast();
    }
}

//队列实现栈
class MyStack {
    private Queue<Integer> q1 = new LinkedList<>();



    /** Initialize your data structure here. */
    public MyStack() {

    }

    /** Push element x onto stack. */
    public void push(int x) {
        q1.add(x);
        int sz = q1.size();
        while (sz > 1) {
            q1.add(q1.remove());
            sz--;
        }


    }

    /** Removes the element on top of the stack and returns that element. */
    public void pop() {
        q1.remove();
    }

    /** Get the top element. */
    public int top() {
        return q1.peek();
    }

    /** Returns whether the stack is empty. */
    public boolean empty() {
        return q1.isEmpty();
    }
}
