package problems.hard;

import common.ListNode;

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
 * 思路，用一个大小为K的最小堆，先用每个列表的头元素进行初始化堆，然后，取出堆顶元素，作为第一个元素，然后再将该元素的next元素进堆，不断的操作，知道所有list都为空，再出所有堆元素
 */
public class MergeKSortedLists {

    public ListNode mergeKLists(ListNode[] lists) {
        return null;
    }

}
