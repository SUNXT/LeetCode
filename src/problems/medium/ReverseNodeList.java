package problems.medium;

/**
 * 非LeetCode题
 *
 * 单向列表的反转
 * 例如：
 * 输入：1 -> 2 -> 3 -> 4 -> 5 -> 6
 * 输出：6 -> 5 -> 4 -> 3 -> 2 -> 1
 */
public class ReverseNodeList {

    private static class Node{
        Node next;
        int value;
    }

    public static void main(String[] args){
        Node head = create(new int[]{1, 2, 3, 4});
        print(head);
        print(reverse(head));
    }

    public static Node create(int[] array){
        if (array == null || array.length == 0){
            return null;
        }
        Node head;
        Node cur = new Node();
        cur.value = array[0];
        head = cur;
        for (int i = 1; i < array.length; i ++){
            cur.next = new Node();
            cur.next.value = array[i];
            cur = cur.next;
        }
        return head;
    }

    /**
     * 逆转
     * 循环一遍
     * @param head
     * @return
     */
    public static Node reverse(Node head){
        if (head == null || head.next == null){
            return head;
        }

        Node pre = head;
        Node cur = head.next;
        Node temp;
        // 当cur.next 为null的时候，cur已经到最后一个节点了，跳出循环
        while (cur.next != null){
            // 暂存当前cur节点的下一个节点
            temp = cur.next;
            // 将当前cur节点指向pre前一个节点
            cur.next = pre;
            // 将pre向前移动一位，指向cur节点
            pre = cur;
            // 将当前cur节点往后移动一位
            cur = temp;
            // 这时候相对于cur节点已经指向了pre节点，并且pre和cur都往后移动了一位
        }
        // 将head节点的下一位设置为null
        head.next = null;
        // cur已经是最后一个节点了，需要将指针指向cur的前一个指针，完成连接 cur -> pre
        cur.next = pre;
        return cur;
    }

    public static void print(Node head){
        if (head == null){
            return;
        }
        System.out.print("List: ");
        while (head != null){
            if (head.next != null){
                System.out.print(head.value + " -> ");
            }else {
                System.out.print(head.value);
            }
            head = head.next;
        }
        System.out.println(" ;");
    }
}
