package common;

/**
 * 单向链表
 */
public class ListNode {
    public int val;
    public ListNode next;

    public ListNode(int x) {
        val = x;
    }

    public static ListNode create(int[] array) {
        if (array == null || array.length == 0) {
            return null;
        }
        ListNode head;
        ListNode cur = new ListNode(array[0]);
        head = cur;
        for (int i = 1; i < array.length; i++) {
            cur.next = new ListNode(array[i]);
            cur = cur.next;
        }
        return head;
    }

    public static void print(ListNode head){
        if (head == null){
            return;
        }
        System.out.print("List: ");
        while (head != null){
            if (head.next != null){
                System.out.print(head.val + " -> ");
            }else {
                System.out.print(head.val);
            }
            head = head.next;
        }
        System.out.println(" ;");
    }
}