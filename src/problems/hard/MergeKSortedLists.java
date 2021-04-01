package problems.hard;

import common.ListNode;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * LeetCode 23
 *
 * Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.

    Example:

    Input:
    [
    1->4->5,
    1->3->4,
    2->6
    ]
    Output: 1->1->2->3->4->4->5->6
 *
 * 思路，用一个大小为K的最小堆，先用每个列表的头元素进行初始化堆，然后，取出堆顶元素，作为第一个元素，然后再将该元素的next元素进堆，不断的操作，直到所有list都为空，再出所有堆元素
 */
public class MergeKSortedLists {

    public static void main(String[] args){
        MergeKSortedLists test = new MergeKSortedLists();
        ListNode l1 = ListNode.create(new int[]{1, 2, 2});
        ListNode l2 = ListNode.create(new int[]{1, 1, 2});
        ListNode l3= ListNode.create(new int[]{2, 6});
        ListNode.print(test.mergeKLists(new ListNode[]{l1, l2,l3}));
    }

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length < 1){
            return null;
        }
        if (lists.length == 1){
            return lists[0];
        }
        // 使用Java的优先队列（堆）
        PriorityQueue<ListNode> heap = new PriorityQueue<>(lists.length, Comparator.comparingInt(o -> o.val));
        // 遍历lists，将头结点进堆
        for (ListNode node: lists){
            if (node != null){
                heap.add(node);
            }
        }
        // 取出堆顶的结点
        ListNode headNode = heap.poll();
        if (headNode == null){
            return null;
        }
        if (headNode.next != null){
            heap.add(headNode.next);
        }
        ListNode qNode = headNode;
        ListNode pNode = null;
        while (heap.size() > 1 || pNode != null){
            if (pNode != null){
                // 加入一个结点
                heap.add(pNode);
            }
            // 再次取出堆顶结点
            ListNode top = heap.poll();
            pNode = top.next;
            qNode.next = top;
            qNode = qNode.next;
        }
        qNode.next = heap.poll();
        return headNode;
    }

}
