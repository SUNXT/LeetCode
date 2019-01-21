package problems;

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
 */

public class AddTwoNumbers {

    public static void main(String[] args){
        AddTwoNumbers addTwoNumbers = new AddTwoNumbers();
        addTwoNumbers.test();
    }

    private void print(Object o){
        System.out.print(o);
    }

    private void println(Object o){
        System.out.println(o);
    }

    private void println(){
        System.out.println();
    }

    public void test(){
        ListNode l1 = createListNode(new int[]{2, 4, 3});
        ListNode l2 = createListNode(new int[]{5, 6, 4});
        printNodes(l1);
        printNodes(l2);
        addTwoNumbers(l1, l2);
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

    private void printNodes(ListNode node){
        print("[ ");
        while (node != null){
            print(node.val);
            print(", ");
            node = node.next;
        }
        println(" ]");
    }

    private ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        return trans(calculate(l1) + calculate(l2));
    }

    private int calculate(ListNode node){
        int sum = 0;
        while (node != null){
            sum = (sum * 10 + node.val);
            node = node.next;
        }
        println("calculate => " + sum);
        return sum;
    }

    private ListNode trans(int num){
        int sum = 0;
        while (num > 0){
            sum = sum * 10 + num % 10;
            num /= 10;
        }
        ListNode node = new ListNode(sum%10);
        sum /= 10;
        while (sum > 0){
            ListNode temp = new ListNode(sum%10);
            node.next = temp;
            node = temp;
            sum /= 10;
        }
        return node;
    }

    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }
}
