package problems.medium.listnode;

import common.ListNode;

/**
 * 给你一个链表的头节点 head ，旋转链表，将链表每个节点向右移动 k 个位置。
 *
 *  
 *
 * 示例 1：
 *
 *
 * 输入：head = [1,2,3,4,5], k = 2
 * 输出：[4,5,1,2,3]
 * 示例 2：
 *
 *
 * 输入：head = [0,1,2], k = 4
 * 输出：[2,0,1]
 *  
 *
 * 提示：
 *
 * 链表中节点的数目在范围 [0, 500] 内
 * -100 <= Node.val <= 100
 * 0 <= k <= 2 * 109
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/rotate-list
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * author: sunxuedian
 * create: 2021/3/27
 */
public class RotateRightListNode_N61 {

    public static void main(String[] args){

    }

    public ListNode rotateRight(ListNode head, int k) {
        // 如果没有节点，或者只有一个节点，或者k == 0直接返回
        if (head == null || head.next == null || k == 0){
            return head;
        }
        // 先计算出链表的长度
        int size = 1;
        ListNode temp = head;
        while (temp.next != null){
            size ++;
            temp = temp.next;
        }
        // 算出k%size的值，避免不必要的旋转
        int n = k % size;
        // 如果n = 0，则说明，直接不需要旋转了
        if (n == 0){
            return head;
        }
        // 遍历一遍，找出第 size - n, size -n + 1个节点
        ListNode p1 = null, p2 = null;
        temp = head;
        for (int i = 0; i < size; i ++){
            // 如果是最后一个节点了，则让其指向头节点
            // 向将尾部节点指向第一个节点，形成环
            if (i == size - 1){
                temp.next = head;
            }
            // 找出p1和p2
            if (i == size - n - 1){
                p1 = temp;
                p2 = p1.next;
            }
            temp = temp.next;
        }
        // 旋转 n次，则只需要让第 size - n个节点指向null即可，然后返回 第 size - n + 1个节点（作为新的头节点）
        if (p1 != null){
            p1.next = null;
        }
        return p2;
    }
}
