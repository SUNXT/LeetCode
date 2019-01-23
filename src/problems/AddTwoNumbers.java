package problems;

import utils.LoggerFactory;
import utils.Logger;

/**
 * You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.
 *
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 *
 * Example:
 *
 * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
 * Output: 7 -> 0 -> 8
 * Explanation: 342 + 465 = 807.
 *
 * testCase1 (0 -> 0 -> 3) + (0 -> 2 -> 0) => (0 -> 2 -> 3)
 * testCase2 (9 -> 2 -> 9) + (3 -> 8 -> 2) => (2 -> 1 -> 2 -> 1)
 */

public class AddTwoNumbers {

    public static void main(String[] args){
        AddTwoNumbers addTwoNumbers = new AddTwoNumbers();
        addTwoNumbers.test();
    }

    private Logger logger = LoggerFactory.getLogger(this);

    public void test(){
        ListNode l1 = createListNode(new int[]{0, 0, 3});
        ListNode l2 = createListNode(new int[]{0, 2, 0});
        logger.d(nodeToString(l1));
        logger.d(nodeToString(l2));
        ListNode result = addTwoNumber(l1, l2);
        logger.d("计算结果:" + nodeToString(result));

    }

    private ListNode createListNode(int[] nums){
        if (nums == null || nums.length < 1){
            return null;
        }

        ListNode node = new ListNode(nums[0]);
        ListNode result = node;
        for (int i =1; i < nums.length; i ++){
            node.next = new ListNode(nums[i]);
            node = node.next;
        }
        return result;
    }

    private String nodeToString(ListNode node){
        StringBuilder sb = new StringBuilder();
        sb.append("[ ");
        while (node != null){
            sb.append(node.val);
            sb.append(", ");
            node = node.next;
        }
        sb.append(" ]");
        return sb.toString();
    }

    private ListNode addTwoNumber(ListNode node1, ListNode node2){
        ListNode result;
        // 当两个都存在下一位的时候，才进行位加运算，从第一个节点开始
        ListNode temp1 = node1;
        ListNode temp2 = node2;
        if (temp1 == null || temp2 == null) {
            if (temp1 == null){
                return temp2;
            }else {
                return temp1;
            }
        }
        // 余数，第一个节点数值
        int val = (temp1.val + temp2.val) % 10;
        // 除数，如果大于1进一位
        int divide = (temp1.val + temp2.val) / 10;
        ListNode temp = new ListNode(val);
        result = temp;
        // 进一位
        if (divide > 0){
            temp.next = new ListNode(1);
        }

        // 位数相等的情况下，一起加
        temp1 = temp1.next;
        temp2 = temp2.next;
        while (temp1 != null && temp2 != null){
            int sum = temp1.val + temp2.val;
            if (temp.next != null){
                sum += temp.next.val;
                temp.next.val = sum % 10;
                temp = temp.next;
                if (sum / 10 > 0){
                    temp.next = new ListNode(1);
                }
            }else {
                temp.next = new ListNode(sum % 10);
                temp = temp.next;
                if (sum / 10 > 0){
                    temp.next = new ListNode(1);
                }
            }
            temp1 = temp1.next;
            temp2 = temp2.next;
        }
        // 位数不等了，还有某一个没加完
        ListNode temp3;
        if (temp1 == null){
            temp3 = temp2;
        }else {
            temp3 = temp1;
        }
        while (temp3 != null){
            int sum = temp3.val;
            if (temp.next != null){
                sum += temp.next.val;
                temp.next.val = sum % 10;
                temp = temp.next;
                if (sum / 10 > 0){
                    temp.next = new ListNode(1);
                }
            }else {
                temp.next = new ListNode(sum % 10);
                temp = temp.next;
                if (sum / 10 > 0){
                    temp.next = new ListNode(1);
                }
            }
            temp3 = temp3.next;
        }
        return result;
    }

    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }
}
