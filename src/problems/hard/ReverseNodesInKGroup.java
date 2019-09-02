package problems.hard;

import common.ListNode;

import static common.ListNode.print;

/**
 * 25. Reverse Nodes in k-Group
 * Hard
 * <p>
 * 1354
 * <p>
 * 272
 * <p>
 * Favorite
 * <p>
 * Share
 * Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.
 * <p>
 * k is a positive integer and is less than or equal to the length of the linked list. If the number of nodes is not a multiple of k then left-out nodes in the end should remain as it is.
 * <p>
 * Example:
 * <p>
 * Given this linked list: 1->2->3->4->5
 * <p>
 * For k = 2, you should return: 2->1->4->3->5
 * <p>
 * For k = 3, you should return: 3->2->1->4->5
 * <p>
 * Note:
 * <p>
 * Only constant extra memory is allowed.
 * You may not alter the values in the list's nodes, only nodes itself may be changed.
 * <p>
 * 单向列表逆转
 * 按k个节点进行逆转，然后拼接到一起
 */
public class ReverseNodesInKGroup {

    public static void main(String[] args){
        ListNode listNode = ListNode.create(new int[]{1, 2, 3, 4, 5, 6, 7, 8});
        print(listNode);
        ReverseNodesInKGroup solution = new ReverseNodesInKGroup();
        print(solution.reverseKGroup(listNode, 3));
    }

    /**
     * 按k个进行逆转
     * 最后不够k个节点，不需要逆转
     * 用递归的思路，先逆转前k个 + 后续的逆转k个结果
     * @param head
     * @param k
     * @return
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode temp = head;
        for (int i = 1; i < k && temp != null; i ++){
            temp = temp.next;
        }
        // 如果不够k个，则返回head
        if (temp == null){
            return head;
        }
        ListNode nextHead = temp.next;
        temp.next = null;
        // 对head到第k个进行逆转
        ListNode newHead = reverse(head);
        // 执行第k个后面的链表逆转
        ListNode newTemp = reverseKGroup(nextHead, k);
        // 拼接
        head.next = newTemp;
        return newHead;
    }

    /**
     * 反转链表
     * @param head
     * @return
     */
    public ListNode reverse(ListNode head){
        if (head == null || head.next == null){
            return head;
        }
        ListNode cur = head.next;
        ListNode pre = head;
        ListNode temp;
        while (cur.next != null){
            temp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = temp;
        }
        cur.next = pre;
        head.next = null;
        return cur;
    }
}
